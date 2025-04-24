package com.slovers.sirgep.da.gestion.dao;

import com.slovers.sirgep.domain.gestion.models.Administrador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdministradorDAO {
    void insertar(Administrador admin) throws SQLException, IOException;
    void actualizar(Administrador admin) throws SQLException, IOException;
    void eliminar(int idPersona) throws SQLException, IOException;
    Administrador obtenerPorId(int idPersona) throws SQLException, IOException;
    List<Administrador> obtenerTodos() throws SQLException, IOException;
}