package pe.edu.pucp.ventas;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.sirgep.business.ubicacion.service.DepartamentoServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWS;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWS;
import pe.edu.pucp.sirgep.ws.ubicacion.DepartamentoWS;

public class EventoTest {
    private EventoWS ds;
    //service sí funciona
    @Test
    @Order(1)
    public void testBuscar() {
        this.ds= new EventoWS();
        System.out.println("Ejecutando test buscar...");
        Evento e =  ds.buscarEventoPorID(1);
        System.out.println("Se encontró el evento: ");
        System.out.println(e.toString());
    }
}
