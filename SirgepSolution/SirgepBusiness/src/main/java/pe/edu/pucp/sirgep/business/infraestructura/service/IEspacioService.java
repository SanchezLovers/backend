package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

public interface IEspacioService {
    int insertar(Espacio espacio);
    Espacio buscar(int id);
    List<Espacio> listar();
    boolean actualizar(Espacio espacio);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    List<Espacio> buscarPorTexto(String texto);
}