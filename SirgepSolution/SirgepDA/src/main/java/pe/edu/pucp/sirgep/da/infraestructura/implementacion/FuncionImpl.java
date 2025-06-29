package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.Connection;
import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.sirgep.dbmanager.DBManager;

public class FuncionImpl extends BaseImpl<Funcion> implements FuncionDAO {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Funcion(hora_inicio, hora_fin, Evento_idEvento, fecha, activo)"
                + " VALUES(?, ?, ?, ?, 'A')";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT id_funcion, hora_inicio, hora_fin, Evento_idEvento, fecha FROM "
                + "Funcion WHERE id_funcion = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id_funcion, hora_inicio, hora_fin, Evento_idEvento, fecha FROM Funcion";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Funcion SET hora_inicio = ?, hora_fin = ?, fecha=? "
                + "Evento_idEvento = ? WHERE id_funcion = ?";
    }

    @Override
    protected String getDeleteLogicoQuery() {
        return "UPDATE Funcion SET activo = 'E' WHERE id_funcion = ?";
    }

    @Override
    protected String getDeleteFisicoQuery() {
        return "DELETE FROM Funcion WHERE id_funcion = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Funcion f) {
        try {
            
            String fechaFun = f.getFecha();
            String horaIniFun = f.getHoraInicio();
            String horaFinFun = f.getHoraFin();
            
            // 1. Parsear la fecha y hora
            LocalDate fechaParte = LocalDate.parse(fechaFun);     // "2025-06-17"
            LocalTime horaParteIni = LocalTime.parse(horaIniFun);       // "17:50"
            LocalTime horaParteFin = LocalTime.parse(horaFinFun);       // "17:50"

            // 2. Combinarlos
            LocalDateTime fechaHoraCompletaIni = LocalDateTime.of(fechaParte, horaParteIni);
            LocalDateTime fechaHoraCompletaFin = LocalDateTime.of(fechaParte, horaParteFin);

            // 3. Convertir a Timestamp
            Timestamp timestampIni = Timestamp.valueOf(fechaHoraCompletaIni);
            Timestamp timestampFin = Timestamp.valueOf(fechaHoraCompletaFin);
            
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaFunUtil = formatoEntrada.parse(fechaFun); // java.util.Date
            java.sql.Date fechaSql = new java.sql.Date(fechaFunUtil.getTime());
            
            ps.setTimestamp(1, timestampIni);
            ps.setTimestamp(2, timestampFin);
            ps.setInt(3, f.getEvento().getIdEvento());
            ps.setDate(4, fechaSql);
            
        } catch (SQLException ex) {
            throw new RuntimeException("Error al asignar parámetros de inserción para Funcion", ex);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Funcion f) {
        try {
            ps.setDate(1, Date.valueOf(f.getHoraInicio()));
            ps.setDate(2, Date.valueOf(f.getHoraFin()));
            ps.setDate(3, Date.valueOf(f.getFecha()));
            ps.setInt(4, f.getEvento().getIdEvento());
            ps.setInt(5, f.getIdFuncion());
        } catch (SQLException ex) {
            throw new RuntimeException("Error al asignar parámetros de actualización para Funcion", ex);
        }
    }

    @Override
    protected Funcion createFromResultSet(ResultSet rs) {
        try {
            Funcion f = new Funcion();
            f.setIdFuncion(rs.getInt("id_funcion"));
            f.setHoraInicio(rs.getTime("hora_inicio").toString());
            f.setHoraFin(rs.getTime("hora_fin").toString());
            f.setFecha(rs.getDate("fecha").toString());

            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("Evento_idEvento"));
            f.setEvento(evento);

            return f;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear Funcion desde ResultSet", ex);
        }
    }

    @Override
    protected void setId(Funcion f, int id) {
        f.setIdFuncion(id);
    }

    public String getListarPorIdEvento(){
        return "SELECT id_funcion, hora_inicio, hora_fin, Evento_idEvento, fecha FROM Funcion WHERE Evento_idEvento=? AND activo='A'";
    }

    @Override
    public List<Funcion> listarPorIdEvento(int idEvento){
        List<Funcion> funciones = new ArrayList<>();

        // Utilizaremos procedimientos almacenados
        try (Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = conn.prepareStatement(this.getListarPorIdEvento())) {

            ps.setInt(1, idEvento);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    funciones.add(createFromResultSet(rs));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("ERROR al obtener FUNCIONES por idEvento: ", e);
        }
        return funciones;
    }
    
    public String getSetInactiveQuery(){
        String query = "UPDATE Funcion SET activo = 'I' where fecha < curdate()";
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
