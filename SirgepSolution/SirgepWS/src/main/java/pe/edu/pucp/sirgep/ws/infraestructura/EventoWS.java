package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

@WebService(serviceName = "EventoWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EventoWS {
    
    private IEventoService eventoService;
    
    public EventoWS(){
        eventoService = new EventoServiceImpl();
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
            List<Evento> eventos = eventoService.listar();
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
            return eventoService.listarPorDistrito(id);
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
    /*
    @WebMethod(operationName = "actualizarEvento")
    public boolean actualizar(@WebParam(name = "evento") Evento evento) {
        try{
//            eventoService.
            return eventoService.actualizar(evento);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al actualizar el evento " + ex.getMessage());
        }
    }
    */
    
//    @WebMethod(operationName = "eliminarLogico")
//    public boolean eliminar(@WebParam(name = "id") int id) {
//        try{
//            return eventoService.eliminarLogico(id);
//        }
//        catch(Exception ex){
//            throw new RuntimeException("Error al eliminar el evento con id: " + id + " ... " + ex.getMessage());
//        }
//    }
}
