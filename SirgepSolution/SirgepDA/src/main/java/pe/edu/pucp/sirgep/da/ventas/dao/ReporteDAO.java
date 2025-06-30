package pe.edu.pucp.sirgep.da.ventas.dao;

import java.util.List;
import java.util.Map;

public interface ReporteDAO {
    List<Integer> cantidadReservasMes();
    List<Integer> cantidadEntradasMes();
    Map<String, Integer> EspaciosFavMes();
    Map<String, Integer> EventosFavMes();
}