package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.service.IReservaService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DistritoImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class ReservaServiceImpl implements IReservaService {
    private final ReservaDAO reservaDAO;
    private final CompradorDAO compradorDAO;
    private final EspacioDAO espacioDAO;
    private final DistritoDAO distritoDAO;
    
    public ReservaServiceImpl(){
        reservaDAO = new ReservaImpl();
        compradorDAO = new CompradorImpl();
        espacioDAO = new EspacioImpl() ;
        distritoDAO = new DistritoImpl();
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
    
    //Metodos adicionales
    @Override
    public Comprador buscarCompradorDeReserva(int idComprador){
        try {
            return compradorDAO.buscar(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la reserva: " + ex.getMessage());
        }
    }
    
    @Override
    public Espacio buscarEspacioDeReserva(int idEspacio){
        boolean resultado=false;
        try {
            return espacioDAO.buscar(idEspacio);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la espacio de la reserva: " + ex.getMessage());
        }
    }
    
    @Override
    public Distrito buscarDistritoDeReserva(int idEntrada){
        Distrito resultado=null;
        try {
            resultado=distritoDAO.buscar(idEntrada);
            if(resultado==null){
                throw new RuntimeException("Error al buscar el distrito de la reserva: ");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la reserva: " + ex.getMessage());
        }
    }
}