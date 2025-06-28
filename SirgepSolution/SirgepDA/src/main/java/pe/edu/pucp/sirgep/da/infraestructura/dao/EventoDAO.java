package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EventoDAO extends BaseDAO<Evento>{
    List<Evento> buscarPorTexto(String texto);
    List<Evento> buscarEventosPorFechas(String inicio, String fin);
    Map<String, Object> listarEventosDTO(int idEvento);
}