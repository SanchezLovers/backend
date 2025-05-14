package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface IReservaService {
    int insertar(Reserva reserva);
    Reserva buscar(int id);
    List<Reserva> listar();
    boolean actualizar(Reserva reserva);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}