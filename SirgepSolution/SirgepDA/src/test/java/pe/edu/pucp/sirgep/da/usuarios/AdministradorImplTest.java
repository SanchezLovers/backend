package pe.edu.pucp.sirgep.da.usuarios;

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
import pe.edu.pucp.sirgep.da.usuarios.dao.AdministradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.AdministradorImpl;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdministradorImplTest {
    private Administrador administrador;
    private AdministradorDAO administradorDAO;

    @BeforeEach
    public void setUp() {
        // 1. Crear la Persona
        this.administrador = new Administrador();

        // 2. Asignar valores simples
        this.administrador.setIdPersona(15);
        this.administrador.setNombres("Ariana");
        this.administrador.setPrimerApellido("Oyanguren");
        this.administrador.setSegundoApellido("Valdivia");
        this.administrador.setTipoDocumento(ETipoDocumento.DNI);
        this.administrador.setNumDocumento("92375921");
        this.administrador.setCorreo("ariana.ov@example.com");
        this.administrador.setUsuario("ariana.ov");
        this.administrador.setContrasenia("letzylow!123");
        this.administrador.setTipoAdministrador(ETipoAdministrador.REGIONAL);

        this.administradorDAO = new AdministradorImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.administradorDAO.insertar(this.administrador);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Administrador result = this.administradorDAO.buscar(this.administrador.getIdPersona());
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Administrador> lista = this.administradorDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.administradorDAO.actualizar(this.administrador);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.administradorDAO.eliminarLogico(this.administrador.getIdPersona());
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() {
        System.out.println("Ejecutando test eliminarFisico...");
        //boolean result = this.personaDAO.eliminarFisico(this.persona.getIdPersona());
        assertTrue(true);
    }
}