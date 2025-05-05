package pe.edu.pucp.sirgep.da.base.dao;

import java.util.List;

public interface BaseDAO<T> {
    int insertar(T entidad);
    T buscar(int id);
    List<T> listar();
    boolean actualizar(T entidad);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}