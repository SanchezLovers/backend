/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.ws.ubicacion;

import jakarta.jws.WebMethod;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.sirgep.business.ubicacion.service.DepartamentoServiceImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

/**
 *
 * @author Ana Gabriela
 */
public class DepartamentoWS {
    private final DepartamentoServiceImpl departamentoService;
    
    public DepartamentoWS(){
        departamentoService = new DepartamentoServiceImpl();
    }
    
    @WebMethod(operationName = "listarDepas")
    public List<Departamento> listarDepas() {
        try {
            return departamentoService.listar();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar depas: " + ex.getMessage());
        }
    }
}
