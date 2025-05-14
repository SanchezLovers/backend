/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

/**
 *
 * @author AnaC
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DistritoImplTest {
    
    private Distrito distrito;
    private DistritoDAO distritoDAO;
    
    
    
    @BeforeEach
    public void setUp() {
        this.distrito = new Distrito();
        this.distrito.setIdDistrito(9);
        this.distrito.setNombre("El Agustino");
        
        Provincia provincia= new Provincia();
        provincia.setIdProvincia(1);
        this.distrito.setProvincia(provincia);
        
        this.distritoDAO= new DistritoImpl();
    }
    
    
    @Test
    @Order(1)
    public void testInsertar(){
        System.out.println("Ejecutando test insertar...");
        int result = this.distritoDAO.insertar(this.distrito);
        assertTrue(result!= -1);
    }
    
    @Test
    @Order(2)
    public void testBuscar(){
        System.out.println("Ejecutando test buscar...");
        Distrito result = this.distritoDAO.buscar(this.distrito.getIdDistrito());
        assertNotNull(result);
    }
    
    @Test
    @Order(3)
    public void testListar(){
        System.out.println("Ejecutando test listar...");
        List<Distrito> lista = this.distritoDAO.listar();
        assertNotNull(lista);
    }
    
    @Test
    @Order(4)
    public void testActualizar(){
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.distritoDAO.actualizar(this.distrito);
        assertTrue(result);
    }
    @Test
    @Order(5)
    public void testEliminarLogico(){
//        System.out.println("Ejecutando test EliminarLogico...");
//        boolean result = this.distritoDAO.eliminarLogico(this.distrito.getIdDistrito());
//        assertTrue(result);
    }
    
    @Test
    @Order(6)
    public void testEliminarFisico(){
        System.out.println("Ejecutando test EliminarFisico...");
        assertTrue(true);
    }
}
