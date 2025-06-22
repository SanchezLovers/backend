/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDiaSemDAO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

/**
 *
 * @author benny
 */
public interface IEspacioDiaSemService{
    List<EspacioDiaSem> listarDiasSem();
    List<EspacioDiaSem> listarDiasSemPorEspacio(int idEspacio);
    boolean insertarDiaSem(EspacioDiaSem entity);
    public boolean eliminarDiasPorEspacio(int idEspacio);
}
