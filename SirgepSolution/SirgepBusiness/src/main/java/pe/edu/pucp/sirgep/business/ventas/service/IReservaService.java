package pe.edu.pucp.sirgep.business.ventas.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaReservaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleReservaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.ReservaDTO;
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
    boolean inactivar();
    
    //Metodos adicionales
    public Comprador buscarCompradorDeReserva(int idComprador);
    public Espacio buscarEspacioDeReserva(int idEspacio);
    public Distrito buscarDistritoDeReserva(int idEntrada);
    public boolean cancelarReserva(int id) throws SQLException;
    public List<Reserva> listarPorMesYAnio(int mes, int anio);

    //Metodos adicionales para el listado de reservas
    public List<ReservaDTO> listarTodos();
    public List<ReservaDTO> listarDetalleReservasPorFecha(Date fecha, boolean activo);
    public List<ReservaDTO> listarPorDistrito(int id_distrito, boolean activo);
    public boolean crearLibroExcelReservas(int idComprador,String fechaInicio, String fechaFin, String estado);
    public ConstanciaReservaDTO buscarConstanciaReserva(int numReserva);
    List<DetalleReservaDTO> listarPorComprador(int idComprador,String fechaInicio, String fechaFin, String estado);
}