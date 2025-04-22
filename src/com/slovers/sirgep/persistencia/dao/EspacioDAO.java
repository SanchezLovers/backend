package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Espacio;
import java.util.ArrayList;

/**
 *
 * @author benny
 */
public interface EspacioDAO {
    void insertar(Espacio espacio);
    void actualizar(Espacio espacio);
    void eliminar(int id);
    ArrayList<Espacio> obtenerTodos();
    Espacio obtenerPorId(int id);
}