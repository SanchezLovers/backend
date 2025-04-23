package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.ventas.Reserva;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author darkJCdark (Jorge Chamorro)
 */
public interface ReservaDAO {
    void insertar(Reserva reserva) throws SQLException,IOException;/*
    void actualizar(Reserva reserva) throws SQLException,IOException;
    void eliminar(int idConstancia) throws SQLException,IOException;
    Reserva obtenerPorId(int idConstancia) throws SQLException,IOException;
    ArrayList<Reserva> obtenerTodos() throws SQLException,IOException;*/
}