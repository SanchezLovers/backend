package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.EMetodoPago;
import com.slovers.sirgep.dominio.models.gestion.Espacio;
import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.dominio.models.ventas.Reserva;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.ReservaDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 
 * @author italo
 */
public class ReservaMySQL implements ReservaDAO{

    @Override //funciona, se le quitó el eTipoEstado
    public void insertar(Reserva reserva) throws SQLException, IOException {
        //Anteriormente debí insertar la Constancia que es la clase padre
        String sql = "INSERT INTO Reserva"
                + "(horario_ini, horario_fin, fecha_reserva, Espacio_id_espacio, Persona_id_persona, id_constancia_reserva, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                this.setPreparedStatement(pst,reserva);
                pst.executeUpdate();
                System.out.println("Se ha registrado la reserva...");
            }
        }
    }
    //Modificado - nombres de variables
    
    /*
    @Override
    public void actualizar(Reserva reserva) throws SQLException, IOException {
        String reservaSql = "UPDATE Reserva SET tipo_Reserva=?,  WHERE id_persona_reserva=?";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement pstConstancia = con.prepareStatement(personaSql)) {
                setPersonaParameters(pstConstancia, reserva);
                pstConstancia.setInt(10, reserva.getIdPersona());
                pstConstancia.executeUpdate();
            }

            try (PreparedStatement pstReserva = con.prepareStatement(reservaSql)) {
                pstReserva.setString(1, reserva.getTipoReserva().name());
                pstReserva.setInt(2, reserva.getIdPersona());
                pstReserva.executeUpdate();
            }
        }
    }
*/
    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "DELETE from Reserva WHERE num_reserva=?";
        try (Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            
            System.out.println("Se ha eliminado la reserva " + id);
        }
    }
    /*
    @Override
    public Reserva obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Persona p JOIN Reserva a ON p.id_persona = a.id_persona_reserva WHERE p.id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapReserva(rs);
            }
        }
        return null;
    }
*/
    @Override
    public Reserva obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Reserva r "
            + "JOIN Espacio e ON r.Espacio_id_espacio=e.id_espacio "
            + "JOIN Persona p ON r.Persona_id_persona=p.id_persona "
            + "JOIN Constancia c ON c.id_constancia=r.id_constancia_reserva "
            + "WHERE num_reserva=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) return mapReserva(rs);
                }
            }
        }
        return null;
    }

    

    private void setPreparedStatement(PreparedStatement pst, Reserva reserva) throws SQLException {
        pst.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioIni())));
        pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioFin())));
        pst.setDate(3, new Date(reserva.getFechaReserva().getTime()));
        Espacio espacio=reserva.getEspacio();
        pst.setInt(4, espacio.getIdEspacio());
        pst.setInt(5, reserva.getPersona().getIdPersona());
        pst.setInt(6, reserva.getIdConstancia());
    }
    public Reserva mapReserva(ResultSet rs) throws SQLException{
            Reserva reserva = new Reserva();
            reserva.setNumReserva(rs.getInt("num_reserva"));
            reserva.setHorarioIni(rs.getTime("horario_ini").toLocalTime());
            reserva.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
            reserva.setFechaReserva(rs.getDate("fecha_reserva"));
            return reserva;
        }
}
