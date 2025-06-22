package pe.edu.pucp.sirgep.da.ubicacion.dao;

import java.util.List;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface ProvinciaDAO extends BaseDAO<Provincia>{

    public List<Provincia> listarPorDepa(int idDepartamento);
    
}