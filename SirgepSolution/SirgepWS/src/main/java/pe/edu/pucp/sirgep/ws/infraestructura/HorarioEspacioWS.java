package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.HorarioEspacioServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IHorarioEspacioService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;

@WebService(serviceName = "HorarioEspacioWS", targetNamespace = "pe.edu.pucp.sirgep")
public class HorarioEspacioWS {
    private final IHorarioEspacioService hService;
    
    public HorarioEspacioWS()
    {
        hService = new HorarioEspacioServiceImpl();
    }    
    
    @WebMethod(operationName = "listarHorariosDelEspacioYDia")
    public List<HorarioEspacio> listarHorariosDelEspacioYDia(@WebParam(name = "idEspacio") int idEspacio,
            @WebParam(name = "fecha_yyyy_mm_dd") String fecha_yyyy_mm_dd) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(fecha_yyyy_mm_dd);
            return hService.listarHorarios(idEspacio, date);
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar un espacio: " + ex.getMessage());
        }
    }
}