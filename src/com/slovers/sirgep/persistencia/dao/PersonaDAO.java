package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Persona;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author italo
 */
public interface PersonaDAO {
    void insertar(Persona persona) throws SQLException,IOException;
    void actualizar(Persona persona) throws SQLException,IOException;
    void eliminar(int idPersona) throws SQLException,IOException;
    Persona obtenerPorId(int idPersona) throws SQLException,IOException;
    ArrayList<Persona> obtenerTodos() throws SQLException,IOException;
}
