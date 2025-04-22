package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Persona;

public interface PersonaDAO {
    void insertar(Persona persona);
    void actualizar(Persona persona);
    void eliminar(int idPersona);
    Persona obtenerPorId(int idPersona);
    List<Persona> obtenerTodos();
}
