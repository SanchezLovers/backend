/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;

/**
 *
 * @author Ana Gabriela
 */
public interface IReporteService {
    List<Integer> cantidadReservasMes();
    List<EspacioRepDTO> EspaciosFavMes();
    List<Integer> cantidadEntradasMes();
    List<EspacioRepDTO> EventosFavMes();
}
