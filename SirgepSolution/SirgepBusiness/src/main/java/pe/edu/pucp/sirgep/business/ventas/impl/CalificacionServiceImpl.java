/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ventas.impl;

import pe.edu.pucp.sirgep.da.infraestructura.implementacion.CalificacionImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Calificacion;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

/**
 *
 * @author Ana Gabriela
 */
public class CalificacionServiceImpl {
     private final CalificacionImpl cImpl;
    
    public CalificacionServiceImpl(){
        cImpl = new CalificacionImpl();
    }
    
    public int insertar(Calificacion c) {
        return cImpl.insertar(c);
    }
}
