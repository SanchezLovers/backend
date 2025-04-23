package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.ventas.Entrada;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author darkJCdark (Jorge Chamorro)
 */
public interface EntradaDAO {
    void insertar(Entrada entrada) throws SQLException, IOException;
    void actualizar(Entrada entrada) throws SQLException, IOException;
    void eliminar(int idConstancia) throws SQLException, IOException;
    Entrada obtenerPorId(int idConstancia) throws SQLException, IOException;
    List<Entrada> obtenerTodos() throws SQLException, IOException;
}