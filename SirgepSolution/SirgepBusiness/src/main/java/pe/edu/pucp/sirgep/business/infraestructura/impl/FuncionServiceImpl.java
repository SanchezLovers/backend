package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.service.IFuncionService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

public class FuncionServiceImpl implements IFuncionService {
    private final FuncionDAO funcionDAO;

    public FuncionServiceImpl(){
        this.funcionDAO=new FuncionImpl();
    }
    
    @Override
    public int insertar(Funcion funcion) {
        return funcionDAO.insertar(funcion);
    }

    @Override
    public Funcion buscar(int id) {
        return funcionDAO.buscar(id);
    }
    
    @Override
    public boolean inactivar() {
        return funcionDAO.inactivar();
    }

    @Override
    public List<Funcion> listar() {
        return funcionDAO.listar();
    }

    @Override
    public boolean actualizar(Funcion funcion) {
        return funcionDAO.actualizar(funcion);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return funcionDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return funcionDAO.eliminarFisico(id);
    }

    @Override
    public List<Funcion> listarPorIdEvento(int idEvento) {
        return funcionDAO.listarPorIdEvento(idEvento);
    }
}