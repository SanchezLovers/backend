package pe.edu.pucp.sirgep.da.ubicacion.dao;
import java.util.List;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;


public interface DistritoDAO extends BaseDAO<Distrito>{

    public List<Distrito> listarPorProv(int idProvincia);
    public Distrito buscarDistritoCompleto(int idDistrito);

}