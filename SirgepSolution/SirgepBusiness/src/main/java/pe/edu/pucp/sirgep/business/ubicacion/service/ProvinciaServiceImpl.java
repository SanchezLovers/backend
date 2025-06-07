/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ubicacion.service;

import java.util.ArrayList;
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
        List<Provincia> provinciasGeneral = provinciaDAO.listar();
        List<Provincia> provinciasDelDepa;
        provinciasDelDepa = new ArrayList<>();
        for (int i = 0; i < provinciasGeneral.size(); i++) {
            Provincia p = provinciasGeneral.get(i);
            if (p.getDepartamento().getIdDepartamento() == id)
                provinciasDelDepa.add(p);
        }
        return provinciasDelDepa;
    }
    
    public Provincia buscar(int id){
        return provinciaDAO.buscar(id);
    }

}
