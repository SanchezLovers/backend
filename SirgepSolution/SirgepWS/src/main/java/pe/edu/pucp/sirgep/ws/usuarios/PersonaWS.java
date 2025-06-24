/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.ws.usuarios;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.sirgep.business.usuarios.impl.PersonaService;
import pe.edu.pucp.sirgep.business.usuarios.service.IPersonaService;

/**
 *
 * @author LEGION
 */
@WebService(serviceName = "PersonaWS", targetNamespace = "pe.edu.pucp.sirgep")
public class PersonaWS {
    IPersonaService personaService;
    
    public PersonaWS(){
        personaService =  new PersonaService();
    }
    
    @WebMethod(operationName = "validarCuentaString")
    public String validarCuentaString(@WebParam(name = "correo") String c,
            @WebParam(name = "passcode") String p) {
//        return "Hello " + txt + " !";
        int n = personaService.validarCuenta(c, p);
        if (n==0){
            return "Las credenciales están fallidas.";
        }
        if(n%10 == 1){
            return "cuenta administrador. ID = " + n/10;
        }
        if (n%10 ==2){
            return "cuenta comprador registrado.ID = " + n/10;
        }
        if (n==-1){
            return "Las credenciales no pertenecen a una cuenta.";
        }
        else return "";
    }
    
    @WebMethod(operationName = "validarCuenta")
    public int validarCuenta(@WebParam(name = "correo") String c,
            @WebParam(name = "passcode") String p) {
//        return "Hello " + txt + " !";
        return personaService.validarCuenta(c, p);
    }
    
    @WebMethod(operationName = "obtenerNombreUsuario")
    public String obtenerNombreUsuario(@WebParam(name = "id") int id){
        return personaService.obtenerNombreUsuario(id);
    }
}