package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Evento;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.IOException;

/**
 *
 * @author willi
 */
public interface EventoDAO {
    void insertar(Evento evento) throws SQLException, IOException;
    void actualizar(Evento evento) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Evento> obtenerTodos() throws SQLException, IOException;
    Evento obtenerPorId(int id) throws SQLException, IOException;
}
