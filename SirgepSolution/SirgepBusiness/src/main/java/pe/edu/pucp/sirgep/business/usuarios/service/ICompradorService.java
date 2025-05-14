package pe.edu.pucp.sirgep.business.usuarios.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public interface ICompradorService {
    int insertar(Comprador comprador);
    Comprador buscar(int id);
    List<Comprador> listar();
    boolean actualizar(Comprador comprador);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
}
