package pe.edu.pucp.sirgep.business.ventas.impl;

import pe.edu.pucp.sirgep.da.infraestructura.implementacion.CalificacionImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Calificacion;

public class CalificacionServiceImpl {
     private final CalificacionImpl cImpl;
    
    public CalificacionServiceImpl(){
        cImpl = new CalificacionImpl();
    }
    
    public int insertar(Calificacion c) {
        return cImpl.insertar(c);
    }
}
