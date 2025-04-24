package com.slovers.sirgep.da.gestion.dao;

import com.slovers.sirgep.domain.gestion.models.Evento;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.IOException;

public interface EventoDAO {
    void insertar(Evento evento) throws SQLException, IOException;
    void actualizar(Evento evento) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Evento> obtenerTodos() throws SQLException, IOException;
    Evento obtenerPorId(int id) throws SQLException, IOException;
}