
package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EspacioServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

@WebService(serviceName = "EspacioWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EspacioWS {

    private final IEspacioService espacioService;
    
    public EspacioWS()
    {
        espacioService = new EspacioServiceImpl();
    }

    @WebMethod(operationName = "insertarEspacio")
    public int insertar(@WebParam(name = "espacio") Espacio espacio) {
        try{
            return espacioService.insertar(espacio);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al insertar un espacio: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "listarEspacio")
    public List<Espacio> listar() {
        try{
            List<Espacio> espacios = espacioService.listar();
            return espacios;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "buscarEspacio")
    public Espacio buscar(@WebParam(name = "id") int id) {
        try{
            return espacioService.buscar(id);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al buscar el espacio con id: " + id + " ... " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "actualizarEspacio")
    public boolean actualizar(@WebParam(name = "espacio") Espacio espacio) {
        try{
            return espacioService.actualizar(espacio);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al actualizar el espacio " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "eliminarLogico")
    public boolean eliminar(@WebParam(name = "id") int id) {
        try{
            return espacioService.eliminarLogico(id);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al eliminar el espacio con id: " + id + " ... " + ex.getMessage());
        }
    }
}
