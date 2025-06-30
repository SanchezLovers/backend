package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

public interface HorarioEspacioDAO{
    public List<HorarioEspacio> listarHorasDisponibles(int idEspacio, Date dia);
    boolean validarDia(Date dia, int idEspacio);
}