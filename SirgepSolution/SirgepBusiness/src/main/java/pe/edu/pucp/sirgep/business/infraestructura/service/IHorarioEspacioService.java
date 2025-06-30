package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

public interface IHorarioEspacioService {
    List<HorarioEspacio> listarHorarios(int idEspacio, Date fecha);
}