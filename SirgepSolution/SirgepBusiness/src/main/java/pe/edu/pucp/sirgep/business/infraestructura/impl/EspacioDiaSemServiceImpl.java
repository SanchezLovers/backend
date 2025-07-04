package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioDiaSemService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDiaSemDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioDiaSemImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

public class EspacioDiaSemServiceImpl implements IEspacioDiaSemService {
    private final EspacioDiaSemDAO diaDAO;
    
    public EspacioDiaSemServiceImpl(){
        diaDAO = new EspacioDiaSemImpl();
    }

    @Override
    public List<EspacioDiaSem> listarDiasSem() {
        return diaDAO.listar();
    }

    @Override
    public boolean insertarDiaSem(EspacioDiaSem entity) {
        return diaDAO.insertarDia(entity);
    }

    @Override
    public List<EspacioDiaSem> listarDiasSemPorEspacio(int idEspacio) {
        return diaDAO.listarPorEspacio(idEspacio);
    }

    @Override
    public boolean eliminarDiasPorEspacio(int idEspacio) {
        return diaDAO.eliminarLogico(idEspacio);
    }
}