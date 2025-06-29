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
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class EntradaImpl extends BaseImpl<Entrada> implements EntradaDAO {

    private final ConstanciaDAO constanciaDAO;

    public EntradaImpl() {
        this.constanciaDAO = new ConstanciaImpl();
    }

    @Override
    protected String getInsertQuery() {
        String sql = "INSERT INTO Entrada(num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion,activo) "
                + "VALUES (?,?,?,?,'A')";
        return sql;
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT id_constancia,fecha,metodo_pago,igv,total,detalle_pago,"
                + "num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion "
                + "FROM Constancia C, Entrada E "
                + "WHERE C.id_constancia = E.id_constancia_entrada AND id_constancia_entrada=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String sql = "SELECT id_constancia,fecha,metodo_pago,igv,total,detalle_pago,"
                + "num_entrada,Persona_id_persona,id_constancia_entrada,Funcion_id_funcion "
                + "FROM Constancia C, Entrada E "
                + "WHERE C.id_constancia = E.id_constancia_entrada AND E.activo='A'";
        return sql;
    }

    @Override
    protected String getUpdateQuery() {
        String sql = "UPDATE Entrada "
                + "SET Persona_id_persona=?, id_constancia_entrada=?, Funcion_id_funcion=? "
                + "WHERE num_entrada=?";
        return sql;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String sql = "UPDATE Entrada SET activo='E' WHERE num_entrada=?";
        return sql;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Entrada WHERE num_entrada=?";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Entrada entrada) {
        try {
            ps.setInt(1, entrada.getNumEntrada());
            ps.setInt(2, entrada.getPersona().getIdPersona());
            ps.setInt(3, entrada.getIdConstancia());
            ps.setInt(4, entrada.getFuncion().getIdFuncion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Entrada createFromResultSet(ResultSet rs) {
        try {
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
            Persona persona = new Persona();
            persona.setIdPersona(rs.getInt("Persona_id_persona"));
            constancia.setPersona(persona);
            return constancia;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Entrada entrada) {
        try {
            ps.setInt(1, entrada.getPersona().getIdPersona());
            ps.setInt(2, entrada.getIdConstancia());
            ps.setInt(3, entrada.getFuncion().getIdFuncion());
            ps.setInt(4, entrada.getNumEntrada());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Entrada entrada, int id) {
        entrada.setNumEntrada(id);
    }

    @Override
    public int insertar(Entrada entrada) {
        int idConstancia = -1, numEntrada = -1;
        try (Connection con = DBManager.getInstance().getConnection()) {
            con.setAutoCommit(false);
            idConstancia = constanciaDAO.insertar((Constancia) entrada);
            entrada.setIdConstancia(idConstancia);
            numEntrada = super.insertar(entrada);
            entrada.setNumEntrada(numEntrada);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar " + entrada.getClass().getSimpleName() + " ", e);
        } finally {
            if (numEntrada > 0) {
                return idConstancia;
            }
            return 0;
        }
    }

    @Override
    public boolean actualizar(Entrada entrada) {
        boolean resultado = false;
        try (Connection con = DBManager.getInstance().getConnection()) {
            con.setAutoCommit(false);
            resultado = constanciaDAO.actualizar(entrada);
            try (PreparedStatement ps = con.prepareStatement(this.getUpdateQuery())) {
                this.setUpdateParameters(ps, entrada);
                ps.executeUpdate();
                con.commit();
                System.out.println("Se actualizo un registro de " + entrada.getClass().getSimpleName());
                resultado = true;
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar " + entrada.getClass().getSimpleName(), e);
        } finally {
            return resultado;
        }
    }

    @Override
    public boolean eliminarLogico(int id) {
        boolean resultado = false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino logicamente un registro con ID=" + id);
                resultado = constanciaDAO.eliminarLogico(id);
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        } finally {
            return resultado;
        }
    }

    @Override
    public boolean eliminarFisico(int id) {
        boolean resultado = false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteFisicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino fisicamente un registro con ID=" + id);
                resultado = constanciaDAO.eliminarFisico(id);
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        } finally {
            return resultado;
        }
    }

    @Override
    public void llenarMapaDetalleEntrada(Map<String, Object> detalleEntrada, ResultSet rs) {
        try {
            putIfPresentInt(rs, detalleEntrada, "id_constancia", "idConstancia");
            putIfPresentInt(rs, detalleEntrada, "num_entrada", "numEntrada");
            putIfPresentString(rs, detalleEntrada, "nombre_evento", "nombreEvento");
            putIfPresentString(rs, detalleEntrada, "ubicacion", "ubicacion");
            putIfPresentString(rs, detalleEntrada, "nombre_distrito", "nombreDistrito");
            putIfPresentDate(rs, detalleEntrada, "fecha_funcion", "fechaFuncion");
            putIfPresentTime(rs, detalleEntrada, "hora_inicio", "horaInicio");
            putIfPresentTime(rs, detalleEntrada, "hora_fin", "horaFin");
            putIfPresentChar(rs, detalleEntrada, "activo", "estado");
        } catch (SQLException ex) {
            throw new RuntimeException("Error al llenar el mapa del detalle de la entrada: " + ex.getMessage(), ex);
        }
    }

    private void putIfPresentInt(ResultSet rs, Map<String, Object> map, String column, String key) throws SQLException {
        String val = rs.getString(column);
        if (val != null) {
            map.put(key, rs.getInt(column));
        }
    }

    private void putIfPresentString(ResultSet rs, Map<String, Object> map, String column, String key) throws SQLException {
        String val = rs.getString(column);
        if (val != null) {
            map.put(key, val);
        }
    }

    private void putIfPresentDate(ResultSet rs, Map<String, Object> map, String column, String key) throws SQLException {
        if (rs.getDate(column) != null) {
            map.put(key, rs.getDate(column));
        }
    }

    private void putIfPresentTime(ResultSet rs, Map<String, Object> map, String column, String key) throws SQLException {
        if (rs.getTime(column) != null) {
            map.put(key, rs.getTime(column));
        }
    }

    private void putIfPresentChar(ResultSet rs, Map<String, Object> map, String column, String key) throws SQLException {
        String val = rs.getString(column);
        if (val != null && !val.isEmpty()) {
            map.put(key, val.charAt(0));
        }
    }

    public void llenarMapaDetalleEntradaConFecha(Map<String, Object> detalleEntrada, ResultSet rs) {
        try {
            llenarMapaDetalleEntrada(detalleEntrada, rs);
            if (rs.getDate("fecha_constancia") != null) {
                detalleEntrada.put("fechaConstancia", rs.getDate("fecha_constancia"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntradaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Map<String, Object> buscarConstanciaEntrada(int idConstancia) {
        String sql = getConsultaConstanciaEntrada();
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idConstancia);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return procesarResultadoConstanciaEntrada(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar la constancia de la entrada: " + ex.getMessage(), ex);
        }
        return null;
    }

    private String getConsultaConstanciaEntrada() {
        return """
           SELECT c.id_constancia, en.num_entrada, ev.nombre AS nombre_evento, 
                  ev.ubicacion, d.nombre AS nombre_distrito, f.fecha AS fecha_funcion, 
                  f.hora_inicio, f.hora_fin, en.activo, c.fecha, c.metodo_pago, c.total, 
                  c.detalle_pago, p.nombres AS nombres_comprador, p.primer_apellido, 
                  p.segundo_apellido, p.correo, p.tipo_documento, p.num_documento 
           FROM Entrada en 
           JOIN Constancia c ON c.id_constancia = en.id_constancia_entrada 
           JOIN Funcion f ON f.id_funcion = en.Funcion_id_funcion 
           JOIN Evento ev ON ev.id_evento = f.Evento_idEvento 
           JOIN Distrito d ON d.id_distrito = ev.Distrito_id_distrito 
           JOIN Persona p ON p.id_persona = en.Persona_id_persona 
           WHERE en.id_constancia_entrada = ?
           """;
    }

    private Map<String, Object> procesarResultadoConstanciaEntrada(ResultSet rs) throws SQLException {
        Map<String, Object> datos = new HashMap<>();
        this.llenarMapaDetalleEntradaConFecha(datos, rs);
        constanciaDAO.llenarMapaDetalleConstancia(datos, rs);
        System.out.println("Se buscó la constancia de la entrada correctamente");
        return datos;
    }

    @Override
    public List<Map<String, Object>> listarDetalleEntradas() {
        List<Map<String, Object>> listaDetalleEntradas = new ArrayList<>();
        String sql = obtenerSQLDetalleEntradas();
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            procesarResultadoDetalleEntradas(rs, listaDetalleEntradas);
            System.out.println("Se listaron las entradas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entradas: ", e);
        }
        return listaDetalleEntradas;
    }

    private String obtenerSQLDetalleEntradas() {
        return """
        SELECT c.fecha AS fecha_constancia, c.id_constancia, e.num_entrada, 
               ev.nombre AS nombre_evento, ev.ubicacion, d.nombre AS nombre_distrito, 
               f.fecha AS fecha_funcion, f.hora_inicio, f.hora_fin, e.activo 
        FROM Entrada e 
        JOIN Constancia c ON c.id_constancia = e.id_constancia_entrada 
        JOIN Funcion f ON e.Funcion_id_funcion = f.id_funcion
        JOIN Evento ev ON f.Evento_idEvento = ev.id_evento 
        JOIN Distrito d ON ev.Distrito_id_distrito = d.id_distrito
        WHERE e.activo = 'A';
    """;
    }

    private void procesarResultadoDetalleEntradas(ResultSet rs, List<Map<String, Object>> lista) throws SQLException {
        while (rs.next()) {
            Map<String, Object> detalleEntrada = new HashMap<>();
            this.llenarMapaDetalleEntradaConFecha(detalleEntrada, rs);
            lista.add(detalleEntrada);
        }
    }

    public String getBuscarPorTexto() {
        return "{CALL BUSCAR_ENTRADA_POR_TEXTO(?)}";
    }

    @Override
    public List<Map<String, Object>> buscarPorTexto(String texto) {
        List<Map<String, Object>> entradas = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(this.getBuscarPorTexto())) {
            cs.setString(1, texto); // asignamos el parámetro de texto
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> detalleEntrada = new HashMap<>();
                    this.llenarMapaDetalleEntradaConFecha(detalleEntrada, rs);
                    entradas.add(detalleEntrada);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener un espacio: ", e);
        }
        return entradas;
    }

    @Override
    public List<Map<String, Object>> listarPorComprador(int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<Map<String, Object>> listaDetalleEntradas = new ArrayList<>();
        StringBuilder sql = construirConsultaBase();
        List<Object> params = construirParametrosYCondiciones(sql, idComprador, fechaInicio, fechaFin, estado);
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            asignarParametros(pst, params);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> detalleEntrada = new HashMap<>();
                    this.llenarMapaDetalleEntrada(detalleEntrada, rs);
                    listaDetalleEntradas.add(detalleEntrada);
                }
            }
            System.out.println("Entradas filtradas correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entradas filtradas", e);
        }
        return listaDetalleEntradas;
    }

    private StringBuilder construirConsultaBase() {
        return new StringBuilder("""
        SELECT c.id_constancia, e.num_entrada, ev.nombre AS nombre_evento, ev.ubicacion,
               d.nombre AS nombre_distrito, f.fecha AS fecha_funcion, f.hora_inicio,
               f.hora_fin, e.activo
        FROM Entrada e
        JOIN Constancia c ON c.id_constancia = e.id_constancia_entrada
        JOIN Funcion f ON e.Funcion_id_funcion = f.id_funcion
        JOIN Evento ev ON f.Evento_idEvento = ev.id_evento
        JOIN Distrito d ON ev.Distrito_id_distrito = d.id_distrito
        WHERE e.Persona_id_persona = ? AND f.fecha >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
    """);
    }

    private List<Object> construirParametrosYCondiciones(StringBuilder sql, int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<Object> params = new ArrayList<>();
        params.add(idComprador);
        agregarCondicionesFecha(sql, params, fechaInicio, fechaFin);
        agregarCondicionEstado(sql, params, estado);
        return params;
    }

    private void agregarCondicionesFecha(StringBuilder sql, List<Object> params, String fechaInicio, String fechaFin) {
        boolean tieneInicio = fechaInicio != null && !fechaInicio.isBlank();
        boolean tieneFin = fechaFin != null && !fechaFin.isBlank();
        if (tieneInicio && tieneFin) {
            sql.append(" AND (f.fecha BETWEEN ? AND ?)");
            params.add(java.sql.Date.valueOf(fechaInicio));
            params.add(java.sql.Date.valueOf(fechaFin));
        } else if (tieneInicio) {
            sql.append(" AND f.fecha >= ?");
            params.add(java.sql.Date.valueOf(fechaInicio));
        } else if (tieneFin) {
            sql.append(" AND f.fecha <= ?");
            params.add(java.sql.Date.valueOf(fechaFin));
        }
    }

    private void agregarCondicionEstado(StringBuilder sql, List<Object> params, String estado) {
        if (estado != null && !estado.isBlank()) {
            boolean flag=true;
            switch (estado.trim()) {
                case "Vigentes" ->
                    params.add("A");
                case "Finalizadas" ->
                    params.add("I");
                default ->
                    flag=false;
            }
           if(flag) sql.append(" AND e.activo = ?");
        }
    }

    private void asignarParametros(PreparedStatement pst, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            pst.setObject(i + 1, params.get(i));
        }
    }
    
    public String getSetInactiveQuery(){
        String query = "UPDATE Entrada e Join Funcion f on e.Funcion_id_funcion"
                + " = f.id_funcion set e.activo = 'I' where f.fecha<curdate()";
        return query;
    }
    
    @Override
    public boolean inactivar() {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(getSetInactiveQuery())) {
                ps.executeUpdate();
                conn.commit();
//                System.out.println("Se actualizo un registro de " + entity.getClass().getSimpleName());
                resultado=true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar ");
        }finally{
            return resultado;
        }
    }
}