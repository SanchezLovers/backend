/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DepartamentoDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

/**
 *
 * @author willi
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartamentoImplTest {
    private Departamento departamento;
    private DepartamentoDAO departamentoDAO;
    
    @BeforeEach
    public void setUp() {
        this.departamento = new Departamento();
        
//        this.departamento.setIdDepartamento(100);
        this.departamento.setNombre("Ica");
        this.departamentoDAO = new DepartamentoImpl();
    }
    
    @Test
    @Order(1)
    public void testInsertar() {//Bien
        System.out.println("Ejecutando test insertar...");
        int result = this.departamentoDAO.insertar(this.departamento);
        assertTrue(result != -1);
    }

    @Test
    @Order(2)
    public void testBuscar() { //Bien
        System.out.println("Ejecutando test buscar...");
        Departamento result = this.departamentoDAO.buscar(this.departamento.getIdDepartamento());
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testListar() { //Bien
        System.out.println("Ejecutando test listar...");
        List<Departamento> lista = this.departamentoDAO.listar();
        assertNotNull(lista);
    }

    @Test
    @Order(4)
    public void testActualizar() { //Bien
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.departamentoDAO.actualizar(this.departamento);
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void testEliminarLogico() { //Bien
        System.out.println("Ejecutando test eliminarLogico...");
        boolean result = this.departamentoDAO.eliminarLogico(this.departamento.getIdDepartamento());
        assertTrue(result);
    }

    @Test
    @Order(6)
    public void testEliminarFisico() { //Bien
        System.out.println("Ejecutando test eliminarFisico...");
        boolean result = this.departamentoDAO.eliminarFisico(this.departamento.getIdDepartamento());
        assertTrue(result);
    }
    
}
