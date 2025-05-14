/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.service.IReservaService;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

/**
 *
 * @author benny
 */
public class ReservaServiceImpl implements IReservaService {

    private final ReservaDAO reservaDAO;
    
    public ReservaServiceImpl(){
        reservaDAO = new ReservaImpl();
    }
    
    @Override
    public int insertar(Reserva reserva) {
        return reservaDAO.insertar(reserva);
    }

    @Override
    public Reserva buscar(int id) {
        return reservaDAO.buscar(id);
    }

    @Override
    public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    @Override
    public boolean actualizar(Reserva reserva) {
        return reservaDAO.actualizar(reserva);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return reservaDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return reservaDAO.eliminarFisico(id);
    }
}
