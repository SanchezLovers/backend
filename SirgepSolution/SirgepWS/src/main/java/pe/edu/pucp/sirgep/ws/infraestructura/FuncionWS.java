/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IFuncionService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

/**
 *
 * @author Ana Gabriela
 */
@WebService(serviceName = "FuncionWS", targetNamespace = "pe.edu.pucp.sirgep")
public class FuncionWS {
    private IFuncionService fService;
    
    public FuncionWS(){
        fService = new FuncionServiceImpl();
    }
    
    @WebMethod(operationName = "buscarFuncionId")
    public Funcion buscarFuncionId(@WebParam(name = "idFuncion") int idFuncion){
        return fService.buscar(idFuncion);
    }
}
