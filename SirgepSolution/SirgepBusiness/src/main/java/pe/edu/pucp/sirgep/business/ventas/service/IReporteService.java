package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;

public interface IReporteService {
    List<Integer> cantidadReservasMes();
    List<EspacioRepDTO> EspaciosFavMes();
    List<Integer> cantidadEntradasMes();
    List<EspacioRepDTO> EventosFavMes();
}