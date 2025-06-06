package pe.edu.pucp.ventas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;



import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import pe.edu.pucp.sirgep.ws.infraestructura.EventoWSAnaG;
import pe.edu.pucp.sirgep.ws.ubicacion.DepartamentoWS;
import pe.edu.pucp.sirgep.ws.ventas.EntradaWS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionTest {
    private EventoWSAnaG eWS;
    //service sí funciona
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
    }
}