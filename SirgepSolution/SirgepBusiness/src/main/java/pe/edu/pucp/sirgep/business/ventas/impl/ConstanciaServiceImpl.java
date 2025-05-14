/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.service.IConstanciaService;
import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ConstanciaImpl;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

/**
 *
 * @author benny
 */
public class ConstanciaServiceImpl implements IConstanciaService {

    private final ConstanciaDAO constanciaDAO;
    
    public ConstanciaServiceImpl(){
        constanciaDAO = new ConstanciaImpl();
    }
    
    @Override
    public int insertar(Constancia constancia) {
        return constanciaDAO.insertar(constancia);
    }

    @Override
    public Constancia buscar(int id) {
        return constanciaDAO.buscar(id);
    }

    @Override
    public List<Constancia> listar() {
        return constanciaDAO.listar();
    }

    @Override
    public boolean actualizar(Constancia constancia) {
        return constanciaDAO.actualizar(constancia);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return constanciaDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return constanciaDAO.eliminarFisico(id);
    }
    
}
