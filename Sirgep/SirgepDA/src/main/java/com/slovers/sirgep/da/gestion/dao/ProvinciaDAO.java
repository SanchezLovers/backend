package com.slovers.sirgep.da.gestion.dao;

import com.slovers.sirgep.domain.gestion.models.Provincia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProvinciaDAO {
    void insertar(Provincia provincia) throws SQLException, IOException;
    void actualizar(Provincia provincia) throws SQLException, IOException;
    void eliminar(int idProvincia) throws SQLException, IOException;
    Provincia obtenerPorId(int idProvincia) throws SQLException, IOException;
    ArrayList<Provincia> obtenerTodos() throws SQLException, IOException;
}