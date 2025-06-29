package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

public class EspacioImpl extends BaseImpl<Espacio> implements EspacioDAO {
    
    @Override
    protected String getInsertQuery() {
        String query = "INSERT INTO Espacio(nombre, tipo_espacio, horario_inicio_atencion, horario_fin_atencion, "
                + "ubicacion, superficie, precio_reserva, activo, Distrito_id_distrito) values(?, ?, ?, ?, ?, ?, ?, 'A', ?)";
        return query;
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT id_espacio, nombre, tipo_espacio, horario_inicio_atencion, horario_fin_atencion, "
                + "ubicacion, superficie, precio_reserva, Distrito_id_distrito FROM Espacio WHERE id_espacio=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String query = "SELECT id_espacio, nombre, tipo_espacio, horario_inicio_atencion, horario_fin_atencion, "
                + "ubicacion, superficie, precio_reserva, Distrito_id_distrito FROM Espacio WHERE activo = 'A' ";
        return query;
    }

    @Override
    protected String getUpdateQuery() {
        String query = "UPDATE Espacio SET nombre=?, tipo_espacio=?, horario_inicio_atencion=?,"
                + " horario_fin_atencion=?, ubicacion=?, superficie=?, precio_reserva=?, Distrito_id_distrito=? WHERE id_espacio=?";
        return query;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String query = "UPDATE Espacio SET activo='E' WHERE id_espacio=?";
        return query;
    }
    
    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Espacio WHERE id_espacio=?";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Espacio e){
        try{
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTipoEspacio().name());
            ps.setTime(3,Time.valueOf(e.getHorarioInicioAtencion()));
            ps.setTime(4,Time.valueOf(e.getHorarioFinAtencion()));
            ps.setString(5, e.getUbicacion());
            ps.setDouble(6, e.getSuperficie());
            ps.setDouble(7, e.getPrecioReserva());
            ps.setInt(8, e.getDistrito().getIdDistrito());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Espacio e){
        try{
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTipoEspacio().name());
            ps.setTime(3,Time.valueOf(e.getHorarioInicioAtencion()));
            ps.setTime(4,Time.valueOf(e.getHorarioFinAtencion()));
            ps.setString(5, e.getUbicacion());
            ps.setDouble(6, e.getSuperficie());
            ps.setDouble(7, e.getPrecioReserva());
            ps.setInt(8, e.getDistrito().getIdDistrito());
            ps.setInt(9, e.getIdEspacio());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected Espacio createFromResultSet(ResultSet rs){
        try{
            Time horaInicio = rs.getTime("horario_inicio_atencion");
            Time horaFin = rs.getTime("horario_fin_atencion");

            Espacio e=new Espacio();
            e.setHorarioInicioAtencion(horaInicio != null ? horaInicio.toString():null);
            e.setHorarioFinAtencion(horaFin != null ? horaFin.toString():null);
            e.setIdEspacio(rs.getInt("id_espacio"));
            e.setNombre(rs.getString("nombre"));
            e.setTipoEspacio(ETipoEspacio.valueOf(rs.getString("tipo_espacio")));
            e.setUbicacion(rs.getString("ubicacion"));
            e.setSuperficie(rs.getDouble("superficie"));
            e.setPrecioReserva(rs.getDouble("precio_reserva"));
            /* Necesario para realizar el filtro de Espacios mediante su Distrito */
            Distrito d = new Distrito();
            d.setIdDistrito(rs.getInt("Distrito_id_distrito"));
            e.setDistrito(d);
            /* ------------------------------------------------------------------- */
            return e;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Espacio e, int id) {
        e.setIdEspacio(id);
    }
    
    //__ STORED PROCEDURE __

    public String getBuscarPorTexto(){
        return "{CALL BUSCAR_ESPACIO_POR_TEXTO(?)}";
    }

    /*Buscar Espacios con cierto patrón de Texto*/
    @Override
    public List<Espacio> buscarPorTexto(String texto) {
        List<Espacio> espacios = new ArrayList<>();

        // Utilizaremos procedimientos almacenados
        try (Connection conn = DBManager.getInstance().getConnection(); 
             CallableStatement cs = conn.prepareCall(this.getBuscarPorTexto())) {

            cs.setString(1, texto); // asignamos el parámetro de texto

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    espacios.add(createFromResultSet(rs));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al obtener un espacio: ", e);
        }
        return espacios;
    }
    
    @Override
    public List<String> ListarDias(int idEspacio) {
        List<String> dias = new ArrayList<>();

        String sql = "SELECT eDiaSemana_dia_semana FROM Espacio_has_eDiaSemana WHERE Espacio_id_espacio = ?";

        try (Connection con = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dias.add(rs.getString("eDiaSemana_dia_semana"));
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return dias;
    }


    @Override
    public List<Espacio> buscarPorDistrito(int idDist) {
        try{
            List<Espacio> espacios = listar();
            List<Espacio> espaciosFiltrados = new ArrayList<>();

            for(Espacio espacio : espacios){
                int id = espacio.getDistrito().getIdDistrito();
                if( id == idDist){
                    espaciosFiltrados.add(espacio);
                }
            }
            return espaciosFiltrados;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria: " + ex.getMessage());
        }
    }
    
    

    @Override
    public List<Espacio> buscarPorCategoria(String categ) {
        try{
            List<Espacio> espacios = listar();
            List<Espacio> espaciosFiltrados = new ArrayList<>();
            ETipoEspacio tipoEspacio = ETipoEspacio.valueOf(categ);
            for(Espacio espacio : espacios){
                if( espacio.getTipoEspacio() == tipoEspacio ){
                    espaciosFiltrados.add(espacio);
                }
            }
            return espaciosFiltrados;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria: " + ex.getMessage());
        }
    }

    @Override
    public List<Espacio> buscarPorDistritoCategoria(int idDist, String categoria) {
        try{
            List<Espacio> espacios = buscarPorDistrito(idDist);
            List<Espacio> espaciosFiltrados = new ArrayList<>();

            for(Espacio espacio : espacios){
                if( espacio.getTipoEspacio().toString().equals(categoria)){
                    espaciosFiltrados.add(espacio);
                }
            }
            return espaciosFiltrados;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria y Distrito: " + ex.getMessage());
        }
    }
}