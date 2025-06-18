/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.ws.ubicacion;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.ubicacion.service.DistritoServiceImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

/**
 *
 * @author Ana Gabriela
 */
@WebService(serviceName = "DistritoWS", targetNamespace = "pe.edu.pucp.sirgep")

public class DistritoWS {
    private DistritoServiceImpl dService;
    
    public DistritoWS(){
        dService = new DistritoServiceImpl();
    }
    
    @WebMethod(operationName = "listarDistritosFiltrados")
    public List<Distrito> listarDistritos(@WebParam(name = "Id")int id) {
        try {
            return dService.listarPorDepartamento(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar distritos: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarTodosDistritos")
    public List<Distrito> listarTodosDistritos() {
        try {
            return dService.listar();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar distritos: " + ex.getMessage());
        }
    }
	
    @WebMethod(operationName = "buscarDistPorId")
    public Distrito buscarDistPorId(@WebParam(name = "idDistrito") int idDistrito) {
        try {
            Distrito distrito = dService.buscar(idDistrito); 
            return distrito;
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar un distrito por su ID: " + idDistrito + ex.getMessage());
        }
    }
}
