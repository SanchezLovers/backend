package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Espacio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author benny
 */
public interface EspacioDAO {
    void insertar(Espacio espacio) throws SQLException, IOException;
    void actualizar(Espacio espacio) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Espacio> obtenerTodos() throws SQLException, IOException;
    Espacio obtenerPorId(int id) throws SQLException, IOException;
}