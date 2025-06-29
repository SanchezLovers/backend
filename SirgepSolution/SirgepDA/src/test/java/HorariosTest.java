/*
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pe.edu.pucp.sirgep.da.infraestructura.dao.HorarioEspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.HorarioEspacioImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

public class HorariosTest {
    private HorarioEspacioDAO es;
    //funciona
    @Test
    public void testListar() throws ParseException {
        this.es= new HorarioEspacioImpl();
        System.out.println("Ejecutando test listar HORARIOS...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-07-02");
        List<HorarioEspacio> lista = this.es.listarHorasDisponibles(32, date);
        if (lista ==null) System.out.println("El espacio no se puede reservar en el día de la semana elegido.");
        else{
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getHoraIni().toString() + " - " + 
                        lista.get(i).getDisponible());
            }
        }
//        assertNotNull(lista);
    }
}
*/