package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EspacioDAO extends BaseDAO<Espacio>{
    List<Espacio> buscarPorTexto(String texto);
    List<Espacio> buscarPorDistrito(int idDist);
    List<Espacio> buscarPorCategoria(String categ);
    List<Espacio> buscarPorDistritoCategoria(int idDist, String categoria);
}