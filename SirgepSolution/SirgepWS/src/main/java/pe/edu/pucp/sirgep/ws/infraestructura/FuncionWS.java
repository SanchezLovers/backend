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
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

/**
 *
 * @author benny
 */
@WebService(serviceName = "FuncionWS", targetNamespace = "pe.edu.pucp.sirgep")
public class FuncionWS {
    private final IFuncionService funcionService;
    
    public FuncionWS(){
        funcionService = new FuncionServiceImpl();
    }
    
    // insertar función
    @WebMethod(operationName = "insertarFuncion")
    public int insertarFuncion(@WebParam(name = "funcion") Funcion funcion) {
        try{
            return funcionService.insertar(funcion);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("ERROR al insertar una FUNCION: " + ex.getMessage());
        }
    }
}
