package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.da.ventas.dao.EntradaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.EntradaImpl;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

public class EntradaServiceImpl implements IEntradaService{

    private final EntradaDAO entradaDAO;
    
    public EntradaServiceImpl(){
        entradaDAO = new EntradaImpl();
    }
    
    @Override
    public int insertar(Entrada entrada) {
        return entradaDAO.insertar(entrada);
    }

    @Override
    public Entrada buscar(int id) {
        return entradaDAO.buscar(id);
    }

    @Override
    public List<Entrada> listar() {
        return entradaDAO.listar();
    }

    @Override
    public boolean actualizar(Entrada entrada) {
        return entradaDAO.actualizar(entrada);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return entradaDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return entradaDAO.eliminarFisico(id);
    }
}