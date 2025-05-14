package pe.edu.pucp.sirgep.business.usuarios.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.usuarios.service.ICompradorService;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public class CompradorServiceImpl implements ICompradorService{

    private final CompradorDAO compradorDAO;

    public CompradorServiceImpl(){
        this.compradorDAO=new CompradorImpl();
    }
    
    @Override
    public int insertar(Comprador comprador) {
        return compradorDAO.insertar(comprador);
    }

    @Override
    public Comprador buscar(int id) {
        return compradorDAO.buscar(id);
    }

    @Override
    public List<Comprador> listar() {
        return compradorDAO.listar();
    }

    @Override
    public boolean actualizar(Comprador comprador) {
        return compradorDAO.actualizar(comprador);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return compradorDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return compradorDAO.eliminarFisico(id);
    }
}