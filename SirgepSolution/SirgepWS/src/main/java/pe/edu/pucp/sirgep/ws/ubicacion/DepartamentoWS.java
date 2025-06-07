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
import pe.edu.pucp.sirgep.business.ubicacion.service.DepartamentoServiceImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

/**esto ya funciona y todo ubicación tmb**/
@WebService(serviceName = "DepartamentoWS", targetNamespace = "pe.edu.pucp.sirgep")
public class DepartamentoWS {
    private DepartamentoServiceImpl departamentoService;
    
    public DepartamentoWS(){
        departamentoService = new DepartamentoServiceImpl();
    }
    
    /**
     *
     * @return
     */
    @WebMethod(operationName = "listarDepas")
    public List<Departamento> listarDepas() {
        try {
            List<Departamento> lista = departamentoService.listar(); 
            return lista;
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar depas: " + ex.getMessage());
        }
    }
	
	@WebMethod(operationName = "buscarDepaPorId")
    public Departamento buscarDepaPorId(@WebParam(name = "idDepartamento") int idDepartamento) {
        try {
            Departamento departamento = departamentoService.buscar(idDepartamento); 
            return departamento;
        } catch (Exception ex) {
            throw new WebServiceException("Error al buscar un departamento por su ID: " + idDepartamento + ex.getMessage());
        }
    }
}
