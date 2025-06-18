/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

/**
 *
 * @author LEGION
 */
public interface IHorarioEspacioService {
    List<HorarioEspacio> listarHorarios(int idEspacio, Date fecha);
}
