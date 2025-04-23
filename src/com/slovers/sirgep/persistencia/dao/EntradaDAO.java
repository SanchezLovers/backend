package com.slovers.sirgep.persistencia.dao;
import com.slovers.sirgep.dominio.models.ventas.Entrada;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */

public interface EntradaDAO {
    void insertar(Entrada entrada) throws SQLException, IOException;
    void actualizar(Entrada entrada) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Entrada> obtenerTodos() throws SQLException, IOException;
    Entrada obtenerPorId(int id) throws SQLException, IOException;
}
