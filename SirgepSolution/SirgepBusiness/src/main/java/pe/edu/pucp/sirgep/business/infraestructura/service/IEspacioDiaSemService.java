package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

public interface IEspacioDiaSemService{
    List<EspacioDiaSem> listarDiasSem();
    List<EspacioDiaSem> listarDiasSemPorEspacio(int idEspacio);
    boolean insertarDiaSem(EspacioDiaSem entity);
    public boolean eliminarDiasPorEspacio(int idEspacio);
}