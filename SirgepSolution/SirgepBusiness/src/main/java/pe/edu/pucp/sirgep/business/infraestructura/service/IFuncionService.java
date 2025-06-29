package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

public interface IFuncionService {
    int insertar(Funcion funcion);
    Funcion buscar(int id);
    List<Funcion> listar();
    List<Funcion> listarPorIdEvento(int idEvento);
    boolean actualizar(Funcion funcion);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    boolean inactivar();
}