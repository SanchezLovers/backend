package com.slovers.sirgep.da.ventas.dao;

import com.slovers.sirgep.domain.ventas.models.Reserva;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservaDAO {
    void insertar(Reserva reserva) throws SQLException,IOException;
    void actualizar(Reserva reserva) throws SQLException,IOException;
    void eliminar(int idConstancia) throws SQLException,IOException;
    Reserva obtenerPorId(int idConstancia) throws SQLException,IOException;
    ArrayList<Reserva> obtenerTodos() throws SQLException,IOException;
}