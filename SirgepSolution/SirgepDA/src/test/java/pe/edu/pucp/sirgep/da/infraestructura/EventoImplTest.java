/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.da.infraestructura;


import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

/**
 *
 * @author Ana Gabriela
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventoImplTest {
    private Evento e;
    private EventoImpl eventoImpl;

    @BeforeEach
    public void setUp() {
        this.e = new Evento();
        
        Distrito distrito = new Distrito();
        distrito.setIdDistrito(1);
        this.e.setDistrito(distrito);  // ← usar el mismo distrito

        this.e.setCantEntradasDispo(10);
        this.e.setCantEntradasVendidas(15);
        this.e.setNombre("Festival de Café");
        this.e.setDescripcion("Emprendedores vecinos y diferentes cafeterías darán a degustar"
                + "y pondrán en venta sus productos de producción cafetera!");
        this.e.setIdEvento(1);
        this.e.setFecha(java.sql.Date.valueOf("2025-10-01"));
        this.e.setPrecioEntrada(5.50);
        this.e.setReferencia("En la esquina del cruce A y B");
        this.e.setUbicacion("Avenida ABC 123");
        //this.e.setUrlImagen();
        

        this.eventoImpl = new EventoImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar EVENTO...");
        int result = this.eventoImpl.insertar(this.e);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Evento result = this.eventoImpl.buscar(1);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Evento> lista = this.eventoImpl.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.eventoImpl.actualizar(this.e);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.eventoImpl.eliminarLogico(this.e.getIdEvento());
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() {
        System.out.println("Ejecutando test eliminarFisico...");
        //boolean result = this.espacioDAO.eliminarFisico(this.espacio.getIdEspacio());
        assertTrue(true);
    }
}
