package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.ventas.Constancia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author darkJCdark (Jorge Chamorro)
 */
public interface ConstanciaDAO {
    void insertar(Constancia constancia) throws SQLException,IOException;
    void actualizar(Constancia constancia) throws SQLException,IOException;
    void eliminar(int idConstancia) throws SQLException,IOException;
    Constancia obtenerPorId(int idConstancia) throws SQLException,IOException;
    ArrayList<Constancia> obtenerTodos() throws SQLException,IOException;
}