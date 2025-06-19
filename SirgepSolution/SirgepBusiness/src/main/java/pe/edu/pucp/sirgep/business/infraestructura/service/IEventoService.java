package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public interface IEventoService{
    int insertar(Evento evento);
    Evento buscar(int id);
    List<Evento> listar();
    boolean actualizar(Evento evento);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    List<Evento> listarPorDistrito(int id);
    List<Evento> buscarPorTexto(String texto);
    List<Evento> buscarEventosPorFechas(String inicio, String fin);
}