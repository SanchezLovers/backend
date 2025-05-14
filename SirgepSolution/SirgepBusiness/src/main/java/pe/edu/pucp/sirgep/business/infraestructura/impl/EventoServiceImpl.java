package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public class EventoServiceImpl implements IEventoService {
    
    private final EventoDAO eventoDAO;

    public EventoServiceImpl(){
        this.eventoDAO=new EventoImpl();
    }
    
    @Override
    public int insertar(Evento evento) {
        return eventoDAO.insertar(evento);
    }

    @Override
    public Evento buscar(int id) {
        return eventoDAO.buscar(id);
    }

    @Override
    public List<Evento> listar() {
        return eventoDAO.listar();
    }

    @Override
    public boolean actualizar(Evento evento) {
        return eventoDAO.actualizar(evento);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return eventoDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return eventoDAO.eliminarFisico(id);
    }
}