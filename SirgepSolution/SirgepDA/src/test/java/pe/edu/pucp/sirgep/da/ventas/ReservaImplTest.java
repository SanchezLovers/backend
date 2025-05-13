/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.sirgep.da.ventas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

/**
 *
 * @author benny
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaImplTest {
    
    private final ReservaDAO reservaDAO;
    private final Reserva entidad;
    private final Espacio espacio;
    private final Persona persona;
    private static int idGenerado;
    
    public ReservaImplTest() {
        reservaDAO = new ReservaImpl();
        entidad = new Reserva();
        espacio = new Espacio();
        persona = new Persona();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // creando espacio
        espacio.setNombre("ESPACIO - PRUEBA - RESERVA");
        espacio.setIdEspacio(98);
        espacio.setPrecioReserva(45.50);
        espacio.setTipoEspacio(ETipoEspacio.TEATRO);
        
        // creando persona
        persona.setNombres("PRUEBA TEST");
        persona.setContrasenia("probando12345&");
        persona.setCorreo("test@test.com");
        persona.setIdPersona(98);
        persona.setNumDocumento("65498723");
        persona.setPrimerApellido("TEST PRIMER APE");
        persona.setSegundoApellido("TEST 2DO APE");
        
        // RESERVA:
        entidad.setDetallePago("DETALLE PRUEBA DE RESERVA - PROBANDO 1 2 3 ...");
        entidad.setEspacio(espacio);
        Date fechaConstancia = java.sql.Date.valueOf(LocalDate.now());
        Date fechaReserva = java.sql.Date.valueOf(LocalDate.of(2023, Month.MARCH, 30));
        
        entidad.setFecha(fechaConstancia);
        entidad.setFechaReserva(fechaReserva);
        
        entidad.setHorarioIni(LocalTime.MIDNIGHT);
        entidad.setHorarioFin(LocalTime.NOON);
        
        entidad.setIdConstancia(99);
        entidad.setIgv(0.188);
        entidad.setMetodoPago(EMetodoPago.PLIN);
        entidad.setPersona(persona);
        entidad.setTotal(1234.65);
        
        if(idGenerado > 0) this.entidad.setNumReserva(idGenerado);
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    @Order(1)
    public void probarInsertar(){
        System.out.println("Se prueba el insertar en RESERVA ... ID: " + entidad.getNumReserva());
        
        int retorno = reservaDAO.insertar(entidad);
        idGenerado = retorno;
        assertTrue(retorno != -1);
    }
    
    @Test
    @Order(2)
    public void probarBuscar() {
        System.out.println("Se prueba el BUSCAR en RESERVA ... ID: " + entidad.getNumReserva());
        Reserva co = reservaDAO.buscar(entidad.getNumReserva());
        assertNotNull(co);
    }
    
    @Test
    @Order(3)
    public void probarListar() {
        System.out.println("Se prueba el LISTAR en RESERVA ...");
        List<Reserva> reservas = reservaDAO.listar();
        assertNotNull(reservas);
    }
    
    @Test
    @Order(4)
    public void probarActualizar(){
        System.out.println("Se prueba el actualizar en RESERVA ... ID: " + entidad.getNumReserva());
        entidad.setDetallePago("RESERVA ... Se compro el ACTUALIZADO_ESPACIO/EVENTO X para la fecha FECHA_ACTUALIZADA_TEST por el monto MONTO_ACTUALIZADO_TEST");
        entidad.setFecha(new Date(2023,12,02)); // cambiar porque es función deprecada
        entidad.setIgv(0.198);
        entidad.setMetodoPago(EMetodoPago.YAPE);
        entidad.setTotal(1000.47);
        
        // cambios para reserva propiamente:
        entidad.setNumReserva(54666);
        entidad.setHorarioFin(LocalTime.of(4, 30));
        entidad.setHorarioFin(LocalTime.of(8, 55));
        boolean retorno = reservaDAO.actualizar(entidad);
        assertTrue(retorno);
    }
    
    @Test
    @Order(5)
    public void probarEliminarLogico() {
        System.out.println("Se prueba el ELIMINADO LOGICO en RESERVA ... ID: " + entidad.getNumReserva());
        boolean retorno = reservaDAO.eliminarLogico(entidad.getNumReserva());
        assertTrue(retorno);
    }
    
    @Test
    @Order(6)
    public void probarEliminarFisico() {
        System.out.println("Se prueba el ELIMINADO FISICO en RESERVA ... ID: " + entidad.getNumReserva());
        boolean retorno = reservaDAO.eliminarFisico(entidad.getNumReserva());
        assertTrue(retorno);
    }
}
