/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

/**
 *
 * @author LEGION
 */
@WebService(serviceName = "EventoWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EventoWSAnaG {
    private EventoServiceImpl eService;
    private FuncionServiceImpl fService;
    
    public EventoWSAnaG(){
        eService = new EventoServiceImpl();
        fService =  new FuncionServiceImpl();
    }
    
    @WebMethod(operationName = "listarPorID")
    public Evento listarPorID(@WebParam(name = "Id") int id) {
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
}
