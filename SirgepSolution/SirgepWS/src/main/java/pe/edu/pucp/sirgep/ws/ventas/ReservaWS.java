package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleReserva;

import pe.edu.pucp.sirgep.business.ventas.impl.ReservaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IReservaService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

@WebService(serviceName = "ReservaWS", targetNamespace = "pe.edu.pucp.sirgep")
public class ReservaWS {
    private final IReservaService reservaService;
    
    public ReservaWS(){
        reservaService = new ReservaServiceImpl();
    }
    
    //Metodos CRUD
    @WebMethod(operationName = "insertarReserva")
    public int insertarReserva(@WebParam(name = "reserva") Reserva reserva) {
        int id=-1;
        try {
            return reservaService.insertar(reserva);
        } catch (Exception ex) {
            throw new WebServiceException("Error al insertar reserva: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "buscarReserva") 
    public Reserva buscarReserva(@WebParam(name = "idReserva") int idReserva) {
        try {
            return reservaService.buscar(idReserva);
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar reserva: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarReservas")
    public List<Reserva> listarReserva() {
        try {
            return reservaService.listar();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar reserva: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarReserva")
    public boolean actualizarReserva(@WebParam(name = "reserva") Reserva reserva) {
        boolean resultado=false;
        try {
            return reservaService.actualizar(reserva);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar reserva: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarLogicoReserva")
    public boolean eliminarLogicoReserva(@WebParam(name = "idReserva") int idReserva){
        boolean resultado=false;
        try {
            return reservaService.eliminarLogico(idReserva);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar logicamente reserva: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarFisicoReserva")
    public boolean eliminarFisicoReserva(@WebParam(name = "idReserva") int idReserva){
        boolean resultado=false;
        try {
            return reservaService.eliminarFisico(idReserva);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar fisicamente reserva: " + ex.getMessage());
        }
    }
    
    //Metodos adicionales
    @WebMethod(operationName = "buscarCompradorDeReserva")
    public Comprador buscarCompradorDeReserva(@WebParam(name = "idComprador") int idComprador){
        Comprador resultado=null;
        try {
            resultado=reservaService.buscarCompradorDeReserva(idComprador);
            if(resultado==null){
                throw new RuntimeException("Comprador de la reserva no encontradoa");
            }
            return resultado;
        }  catch (Exception ex) {
                throw new RuntimeException("Error interno al buscar la espacio de la reserva: "+ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarEspacioDeReserva")
    public Espacio buscarEspacioDeReserva(@WebParam(name = "idEspacio") int idEspacio){
        Espacio resultado=null;
        try {
            resultado=reservaService.buscarEspacioDeReserva(idEspacio);
            if(resultado==null){
                throw new RuntimeException("Espacio de la reserva no encontrada");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la espacio de la reserva: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarDistritoDeReserva")
    public Distrito buscarDistritoDeReserva(@WebParam(name = "idReserva") int idReserva){
        Distrito resultado=null;
        try {
            resultado=reservaService.buscarDistritoDeReserva(idReserva);
            if(resultado==null){
                throw new RuntimeException("Distrito de la reserva no encontrado");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la reserva: " + ex.getMessage());
        }
    }
    
    //Metodo para crear libro de Excel para las reservas
    @WebMethod(operationName = "crearLibroExcelReservas")
    public void crearLibroExcelReservas(@WebParam(name = "idComprador")int idComprador){
        try {
            reservaService.crearLibroExcelReservas(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al exportar el libro excel de las reservas: : " + ex.getMessage());
        }
    }
    
    //Metodo para listar el detalle de las reservas
    @WebMethod(operationName = "listarDetalleReservasPorComprador")
    public List<DetalleReserva> listarDetalleReservasPorComprador(@WebParam(name = "idComprador")int idComprador){
        try {
            return reservaService.listarDetalleReservasPorComprador(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al listar el detalle de las entradas del comprador : " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarPorFecha")
    public List<Reserva> listarEventoPorFecha(@WebParam(name = "fecha")String fechaSt,
            @WebParam(name = "activo")boolean activo) {
        try {
            //Convertimos la fecha obtenida de string a date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaSt);
            return reservaService.listarPorFecha(fecha,activo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por fecha: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarReservaPorHorario")
    public List<Reserva> listarPorHorario(@WebParam(name = "horaInicio")String horaInicio, 
            @WebParam(name = "horaFin")String horaFin, @WebParam(name = "fecha")String fechaSt, 
            @WebParam(name = "activo")boolean activo) {
        try {
            //Convertimos la fecha obtenida de string a date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaSt);
            return reservaService.listarPorHorario(horaInicio,horaFin,fecha,activo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por horario: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarReservaPorDistrito")
    public List<Reserva> listarEventoPorDistrito(@WebParam(name = "idDistrito")int id,
            @WebParam(name = "activo")boolean activo) {
        try {
            return reservaService.listarPorDistrito(id,activo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por distritos: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarReservaPorEspacio")
    public List<Reserva> listarPorEspacio(@WebParam(name = "idEspacio")int id,
            @WebParam(name = "activo")boolean activo) {
        try {
            return reservaService.listarPorEspacio(id,activo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por espacios: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarReservaPorPersona")
    public List<Reserva> listarPorPersona(@WebParam(name = "idPersona")int id,
            @WebParam(name = "activo")boolean activo) {
        try {
            return reservaService.listarPorPersona(id,activo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por persona: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "cancelarReserva")
    public boolean cancelarReserva(@WebParam(name = "idReserva")int id){
        try {
            return reservaService.cancelarReserva(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar por persona: " + ex.getMessage());
        }
    }
}