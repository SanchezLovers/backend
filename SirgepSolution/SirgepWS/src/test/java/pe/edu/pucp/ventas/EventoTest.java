/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.ventas;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.sirgep.business.ubicacion.service.DepartamentoServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWS;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWSAnaG;
import pe.edu.pucp.sirgep.ws.ubicacion.DepartamentoWS;

/**
 *
 * @author Ana Gabriela
 */
public class EventoTest {
    private EventoWSAnaG ds;
    //service sí funciona
    @Test
    @Order(1)
    public void testBuscar() {
        this.ds= new EventoWSAnaG();
        System.out.println("Ejecutando test buscar...");
        Evento e =  ds.buscarPorID(1);
        System.out.println("Se encontró el evento: ");
        System.out.println(e.toString());
    }
}
