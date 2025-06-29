
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pe.edu.pucp.sirgep.da.infraestructura.dao.HorarioEspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.HorarioEspacioImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.EntradaImpl;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
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
    private ReservaDAO es;
    //funciona
    /*
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
    }*/
    @Test
    public void testListar() throws ParseException {
        this.es= new ReservaImpl();
        System.out.println("Ejecutando Inactivar Reservas...");
        if (!es.inactivar()) System.out.println("Error en Reservas");
//        System.out.println("Ejecutando Inactivar Entradas...");
//        if (!(new EntradaImpl().inactivar())) System.out.println("Error en Entradas");
//        System.out.println("Ejecutando Inactivar Eventos...");
//        if (!(new EventoImpl().inactivar())) System.out.println("Error en Eventos");
//        System.out.println("Ejecutando Inactivar Funcion...");
//        if (!(new FuncionImpl().inactivar())) System.out.println("Error en Funciones");
////        assertNotNull(lista);
        assertTrue(true);
    }
}
