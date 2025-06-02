package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.impl.EntradaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

@WebService(serviceName = "EntradaWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EntradaWS {
    private final IEntradaService entradaService;
    
    public EntradaWS(){
        entradaService = new EntradaServiceImpl();
    }

    @WebMethod(operationName = "insertarEntrada")
    public int insertarEntrada(@WebParam(name = "entrada") Entrada entrada) {
        int id=-1;
        try {
            return entradaService.insertar(entrada);
        } catch (Exception ex) {
            throw new WebServiceException("Error al insertar entrada: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "buscarEntrada") 
    public Entrada buscarEntrada(@WebParam(name = "idEntrada") int idEntrada) {
        try {
            return entradaService.buscar(idEntrada);
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar entrada: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarVentas")
    public List<Entrada> listarEntrada() {
        try {
            return entradaService.listar();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar entrada: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarEntrada")
    public boolean actualizarEntrada(@WebParam(name = "entrada") Entrada entrada) {
        boolean resultado=false;
        try {
            return entradaService.actualizar(entrada);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar entrada: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarLogicoEntrada")
    public boolean eliminarLogicoEntrada(@WebParam(name = "idEntrada") int idEntrada){
        boolean resultado=false;
        try {
            return entradaService.eliminarLogico(idEntrada);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar logicamente entrada: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarFisicoEntrada")
    public boolean eliminarFisicoEntrada(@WebParam(name = "idEntrada") int idEntrada){
        boolean resultado=false;
        try {
            return entradaService.eliminarFisico(idEntrada);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar fisicamente entrada: " + ex.getMessage());
        }
    }
}