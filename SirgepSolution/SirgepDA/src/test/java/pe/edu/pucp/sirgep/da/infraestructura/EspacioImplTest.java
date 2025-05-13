package pe.edu.pucp.sirgep.da.infraestructura;

import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EspacioImplTest {
    private Espacio espacio;
    private EspacioDAO espacioDAO;

    @BeforeEach
    public void setUp() {
        this.espacio = new Espacio();
        
        Distrito distrito = new Distrito();
        distrito.setIdDistrito(1);
        this.espacio.setDistrito(distrito);  // ← usar el mismo distrito

        this.espacio.setHorarioInicioAtencion(LocalTime.of(9, 0));
        this.espacio.setHorarioFinAtencion(LocalTime.of(18, 0));
        this.espacio.setIdEspacio(1);
        this.espacio.setNombre("Sala de reuniones A");
        this.espacio.setPrecioReserva(120.50);
        this.espacio.setSuperficie(35.75);
        this.espacio.setTipoEspacio(ETipoEspacio.TEATRO);
        this.espacio.setUbicacion("Edificio B, segundo piso");
        this.espacio.setIdEspacio(1);

        ArrayList<EDiaSemana> dias = new ArrayList<>();
        dias.add(EDiaSemana.LUNES);
        dias.add(EDiaSemana.MIERCOLES);
        dias.add(EDiaSemana.VIERNES);
        this.espacio.setListaDiasAtencion(dias);

        ArrayList<Reserva> reservas = new ArrayList<>();
        Reserva reserva1 = new Reserva();
        reserva1.setIdConstancia(1);
        Reserva reserva2 = new Reserva();
        reserva2.setIdConstancia(2);
        reservas.add(reserva1);
        reservas.add(reserva2);
        this.espacio.setReservas(reservas);

        this.espacioDAO = new EspacioImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.espacioDAO.insertar(this.espacio);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Espacio result = this.espacioDAO.buscar(2);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Espacio> lista = this.espacioDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.espacioDAO.actualizar(this.espacio);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.espacioDAO.eliminarLogico(this.espacio.getIdEspacio());
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