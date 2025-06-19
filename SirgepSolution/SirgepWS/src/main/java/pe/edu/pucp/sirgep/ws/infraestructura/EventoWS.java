package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
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
    @WebMethod(operationName = "insertarEvento")
    public int insertarEvento(@WebParam(name = "evento") Evento evento) {
        try{
            return eService.insertar(evento);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al insertar un evento: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "listarEvento")
    public List<Evento> listarEvento() {
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
    
    @WebMethod(operationName = "buscarEventoPorTexto")
    public List<Evento> buscarPorTextoEvento(@WebParam(name = "texto") String texto) {
        try{
            return eService.buscarPorTexto(texto);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR al buscar el EVENTO mediante un texto: "+ ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarEventosPorFechas")
    public List<Evento> buscarEventosPorFechas(@WebParam(name = "fecha_inicio") String inicio, @WebParam(name = "fecha_fin") String fin){
        try{
            return eService.buscarEventosPorFechas(inicio, fin);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR al LISTAR EVENTOS POR FECHAS: ... " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarEventoPorID")
    public Evento buscarEventoPorID(@WebParam(name = "id") int id) {
        try{
            return eService.buscar(id);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al buscar el evento con id: " + id + " ... " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarEvento")
    public boolean actualizarEvento(@WebParam(name = "evento") Evento evento) {
        try{
            return eService.actualizar(evento);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR al actualizar el evento " + ex.getMessage());
        }
    }
    
    public boolean eliminarFuncionesDeEvento(int id){
        List<Funcion> funcionesEvento = fService.listar();
        for(Funcion f : funcionesEvento){
            if(f.getEvento().getIdEvento() == id){
                // pertenece al evento a eliminar
                boolean eliminado = fService.eliminarLogico(f.getIdFuncion()); // eliminamos la función
                if(!eliminado) return false;
            }
        }
        return true;
    }
    
    @WebMethod(operationName = "eliminarLogico")
    public boolean eliminarEvento(@WebParam(name = "id") int id) {
        try{
            boolean eliminarFunciones = eliminarFuncionesDeEvento(id);
            if(!eliminarFunciones) return false;
            boolean eliminarEvento = eService.eliminarLogico(id);
            return eliminarEvento;
        }
        catch(Exception ex){
            throw new RuntimeException("Error al eliminar el evento con id: " + id + " ... " + ex.getMessage());
        }
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

    public int obtenerCantEntradasDisponibles(@WebParam(name = "IdFuncion") int id,
            @WebParam(name = "obtenerCantEDisponibles") int cantEntradasPorFuncion){
        return entradasService.cantidadDispo(id, cantEntradasPorFuncion);
    }
}
