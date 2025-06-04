package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FuncionImpl extends BaseImpl<Funcion> implements FuncionDAO {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Funcion(hora_inicio, hora_fin, Evento_idEvento, fecha)"
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
            ps.setDate(1, Date.valueOf(f.getHoraInicio().toString()));
            ps.setDate(2, Date.valueOf(f.getHoraFin().toString()));
            ps.setInt(3, f.getEvento().getIdEvento());
            ps.setDate(4, Date.valueOf(f.getFecha().toString()));
        } catch (SQLException ex) {
            throw new RuntimeException("Error al asignar parámetros de inserción para Funcion", ex);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Funcion f) {
        try {
            ps.setDate(1, Date.valueOf(f.getHoraInicio().toString()));
            ps.setDate(2, Date.valueOf(f.getHoraFin().toString()));
            ps.setDate(3, Date.valueOf(f.getFecha().toString()));
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
            String fechaHoraStr = rs.getString("hora_inicio");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt = LocalDateTime.parse(fechaHoraStr, formatter);
//            System.out.println("hora_inicio como String: " + fechaHoraStr);
            f.setHoraInicio(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
//            f.setHoraFin(rs.getTimestamp("hora_fin"));
            f.setFecha(rs.getDate("fecha"));

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
}
