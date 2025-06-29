/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.sirgep.ws.ventas;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EspacioDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;
import pe.edu.pucp.sirgep.business.ventas.impl.ReporteServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IReporteService;

/**
 *
 * @author Ana Gabriela
 */
@WebService(serviceName = "ReporteWS")
public class ReporteWS {

    private final IReporteService reporteService;

    public ReporteWS() {
        this.reporteService = new ReporteServiceImpl();
    }
    /**
     * This is a sample web service operation
     * @return 
     */
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
