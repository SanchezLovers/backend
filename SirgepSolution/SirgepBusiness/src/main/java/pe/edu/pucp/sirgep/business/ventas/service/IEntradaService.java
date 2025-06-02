package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

public interface IEntradaService {
    int insertar(Entrada reserva);
    Entrada buscar(int id);
    List<Entrada> listar();
    boolean actualizar(Entrada entrada);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}