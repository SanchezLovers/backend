package pe.edu.pucp.sirgep.business.usuarios.impl;

import pe.edu.pucp.sirgep.business.usuarios.service.IAdministradorService;
import pe.edu.pucp.sirgep.da.usuarios.dao.AdministradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.AdministradorImpl;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;

public class AdministradorServiceImpl implements IAdministradorService{

    private final AdministradorDAO administradorDAO;

    public AdministradorServiceImpl(){
        this.administradorDAO=new AdministradorImpl();
    }
    
    @Override
    public Administrador buscar(int id) {
        return administradorDAO.buscar(id);
    }
}