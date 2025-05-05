package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.ResultSet;

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
                + "ubicacion, superficie, precio_reserva FROM Espacio WHERE id_espacio=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String query = "SELECT id_espacio, nombre, tipo_espacio, horario_inicio_atencion, horario_fin_atencion, "
                + "ubicacion, superficie, precio_reserva FROM Espacio";
        return query;
    }

    @Override
    protected String getUpdateQuery() {
        String query = "UPDATE Espacio SET nombre=?, tipo_espacio=?, horario_inicio_atencion=?,"
                + " horario_fin_atencion=?, ubicacion=?, superficie=?, precio_reserva=? WHERE id_espacio=?";
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
            ps.setTime(3,Time.valueOf(e.getHorarioInicioAtencion().toString()+":00"));
            ps.setTime(4,Time.valueOf(e.getHorarioFinAtencion().toString() + ":00"));
            ps.setString(5, e.getUbicacion());
            ps.setDouble(6, e.getSuperficie());
            ps.setDouble(7, e.getPrecioReserva());
            ps.setDouble(8, e.getDistrito().getIdDistrito());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Espacio e){
        try{
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getTipoEspacio().name());
            ps.setTime(3,Time.valueOf(e.getHorarioInicioAtencion().toString()+":00"));
            ps.setTime(4,Time.valueOf(e.getHorarioFinAtencion().toString() + ":00"));
            ps.setString(5, e.getUbicacion());
            ps.setDouble(6, e.getSuperficie());
            ps.setDouble(7, e.getPrecioReserva());
            ps.setInt(8, e.getIdEspacio());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected Espacio createFromResultSet(ResultSet rs){
        try{
            Espacio e=new Espacio();
            e.setIdEspacio(rs.getInt("id_espacio"));
            e.setNombre(rs.getString("nombre"));
            e.setTipoEspacio(ETipoEspacio.valueOf(rs.getString("tipo_espacio")));
            e.setHorarioInicioAtencion(rs.getTime("horario_inicio_atencion").toLocalTime());
            e.setHorarioFinAtencion(rs.getTime("horario_fin_atencion").toLocalTime());
            e.setUbicacion(rs.getString("ubicacion"));
            e.setSuperficie(rs.getDouble("superficie"));
            e.setPrecioReserva(rs.getDouble("precio_reserva"));
            return e;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Espacio e, int id) {
        e.setIdEspacio(id);
    }
    
/*
    @Override 
    public int insertar(Espacio espacio){
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(this.getInsertQuery());) {
            this.setEspacioParameters(ps, espacio); //Preparamos el ps con los atributos del objeto
            ps.executeUpdate();
            //Traer el último ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){
                if(rskeys.next()){
                    int id=rskeys.getInt(1);
                    espacio.setIdEspacio(id);
                    return id;
                }
            }
            return -1;
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al insertar un espacio: ", e);
        }
    }
    
    @Override
    public Espacio buscar(int id){
        try (Connection conn = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(this.getSelectByIdQuery())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEspacio(rs);
                }
            }
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al obtener un espacio: ", e);
        }
        return null;
    }
    
    @Override
    public ArrayList<Espacio> listar(){
        ArrayList<Espacio> espacios = new ArrayList<>();
        try(Connection con = DBManager.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(this.getSelectAllQuery())) {        
            while(rs.next()){
                Espacio espacio = mapEspacio(rs);
                espacios.add(espacio);
            }
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al obtener un espacios: ", e);
        }
        return espacios;
    }
    
    @Override
    public boolean actualizar(Espacio espacio){
        try (Connection conn = DBManager.getInstance().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(this.getUpdateQuery())) {
            setEspacioParameters(ps, espacio);
            ps.setInt(8, espacio.getIdEspacio());
            ps.executeUpdate();
            return true;
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al actualizar un espacio: ", e);
        }
    }
    
    @Override
    public boolean eliminarLogico(int id){
        try (Connection conn = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {            
             ps.setInt(1, id);
             ps.executeUpdate();
             return true;
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al eliminar logicamente un espacio: ", e);
        }
    }
    
    public boolean eliminarFisico(int id){
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(this.getDeleteFisicoQuery())){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException|IOException e){
            throw new RuntimeException("Error al eliminar fisicamente un espacio: ", e);
        }
    }
*/
}