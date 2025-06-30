package pe.edu.pucp.sirgep.ws.infraestructura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EspacioDiaSemServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioDiaSemService;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

@WebService(serviceName = "EspacioDiaSemWS", targetNamespace = "pe.edu.pucp.sirgep")
public class EspacioDiaSemWS {
    private final IEspacioDiaSemService diaService;
    
    public EspacioDiaSemWS(){
        diaService = new EspacioDiaSemServiceImpl();
    }
    
    @WebMethod(operationName = "insertarDia")
    public boolean insertarDiaSem(@WebParam (name = "diaSem") EspacioDiaSem entity){
        try{
            return diaService.insertarDiaSem(entity);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR AL INSERTAR UN DIA DE LA SEMANA" + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarDiasSemDeEspacio")
    public List<EspacioDiaSem> listarDiasSemPorEspacio(@WebParam (name = "idEspacio") int idEspacio){
        try{
            return diaService.listarDiasSemPorEspacio(idEspacio);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR AL INSERTAR UN DIA DE LA SEMANA" + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarDiasPorEspacio")
    public boolean eliminarDiasPorEspacio(@WebParam (name = "idEspacio") int idEspacio){
        try{
            return diaService.eliminarDiasPorEspacio(idEspacio);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR AL INSERTAR UN DIA DE LA SEMANA" + ex.getMessage());
        }
    }
}