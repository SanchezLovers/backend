package pe.edu.pucp.sirgep.da.ventas.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager; 
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.EntradaDAO;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;

import java.sql.Connection;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class EntradaImpl extends BaseImpl<Entrada> implements EntradaDAO{
    private final ConstanciaDAO constanciaDAO;
    
    public EntradaImpl(){
        this.constanciaDAO = new ConstanciaImpl();
    }
    
    @Override
    protected String getInsertQuery(){
        String sql = "INSERT INTO Entrada(num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion,activo) "
                   + "VALUES (?,?,?,?,'A')";
        return sql;
    }
    
    @Override
    protected String getSelectByIdQuery(){
        String sql = "SELECT id_constancia,fecha,metodo_pago,igv,total,detalle_pago,"
                + "num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion "
                + "FROM Constancia C, Entrada E "
                + "WHERE C.id_constancia = E.id_constancia_entrada AND id_constancia_entrada=?";
        return sql;
    }
    
    @Override
    protected String getSelectAllQuery(){
        String sql = "SELECT id_constancia,fecha,metodo_pago,igv,total,detalle_pago,"
                + "num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion "
                + "FROM Constancia C, Entrada E "
                + "WHERE C.id_constancia = E.id_constancia_entrada AND E.activo='A'";
        return sql;
    }
    
    @Override
    protected String getUpdateQuery(){
        String sql = "UPDATE Entrada "
                   + "SET Persona_id_persona=?, id_constancia_entrada=?, Funcion_id_funcion=? "
                   + "WHERE num_entrada=?";
        return sql;
    }
    
    @Override
    protected String getDeleteLogicoQuery(){
        String sql = "UPDATE Entrada SET activo='E' WHERE num_entrada=?";
        return sql;
    }
    
    @Override
    protected String getDeleteFisicoQuery(){
        String query = "DELETE FROM Entrada WHERE num_entrada=?";
        return query;
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Entrada entrada){
        try{
            ps.setInt(1, entrada.getNumEntrada());
            ps.setInt(2, entrada.getPersona().getIdPersona());
            ps.setInt(3, entrada.getIdConstancia());
            ps.setInt(4, entrada.getFuncion().getIdFuncion());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected Entrada createFromResultSet(ResultSet rs){
         try{
            Entrada constancia = new Entrada();
            constancia.setIdConstancia(rs.getInt("id_constancia"));
            constancia.setFecha(rs.getDate("fecha"));
            constancia.setMetodoPago(EMetodoPago.valueOf(rs.getString("metodo_pago")));
            constancia.setIgv(rs.getDouble("igv"));
            constancia.setTotal(rs.getDouble("total"));
            constancia.setDetallePago(rs.getString("detalle_pago"));
            constancia.setNumEntrada(rs.getInt("num_entrada"));
             Funcion f = new Funcion();
             f.setIdFuncion(rs.getInt("Funcion_id_funcion"));
             constancia.setFuncion(f);
             Persona persona=new Persona();
             persona.setIdPersona(rs.getInt("Persona_id_persona"));
             constancia.setPersona(persona);
            return constancia;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Entrada entrada){
        try{
            ps.setInt(1, entrada.getPersona().getIdPersona());
            ps.setInt(2, entrada.getIdConstancia());
            ps.setInt(3, entrada.getFuncion().getIdFuncion());
            ps.setInt(4, entrada.getNumEntrada());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void setId(Entrada entrada, int id){
        entrada.setNumEntrada(id);
    }
    
    @Override
    public int insertar(Entrada entrada){
        int idC=-1, idE=-1;
        try(Connection con = DBManager.getInstance().getConnection()){
            con.setAutoCommit(false);
            idC = constanciaDAO.insertar((Constancia)entrada);
            entrada.setIdConstancia(idC);
            idE = super.insertar(entrada);
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entrada.getClass().getSimpleName()+" ", e);
        }finally{
            if(idE>0)
                return idC;
            return 0;
        }
    }
    
    @Override
    public boolean actualizar(Entrada entrada){
        boolean resultado = false;
        try(Connection con = DBManager.getInstance().getConnection()){
            con.setAutoCommit(false);
            resultado = constanciaDAO.actualizar(entrada);
            try(PreparedStatement ps = con.prepareStatement(this.getUpdateQuery())){
                this.setUpdateParameters(ps, entrada);
                ps.executeUpdate();
                con.commit();
                System.out.println("Se actualizo un registro de " + entrada.getClass().getSimpleName());
                resultado=true;
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar " + entrada.getClass().getSimpleName(), e);
        }finally{
            return resultado;
        }
    }
    
    @Override
    public boolean eliminarLogico(int id){
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino logicamente un registro con ID=" + id);
                resultado=constanciaDAO.eliminarLogico(id);
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico " , e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
    
    @Override
    public boolean eliminarFisico(int id) {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteFisicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino fisicamente un registro con ID=" + id);
                resultado=constanciaDAO.eliminarFisico(id);
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        }finally{
            return resultado;
        }
    }

    @Override
    public List<Map<String, Object>> listarDetalleEntradasPorComprador(int IdComprador) {
    List<Map<String, Object>> listaDetalleEntradas = null;
    String sql = """
                 SELECT e.num_entrada, ev.nombre AS nombre_evento, ev.ubicacion, d.nombre AS nombre_distrito, f.fecha, 
                 f.hora_inicio, f.hora_fin FROM Entrada e JOIN Funcion f ON e.Funcion_id_funcion = f.id_funcion
                 JOIN Evento ev ON f.Evento_idEvento = ev.id_evento JOIN Distrito d ON ev.Distrito_id_distrito = d.id_distrito
                 WHERE e.activo = 'A' AND e.Persona_id_persona = 
                 """+IdComprador;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            listaDetalleEntradas = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("numEntrada", rs.getInt("num_entrada"));
                fila.put("nombreEvento", rs.getString("nombre_evento"));
                fila.put("ubicacion", rs.getString("ubicacion"));
                fila.put("nombreDistrito", rs.getString("nombre_distrito"));
                fila.put("fecha", rs.getDate("fecha"));
                fila.put("horaInicio", rs.getTime("hora_inicio"));
                fila.put("horaFin", rs.getTime("hora_fin"));
                listaDetalleEntradas.add(fila);
            }
            System.out.println("Se listo las entradas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entradas: ", e);
        } finally {
            return listaDetalleEntradas;
        }
    }
}