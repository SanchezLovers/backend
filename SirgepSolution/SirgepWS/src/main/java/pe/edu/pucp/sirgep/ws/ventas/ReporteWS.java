package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;
import pe.edu.pucp.sirgep.business.ventas.impl.ReporteServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IReporteService;

@WebService(serviceName = "ReporteWS")
public class ReporteWS {

    private final IReporteService reporteService;

    public ReporteWS() {
        this.reporteService = new ReporteServiceImpl();
    }
    
    @WebMethod(operationName = "espaciosFavoritosDelMes")
    public List<EspacioRepDTO> espaciosFavoritosDelMes() {
        return reporteService.EspaciosFavMes();
    }
    
    @WebMethod(operationName = "reservasPorMes")
    public List<Integer> reservasPorMes() {
        return reporteService.cantidadReservasMes();
    }
    
    @WebMethod(operationName = "eventosFavoritosDelMes")
    public List<EspacioRepDTO> eventosFavoritosDelMes() {
        return reporteService.EventosFavMes();
    }
    
    @WebMethod(operationName = "entradasPorMes")
    public List<Integer> entradasPorMes(){
        return reporteService.cantidadEntradasMes();
    }
}