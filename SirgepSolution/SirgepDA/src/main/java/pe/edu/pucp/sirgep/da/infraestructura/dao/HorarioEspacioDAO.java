/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.da.infraestructura.dao;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;
/**
 *
 * @author Ana Gabriela
 */
public interface HorarioEspacioDAO{
    public List<HorarioEspacio> listarHorasDisponibles(int idEspacio, Date dia);
}
