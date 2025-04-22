package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Provincia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ana c
 */
public interface ProvinciaDAO {
    void insertar(Provincia provincia) throws SQLException, IOException;
    void actualizar(Provincia provincia) throws SQLException, IOException;
    void eliminar(int idProvincia) throws SQLException, IOException;
    Provincia obtenerPorId(int idProvincia) throws SQLException, IOException;
    ArrayList<Provincia> obtenerTodos() throws SQLException, IOException;
}
