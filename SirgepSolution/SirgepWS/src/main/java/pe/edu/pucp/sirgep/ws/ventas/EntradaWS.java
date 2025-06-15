package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

import pe.edu.pucp.sirgep.business.ventas.impl.EntradaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleEntrada;
import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

@WebService(serviceName = "EntradaWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EntradaWS {
    private final IEntradaService entradaService;
    
    public EntradaWS(){
        entradaService = new EntradaServiceImpl();
    }
    
    //Metodos CRUD
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
    
    @WebMethod(operationName = "listarEntradasActivas")
    public List<Entrada> listarEntradasActivas() {
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
    
    //Metodos adicionales
    @WebMethod(operationName = "buscarCompradorDeEntrada")
    public Comprador buscarCompradorDeEntrada(@WebParam(name = "idComprador") int idComprador){
        Comprador resultado=null;
        try {
            resultado=entradaService.buscarCompradorDeEntrada(idComprador);
            if(resultado==null){
                throw new RuntimeException("Comprador de la entrada no encontradoa");
            }
            return resultado;
        }  catch (Exception ex) {
                throw new RuntimeException("Error interno al buscar la funcion de la entrada: "+ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarFuncionDeEntrada")
    public Funcion buscarFuncionDeEntrada(@WebParam(name = "idFuncion") int idFuncion){
        Funcion resultado=null;
        try {
            resultado=entradaService.buscarFuncionDeEntrada(idFuncion);
            if(resultado==null){
                throw new RuntimeException("Funcion de la entrada no encontrada");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarEventoDeEntrada")
    public Evento buscarEventoDeEntrada(@WebParam(name = "idEntrada") int idEntrada){
        Evento resultado=null;
        try {
            resultado=entradaService.buscarEventoDeEntrada(idEntrada);
            if(resultado==null){
                throw new RuntimeException("Evento de la entrada no encontrado");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarDistritoDeEntrada")
    public Distrito buscarDistritoDeEntrada(@WebParam(name = "idEntrada") int idEntrada){
        Distrito resultado=null;
        try {
            resultado=entradaService.buscarDistritoDeEntrada(idEntrada);
            if(resultado==null){
                throw new RuntimeException("Distrito de la entrada no encontrado");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la entrada: " + ex.getMessage());
        }
    }

    //Metodo para listar el detalle de las entradas
    @WebMethod(operationName = "listarDetalleEntradasPorComprador")
    public List<DetalleEntrada> listarDetalleEntradasPorComprador(@WebParam(name = "idComprador")int idComprador){
        try {
            return entradaService.listarDetalleEntradasPorComprador(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al listar el detalle de las entradas del comprador : " + ex.getMessage());
        }
    }
    
    //Metodo para crear libro de Excel para las entradas
    @WebMethod(operationName = "crearLibroExcelEntradas")
    public void crearLibroExcelEntradas(@WebParam(name = "idComprador")int idComprador){
        try {
            entradaService.crearLibroExcelEntradas(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al exportar el libro excel de las entradas: : " + ex.getMessage());
        }
    }
}