
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import pe.edu.pucp.sirgep.da.infraestructura.dao.HorarioEspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.HorarioEspacioImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.ReporteDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReporteImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ana Gabriela
 */

public class ReporteTest {
    private ReporteDAO es;
    //funciona
    @Test
    
    public void testListar() throws ParseException {
        this.es= new ReporteImpl();
        System.out.println("Ejecutando test Entradas por Mes...");
        List<Integer> lista = this.es.cantidadEntradasMes();
        String cant;
        for (int i = 1; i <= lista.size(); i++) {
            cant = lista.get(i-1).toString();
            System.out.println( "Mes " + i + " - " + cant);
        }
        assertNotNull(lista);
    }
    
    /*
    public void testEspacios() throws ParseException {
        this.es = new ReporteImpl();
        System.out.println("Ejecutando test Espacios más reservados del mes...");

        Map<String, Integer> mapa = this.es.EspaciosFavMes();

        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String nombreEspacio = entry.getKey();
            Integer cantidadReservas = entry.getValue();

            System.out.println("Espacio: " + nombreEspacio + " - Reservas: " + cantidadReservas);
        }

        assertNotNull(mapa);
    }
    public void testEventos() throws ParseException {
        this.es = new ReporteImpl();
        System.out.println("Ejecutando test Eventos más comprados del mes...");

        Map<String, Integer> mapa = this.es.EventosFavMes();

        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String nombreEspacio = entry.getKey();
            Integer cantidadReservas = entry.getValue();

            System.out.println("Evento: " + nombreEspacio +
                    " - Entradas: " + cantidadReservas);
        }

        assertNotNull(mapa);
    }*/
}
