package pe.edu.pucp.sirgep.ws.ubicacion;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.ubicacion.service.DistritoServiceImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

@WebService(serviceName = "DistritoWS", targetNamespace = "pe.edu.pucp.sirgep")

public class DistritoWS {
    private DistritoServiceImpl dService;
    
    public DistritoWS(){
        dService = new DistritoServiceImpl();
    }
    
    @WebMethod(operationName = "buscarDistrito")
    public Distrito buscarDistrito(@WebParam(name = "Id")int id) {
        try {
            return dService.buscarDistrito(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar distrito: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarDistritosFiltrados")
    public List<Distrito> listarDistritos(@WebParam(name = "Id")int id) {
        try {
            return dService.listarPorDepartamento(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar distritos: " + ex.getMessage());
        }
    }
}