package pe.edu.pucp.sirgep.da.gestion.dao;

import pe.edu.pucp.sirgep.domain.gestion.models.Espacio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EspacioDAO {
    void insertar(Espacio espacio) throws SQLException, IOException;
    void actualizar(Espacio espacio) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Espacio> obtenerTodos() throws SQLException, IOException;
    Espacio obtenerPorId(int id) throws SQLException, IOException;
}