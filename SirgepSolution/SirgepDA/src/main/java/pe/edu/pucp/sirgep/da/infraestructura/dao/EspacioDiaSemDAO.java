package pe.edu.pucp.sirgep.da.infraestructura.dao;
import java.util.List;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

public interface EspacioDiaSemDAO extends BaseDAO<EspacioDiaSem> {
    boolean insertarDia(EspacioDiaSem entity);
    List<EspacioDiaSem> listarPorEspacio(int idEspacio);
}
