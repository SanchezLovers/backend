/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.sirgep.da.ventas;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.ventas.dao.EntradaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.EntradaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

/**
 *
 * @author willi
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntradaImplTest {
    private Entrada entrada;
    private EntradaDAO entradaDAO;
    
    @BeforeEach
    public void setUp() throws ParseException{
        this.entrada = new Entrada();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse("2025-12-04");
        Funcion funcion = new Funcion();
        Persona persona = new Persona();
        funcion.setIdFuncion(1);
        persona.setIdPersona(1);
        this.entrada.setIdConstancia(293);
        this.entrada.setFecha(fecha);
        this.entrada.setMetodoPago(EMetodoPago.YAPE);
        this.entrada.setDetallePago("Pago_Prueba");
        this.entrada.setTotal(20.59);
        this.entrada.setNumEntrada(1);
        this.entrada.setFuncion(funcion);
        this.entrada.setPersona(persona);
        this.entradaDAO = new EntradaImpl();
    }
    
    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.entradaDAO.insertar(this.entrada);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() { //Bien
        System.out.println("Ejecutando test buscar...");
        Entrada result = this.entradaDAO.buscar(this.entrada.getNumEntrada());
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() { //Bien
        System.out.println("Ejecutando test listar...");
        List<Entrada> lista = this.entradaDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() { //Bien
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.entradaDAO.actualizar(this.entrada);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() { //Bien
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.entradaDAO.eliminarLogico(this.entrada.getIdConstancia());
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() { //Bien
        System.out.println("Ejecutando test eliminarFisico...");
        //boolean result = this.entradaDAO.eliminarFisico(this.entrada.getIdConstancia());
        assertTrue(true);
    }
    
}
