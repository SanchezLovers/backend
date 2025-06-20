
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ana Gabriela
 */
public class HorariosTest {
    private HorarioEspacioDAO es;
    //funciona
    @Test
    public void testListar() throws ParseException {
        this.es= new HorarioEspacioImpl();
        System.out.println("Ejecutando test listar HORARIOS...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-06-01");
        List<HorarioEspacio> lista = this.es.listarHorasDisponibles(2, date);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getHoraIni().toString() + " - " + 
                    lista.get(i).getDisponible());
        }
        assertNotNull(lista);
    }
}
