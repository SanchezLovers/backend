package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

public class EspacioServiceImpl implements IEspacioService {
    
    private final EspacioDAO espacioDAO;

    public EspacioServiceImpl(){
        this.espacioDAO=new EspacioImpl();
    }
    
    @Override
    public int insertar(Espacio espacio) {
        return espacioDAO.insertar(espacio);
    }

    @Override
    public Espacio buscar(int id) {
        return espacioDAO.buscar(id);
    }

    @Override
    public List<Espacio> listar() {
        return espacioDAO.listar();
    }

    @Override
    public boolean actualizar(Espacio espacio) {
        return espacioDAO.actualizar(espacio);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return espacioDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return espacioDAO.eliminarFisico(id);
    }
    
    @Override
    public List<Espacio> buscarPorTexto(String texto) {
        return espacioDAO.buscarPorTexto(texto);
    }
}