package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.business.usuarios.impl.CompradorServiceImpl;
import pe.edu.pucp.sirgep.business.usuarios.service.ICompradorService;
import pe.edu.pucp.sirgep.business.ventas.impl.ConstanciaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IConstanciaService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

@WebService(serviceName = "CompraWS", targetNamespace = "pe.edu.pucp.sirgep")
public class CompraWS {
    //private IEntradaService iEntradaService;
    //private IReservaService iReservaService;
    private IConstanciaService iConstanciaService;
    private IEventoService iEventoService;
    private ICompradorService iCompradorService;
    
    public CompraWS(){
        //iEntradaService = new EntradaServiceImpl();
        //iReservaService = new ReservaServiceImpl();
        iConstanciaService = new ConstanciaServiceImpl();
        iEventoService = new EventoServiceImpl();
        iCompradorService = new CompradorServiceImpl();
        
    }
    
    //actualiza o insertar buscar compr
    //insertar constancia
    @WebMethod(operationName = "buscarEventos")
    public Evento buscarEventos(int id) {
        return iEventoService.buscar(id);
    }
    
    @WebMethod(operationName = "insertarComprador")
    public int insertarComprador(Comprador comprador) {
        return iCompradorService.insertar(comprador);
    }
    
    @WebMethod(operationName = "buscarComprador")
    public Comprador buscarComprador(int id) {
        return iCompradorService.buscar(id);
    }
    
    @WebMethod(operationName = "actualizarComprador")
    public boolean actualizarComprador(Comprador comprador) {
        return iCompradorService.actualizar(comprador);
    }
    
    @WebMethod(operationName = "insertarConstancia")
    public int insertarConstancia(Constancia constancia) {
        return iConstanciaService.insertar(constancia);
    }
    
    @WebMethod(operationName = "buscarCompradorPorDni")
    public Comprador buscarCompradorPorDni(String dni) {
        return iCompradorService.buscarPorDni(dni);
    }
}