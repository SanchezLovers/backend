/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.sirgep.business.ventas.impl.CalificacionServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Calificacion;

/**
 *
 * @author Ana Gabriela
 */

@WebService(serviceName = "CalificacionWS", targetNamespace = "pe.edu.pucp.sirgep")
public class CalificacionWS {

    private final CalificacionServiceImpl cService;
    
    public CalificacionWS()
    {
        cService = new CalificacionServiceImpl();
    }    
    
    @WebMethod(operationName = "calificarServicio")
    public int calificarEspacio(@WebParam(name = "puntaje") int puntaje,
            @WebParam(name = "text") String comentario,
            @WebParam(name = "servicio") String Servicio) {
        try{
            Calificacion c = new Calificacion();
            c.setPuntaje(puntaje);
            c.setServicio(Servicio.charAt(1));
            c.setTexto(comentario);
            return cService.insertar(c);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al insertar un espacio: " + ex.getMessage());
        }
    }
}
