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
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import pe.edu.pucp.sirgep.ws.ventas.EntradaWS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntradaWSTest {
    private Entrada entrada;
    private EntradaWS entradaWS;

    @BeforeEach
    public void setUp() {
        this.entrada = new Entrada();
        this.entrada.setNumEntrada(1);
        this.entrada.setIdConstancia(1);
        this.entrada.setTotal(66);
        this.entrada.setDetallePago("Pagado completo");
        this.entrada.setFecha(new Date(2025,6,2));
        Funcion funcion=new Funcion();
        funcion.setIdFuncion(1);
        this.entrada.setFuncion(funcion);
        this.entrada.setMetodoPago(EMetodoPago.TARJETA);
        Persona persona=new Persona();
        persona.setIdPersona(1);
        this.entrada.setPersona(persona);
        
        this.entradaWS = new EntradaWS();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.entradaWS.insertarEntrada(this.entrada);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Entrada result = this.entradaWS.buscarEntrada(1);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Entrada> lista = this.entradaWS.listarEntrada();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.entradaWS.actualizarEntrada(this.entrada);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.entradaWS.eliminarLogicoEntrada(1);
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() {
        System.out.println("Ejecutando test eliminarFisico...");
        //boolean result = entradaWS.eliminarFisicoEntrada(1);
        //assertTrue(result);
    }
}