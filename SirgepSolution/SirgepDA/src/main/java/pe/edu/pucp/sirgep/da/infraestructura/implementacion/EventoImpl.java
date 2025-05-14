package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public class EventoImpl extends BaseImpl<Evento> implements EventoDAO{
    //añadiendo activo
    @Override
    //modificado 
    protected String getInsertQuery(){
        String query = "INSERT INTO Evento(nombre, fecha, "
                + "descripcion, ubicacion, referencia, cant_entradas_dispo, cant_entradas_vendidas, "
                + "precio_entradas, Distrito_id_distrito, activo, url_imagen) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?)";
        return query;
    }

    @Override
    protected String getUpdateQuery(){
        String query = "UPDATE Evento SET nombre=?, fecha=?, descripcion=?, ubicacion=?, "
                + "referencia=?, cant_entradas_dispo=?, cant_entradas_vendidas=?, "
                + "precio_entradas=?, Distrito_id_distrito=?, url_imagen=? WHERE id_evento=?";
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
                + "ON e.Distrito_id_distrito=d.id_distrito";
        
        return query;
    }
/*(nombre, fecha, "
                + "descripcion, ubicacion, referencia, cant_entradas_dispo, cant_entradas_vendidas, "
                + "precio_entradas, Distrito_id_distrito, activo, url_imagen)*/
    @Override
    protected void setInsertParameters(PreparedStatement ps, Evento e){
        try{
            ps.setString(1, e.getNombre());
            ps.setDate(2, Date.valueOf(e.getFecha().toString()));
            ps.setString(3, e.getDescripción());
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getReferencia());
            ps.setInt(6, e.getCantEntradasDispo());
            ps.setInt(7, e.getCantEntradasVendidas());
            ps.setDouble(8, e.getPrecioEntrada());
            ps.setInt(9, e.getDistrito().getIdDistrito());
            ps.setString(10, e.getUrlImagen());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
/*SET nombre=?, fecha=?, descripcion=?, ubicacion=?, "
                + "referencia=?, cant_entradas_dispo=?, cant_entradas_vendidas=?, "
                + "precio_entradas=?, Distrito_id_distrito=?, url_imagen=? */
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Evento e){
        try{
            ps.setString(1, e.getNombre());
            ps.setDate(2, Date.valueOf(e.getFecha().toString()));
            ps.setString(3, e.getDescripción());
            ps.setString(4, e.getUbicacion());
            ps.setString(5, e.getReferencia());
            ps.setInt(6, e.getCantEntradasDispo());
            ps.setInt(7, e.getCantEntradasVendidas());
            ps.setDouble(8, e.getPrecioEntrada());
            ps.setInt(9, e.getDistrito().getIdDistrito());
            ps.setString(10, e.getUrlImagen());
            ps.setInt(11, e.getIdEvento());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected Evento createFromResultSet(ResultSet rs){
        try{
            Evento e=new Evento();
            e.setIdEvento(rs.getInt("id_evento"));
            e.setCantEntradasDispo(rs.getInt("cant_entradas_dispo"));
            e.setCantEntradasVendidas(rs.getInt("cant_entradas_vendidas"));
            e.setNombre(rs.getString("nombre"));
            e.setUbicacion(rs.getString("ubicacion"));
            e.setReferencia(rs.getString("referencia"));
            e.setDescripcion(rs.getString("descripcion"));
            e.setFecha(rs.getDate("fecha"));
            e.setPrecioEntrada(rs.getDouble("precio_entradas"));
            return e;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Evento e, int id) {
        e.setIdEvento(id);
    }
    
    
}