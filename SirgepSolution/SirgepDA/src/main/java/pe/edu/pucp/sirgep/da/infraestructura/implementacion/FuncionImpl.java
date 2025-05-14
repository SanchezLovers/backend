package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;


public class FuncionImpl extends BaseImpl<Funcion>implements FuncionDAO{
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Funcion(hora_inicio, hora_fin, Evento_idEvento, activo)"
                + " VALUES(?, ?, ?, 'A')";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT id_funcion, hora_inicio, hora_fin, Evento_idEvento FROM "
                + "Funcion WHERE id_funcion = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id_funcion, hora_inicio, hora_fin, Evento_idEvento FROM Funcion";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Funcion SET hora_inicio = ?, hora_fin = ?, "
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
            ps.setTime(1, Time.valueOf(f.getHoraInicio()));
            ps.setTime(2, Time.valueOf(f.getHoraFin()));
            ps.setInt(3, f.getEvento().getIdEvento());
        } catch (SQLException ex) {
            throw new RuntimeException("Error al asignar parámetros de inserción para Funcion", ex);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Funcion f) {
        try {
            ps.setTime(1, Time.valueOf(f.getHoraInicio()));
            ps.setTime(2, Time.valueOf(f.getHoraFin()));
            ps.setInt(3, f.getEvento().getIdEvento());
            ps.setInt(4, f.getIdFuncion());
        } catch (SQLException ex) {
            throw new RuntimeException("Error al asignar parámetros de actualización para Funcion", ex);
        }
    }

    @Override
    protected Funcion createFromResultSet(ResultSet rs) {
        try {
            Funcion f = new Funcion();
            f.setIdFuncion(rs.getInt("id_funcion"));
            f.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
            f.setHoraFin(rs.getTime("hora_fin").toLocalTime());

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