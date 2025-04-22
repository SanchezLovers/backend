package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Administrador;

public interface AdministradorDAO {
    void insertar(Administrador admin);
    void actualizar(Administrador admin);
    void eliminar(int idPersona);
    Administrador obtenerPorId(int idPersona);
    List<Administrador> obtenerTodos();
}
