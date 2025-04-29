package pe.edu.pucp.sirgep.da.gestion.dao;

import pe.edu.pucp.sirgep.domain.gestion.models.Persona;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonaDAO {
    void insertar(Persona persona) throws SQLException,IOException;
    void actualizar(Persona persona) throws SQLException,IOException;
    void eliminar(int idPersona) throws SQLException,IOException;
    Persona obtenerPorId(int idPersona) throws SQLException,IOException;
    ArrayList<Persona> obtenerTodos() throws SQLException,IOException;
}