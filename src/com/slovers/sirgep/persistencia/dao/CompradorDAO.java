package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Comprador;

public interface CompradorDAO {
    void insertar(Comprador comprador);
    void actualizar(Comprador comprador);
    void eliminar(int idPersona);
    Comprador obtenerPorId(int idPersona);
    List<Comprador> obtenerTodos();
}
