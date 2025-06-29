package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface FuncionDAO extends BaseDAO<Funcion>{
    List<Funcion> listarPorIdEvento(int idEvento);
    boolean inactivar();
}