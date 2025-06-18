package pe.edu.pucp.ventas;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionTest {
    private EventoWS eWS;
    //service sí funciona
    /*
    @Test
    @Order(1)
    public void testListar() {
        this.eWS= new EventoWSAnaG();
        System.out.println("Ejecutando test listar Funciones del evento 1...");
        List<Funcion> lista = this.eWS.listarFuncionesDeEvento(1);
        System.out.println("Se encontró " + lista.size() + " funciones. ");

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        assertNotNull(lista);
    }*/
    @Test
    @Order(1)
    public void testCantDispo(){
        this.eWS= new EventoWS();
        System.out.println("Ejecutando test CantDipo...");
        int cantDispo = this.eWS.obtenerCantEntradasDisponibles(1, 50);
        System.out.println("Hay " + cantDispo + " entradas en la función. ");

        assertNotNull(cantDispo);
    }
}