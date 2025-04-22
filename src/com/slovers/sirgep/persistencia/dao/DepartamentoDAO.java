package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Departamento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ana c
 */
public interface DepartamentoDAO {
    void insertar(Departamento departamento) throws SQLException, IOException;
    void actualizar(Departamento departamento) throws SQLException, IOException;
    void eliminar(int idDepartamento) throws SQLException, IOException;
    Departamento obtenerPorId(int idDepartamento) throws SQLException, IOException;
    ArrayList<Departamento> obtenerTodos() throws SQLException, IOException;
}