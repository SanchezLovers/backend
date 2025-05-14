package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

public interface IConstanciaService {
    int insertar(Constancia constancia);
    Constancia buscar(int id);
    List<Constancia> listar();
    boolean actualizar(Constancia constancia);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}