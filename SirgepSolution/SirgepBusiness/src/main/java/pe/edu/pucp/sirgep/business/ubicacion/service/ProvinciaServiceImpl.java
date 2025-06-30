package pe.edu.pucp.sirgep.business.ubicacion.service;

import java.util.List;
import pe.edu.pucp.sirgep.da.ubicacion.dao.ProvinciaDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.ProvinciaImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

public class ProvinciaServiceImpl {
    private final ProvinciaDAO provinciaDAO;

    public ProvinciaServiceImpl(){
        this.provinciaDAO=new ProvinciaImpl();
    }
    public List<Provincia> listarPorDepartamento(int id) {
        try{
            return provinciaDAO.listarPorDepa(id);
        }
        catch(Exception ex){
            throw new RuntimeException("ERROR AL LISTAR PROVINCIAS POR DEPARTAMENTO ..." + ex.getMessage());
        }
    }
    
    public Provincia buscar(int id){
        return provinciaDAO.buscar(id);
    }

}
