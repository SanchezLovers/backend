package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface IReservaService {
    //Metodos CRUD
    int insertar(Reserva reserva);
    Reserva buscar(int id);
    List<Reserva> listar();
    boolean actualizar(Reserva reserva);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    
    //Metodos adicionales
    public Comprador buscarCompradorDeReserva(int idComprador);
    public Espacio buscarEspacioDeReserva(int idEspacio);
    public Distrito buscarDistritoDeReserva(int idEntrada);
}