/*
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.ubicacion.service.DepartamentoServiceImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public class EventoTest {
    private Evento e;
    private EventoServiceImpl es;
    //service sí funciona
    @Test
    @Order(1)
    public void testActualizar(){
        this.es = new EventoServiceImpl();
        System.out.println("Ejecutando test Actualizar...");
        e = es.buscar(1);
        e.setCantEntradasVendidas(e.getCantEntradasVendidas()+1);
        es.actualizar(e);
    }
    
    public void testListar() {
        this.es= new EventoServiceImpl();
        System.out.println("Ejecutando test listar...");
        List<Evento> lista = this.es.listar();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());;
        }
        assertNotNull(lista);
    }
}
*/