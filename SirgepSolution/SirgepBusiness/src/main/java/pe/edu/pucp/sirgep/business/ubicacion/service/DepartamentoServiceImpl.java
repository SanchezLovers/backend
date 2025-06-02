/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ubicacion.service;

import java.util.List;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DepartamentoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DepartamentoImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

public class DepartamentoServiceImpl {
    private final DepartamentoDAO departamentoDAO;

    public DepartamentoServiceImpl(){
        this.departamentoDAO=new DepartamentoImpl();
    }
    
    public int insertar(Departamento departamento) {
        return departamentoDAO.insertar(departamento);
    }

    public Departamento buscar(int id) {
        return departamentoDAO.buscar(id);
    }

    public List<Departamento> listar() {
        return departamentoDAO.listar();
    }

    public boolean actualizar(Departamento depa) {
        return departamentoDAO.actualizar(depa);
    }

    public boolean eliminarLogico(int id) {
        return departamentoDAO.eliminarLogico(id);
    }

    public boolean eliminarFisico(int id) {
        return departamentoDAO.eliminarFisico(id);
    }
}
