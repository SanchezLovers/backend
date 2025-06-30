package pe.edu.pucp.sirgep.da.ventas.implementacion;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

public class ReservaImpl extends BaseImpl<Reserva> implements ReservaDAO {
    private ConstanciaDAO constanciaDAO;

    public ReservaImpl() {
        constanciaDAO = new ConstanciaImpl();
    }

    @Override
    protected String getInsertQuery() {
        // primero debemos insertar a CONSTANCIA --> se hará en la sobrecarga del insertar
        return "INSERT INTO Reserva(horario_ini, horario_fin, fecha_reserva, Espacio_id_espacio, Persona_id_persona, id_constancia_reserva, activo) "
                + "VALUES(?,?,?,?,?,?,?)";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona "
                + "WHERE r.num_reserva = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r " +
               "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio " +
               "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito " +
               "JOIN Persona p ON p.id_persona = r.Persona_id_persona " +
               "WHERE r.activo='A'";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Reserva SET horario_ini=?,"
                + " horario_fin=?,"
                + " fecha_reserva=?,"
                + " Espacio_id_espacio=?,"
                + " Persona_id_persona=?,"
                + " id_constancia_reserva=?"
                + " WHERE num_reserva = ?";
    }

    @Override
    protected String getDeleteLogicoQuery() {
        return "UPDATE Reserva SET activo = 'E' WHERE num_reserva = ?";
    }

    @Override
    protected String getDeleteFisicoQuery() {
        return "DELETE FROM Reserva WHERE num_reserva = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Reserva entity) {
        try {
            // return "INSERT INTO Reserva(horario_ini, horario_fin, fecha_reserva, Espacio_id_espacio, Persona_id_persona,
            // id_constancia_reserva, activo) "
            // + "VALUES(?,?,?,?,?,?,?)";
            ps.setTime(1, Time.valueOf(entity.getHorarioIni()));
            ps.setTime(2, Time.valueOf(entity.getHorarioFin()));
            ps.setDate(3, new java.sql.Date(entity.getFechaReserva().getTime()));
            ps.setInt(4, entity.getEspacio().getIdEspacio());
            ps.setInt(5, entity.getPersona().getIdPersona());
            ps.setInt(6, entity.getIdConstancia());
            ps.setString(7, String.valueOf('A')); // es activo
        } catch (SQLException e) {
            System.out.println("Se encontro un error a la hora de insertar reserva parametros: " + e.getMessage());
        }
    }

    @Override
    protected Reserva createFromResultSet(ResultSet rs) {
        try {
            Reserva reserva = construirReserva(rs);
            reserva.setEspacio(construirEspacio(rs));
            reserva.setPersona(construirPersona(rs));
            return reserva;
        } catch (SQLException e) {
            System.out.println("Se encontró un error al crear Reserva desde RS: " + e.getMessage());
            return null;
        }
    }

    private Reserva construirReserva(ResultSet rs) throws SQLException {
        Reserva aux = new Reserva();
        aux.setNumReserva(rs.getInt("num_reserva"));
        aux.setHorarioIni(rs.getTime("horario_ini").toLocalTime());
        aux.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
        aux.setFechaReserva(rs.getDate("fecha_reserva"));
        aux.setIniString(aux.getHorarioIni().toString());
        aux.setFinString(aux.getHorarioFin().toString());
        aux.setIdConstancia(rs.getInt("id_constancia_reserva"));
        String activo = rs.getString("activo");
        aux.setActivo(activo.charAt(0));
        return aux;
    }

    private Espacio construirEspacio(ResultSet rs) throws SQLException {
        Espacio esp = new Espacio();
        esp.setIdEspacio(rs.getInt("Espacio_id_espacio"));
        esp.setNombre(rs.getString("E.nombre"));
        esp.setDistrito(construirDistrito(rs));
        return esp;
    }

    private Distrito construirDistrito(ResultSet rs) throws SQLException {
        Distrito dis = new Distrito();
        dis.setIdDistrito(rs.getInt("id_distrito"));
        dis.setNombre(rs.getString("D.nombre"));
        return dis;
    }

    private Persona construirPersona(ResultSet rs) throws SQLException {
        Persona per = new Persona();
        per.setIdPersona(rs.getInt("Persona_id_persona"));
        per.setCorreo(rs.getString("P.correo"));
        return per;
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Reserva entity) {
        try {
            ps.setTime(1, Time.valueOf(entity.getHorarioIni()));
            ps.setTime(2, Time.valueOf(entity.getHorarioFin()));
            ps.setDate(3, new java.sql.Date(entity.getFechaReserva().getTime()));
            ps.setInt(4, entity.getEspacio().getIdEspacio());
            ps.setInt(5, entity.getPersona().getIdPersona());
            ps.setInt(6, entity.getIdConstancia());
            ps.setInt(7, entity.getNumReserva());
        } catch (SQLException e) {
            System.out.println("Se encontro un error a la hora de MODIFICAR tabla RESERVA: " + e.getMessage());
        }
    }

    @Override
    protected void setId(Reserva entity, int id) {
        entity.setNumReserva(id);
    }
    
    //Metodos CRUD
    @Override
    public int insertar(Reserva entity) {
        // SOBRECARGAS NECESARIAS para considerar la herencia con la clase CONSTANCIA
        // Siempre que se quiera hacer un CRUD sobre RESERVA se hace en CONSTANCIA primero
        int idConstancia = -1, numReserva = -1;
        try (Connection con = DBManager.getInstance().getConnection()) {
            con.setAutoCommit(false);
            idConstancia = constanciaDAO.insertar((Constancia) entity);
            entity.setIdConstancia(idConstancia);
            numReserva = super.insertar(entity);
            entity.setNumReserva(numReserva);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar " + entity.getClass().getSimpleName() + " ", e);
        } finally {
            if (numReserva > 0) {
                return idConstancia;
            }
            return -1;
        }
    }
    
    public boolean actualizarDerivada(Reserva entity, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(this.getUpdateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            this.setUpdateParameters(ps, entity);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se actualizo un registro de " + entity.getClass().getSimpleName());
            return true; // si todo fue bien, la respuesta será verdadera
        } catch (SQLException e) {
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
        } finally {
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }

    public boolean eliminarLogicoDerivada(int id, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(this.getDeleteLogicoQuery(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se elimino un registro LOGICAMENTE de forma correcta");
            return true; // si todo fue bien, la respuesta será verdadera
        } catch (SQLException e) {
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
        } finally {
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }

    public boolean eliminarFisicoDerivada(int id, Connection con) throws SQLException {
        // Primero, debemos eliminar la constancia
        try (PreparedStatement ps = con.prepareStatement(this.getDeleteLogicoQuery(), Statement.RETURN_GENERATED_KEYS)) {
            if (!constanciaDAO.eliminarFisico(id)) {
                return false;
            }
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se elimino un registro FISICAMENTE de forma correcta");
            return true; // si todo fue bien, la respuesta será verdadera
        } catch (SQLException e) {
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
        } finally {
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean actualizar(Reserva entidad) {
        // Reserva: Constancia + algo más, entonces puede utilizar todo lo de constancia
        boolean seActualizoC = false, seActualizoR = false;
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try (Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            // intentamos insertar constancia
            seActualizoC = constanciaDAO.actualizar(entidad);
            if (!seActualizoC) {
                throw new RuntimeException("No se actualizo la constancia correctamente");
            }
            // ahora, necesito insertar la reserva como tal
            seActualizoR = actualizarDerivada(entidad, con);
            if (!seActualizoR) {
                throw new RuntimeException("No se actualizo la reserva correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        return (seActualizoR && seActualizoC);
    }

    @Override
    public boolean eliminarLogico(int id) {
        boolean seEliminoLogC = false, seEliminoLogR = false;
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try (Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            // intentamos insertar constancia
            seEliminoLogC = constanciaDAO.eliminarLogico(id);
            if (!seEliminoLogC) {
                throw new RuntimeException("No se actualizo la constancia correctamente");
            }
            // ahora, necesito insertar la reserva como tal
            seEliminoLogR = eliminarLogicoDerivada(id, con);
            if (!seEliminoLogR) {
                throw new RuntimeException("No se actualizo la reserva correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        return (seEliminoLogR && seEliminoLogC);
    }

    @Override
    public boolean cancelarReserva(int id) throws SQLException {
        String query = "UPDATE Reserva SET activo = 'C' WHERE num_reserva = ?";
        try (Connection con = DBManager.getInstance().getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                int filasAfectadas = ps.executeUpdate();
                con.commit();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                con.rollback();
                System.err.println("Error al cancelar la reserva: " + e.getMessage());
                return false;
            }
        }
    }

    @Override
    public boolean eliminarFisico(int id) {
        boolean seEliminoFisC = false, seEliminoFisR = false;
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try (Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            // intentamos insertar constancia
            seEliminoFisC = constanciaDAO.eliminarLogico(id);
            if (!seEliminoFisC) {
                throw new RuntimeException("No se ELIMINO de forma FISICA la constancia correctamente");
            }
            // ahora, necesito insertar la reserva como tal
            seEliminoFisR = eliminarFisicoDerivada(id, con);
            if (!seEliminoFisR) {
                throw new RuntimeException("No se ELIMINO de forma FISICA la reserva correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        return (seEliminoFisR && seEliminoFisC);
    }

    @Override
    public List<Reserva> listarPorDiaYEspacio(int idEspacio, java.util.Date fecha) {
        List<Reserva> listaReserva = null;
        String sql = "{CALL reservasPorDiaYEspacio(?, ?)}";
        try (Connection conn = DBManager.getInstance().getConnection()) {
            listaReserva = new ArrayList<>();
            CallableStatement pst = conn.prepareCall(sql);
            pst.setInt(1, idEspacio);
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reserva r = createFromResultSet(rs);
                listaReserva.add(r);
            }
            System.out.println("Se listo las entradas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        } finally {
            return listaReserva;
        }
    }

    @Override
    public List<Map<String, Object>> listarTodos() {
        List<Map<String, Object>> listaReservas = new ArrayList<>();
        String sql = construirSQLListarTodos();
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); 
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> reserva = new HashMap<>();
                llenarMapaReserva(reserva, rs);
                listaReservas.add(reserva);
            }
            System.out.println("Se listaron las reservas por distrito correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por distrito: ", e);
        }
        return listaReservas;
    }

    private String construirSQLListarTodos() {
        return """
        SELECT c.fecha AS fecha_constancia, r.num_reserva, r.fecha_reserva,
               r.activo, r.id_constancia_reserva,
               e.nombre AS nombre_espacio,
               d.nombre AS nombre_distrito,
               p.correo
        FROM Reserva r
        JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio
        JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito
        JOIN Persona p ON p.id_persona = r.Persona_id_persona
        JOIN Constancia c ON r.id_constancia_reserva = c.id_constancia
        WHERE r.activo != 'E'
    """;
    }

    @Override
    public List<Map<String, Object>> listarDetalleReservasPorFecha(Date fecha, boolean activo) {
        List<Map<String, Object>> listaDetalleReservas = new ArrayList<>();
        String sql = obtenerSQLReservasPorFecha(activo);
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            establecerParametroFecha(pst, fecha);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> reserva = new HashMap<>();
                    this.llenarMapaReserva(reserva, rs);
                    listaDetalleReservas.add(reserva);
                }
            }
            System.out.println("Se listaron las reservas por fecha correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por fecha: ", e);
        }
        return listaDetalleReservas;
    }

    private String obtenerSQLReservasPorFecha(boolean activo) {
        String baseSQL = """
        SELECT c.fecha AS fecha_constancia, r.num_reserva, r.fecha_reserva, r.id_constancia_reserva, 
               d.nombre AS nombre_distrito, e.nombre AS nombre_espacio, p.correo, r.activo
        FROM Reserva r
        JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio
        JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito
        JOIN Persona p ON p.id_persona = r.Persona_id_persona
        JOIN Constancia c ON r.id_constancia_reserva = c.id_constancia
    """;
        return baseSQL + obtenerCondicionWherePorFecha(activo);
    }

    private String obtenerCondicionWherePorFecha(boolean activo) {
        if (activo) {
            return " WHERE r.fecha_reserva = ? AND r.activo = 'A'";
        } else {
            return " WHERE r.fecha_reserva = ? AND r.activo != 'E'";
        }
    }

    private void establecerParametroFecha(PreparedStatement pst, Date fecha) throws SQLException {
        pst.setDate(1, new java.sql.Date(fecha.getTime()));
    }

    @Override
    public List<Map<String, Object>> listarPorDistrito(int idDistrito, boolean activo) {
        List<Map<String, Object>> listaReservas = new ArrayList<>();
        String sql = obtenerSQLListarPorDistrito(activo);
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDistrito);
            try (ResultSet rs = pst.executeQuery()) {
                procesarReservas(rs, listaReservas);
            }
            System.out.println("Se listaron las reservas por distrito correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por distrito: ", e);
        }
        return listaReservas;
    }

    private String obtenerSQLListarPorDistrito(boolean activo) {
        StringBuilder sql = new StringBuilder();
        sql.append(obtenerSQLBaseReservaDistrito());
        sql.append(activo ? " AND r.activo = 'A'" : " AND r.activo != 'E'");
        return sql.toString();
    }

    private String obtenerSQLBaseReservaDistrito() {
        return """
        SELECT c.fecha AS fecha_constancia, r.num_reserva, r.fecha_reserva, r.activo, r.id_constancia_reserva,
               e.nombre AS nombre_espacio,
               d.nombre AS nombre_distrito,
               p.correo
        FROM Reserva r
        JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio
        JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito
        JOIN Persona p ON p.id_persona = r.Persona_id_persona
        JOIN Constancia c ON r.id_constancia_reserva = c.id_constancia
        WHERE d.id_distrito = ?
    """;
    }

    private void procesarReservas(ResultSet rs, List<Map<String, Object>> listaReservas) throws SQLException {
        while (rs.next()) {
            Map<String, Object> reserva = new HashMap<>();
            llenarMapaReserva(reserva, rs);
            listaReservas.add(reserva);
        }
    }

    @Override
    public void llenarMapaReserva(Map<String, Object> reserva, ResultSet rs) {
        try {
            putIntIfExists(reserva, rs, "num_reserva", "num_reserva");
            putDateIfExists(reserva, rs, "fecha_reserva", "fecha_reserva");
            putDateIfExists(reserva, rs, "fecha_constancia", "fecha_constancia");
            putIntIfExists(reserva, rs, "id_constancia_reserva", "id_constancia_reserva");
            putStringIfExists(reserva, rs, "nombre_espacio", "nombre_espacio");
            putStringIfExists(reserva, rs, "nombre_distrito", "nombre_distrito");
            putStringIfExists(reserva, rs, "correo", "correo");
            putCharIfExists(reserva, rs, "activo", "activo");
        } catch (SQLException ex) {
            throw new RuntimeException("Error al llenar el mapa del detalle de la reserva: " + ex.getMessage(), ex);
        }
    }

    private void putIntIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        int value = rs.getInt(column);
        if (!rs.wasNull()) {
            map.put(key, value);
        }
    }

    private void putDateIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        java.sql.Date date = rs.getDate(column);
        if (date != null) {
            map.put(key, date);
        }
    }

    private void putStringIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        String value = rs.getString(column);
        if (value != null) {
            map.put(key, value);
        }
    }

    private void putCharIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        String value = rs.getString(column);
        if (value != null && !value.isEmpty()) {
            map.put(key, value.charAt(0));
        }
    }
    
    @Override
    public void llenarMapaDetalleReserva(Map<String, Object> detalleReserva, ResultSet rs) {
        try {
            putIntIfExists(detalleReserva, rs, "id_constancia", "idConstancia");
            putIntIfExists(detalleReserva, rs, "num_reserva", "numReserva");
            putStringIfExists(detalleReserva, rs, "nombre_espacio", "nombreEspacio");
            putStringIfExists(detalleReserva, rs, "ubicacion", "ubicacion");
            putStringIfExists(detalleReserva, rs, "nombre_distrito", "nombreDistrito");
            putStringIfExists(detalleReserva, rs, "categoria_espacio", "categoria");
            putDoubleIfExists(detalleReserva, rs, "superficie", "superficie");
            putDateIfExists(detalleReserva, rs, "fecha_reserva", "fecha_reserva");
            putTimeIfExists(detalleReserva, rs, "hora_inicio", "horaInicio");
            putTimeIfExists(detalleReserva, rs, "hora_fin", "horaFin");
            putCharIfExists(detalleReserva, rs, "activo", "estado");
        } catch (SQLException ex) {
            throw new RuntimeException("Error al llenar el mapa del detalle de la reserva: " + ex.getMessage(), ex);
        }
    }

    private void putDoubleIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        double value = rs.getDouble(column);
        if (!rs.wasNull()) {
            map.put(key, value);
        }
    }

    private void putTimeIfExists(Map<String, Object> map, ResultSet rs, String column, String key) throws SQLException {
        java.sql.Time time = rs.getTime(column);
        if (time != null) {
            map.put(key, time);
        }
    }
   
    @Override
    public Map<String, Object> buscarConstanciaReserva(int idConstancia) {
        String sql = obtenerSqlConstanciaReserva();
        try (Connection conn = DBManager.getInstance().getConnection(); 
                PreparedStatement pst = prepararConsultaConstanciaReserva(conn, sql, idConstancia); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return construirMapaConstanciaReserva(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar la constancia de la reserva: " + ex.getMessage(), ex);
        }
        return null;
    }

    private String obtenerSqlConstanciaReserva() {
        return """
        SELECT c.id_constancia, r.num_reserva, e.nombre AS nombre_espacio, e.tipo_espacio AS categoria_espacio,
               e.ubicacion, e.superficie, d.nombre AS nombre_distrito, r.fecha_reserva, r.horario_ini AS hora_inicio,
               r.horario_fin AS hora_fin, r.activo, c.fecha, c.metodo_pago, c.total, c.detalle_pago,
               p.nombres AS nombres_comprador, p.primer_apellido, p.segundo_apellido, p.correo,
               p.tipo_documento, p.num_documento
        FROM Reserva r
        JOIN Constancia c ON c.id_constancia = r.id_constancia_reserva
        JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio
        JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito
        JOIN Persona p ON p.id_persona = r.Persona_id_persona
        WHERE c.id_constancia = ?
    """;
    }

    private PreparedStatement prepararConsultaConstanciaReserva(Connection conn, String sql, int idConstancia) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, idConstancia);
        return pst;
    }

    private Map<String, Object> construirMapaConstanciaReserva(ResultSet rs) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        llenarMapaDetalleReserva(map, rs);
        constanciaDAO.llenarMapaDetalleConstancia(map, rs);
        System.out.println("Se buscó la constancia de la reserva correctamente");
        return map;
    }

    @Override
    public List<Reserva> listarPorMesYAnio(int mes, int anio) {
        List<Reserva> listaReserva = new ArrayList<>();
        String sql = "{CALL BUSCA_RESERVAS_POR_MES_Y_ANIO(?, ?)}";
        try (Connection conn = DBManager.getInstance().getConnection();
             CallableStatement pst = conn.prepareCall(sql)) {
            pst.setInt(1, mes);   // Ej: "06"
            pst.setInt(2, anio);  // Ej: "2025"
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reserva r = createFromResultSet(rs); // Este método debería mapear correctamente el ResultSet
                listaReserva.add(r);
            }
            System.out.println("Se listaron las reservas por mes y año correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por mes y año", e);
        }
        return listaReserva;
    }

    @Override
    public List<Map<String, Object>> listarPorComprador(int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<Map<String, Object>> listaDetalleReservas = new ArrayList<>();
        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = construirSQLReservaFiltrada(idComprador, fechaInicio, fechaFin, estado, parametros);
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            asignarParametros(pst, parametros);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> detalleReserva = new HashMap<>();
                    this.llenarMapaDetalleReserva(detalleReserva, rs);
                    listaDetalleReservas.add(detalleReserva);
                }
            }
            System.out.println("Reservas filtradas correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas filtradas", e);
        }
        return listaDetalleReservas.isEmpty() ? null : listaDetalleReservas;
    }

    private StringBuilder construirSQLReservaFiltrada(int idComprador, String fechaInicio, String fechaFin, String estado,
            List<Object> params) {
        StringBuilder sql = construirQueryBaseDetalleReservas();
        params.add(idComprador);
        agregarFiltrosFechas(sql, fechaInicio, fechaFin, params);
        agregarFiltrosEstado(sql, estado, params);
        return sql;
    }

    private StringBuilder construirQueryBaseDetalleReservas() {
        return new StringBuilder("""
        SELECT c.id_constancia, r.num_reserva, e.nombre AS nombre_espacio, e.tipo_espacio AS categoria_espacio,
               e.ubicacion, d.nombre AS nombre_distrito, r.fecha_reserva, r.horario_ini AS hora_inicio,
               r.horario_fin AS hora_fin, e.superficie, r.activo
        FROM Reserva r
        JOIN Constancia c ON c.id_constancia = r.id_constancia_reserva
        JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio
        JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito
        WHERE r.Persona_id_persona = ?
    """);
    }

    private void agregarFiltrosFechas(StringBuilder sql, String fechaInicio, String fechaFin, List<Object> params) {
        if (fechaInicio != null && !fechaInicio.isBlank() && fechaFin != null && !fechaFin.isBlank()) {
            sql.append(" AND r.fecha_reserva BETWEEN ? AND ?");
            params.add(java.sql.Date.valueOf(fechaInicio));
            params.add(java.sql.Date.valueOf(fechaFin));
        } else if (fechaInicio != null && !fechaInicio.isBlank()) {
            sql.append(" AND r.fecha_reserva >= ?");
            params.add(java.sql.Date.valueOf(fechaInicio));
        } else if (fechaFin != null && !fechaFin.isBlank()) {
            sql.append(" AND r.fecha_reserva <= ?");
            params.add(java.sql.Date.valueOf(fechaFin));
        }
    }

    private void agregarFiltrosEstado(StringBuilder sql, String estado, List<Object> params) {
        if (estado != null && !estado.isBlank()) {
            if (estado.equals("Vigentes")) {
                params.add("A");
            } else if (estado.equals("Finalizadas")) {
                params.add("I");
            } else if (estado.equals("Canceladas")) {
                params.add("C");
            }else{
                return;
            }
            sql.append(" AND r.activo IN (");
            sql.append("?");
            sql.append(")");
        }
    }

    private void asignarParametros(PreparedStatement pst, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            pst.setObject(i + 1, params.get(i));
        }
    }
    
    public String getSetInactiveQuery(){
        String query = "UPDATE Reserva set activo = 'P' where fecha_reserva<curdate() and activo != 'E'";
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