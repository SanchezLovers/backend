package com.slovers.sirgep.da.gestion.dao;

import com.slovers.sirgep.domain.gestion.models.Distrito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DistritoDAO {
    void insertar(Distrito distrito) throws SQLException, IOException;
    void actualizar(Distrito distrito) throws SQLException, IOException;
    void eliminar(int idDistrito) throws SQLException, IOException;
    Distrito obtenerPorId(int idDistrito) throws SQLException, IOException;
    ArrayList<Distrito> obtenerTodos() throws SQLException, IOException;
}