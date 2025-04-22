package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Funcion;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public interface FuncionDAO {
    void insertar(Funcion funcion);
    void actualizar(Funcion funcion);
    void eliminar(int id);
    ArrayList<Funcion> obtenerTodos();
    Funcion obtenerPorId(int id);
}
