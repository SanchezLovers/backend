package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.EventoDAO;
import com.slovers.sirgep.dominio.models.gestion.Evento;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author willi
 */
public class EventoMySql implements EventoDAO{
    
    @Override
    public void insertar(Evento evento) throws SQLException, IOException{
        String query = "INSERT INTO Evento(id_evento, nombre, fecha, "
                + "ubicacion, referencia, cant_entradas_dispo, cant_entradas_vendidas, "
                + "precio_entradas, Distrito_id_distrito) VALUES (?, ?, ?, ?, ?, ?, "
                + "?, ?, ?)";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                setEventoParameters(ps, evento);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        evento.setIdEvento(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void actualizar(Evento evento) throws SQLException, IOException{
        String query = "UPDATE Evento SET nombre=?, fecha=?, ubicacion=?, "
                + "referencia=?, cant_entradas_dispo=?, can_entradas_vendidas=?, "
                + "precio_entradas=?, Distrito_id_distrito=? WHERE id_evento=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps=con.prepareStatement(query)){
                setEventoParameters(ps, evento);
                ps.setInt(9, evento.getIdEvento());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void eliminar(int idEvento) throws SQLException, IOException{
        String query = "UPDATE Evento SET activo='E' WHERE id_evento=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps=con.prepareStatement(query)){
                ps.setInt(1, idEvento);
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Evento obtenerPorId(int idEvento) throws SQLException, IOException{
        String query = "SELECT e.*, d.id_distrito, d.nombre as nombre_distrito, "
                + "d.Provincia_id_provincia, d.activo FROM Evento e JOIN Distrito d "
                + "ON e.Distrito_id_distrito=d.id_distrito "
                + "WHERE e.id_evento=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps=con.prepareStatement(query)){
                ps.setInt(1, idEvento);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) return mapEvento(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Evento> obtenerTodos() throws SQLException, IOException{
        ArrayList<Evento> eventos = new ArrayList<>();
        String query = "SELECT e.*, d.id_distrito, d.nombre as nombre_distrito, "
                + "d.Provincia_id_provincia, d.activo FROM Evento e JOIN Distrito d "
                + "ON e.Distrito_id_distrito=d.id_distrito";
        try(Connection con = DBManager.getInstance().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                eventos.add(mapEvento(rs));
            }
        }
        return eventos;
    }
    
    public void setEventoParameters(PreparedStatement ps, Evento evento) throws SQLException{
        ps.setInt(1, evento.getIdEvento());
        ps.setString(2, evento.getNombre());
        ps.setDate(3, new java.sql.Date(evento.getFecha().getTime())); //Se convierte el java.util.Date a java.sql.Date
        ps.setString(4, evento.getUbicacion());
        ps.setString(5, evento.getReferencia());
        ps.setInt(5, evento.getCantEntradasDispo());
        ps.setInt(6, evento.getCantEntradasVendidas());
        ps.setDouble(7, evento.getPrecioEntrada());
        ps.setInt(8, evento.getDistrito().getIdDistrito());
        ps.setString(9, "A");
    }
    
    public Evento mapEvento(ResultSet rs) throws SQLException{
        Evento evento = new Evento();
        evento.setIdEvento(rs.getInt("id_evento"));
        evento.setNombre(rs.getString("nombre"));
        evento.setFecha(rs.getDate("fecha"));
        evento.setUbicacion(rs.getString("ubicacion"));
        evento.setReferencia(rs.getString("referencia"));
        evento.setCantEntradasDispo(rs.getInt("cant_entradas_dispo"));
        evento.setCantEntradasVendidas(rs.getInt("cant_entradas_vendidas"));
        evento.setPrecioEntrada(rs.getDouble("precio_entradas"));
        return evento;
    }
}
