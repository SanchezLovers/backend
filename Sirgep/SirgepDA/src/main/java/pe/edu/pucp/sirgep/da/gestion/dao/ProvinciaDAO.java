package pe.edu.pucp.sirgep.da.gestion.dao;

import pe.edu.pucp.sirgep.domain.gestion.models.Provincia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProvinciaDAO {
    void insertar(Provincia provincia) throws SQLException, IOException;
    void actualizar(Provincia provincia) throws SQLException, IOException;
    void eliminar(int idProvincia) throws SQLException, IOException;
    Provincia obtenerPorId(int idProvincia) throws SQLException, IOException;
    ArrayList<Provincia> obtenerTodos() throws SQLException, IOException;
}