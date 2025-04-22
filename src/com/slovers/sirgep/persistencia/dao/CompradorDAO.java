package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.ventas.Comprador;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface CompradorDAO {
    void insertar(Comprador comprador) throws SQLException,IOException;
    void actualizar(Comprador comprador) throws SQLException,IOException;
    void eliminar(int idPersona) throws SQLException,IOException;
    Comprador obtenerPorId(int idPersona) throws SQLException,IOException;
    List<Comprador> obtenerTodos() throws SQLException,IOException;
}
