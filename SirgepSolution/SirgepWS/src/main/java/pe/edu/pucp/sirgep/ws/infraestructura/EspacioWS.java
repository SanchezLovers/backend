
package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EspacioDTO;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EspacioServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.ws.ventas.CompraWS;
import pe.edu.pucp.sirgep.ws.ventas.ReservaWS;

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
    
    @WebMethod(operationName = "listarEspacioPorCategoria")
    public List<Espacio> listarPorCategoria(@WebParam(name = "nombreCategoria") String categ) {
        try{
            List<Espacio> espacios = espacioService.listar();
            List<Espacio> espaciosFiltrados = new ArrayList<>();
            ETipoEspacio tipoEspacio = ETipoEspacio.valueOf(categ);
            for(Espacio espacio : espacios){
                if( espacio.getTipoEspacio() == tipoEspacio ){
                    espaciosFiltrados.add(espacio);
                }
            }
            return espaciosFiltrados;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarEspacioPorDistrito")
    public List<Espacio> listarPorDistrito(@WebParam(name = "idDistrito") int idDist) {
        try{
            List<Espacio> espacios = espacioService.buscarPorDistrito(idDist);
            return espacios;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarEspacioDistyCat")
    public List<Espacio> listarPorDistyCat(@WebParam(name = "idDistrito") int idDist,
                                           @WebParam(name = "nombreCategoria") String cat) {
        try{
            List<Espacio> espacios = listarPorDistrito(idDist);
            List<Espacio> espaciosFiltrados = new ArrayList<>();

            for(Espacio espacio : espacios){
                if( espacio.getTipoEspacio().toString().equals(cat)){
                    espaciosFiltrados.add(espacio);
                }
            }
            return espaciosFiltrados;
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al listar espacios con el filtro de Categoria y Distrito: " + ex.getMessage());
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
    
    @WebMethod(operationName = "buscarEspacioPorTexto")
    public List<Espacio> buscarPorTexto(@WebParam(name = "texto") String texto) {
        try{
            return espacioService.buscarPorTexto(texto);
        }
        catch(Exception ex){
            throw new RuntimeException("Error al buscar el espacio mediante un texto: "+ ex.getMessage());
        }
    }
    
    //Metodo para el envio de correo a los compradores registrados con el mismo distrito
    @WebMethod(operationName = "enviarCorreosCompradoresPorDistritoDeEspacio")
    public boolean enviarCorreosCompradoresPorDistritoDeEspacio(@WebParam(name = "espacio") Espacio espacio) {
        boolean resultado=false;
        try{
            resultado=espacioService.enviarCorreosCompradoresPorDistritoDeEspacio(espacio);
            if(!resultado){
                throw new RuntimeException("No se enviaron los correos");
            }
        }
        catch(Exception ex){
            throw new RuntimeException("Error al enviar correos a compradores con el mismo distrito del espacio: "+ ex.getMessage());
        }finally{
            return resultado;
        }
    }
    
    @WebMethod(operationName = "obtenerEspacioDTO")
    public EspacioDTO obtenerEspacioDTO(@WebParam(name = "idEspacio") int idEspacio){
        try{
            return espacioService.llenarEspacioDTOEdicion(idEspacio);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR AL OBTENER ESPACIO DTO " + ex.getMessage());
        }
    }
}