/*
package pe.edu.pucp.sirgep.da.ventas;

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
import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ConstanciaImpl;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConstanciaImplTest {
    
    // mis atributos
    private final ConstanciaDAO constanciaDAO;
    private Constancia entidad;
    private static int idGenerado;
    
    public ConstanciaImplTest() {
        constanciaDAO = new ConstanciaImpl();
        entidad = new Constancia();
    }

    @BeforeEach
    public void setUp() {
        entidad.setDetallePago("Se compro el ESPACIO/EVENTO X para la fecha FECHA_TEST por el monto MONTO_TEST");
        entidad.setFecha(new Date(2023,05,06)); // cambiar porque es función deprecada
        entidad.setIgv(0.18);
        entidad.setMetodoPago(EMetodoPago.TARJETA);
        entidad.setTotal(1654.32);
        
        if (idGenerado > 0) {
            entidad.setIdConstancia(idGenerado); // Recuperar el ID generado si ya existe
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    @Order(1)
    public void probarInsertar(){
        System.out.println("Se prueba el insertar en constancia ... + ID: " + entidad.getIdConstancia());
        
        int retorno = constanciaDAO.insertar(entidad);
        idGenerado = retorno;
        assertTrue(retorno != -1);
    }
    
    @Test
    @Order(2)
    public void probarBuscar() {
        System.out.println("Se prueba el BUSCAR en constancia con ID: " + entidad.getIdConstancia());
        Constancia co = constanciaDAO.buscar(entidad.getIdConstancia());
        assertNotNull(co);
    }
    
    @Test
    @Order(3)
    public void probarListar() {
        System.out.println("Se prueba el LISTAR en CONSTANCIA ...");
        List<Constancia> constancias = constanciaDAO.listar();
        assertNotNull(constancias);
    }
    
    @Test
    @Order(4)
    public void probarActualizar(){
        System.out.println("Se prueba el actualizar en constancia ... en el ID: " + entidad.getIdConstancia());
        
        entidad.setFecha(new java.util.Date(2023,12,02)); // cambiar porque es función deprecada
        entidad.setMetodoPago(EMetodoPago.YAPE);
        entidad.setIgv(0.19);
        entidad.setTotal(1000.47);
        entidad.setDetallePago("Se compro el ACTUALIZADO_ESPACIO/EVENTO X ___ MONTO_ACTUALIZADO_TEST");
        // el activo ya está en rs
        boolean retorno = constanciaDAO.actualizar(entidad);
        assertTrue(retorno);
    }
    
    @Test
    @Order(5)
    public void probarEliminarLogico() {
        System.out.println("Se prueba el ELIMINADO LOGICO en constancia ... con ID= " + entidad.getIdConstancia());
        boolean retorno = constanciaDAO.eliminarLogico(entidad.getIdConstancia());
        assertTrue(retorno);
    }
    
    @Test
    @Order(6)
    public void probarEliminarFisico() {
        System.out.println("Se prueba el ELIMINADO FISICO en constancia ...");
        boolean retorno = constanciaDAO.eliminarFisico(entidad.getIdConstancia());
        assertTrue(retorno);
    }
}*/
