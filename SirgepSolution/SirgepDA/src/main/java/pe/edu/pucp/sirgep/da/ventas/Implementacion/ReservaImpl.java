package pe.edu.pucp.sirgep.da.ventas.Implementacion;

import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservaImpl implements ReservaDAO{
    @Override
    public void actualizar(Reserva reserva) throws SQLException, IOException {
        String sql = "UPDATE Reserva SET "
                + "horario_ini=?, horario_fin=?, fecha_reserva=?, Espacio_id_espacio=?, Persona_id_persona=?, id_constancia_reserva=? "
                + "WHERE id_constancia_reserva=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioIni())));
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), reserva.getHorarioFin())));
            ps.setDate(3, new Date(reserva.getFechaReserva().getTime()));
            ps.setInt(4, reserva.getEspacio().getIdEspacio());
            ps.setInt(5, reserva.getPersona().getIdPersona());
            ps.setInt(6, reserva.getIdConstancia());
            ps.setInt(7, reserva.getIdConstancia());
            ps.executeUpdate();
        }
    }

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

    @Override
    public ArrayList<Reserva> obtenerTodos() throws SQLException, IOException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM Persona p JOIN Reserva a ON p.id_persona = a.Persona_id_persona";

        try(Connection con = DBManager.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);) {        
            while(rs.next()){
                Reserva reserva = mapReserva(rs);
                reservas.add(reserva);
            }
        } 
        return reservas;
    }

    private Reserva mapReserva(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setIdConstancia(rs.getInt("id_constancia_reserva"));
        reserva.setFecha(rs.getDate("fecha_reserva"));
        reserva.setHorarioIni(rs.getTime("horario_ini").toLocalTime());
        reserva.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
//        reserva.setMetodoPago(EMetodoPago.valueOf(rs.getString("metodo_pago")));
//        reserva.setTotal(rs.getDouble("total"));
//        reserva.setDetallePago(rs.getString("detalle_pago"));
//        
        return reserva;
    }

//            try (PreparedStatement pstReserva = con.prepareStatement(reservaSql)) {
//                pstReserva.setString(1, reserva.getTipoReserva().name());
//                pstReserva.setInt(2, reserva.getIdPersona());
//                pstReserva.executeUpdate();
//            }
//        }
//    }

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
}