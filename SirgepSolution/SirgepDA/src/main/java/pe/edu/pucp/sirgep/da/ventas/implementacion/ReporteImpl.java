package pe.edu.pucp.sirgep.da.ventas.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.ventas.dao.ReporteDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;

public class ReporteImpl implements ReporteDAO{
    
    @Override
    public List<Integer> cantidadEntradasMes(){
        List<Integer> meses = new ArrayList<>(Collections.nCopies(12, 0));
        String query = "SELECT " +
                "MONTH(c.fecha) AS mes, " +
                "COUNT(e.num_entrada) AS cantidad_entradas " +
                "FROM Entrada e" +
                " JOIN Constancia c ON e.id_constancia_entrada = c.id_constancia " +
                "WHERE " +
                "YEAR(c.fecha) = YEAR(CURDATE()) " +
                "GROUP BY mes " +
                "ORDER BY mes";
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(query);
              ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int mes = rs.getInt("mes");
                Integer cantidad = rs.getInt("cantidad_entradas");
                meses.set(mes-1, cantidad);
            }
            System.out.println("Se listo los registros");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return meses;
        }
    }
    @Override
    public List<Integer> cantidadReservasMes(){
        List<Integer> meses = new ArrayList<>(Collections.nCopies(12, 0));
        String query = "SELECT " +
                "MONTH(c.fecha) AS mes, " +
                "COUNT(e.num_reserva) AS cantidad " +
                "FROM Reserva e " +
                " JOIN Constancia c ON e.id_constancia_reserva= c.id_constancia " +
                "WHERE " +
                "YEAR(c.fecha) = YEAR(CURDATE()) " +
                "GROUP BY mes " +
                "ORDER BY mes";
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(query);
              ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int mes = rs.getInt("mes");
                Integer cantidad = rs.getInt("cantidad");
                meses.set(mes-1, cantidad);
            }
            System.out.println("Se listo los registros");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return meses;
        }
    }
    
    @Override
    public Map<String, Integer> EspaciosFavMes(){
        Map<String, Integer> mapa = new LinkedHashMap<>();
        String query = "SELECT e.nombre as nombre_espacio, count(r.num_reserva)"
                + " as cantidad_reservas from Reserva r join Espacio e on "
                + "r.Espacio_id_espacio = e.id_espacio "
                + "where r.activo = 'A' and month(r.fecha_reserva) = month(curdate()) "
                + "and year(r.fecha_reserva) = year(curdate()) "
                + "group by e.nombre "
                + "order by cantidad_reservas desc "
                + "limit 5";
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(query);
              ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre_espacio");
                Integer cantidad = rs.getInt("cantidad_reservas");
                mapa.put(nombre, cantidad);
            }
            System.out.println("Se listo los registros");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return mapa;
        }
    }
    
    @Override
    public Map<String, Integer> EventosFavMes(){
        Map<String, Integer> mapa = new LinkedHashMap<>();
        String query = "SELECT e.nombre as nombre_evento, count(r.num_entrada)"
                + " as cantidad from Entrada r join Evento e join Funcion f on "
                + "r.Funcion_id_funcion = f.id_funcion "
                + "and month(f.fecha) = month(curdate()) "
                + "and year(f.fecha) = year(curdate()) "
                + "group by e.nombre "
                + "order by cantidad desc "
                + "limit 5";
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(query);
              ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre_evento");
                Integer cantidad = rs.getInt("cantidad");
                mapa.put(nombre, cantidad);
            }
//            System.out.println("Se listo los registros");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return mapa;
        }
    }
}