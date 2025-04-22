package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Persona;

public interface PersonaDAO {
    int insertar(Persona persona);
    int actualizar(Persona persona);
    int eliminar(int idPersona);
    Persona obtenerPorId(int idPersona);
}
