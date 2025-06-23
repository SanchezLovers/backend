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
        int idConstancia=-1, numEntrada=-1;
        try(Connection con = DBManager.getInstance().getConnection()){
            con.setAutoCommit(false);
            idConstancia = constanciaDAO.insertar((Constancia)entrada);
            entrada.setIdConstancia(idConstancia);
            numEntrada = super.insertar(entrada);
            entrada.setNumEntrada(numEntrada);
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entrada.getClass().getSimpleName()+" ", e);
        }finally{
            if(numEntrada>0)
                return idConstancia;
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
                 SELECT c.id_constancia, e.num_entrada, ev.nombre AS nombre_evento, ev.ubicacion, d.nombre AS 
                 nombre_distrito, f.fecha AS fecha_funcion, f.hora_inicio, f.hora_fin, e.activo FROM Entrada e JOIN Constancia c ON 
                 c.id_constancia=e.id_constancia_entrada JOIN Funcion f ON e.Funcion_id_funcion = f.id_funcion
                 JOIN Evento ev ON f.Evento_idEvento = ev.id_evento JOIN Distrito d ON ev.Distrito_id_distrito = d.id_distrito
                 WHERE e.Persona_id_persona = 
                 """+IdComprador;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            listaDetalleEntradas = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> detalleEntrada = new HashMap<>();
                this.llenarMapaDetalleEntrada(detalleEntrada,rs);
                listaDetalleEntradas.add(detalleEntrada);
            }
            System.out.println("Se listo las entradas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entradas: ", e);
        } finally {
            return listaDetalleEntradas;
        }
    }
    
    
    @Override
    public void llenarMapaDetalleEntrada(Map<String, Object>detalleEntrada,ResultSet rs){
        try{
            if (rs.getString("id_constancia")!=null) {
                detalleEntrada.put("idConstancia", rs.getInt("id_constancia"));
            }
            if (rs.getString("num_entrada")!=null){
                detalleEntrada.put("numEntrada", rs.getInt("num_entrada"));
            }
            if (rs.getString("nombre_evento") != null) {
                detalleEntrada.put("nombreEvento", rs.getString("nombre_evento"));
            }
            if (rs.getString("ubicacion") != null) {
                detalleEntrada.put("ubicacion", rs.getString("ubicacion"));
            }
            if (rs.getString("nombre_distrito") != null) {
                detalleEntrada.put("nombreDistrito", rs.getString("nombre_distrito"));
            }
            if (rs.getDate("fecha_funcion") != null) {
                detalleEntrada.put("fecha", rs.getDate("fecha_funcion"));
            }
            if (rs.getTime("hora_inicio") != null) {
                detalleEntrada.put("horaInicio", rs.getTime("hora_inicio"));
            }
            if (rs.getTime("hora_fin") != null) {
                detalleEntrada.put("horaFin", rs.getTime("hora_fin"));
            }
            if (rs.getString("activo") != null && !rs.getString("activo").isEmpty()) {
                detalleEntrada.put("estado", rs.getString("activo").charAt(0));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al llenar el mapa del detalle de la entrada: " + ex.getMessage());
        }
    }
    @Override
    public Map<String, Object> buscarConstanciaEntrada(int idConstancia){
        Map<String, Object> constanciaEntrada = null;
        String sql = """
                     SELECT c.id_constancia, en.num_entrada, ev.nombre AS nombre_evento, 
                     ev.ubicacion, d.nombre AS nombre_distrito, f.fecha AS fecha_funcion, 
                     f.hora_inicio, f.hora_fin, en.activo, c.fecha, c.metodo_pago, c.total, 
                     c.detalle_pago, p.nombres AS nombres_comprador, p.primer_apellido, 
                     p.segundo_apellido, p.correo, p.tipo_documento, p.num_documento 
                     FROM Entrada en JOIN Constancia c ON c.id_constancia=en.id_constancia_entrada 
                     JOIN Funcion f ON f.id_funcion=en.Funcion_id_funcion JOIN Evento ev ON 
                     ev.id_evento = f.Evento_idEvento JOIN Distrito d ON d.id_distrito = 
                     ev.Distrito_id_distrito JOIN Persona p ON p.id_persona=en.Persona_id_persona 
                     WHERE en.id_constancia_entrada = 
                 """ + idConstancia;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if(rs.next()){
                constanciaEntrada = new HashMap<>();
                this.llenarMapaDetalleEntrada(constanciaEntrada,rs);
                constanciaDAO.llenarMapaDetalleConstancia(constanciaEntrada,rs);
                System.out.println("Se busco la constancia de la entrada correctamente");
                return constanciaEntrada;
            }else{
                throw new RuntimeException("Constancia de la entrada no encontrada");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar la constancia de la entrada: " + ex.getMessage());
        }
    }
}