/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.business.ventas.dtos.EspacioRepDTO;
import pe.edu.pucp.sirgep.business.ventas.service.IReporteService;
import pe.edu.pucp.sirgep.da.ventas.dao.ReporteDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReporteImpl;

/**
 *
 * @author Ana Gabriela
 */
public class ReporteServiceImpl implements IReporteService{

    
    private ReporteDAO rDao;
    public ReporteServiceImpl() {
        rDao = new ReporteImpl();
    }

    @Override
    public List<Integer> cantidadReservasMes() {
        List<Integer> cant = rDao.cantidadReservasMes();
        return cant;
    }

    @Override
    public List<EspacioRepDTO> EspaciosFavMes() {
        List<EspacioRepDTO> espacios = new ArrayList<>();
        Map<String, Integer> mapita = rDao.EspaciosFavMes();
        EspacioRepDTO e;
        
        for (Map.Entry<String, Integer> entry: mapita.entrySet()) {
            e = new EspacioRepDTO(entry.getKey(), entry.getValue());
            espacios.add(e);
        }
        return espacios;
    }

    @Override
    public List<Integer> cantidadEntradasMes(){
        List<Integer> cant = rDao.cantidadEntradasMes();
        return cant;
    }
    
    @Override
    public List<EspacioRepDTO> EventosFavMes(){
        List<EspacioRepDTO> eventos = new ArrayList<>();
        Map<String, Integer> mapita = rDao.EventosFavMes();
        EspacioRepDTO e;
        
        for (Map.Entry<String, Integer> entry: mapita.entrySet()) {
            e = new EspacioRepDTO(entry.getKey(), entry.getValue());
            eventos.add(e);
        }
        return eventos;
    }
    
}
