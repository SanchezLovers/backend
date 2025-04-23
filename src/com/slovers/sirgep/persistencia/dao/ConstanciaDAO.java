package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.ventas.Constancia;
import java.util.ArrayList;

/**
 * @author darkJCdark (Jorge Chamorro)
 */
public interface ConstanciaDAO {
    int insertar(Constancia constancia);
    int actualizar(Constancia constancia);
    int eliminar(int idConstancia);
    Constancia obtenerPorId(int idConstancia);
    ArrayList<Constancia> obtenerTodos();
}