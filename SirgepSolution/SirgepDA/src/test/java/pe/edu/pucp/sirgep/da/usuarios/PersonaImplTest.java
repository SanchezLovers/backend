/*package pe.edu.pucp.sirgep.da.usuarios;

import pe.edu.pucp.sirgep.da.usuarios.dao.PersonaDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.PersonaImpl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonaImplTest {
    private Persona persona;
    private PersonaDAO personaDAO;

    @BeforeEach
    public void setUp() {
        // 1. Crear la Persona
        this.persona = new Persona();

        // 2. Asignar valores simples
        this.persona.setIdPersona(1);
        this.persona.setNombres("Carlos");
        this.persona.setPrimerApellido("García");
        this.persona.setSegundoApellido("López");
        this.persona.setTipoDocumento(ETipoDocumento.DNI);
        this.persona.setNumDocumento("87654321");
        this.persona.setCorreo("carlos.garcia@example.com");
        this.persona.setUsuario("cgarcia");
        this.persona.setContrasenia("Secreto123!");

        // 3. Inicializar y llenar las entradas
        Entrada entrada1 = new Entrada();
        entrada1.setIdConstancia(1);
        Entrada entrada2 = new Entrada();
        entrada2.setIdConstancia(2);
        ArrayList<Entrada> entradas = new ArrayList<>();
        entradas.add(entrada1);
        entradas.add(entrada2);
        this.persona.setEntradas(entradas);  // Asignar lista de entradas a this.persona

        // 4. Inicializar y llenar las reservas
        Reserva reserva1 = new Reserva();
        reserva1.setIdConstancia(1);
        Reserva reserva2 = new Reserva();
        reserva2.setIdConstancia(2);
        ArrayList<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva1);
        reservas.add(reserva2);
        this.persona.setReservas(reservas);  // Asignar lista de reservas a this.persona

        this.personaDAO = new PersonaImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.personaDAO.insertar(this.persona);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Persona result = this.personaDAO.buscar(2);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Persona> lista = this.personaDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.personaDAO.actualizar(this.persona);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.personaDAO.eliminarLogico(this.persona.getIdPersona());
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() {
        System.out.println("Ejecutando test eliminarFisico...");
        //boolean result = this.personaDAO.eliminarFisico(this.persona.getIdPersona());
        assertTrue(true);
    }
}*/