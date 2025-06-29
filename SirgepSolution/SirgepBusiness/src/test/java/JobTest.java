
import org.junit.Test;
import org.junit.jupiter.api.Order;
import pe.edu.pucp.sirgep.business.infraestructura.jobs.EstadoJob;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ana Gabriela
 */
public class JobTest {
    private EstadoJob e;
    //service sí funciona
    @Test
    @Order(1)
    public void testActualizar(){
        this.e = new EstadoJob();
        e.actualizarEstados();
    }
}
