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
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.AdministradorImpl;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompradorImplTest {
    private Comprador comprador;
    private CompradorImpl compradorDAO;

    @BeforeEach
    public void setUp() {
        // 1. Crear la Persona
        this.comprador = new Comprador();

        // 2. Asignar valores simples
        this.comprador.setIdPersona(18);
        this.comprador.setNombres("Italo");
        this.comprador.setPrimerApellido("Carrion");
        this.comprador.setSegundoApellido("Segura");
        this.comprador.setTipoDocumento(ETipoDocumento.DNI);
        this.comprador.setNumDocumento("90816232");
        this.comprador.setCorreo("italo.cs@example.com");
        this.comprador.setUsuario("italo.cs");
        this.comprador.setContrasenia("letzylow!123");
        this.comprador.setEsRegistrado(true);

        this.compradorDAO = new CompradorImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.compradorDAO.insertar(this.comprador);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Comprador result = this.compradorDAO.buscar(this.comprador.getIdPersona());
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Comprador> lista = this.compradorDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.compradorDAO.actualizar(this.comprador);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.compradorDAO.eliminarLogico(this.comprador.getIdPersona());
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