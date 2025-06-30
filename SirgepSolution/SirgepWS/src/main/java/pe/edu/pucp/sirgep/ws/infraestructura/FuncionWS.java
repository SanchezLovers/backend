package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IFuncionService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

@WebService(serviceName = "FuncionWS", targetNamespace = "pe.edu.pucp.sirgep")
public class FuncionWS {
    private final IFuncionService funcionService;
    
    public FuncionWS(){
        funcionService = new FuncionServiceImpl();
    }
    
    // insertar función
    @WebMethod(operationName = "insertarFuncion")
    public int insertarFuncion(@WebParam(name = "funcion") Funcion funcion) {
        try{
            return funcionService.insertar(funcion);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("ERROR al insertar una FUNCION: " + ex.getMessage());
        }
    }
    
    // lista funciones de un Evento mediante el idEvento
    @WebMethod(operationName = "listarFuncionesPorIdEvento")
    public List<Funcion> listarFuncionesPorIdEvento(@WebParam(name = "idEvento") int idEvento){
        try{
            return funcionService.listarPorIdEvento(idEvento);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR al listar funciones por idEvento " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "buscarFuncionId")
    public Funcion buscarFuncionId(@WebParam(name = "idFuncion") int idFuncion){
        return funcionService.buscar(idFuncion);
    }
}
