package pe.edu.pucp.sirgep.ws.ubicacion;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.ubicacion.service.ProvinciaServiceImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

@WebService(serviceName = "ProvinciaWS", targetNamespace = "pe.edu.pucp.sirgep")
public class ProvinciaWS {
   private ProvinciaServiceImpl provinciaService;
    
    public ProvinciaWS(){
        provinciaService = new ProvinciaServiceImpl();
    }
    
    @WebMethod(operationName = "listarProvinciaPorDepa")
    public List<Provincia> listarProvinciaPorDepa(@WebParam(name = "Id")int id) {
        try {
            return provinciaService.listarPorDepartamento(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar depas: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarProvinciaPorId")
    public Provincia buscarProvPorId(@WebParam(name = "idProvincia") int idProvincia) {
        try {
            Provincia provincia = provinciaService.buscar(idProvincia); 
            return provincia;
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar una provincia por su ID: " + idProvincia + ex.getMessage());
        }
    }
}
