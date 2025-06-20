package pe.edu.pucp.sirgep.da.ventas.implementacion;

import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ConstanciaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import java.util.Date;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

public class ReservaImpl extends BaseImpl<Reserva> implements ReservaDAO{

    // necesario para insertar la constancia:
    private ConstanciaDAO constanciaDAO;
    
    public ReservaImpl(){
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
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona"
                + "WHERE r.num_reserva = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona";
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
        return "UPDATE Reserva SET activo = 'I' WHERE num_reserva = ?";
    }

    @Override
    protected String getDeleteFisicoQuery() {
        return "DELETE FROM Reserva WHERE num_reserva = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Reserva entity) {
        try{
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
        }catch(SQLException e){
            System.out.println("Se encontro un error a la hora de insertar reserva parametros: " + e.getMessage());
        }
    }

    @Override
    protected Reserva createFromResultSet(ResultSet rs) {
        Reserva aux = new Reserva();
        Espacio esp = new Espacio();
        Distrito dis = new Distrito();
        Persona per = new Persona();
        
        try{
            aux.setNumReserva(rs.getInt("num_reserva"));
            aux.setHorarioIni(rs.getTime("horario_ini").toLocalTime());
            aux.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
            aux.setFechaReserva(rs.getDate("fecha_reserva"));
            aux.setFechaReserva(rs.getDate("fecha_reserva"));
            
            
            aux.setIniString(aux.getHorarioIni().toString());
            aux.setFinString(aux.getHorarioIni().toString());
            
            dis.setIdDistrito(rs.getInt("id_distrito"));
            dis.setNombre(rs.getString("D.nombre"));

            esp.setIdEspacio(rs.getInt("Espacio_id_espacio"));
            esp.setNombre(rs.getString("E.nombre"));
            esp.setDistrito(dis);
            per.setIdPersona(rs.getInt("Persona_id_persona"));
            per.setCorreo(rs.getString("P.correo"));
            
            aux.setEspacio(esp);
            aux.setPersona(per);
            
            aux.setIdConstancia(rs.getInt("id_constancia_reserva"));
            String activo=rs.getString("activo");
            aux.setActivo(activo.charAt(0));
        }
        catch(SQLException e){
            System.out.println("Se encontro un error a la hora de crear Reserva desde RS: " + e.getMessage());
        }
        return aux;
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Reserva entity) {
        try{
            /*
            "UPDATE Reserva SET horario_ini=?,"
                + " horario_fin=?,"
                + " fecha_reserva=?,"
                + " Espacio_id_espacio=?,"
                + " Persona_id_persona=?,"
                + " id_constancia_reserva=?"
                + " WHERE num_reserva = ?";
            */
            
            /*
            return "UPDATE Reserva SET horario_ini=?,"
                + " horario_fin=?,"
                + " fecha_reserva=?,"
                + " Espacio_id_espacio=?,"
                + " Persona_id_persona=?,"
                + " id_constancia_reserva=?"
                + " WHERE num_reserva = ?";*/
            ps.setTime(1, Time.valueOf(entity.getHorarioIni()));
            ps.setTime(2, Time.valueOf(entity.getHorarioFin()));
            ps.setDate(3, new java.sql.Date(entity.getFechaReserva().getTime()));
            ps.setInt(4, entity.getEspacio().getIdEspacio());
            ps.setInt(5, entity.getPersona().getIdPersona());
            ps.setInt(6, entity.getIdConstancia());
            ps.setInt(7, entity.getNumReserva());
            
        }catch(SQLException e){
            System.out.println("Se encontro un error a la hora de MODIFICAR tabla RESERVA: " + e.getMessage());
        }
    }

    @Override
    protected void setId(Reserva entity, int id) {
        entity.setNumReserva(id);
    }
    
    // SOBRECARGAS NECESARIAS para considerar la herencia con la clase CONSTANCIA
    // Siempre que se quiera hacer un CRUD sobre RESERVA se hace en CONSTANCIA primero
    
    @Override
    public int insertar(Reserva entity){
        int idC=-1, idR=-1;
        try (Connection con = DBManager.getInstance().getConnection()){
            con.setAutoCommit(false);
            // insertar la constancia
            
            idC = constanciaDAO.insertar((Constancia)entity);
            entity.setIdConstancia(idC);
            idR = super.insertar(entity);
            
            
//            try(PreparedStatement ps=con.prepareStatement(this.getInsertQuery(),Statement.RETURN_GENERATED_KEYS)){
//                this.setInsertParameters(ps, entity); //armamos el ps con la entidad Reserva pasada
//                ps.executeUpdate(); // se inserta la Reserva ahora
//                con.commit();
//                System.out.println("Se inserto un registro de "+entity.getClass().getSimpleName()+" con ID="+id);
//            }catch (SQLException e) {
//                con.rollback();
//                throw new RuntimeException("Error al insertar la entidad" + e.getMessage());
//            }finally {
//                con.setAutoCommit(true);
//            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            if(idR>0)
                return idR;
            return -1;
        }
    }
//    T buscar(int id);
//    List<T> listar();
    public boolean actualizarDerivada(Reserva entity, Connection con) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement(this.getUpdateQuery(), Statement.RETURN_GENERATED_KEYS)){
            this.setUpdateParameters(ps, entity);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se actualizo un registro de "+entity.getClass().getSimpleName());
            return true; // si todo fue bien, la respuesta será verdadera
        }
        catch(SQLException e){
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
            
        } finally{
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }
    
    public boolean eliminarLogicoDerivada(int id, Connection con) throws SQLException{
        try(PreparedStatement ps = con.prepareStatement(this.getDeleteLogicoQuery(), Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se elimino un registro LOGICAMENTE de forma correcta");
            return true; // si todo fue bien, la respuesta será verdadera
        }
        catch(SQLException e){
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
            
        } finally{
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }
    
    public boolean eliminarFisicoDerivada(int id, Connection con) throws SQLException{
        // primero, debemos eliminar la constancia
        
        try(PreparedStatement ps = con.prepareStatement(this.getDeleteLogicoQuery(), Statement.RETURN_GENERATED_KEYS)){
            if(!constanciaDAO.eliminarFisico(id)) return false;
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Se elimino un registro FISICAMENTE de forma correcta");
            return true; // si todo fue bien, la respuesta será verdadera
        }
        catch(SQLException e){
            con.rollback();
            return false; // si algo falló, la respuesta será falsa
            
        } finally{
            // el finally siempre se ejecuta... asi hayan returns antes
            con.setAutoCommit(true);
        }
    }
    
    @Override
    public boolean actualizar(Reserva entidad){
        // Reserva: Constancia + algo más, entonces puede utilizar todo lo de constancia
        boolean seActualizoC=false, seActualizoR=false;
        
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try(Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            
            // intentamos insertar constancia
            seActualizoC = constanciaDAO.actualizar(entidad);
            if(!seActualizoC) throw new RuntimeException("No se actualizo la constancia correctamente");
            
            // ahora, necesito insertar la reserva como tal
            seActualizoR = actualizarDerivada(entidad,con);
            if(!seActualizoR) throw new RuntimeException("No se actualizo la reserva correctamente");
        }
        catch(SQLException e){
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        
        return (seActualizoR && seActualizoC);
    }
    
    @Override
    public boolean eliminarLogico(int id){
        boolean seEliminoLogC=false, seEliminoLogR=false;
        
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try(Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            
            // intentamos insertar constancia
            seEliminoLogC = constanciaDAO.eliminarLogico(id);
            if(!seEliminoLogC) throw new RuntimeException("No se actualizo la constancia correctamente");
            
            // ahora, necesito insertar la reserva como tal
            seEliminoLogR = eliminarLogicoDerivada(id,con);
            if(!seEliminoLogR) throw new RuntimeException("No se actualizo la reserva correctamente");
        }
        catch(SQLException e){
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        
        return (seEliminoLogR && seEliminoLogC);
    }
    
    @Override
    public boolean eliminarFisico(int id){
        boolean seEliminoFisC=false, seEliminoFisR=false;
        
        // intento realizar el procedimiento: actualizar Constancia y, luego, Reserva:
        try(Connection con = DBManager.getInstance().getConnection()) // para que se cierre automáticamente al finalizar try
        {
            con.setAutoCommit(false); // no quiero que se guarde por si hay algo erróneo
            
            // intentamos insertar constancia
            seEliminoFisC = constanciaDAO.eliminarLogico(id);
            if(!seEliminoFisC) throw new RuntimeException("No se ELIMINO de forma FISICA la constancia correctamente");
            
            // ahora, necesito insertar la reserva como tal
            seEliminoFisR = eliminarFisicoDerivada(id,con);
            if(!seEliminoFisR) throw new RuntimeException("No se ELIMINO de forma FISICA la reserva correctamente");
        }
        catch(SQLException e){
            throw new RuntimeException("Sucedio un error al actualizar la reserva: " + e.getMessage());
        }
        
        return (seEliminoFisR && seEliminoFisC);
    }
    
    @Override
    public List<Reserva> listarPorDiaYEspacio(int idEspacio, java.util.Date fecha){
        List<Reserva> listaReserva=null;
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
    public List<Map<String, Object>> listarDetalleReservasPorComprador(int IdComprador) {
        List<Map<String, Object>> listaDetalleReservas = null;
        String sql = """
                         SELECT r.num_reserva, e.nombre AS nombre_espacio, e.tipo_espacio AS categoria_espacio, e.ubicacion, 
                     d.nombre AS nombre_distrito, r.fecha_reserva, r.horario_ini AS hora_inicio, r.horario_fin AS hora_fin
                         FROM Reserva r JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio JOIN Distrito d ON 
                     e.Distrito_id_distrito = d.id_distrito WHERE r.activo = 'A' AND r.Persona_id_persona = 
                 """ + IdComprador;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            listaDetalleReservas = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("numReserva", rs.getInt("num_reserva"));
                fila.put("nombreEspacio", rs.getString("nombre_espacio"));
                fila.put("categoria", rs.getString("categoria_espacio"));
                fila.put("ubicacion", rs.getString("ubicacion"));
                fila.put("nombreDistrito", rs.getString("nombre_distrito"));
                fila.put("fecha", rs.getDate("fecha_reserva"));
                fila.put("horaInicio", rs.getTime("hora_inicio"));
                fila.put("horaFin", rs.getTime("hora_fin"));
                listaDetalleReservas.add(fila);
            }
            System.out.println("Se listo las entradas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entradas: ", e);
        } finally {
            return listaDetalleReservas;
        }
    }
    
    @Override
    public List<Reserva> listarPorFecha(Date fecha, boolean activo){
        List<Reserva> listaReservas=null;
        String sql;
        if(activo){
            sql= "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona "
                + "WHERE r.fecha_reserva = ? and r.activo = 'A'";
        }else{
            sql= "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona "
                + "WHERE r.fecha_reserva = ?";
        }
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            listaReservas = new ArrayList<>();
            //Conversion de java.util.Date a java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            pst.setDate(1, sqlDate);  // Seteamos la fecha en el primer parámetro
            try(ResultSet rs = pst.executeQuery()){
                while (rs.next()) {
                    listaReservas.add(createFromResultSet(rs));
                }
            }
            System.out.println("Se listo las reservas por fecha correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por fecha: ", e);
        } finally {
            return listaReservas;
        }
    }
        
    @Override
    public List<Reserva> listarPorDistrito(int id_distrito, boolean activo){
        List<Reserva> listaReservas=null;
        String sql;
        if(activo){
            sql= "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona "
                + "WHERE d.id_distrito = " +id_distrito+ " and r.activo = 'A'";
        }else{
            sql= "SELECT r.*, e.id_espacio, e.nombre AS 'E.nombre', d.id_distrito, d.nombre AS 'D.nombre', p.correo FROM Reserva r "
                + "JOIN Espacio e ON r.Espacio_id_espacio = e.id_espacio "
                + "JOIN Distrito d ON e.Distrito_id_distrito = d.id_distrito "
                + "JOIN Persona p ON p.id_persona = r.Persona_id_persona "
                + "WHERE d.id_distrito = " +id_distrito;
        }
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            listaReservas = new ArrayList<>();
            while (rs.next()) {
                listaReservas.add(createFromResultSet(rs));
            }
            System.out.println("Se listo las reservas por distrito correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas por distrito: ", e);
        } finally {
            return listaReservas;
        }
    }
}
