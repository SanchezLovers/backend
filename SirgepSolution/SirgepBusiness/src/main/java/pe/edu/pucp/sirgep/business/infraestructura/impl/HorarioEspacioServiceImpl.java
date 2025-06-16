/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.service.IHorarioEspacioService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.HorarioEspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.HorarioEspacioImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

/**
 *
 * @author Ana Gabriela
 */
public class HorarioEspacioServiceImpl implements IHorarioEspacioService{
    HorarioEspacioDAO hdao; 
    public HorarioEspacioServiceImpl(){
        hdao= new HorarioEspacioImpl();
    }
    
    @Override
    public List<HorarioEspacio> listarHorarios(int idEspacio, Date fecha) {
        return hdao.listarHorasDisponibles(idEspacio, fecha);
    }
    
}
