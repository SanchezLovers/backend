package pe.edu.pucp.sirgep.da.usuarios.dao;

import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CompradorDAO {
    void insertar(Comprador comprador) throws SQLException,IOException;
    void actualizar(Comprador comprador) throws SQLException,IOException;
    void eliminar(int idPersona) throws SQLException,IOException;
    Comprador obtenerPorId(int idPersona) throws SQLException,IOException;
    ArrayList<Comprador> obtenerTodos() throws SQLException,IOException;
}