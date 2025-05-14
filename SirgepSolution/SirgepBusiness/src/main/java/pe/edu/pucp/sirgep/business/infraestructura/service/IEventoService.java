package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public interface IEventoService {
    int insertar(Evento espacio);
    Evento buscar(int id);
    List<Evento> listar();
    boolean actualizar(Evento espacio);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}