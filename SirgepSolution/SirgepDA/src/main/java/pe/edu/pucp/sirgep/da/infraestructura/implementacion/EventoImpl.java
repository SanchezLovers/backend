package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoImpl extends BaseImpl<Evento> implements EventoDAO{
    //añadiendo activo
    @Override
    //modificado 
    protected String getInsertQuery(){
        String query = "INSERT INTO Evento(nombre, fecha_inicio, fecha_fin, "
                + "ubicacion, referencia, cant_entradas_dispo, cant_entradas_vendidas, "
                + "precio_entradas, Distrito_id_distrito, activo, descripcion) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?)";
        return query;
    }

    @Override
    protected String getUpdateQuery(){
        String query = "UPDATE Evento SET nombre=?, fecha_inicio=?, fecha_fin=?, ubicacion=?, "
                + "referencia=?, cant_entradas_dispo=?, cant_entradas_vendidas=?, "
                + "precio_entradas=?, Distrito_id_distrito=?, descripcion=? WHERE id_evento=?";
        return query;
    }

    @Override
    protected String getDeleteLogicoQuery(){
        String query = "UPDATE Evento SET activo='E' WHERE id_evento=?";
        return query;
    }
    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Evento WHERE id_espacio=?";
        return query;
    }


    @Override
    protected String getSelectByIdQuery(){
        String query = "SELECT e.*, d.id_distrito, d.nombre as nombre_distrito, "
                + "d.Provincia_id_provincia, d.activo FROM Evento e JOIN Distrito d "
                + "ON e.Distrito_id_distrito=d.id_distrito "
                + "WHERE e.id_evento=?";
        
        return query;
    }

    @Override
    public String getSelectAllQuery(){
        String query = "SELECT e.*, d.id_distrito, d.nombre as nombre_distrito, "
                + "d.Provincia_id_provincia, d.activo FROM Evento e JOIN Distrito d "
                + "ON e.Distrito_id_distrito=d.id_distrito WHERE e.activo='A'";
        
        return query;
    }
/*(nombre, fecha, "
                + "descripcion, ubicacion, referencia, cant_entradas_dispo, cant_entradas_vendidas, "
                + "precio_entradas, Distrito_id_distrito, activo, url_imagen)*/
    @Override
    protected void setInsertParameters(PreparedStatement ps, Evento e){
        try{
            // Antes se utilizaba para insertar correctamente: (estoy probando si es que se puede sin esto)
            String fechaIniEvento = e.getFecha_inicio();
            String fechaFinEvento = e.getFecha_fin();

            // Parsear la fecha desde dd/MM/yyyy
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaIniUtil = formatoEntrada.parse(fechaIniEvento); // java.util.Date
            Date fechaFinUtil = formatoEntrada.parse(fechaFinEvento); // java.util.Date

            // Convertir a java.sql.Date
            java.sql.Date fechaIniSql = new java.sql.Date(fechaIniUtil.getTime());
            java.sql.Date fechaFinSql = new java.sql.Date(fechaFinUtil.getTime());
            
            ps.setString(1, e.getNombre());
            ps.setDate(2, fechaIniSql);
            ps.setDate(3, fechaFinSql);
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getReferencia());
            ps.setInt(6, e.getCantEntradasDispo());
            ps.setInt(7, e.getCantEntradasVendidas());
            ps.setDouble(8, e.getPrecioEntrada());
            ps.setInt(9, e.getDistrito().getIdDistrito());
            ps.setString(10, e.getDescripcion());
//            ps.setString(10, e.getUrlImagen());
            
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            Logger.getLogger(EventoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*SET nombre=?, fecha=?, descripcion=?, ubicacion=?, "
                + "referencia=?, cant_entradas_dispo=?, cant_entradas_vendidas=?, "
                + "precio_entradas=?, Distrito_id_distrito=?, url_imagen=? */
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Evento e){
        try{
            // Antes se utilizaba para insertar correctamente: (estoy probando si es que se puede sin esto)
            String fechaIniEvento = e.getFecha_inicio();
            String fechaFinEvento = e.getFecha_fin();

            // Parsear la fecha desde dd/MM/yyyy
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaIniUtil = formatoEntrada.parse(fechaIniEvento); // java.util.Date
            Date fechaFinUtil = formatoEntrada.parse(fechaFinEvento); // java.util.Date

            // Convertir a java.sql.Date
            java.sql.Date fechaIniSql = new java.sql.Date(fechaIniUtil.getTime());
            java.sql.Date fechaFinSql = new java.sql.Date(fechaFinUtil.getTime());
            
            ps.setString(1, e.getNombre());
            ps.setDate(2, fechaIniSql);
            ps.setDate(3, fechaFinSql);
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getReferencia());
            ps.setInt(6, e.getCantEntradasDispo());
            ps.setInt(7, e.getCantEntradasVendidas());
            ps.setDouble(8, e.getPrecioEntrada());
            ps.setInt(9, e.getDistrito().getIdDistrito());
            ps.setString(10, e.getDescripcion());
//            ps.setString(10, e.getUrlImagen());
            ps.setInt(11, e.getIdEvento());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            Logger.getLogger(EventoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Evento createFromResultSet(ResultSet rs){
        try{
            Evento e=new Evento();
            Distrito distrito=new Distrito();
            e.setIdEvento(rs.getInt("id_evento"));
            e.setNombre(rs.getString("nombre"));
            java.sql.Date fechaInicioSql = rs.getDate("fecha_inicio");
            java.sql.Date fechaFinSql = rs.getDate("fecha_fin");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            Date iniUtilDate = new Date(fechaInicioSql.getTime());
            Date finUtilDate = new Date(fechaFinSql.getTime());
            
            e.setFecha_inicio(fechaInicioSql != null ? formato.format(iniUtilDate) : null);
            e.setFecha_fin(fechaFinSql != null ? formato.format(finUtilDate) : null);
            e.setUbicacion(rs.getString("ubicacion"));
            e.setReferencia(rs.getString("referencia"));
            e.setCantEntradasDispo(rs.getInt("cant_entradas_dispo"));
            e.setCantEntradasVendidas(rs.getInt("cant_entradas_vendidas"));
            e.setPrecioEntrada(rs.getDouble("precio_entradas"));
            distrito.setIdDistrito(rs.getInt("Distrito_id_distrito"));
            e.setDistrito(distrito);
            e.setDescripcion(rs.getString("descripcion"));
            return e;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Evento e, int id) {
        e.setIdEvento(id);
    }
    
    public String getBuscarPorTexto(){
        return "{CALL BUSCAR_EVENTO_POR_TEXTO(?)}";
    }

    @Override
    public List<Evento> buscarPorTexto(String texto) {
        List<Evento> espacios = new ArrayList<>();

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
            throw new RuntimeException("Error al obtener un EVENTO: ", e);
        }
        return espacios;
    }
    
    public String getBuscarPorFechas(){
        return "{CALL FILTRAR_EVENTOS_POR_FECHA(?, ?)}";
    }

    @Override
    public List<Evento> buscarEventosPorFechas(String inicio, String fin){
        List<Evento> espacios = new ArrayList<>();

        // Utilizaremos procedimientos almacenados
        try (Connection conn = DBManager.getInstance().getConnection(); 
             CallableStatement cs = conn.prepareCall(this.getBuscarPorFechas())) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Convertimos a java.util.Date primero
            java.util.Date fechaInicioUtil = sdf.parse(inicio);
            java.util.Date fechaFinUtil = sdf.parse(fin);

            // Luego a java.sql.Date
            java.sql.Date fechaInicioSQL = new java.sql.Date(fechaInicioUtil.getTime());
            java.sql.Date fechaFinSQL = new java.sql.Date(fechaFinUtil.getTime());

            // Asignamos como DATE y no como String
            cs.setDate(1, fechaInicioSQL);
            cs.setDate(2, fechaFinSQL);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    espacios.add(createFromResultSet(rs));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al obtener un EVENTO por FECHA: ", e);
        } catch (ParseException ex) {
            Logger.getLogger(EventoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return espacios;
    }

}