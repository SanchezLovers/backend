package com.slovers.sirgep.persistencia.dao;

import com.slovers.sirgep.dominio.models.gestion.Administrador;
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
