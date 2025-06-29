/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.da.ventas.dao;

import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

/**
 *
 * @author Ana Gabriela
 */
public interface ReporteDAO {
    
    List<Integer> cantidadReservasMes();
    List<Integer> cantidadEntradasMes();
    Map<String, Integer> EspaciosFavMes();
    Map<String, Integer> EventosFavMes();

}
