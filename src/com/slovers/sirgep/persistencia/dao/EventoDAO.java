package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Evento;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public interface EventoDAO {
    void insertar(Evento evento);
    void actualizar(Evento evento);
    void eliminar(int id);
    ArrayList<Evento> obtenerTodos();
    Evento obtenerPorId(int id);
}
