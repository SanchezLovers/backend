
import org.junit.jupiter.api.Order;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;
import pe.edu.pucp.sirgep.business.ventas.impl.ReporteServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IReporteService;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 *
 * @author Ana Gabriela
 */
public class EspacioDTOTest {
    
    private IReporteService rs;
    
    @Test
    public void testListar() {
        System.out.println("Ejecutando test listar Espacios mas reservados...");

        this.rs= new ReporteServiceImpl();
        List<EspacioRepDTO> lista = this.rs.EspaciosFavMes();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getNombre() + " " +
                    lista.get(i).getCantReservas());
        }
        assertNotNull(lista);
    }
}
