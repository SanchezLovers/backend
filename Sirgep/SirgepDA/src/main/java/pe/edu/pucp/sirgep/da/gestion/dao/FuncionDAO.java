package pe.edu.pucp.sirgep.da.gestion.dao;

import pe.edu.pucp.sirgep.domain.gestion.models.Funcion;
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