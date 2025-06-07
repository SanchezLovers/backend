/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ubicacion.service;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DistritoImpl;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

public class DistritoServiceImpl {
    private final DistritoDAO distritoDAO;

    public DistritoServiceImpl(){
        this.distritoDAO=new DistritoImpl();
    }
    public List<Distrito> listarPorDepartamento(int id) {
        List<Distrito> distritosGeneral = distritoDAO.listar();
        List<Distrito> distritosDelDepa;
        distritosDelDepa = new ArrayList<>();
        for (int i = 0; i < distritosGeneral.size(); i++) {
            Distrito p = distritosGeneral.get(i);
            if (p.getProvincia().getIdProvincia()== id)
                distritosDelDepa.add(p);
        }
        return distritosDelDepa;
    }
    
    public Distrito buscar(int id){
        return distritoDAO.buscar(id);
    }

}
