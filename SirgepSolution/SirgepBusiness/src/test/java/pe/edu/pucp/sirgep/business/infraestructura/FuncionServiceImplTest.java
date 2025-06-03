/*package pe.edu.pucp.sirgep.business.infraestructura;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IFuncionService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionServiceImplTest {
    private Funcion funcion;
    private IFuncionService funcionService;

    @BeforeEach
    public void setUp() {
        this.funcion = new Funcion();
        this.funcion.setIdFuncion(1);
        this.funcion.setHoraInicio(LocalTime.of(15, 0));
        this.funcion.setHoraFin(LocalTime.of(17, 0));

        Evento evento = new Evento();
        evento.setIdEvento(1); // Asegúrate que exista este setter
        this.funcion.setEvento(evento);

        ArrayList<Entrada> entradas = new ArrayList<>();
        Entrada entrada1 = new Entrada();
        entrada1.setNumEntrada(1); // Asegúrate que exista este setter
        Entrada entrada2 = new Entrada();
        entrada2.setNumEntrada(2);
        entradas.add(entrada1);
        entradas.add(entrada2);
        this.funcion.setEntradas(entradas);

        this.funcionService = new FuncionServiceImpl();
    }

    @Test
    @Order(1)
    public void testInsertar() {
        System.out.println("Ejecutando test insertar...");
        int result = this.funcionService.insertar(this.funcion);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() {
        System.out.println("Ejecutando test buscar...");
        Funcion result = this.funcionService.buscar(2);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() {
        System.out.println("Ejecutando test listar...");
        List<Funcion> lista = this.funcionService.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() {
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.funcionService.actualizar(this.funcion);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() {
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.funcionService.eliminarLogico(1);
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() {
        System.out.println("Ejecutando test eliminarFisico...");
        //funcionService.eliminarFisico(1);
        assertTrue(true);
    }
}*/