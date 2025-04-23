package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.EEstadoReserva;
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
public class ReservaMySQL implements ReservaDAO {

    @Override
    public void insertar(Reserva reserva) throws SQLException, IOException {
        //Anteriormente debí insertar la Constancia que es la clase padre
        String sql = "INSERT INTO Reserva"
                + "(horario_ini, horario_fin, estado, fecha_reserva, Espacio_id_espacio, Persona_id_persona, id_constancia_reserva, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                this.setPreparedStatement(pst,reserva);
                pst.executeUpdate();
                System.out.println("Se ha registrado la reserva...");
            }
        }
    }
/*
    @Override
    public void actualizar(Reserva reserva) throws SQLException, IOException {
        String personaSql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_persona=?";
        String reservaSql = "UPDATE Reserva SET tipo_Reserva=? WHERE id_persona_reserva=?";

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

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Persona SET activo=0 WHERE id_persona=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

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

    @Override
    public ArrayList<Reserva> obtenerTodos() throws SQLException, IOException {
        ArrayList<Reserva> Reservaes = new ArrayList<>();
        String sql = "SELECT * FROM Persona p JOIN Reserva a ON p.id_persona = a.id_persona_reserva WHERE p.activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Reservaes.add(mapReserva(rs));
            }
        }
        return Reservaes;
    }
*/
    private void setPreparedStatement(PreparedStatement pst, Reserva reserva) throws SQLException {
        pst.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioIni())));
        pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioFin())));
        pst.setString(3, reserva.getEstado().name());
        pst.setDate(4, new Date(reserva.getFechaReserva().getTime()));
        Espacio espacio=reserva.getEspacio();
        pst.setInt(5, espacio.getIdEspacio());
        Persona persona=reserva.getPersona();
        pst.setInt(6, persona.getIdPersona());
        pst.setInt(7, reserva.getIdConstancia());
    }
/*
    private Reserva mapReserva(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setIdConstancia(rs.getInt("id_constancia"));
        reserva.setFecha(rs.getDate("fecha"));
        reserva.setMetodoPago(EMetodoPago.valueOf(rs.getString("metodo_pago")));
        reserva.setTotal(rs.getDouble("total"));
        reserva.setDetallePago(rs.getString("detalle_pago"));
        
        return reserva;
    }
*/
}
