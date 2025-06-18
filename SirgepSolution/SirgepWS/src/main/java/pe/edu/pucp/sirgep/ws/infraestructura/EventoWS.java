package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.business.ventas.impl.EntradaServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

@WebService(serviceName = "EventoWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EventoWS {
    private EventoServiceImpl eService;
    private FuncionServiceImpl fService;
    private EntradaServiceImpl entradasService;
    public EventoWS(){
        eService = new EventoServiceImpl();
        fService =  new FuncionServiceImpl();
        entradasService =  new EntradaServiceImpl();
    }
//    @WebMethod(operationName = "insertarEvento")
//    public int insertar(@WebParam(name = "espacio") Evento evento) {
//        try{
//            return eventoService.insertar(evento);
//        }
//        catch(Exception ex)
//        {
//            throw new RuntimeException("Error al insertar un evento: " + ex.getMessage());
//        }
//    }
    @WebMethod(operationName = "listarEvento")
    public List<Evento> listar() {
        try{
            List<Evento> eventos = eService.listar();
            return eventos;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar eventos: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarEventoPorDistrito")
    public List<Evento> listarEventoPorDistrito(@WebParam(name = "Id")int id) {
        try {
            return eService.listarPorDistrito(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar distritos: " + ex.getMessage());
        }
    }
//    @WebMethod(operationName = "buscarEvento")
//    public Evento buscar(@WebParam(name = "id") int id) {
//        try{
//            return eventoService.buscar(id);
//        }
//        catch(Exception ex){
//            throw new RuntimeException("Error al buscar el evento con id: " + id + " ... " + ex.getMessage());
//        }
//    }
    
    @WebMethod(operationName = "actualizarEvento")
    public boolean actualizarEvento(@WebParam(name = "evento") Evento evento) {
        try{
            return eService.actualizar(evento);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al actualizar el evento " + ex.getMessage());
        }
    }
//    @WebMethod(operationName = "eliminarLogico")
//    public boolean eliminar(@WebParam(name = "id") int id) {
//        try{
//            return eventoService.eliminarLogico(id);
//        }
//        catch(Exception ex){
//            throw new RuntimeException("Error al eliminar el evento con id: " + id + " ... " + ex.getMessage());
//        }
//    }
    
    @WebMethod(operationName = "buscarPorID")
    public Evento buscarPorID(@WebParam(name = "Id") int id) {
        Evento e =  eService.buscar(id);
        return e;
    }
    
    @WebMethod(operationName = "listarFuncionesDeEvento")
    public List<Funcion> listarFuncionesDeEvento(@WebParam(name = "Id") int id) {
        List <Funcion> funcionesGeneral = fService.listar();
        List <Funcion> funcionesEvento = new ArrayList<>();
        for (int i = 0; i < funcionesGeneral.size(); i++) {
            Funcion fAux = funcionesGeneral.get(i);
            if (fAux.getEvento().getIdEvento() == id){
                funcionesEvento.add(fAux);
            }
        }
//        Evento e =  eService.buscar(id);
        return funcionesEvento;
    }
    
    @WebMethod(operationName = "cantEntradasDisponibles")
    public int cantEntradasDisponibles(@WebParam(name = "IdFuncion") int id,
            @WebParam(name = "cantExFuncion") int cantEntradasPorFuncion){
        return entradasService.cantidadDispo(id, cantEntradasPorFuncion);
    }
}
