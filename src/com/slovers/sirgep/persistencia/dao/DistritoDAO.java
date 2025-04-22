package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Distrito;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author ana c
 */

public interface DistritoDAO {
    void insertar(Distrito distrito) throws SQLException, IOException;
    void actualizar(Distrito distrito) throws SQLException, IOException;
    void eliminar(int idDistrito) throws SQLException, IOException;
    Distrito obtenerPorId(int idDistrito) throws SQLException, IOException;
    ArrayList<Distrito> obtenerTodos() throws SQLException, IOException;
}
