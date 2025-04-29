package pe.edu.pucp.sirgep.da.base;

import java.util.List;

public interface BaseDAO<T> {
    void agregar(T entidad);
    T obtener(Integer id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(Integer id);
}