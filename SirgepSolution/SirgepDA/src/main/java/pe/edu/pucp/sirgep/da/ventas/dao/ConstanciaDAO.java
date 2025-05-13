package pe.edu.pucp.sirgep.da.ventas.dao;

import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

import java.util.ArrayList;

public interface ConstanciaDAO {
    int insertar(Constancia constancia);
    int actualizar(Constancia constancia);
    int eliminar(int idConstancia);
    Constancia obtenerPorId(int idConstancia);
    ArrayList<Constancia> obtenerTodosActivos();
}