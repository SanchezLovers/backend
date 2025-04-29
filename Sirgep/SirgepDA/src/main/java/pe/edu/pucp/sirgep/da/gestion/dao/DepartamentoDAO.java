package pe.edu.pucp.sirgep.da.gestion.dao;

import pe.edu.pucp.sirgep.domain.gestion.models.Departamento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DepartamentoDAO {
    void insertar(Departamento departamento) throws SQLException, IOException;
    void actualizar(Departamento departamento) throws SQLException, IOException;
    void eliminar(int idDepartamento) throws SQLException, IOException;
    Departamento obtenerPorId(int idDepartamento) throws SQLException, IOException;
    ArrayList<Departamento> obtenerTodos() throws SQLException, IOException;
}