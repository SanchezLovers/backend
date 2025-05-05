package pe.edu.pucp.sirgep.da.infraestructura.dao;

import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.IOException;

public interface FuncionDAO {
    void insertar(Funcion funcion) throws SQLException, IOException;
    void actualizar(Funcion funcion) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Funcion> obtenerTodos() throws SQLException, IOException;
    Funcion obtenerPorId(int id) throws SQLException, IOException;
}