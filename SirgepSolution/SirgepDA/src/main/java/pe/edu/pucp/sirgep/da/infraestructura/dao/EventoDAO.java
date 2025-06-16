package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EventoDAO extends BaseDAO<Evento>{
    List<Evento> buscarPorTexto(String texto);
}