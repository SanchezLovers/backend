/*
package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.sirgep.da.ubicacion.dao.ProvinciaDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.ProvinciaImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProvinciaImplTest {
    private Provincia provincia;
    private ProvinciaDAO provinciaDAO;
     
    
    @BeforeEach
    public void setUp() {
        this.provincia=new Provincia();
        this.provincia.setIdProvincia(15);
        this.provincia.setNombre("prueba6");
        
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(1);
        
        this.provincia.setDepartamento(departamento);
        
        this.provinciaDAO=new ProvinciaImpl();
    }
    
    @Test
    @Order(1)
    public void testInsertar(){
        System.out.println("Ejecutando test insertar...");
        int result = this.provinciaDAO.insertar(this.provincia);
        assertTrue(result!= -1);
    }
    
    @Test
    @Order(2)
    public void testBuscar(){
        System.out.println("Ejecutando test buscar...");
        Provincia result = this.provinciaDAO.buscar(this.provincia.getIdProvincia());
        assertNotNull(result);
    }
    
    @Test
    @Order(3)
    public void testListar(){
        System.out.println("Ejecutando test listar...");
        List<Provincia> lista = this.provinciaDAO.listar();
        assertNotNull(lista);
    }
    
    @Test
    @Order(4)
    public void testActualizar(){
        System.out.println("Ejecutando test actualizar...");
        boolean result = this.provinciaDAO.actualizar(this.provincia);
        assertTrue(result);
    }
    @Test
    @Order(5)
    public void testEliminarLogico(){
//        System.out.println("Ejecutando test EliminarLogico...");
//        boolean result = this.provinciaDAO.eliminarLogico(this.provincia.getIdProvincia());
//        assertTrue(result);
    }
    
    @Test
    @Order(6)
    public void testEliminarFisico(){
        System.out.println("Ejecutando test EliminarFisico...");
        //assertTrue(true);
    }
    
}
*/