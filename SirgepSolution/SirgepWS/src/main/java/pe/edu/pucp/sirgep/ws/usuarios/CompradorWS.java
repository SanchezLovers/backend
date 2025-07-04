package pe.edu.pucp.sirgep.ws.usuarios;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.usuarios.dtos.CompradorDTO;
import pe.edu.pucp.sirgep.business.usuarios.dtos.DetalleComprador;
import pe.edu.pucp.sirgep.business.usuarios.impl.CompradorServiceImpl;
import pe.edu.pucp.sirgep.business.usuarios.service.ICompradorService;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;

@WebService(serviceName = "CompradorWS", targetNamespace = "pe.edu.pucp.sirgep")
public class CompradorWS {
    private final ICompradorService compradorService;
    
    public CompradorWS(){
        compradorService = new CompradorServiceImpl();
    }

    @WebMethod(operationName = "crearCuenta")     
    public int crearCuenta(@WebParam(name = "comprador") Comprador comprador,
            @WebParam(name = "tipoID") String tipoID,
            @WebParam(name = "distrito") String distrito){
        int id = -1;
        comprador.setTipoDocumento(ETipoDocumento.valueOf(tipoID));
        id = compradorService.insertar(comprador);
        
        actualizarDistritoFavoritoPorIdComprador(distrito, id);
        
        return id;
    }
    
    @WebMethod(operationName = "validarCorreo") 
    public boolean validarCorreo(@WebParam(name = "correo") String correo) {
        try {
            return compradorService.validarCorreo(correo);
        } catch (Exception ex) {
            throw new WebServiceException("Error al validar correo");
        }
    }
    
    @WebMethod(operationName = "buscarDetalleCompradorPorId") 
    public DetalleComprador buscarDetalleCompradorPorId(@WebParam(name = "idComprador") int idComprador) {
        try {
            return compradorService.buscarDetalleCompradorPorId(idComprador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar el detalle del comprador: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarDistritoFavoritoPorIdComprador") 
    public boolean actualizarDistritoFavoritoPorIdComprador(@WebParam(name = "nuevoDistrito") String nuevoDistrito,
            @WebParam(name = "idComprador") int idComprador) {
        try {
            return compradorService.actualizarDistritoFavoritoPorIdComprador(nuevoDistrito,idComprador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar el detalle del comprador: " + ex.getMessage());
        }
    }
    
    //Gestion de ususario por el administrador
    @WebMethod(operationName = "eliminarUsuarioComprador") 
    public boolean eliminarUsuarioComprador(@WebParam(name = "idComprador") int idComprador) {
        try {
            return compradorService.eliminarLogico(idComprador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar el usuario del comprador: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarCompradoresDTO")
    public List<CompradorDTO> listarCompradoresDTO() {
        try {
            return compradorService.listarCompradoresDTO();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar compradores DTO: " + ex.getMessage());
        }
    }
}