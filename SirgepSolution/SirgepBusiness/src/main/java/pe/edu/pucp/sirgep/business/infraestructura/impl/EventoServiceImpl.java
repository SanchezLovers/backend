package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EventoDTO;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;

public class EventoServiceImpl implements IEventoService {
    private final EventoDAO eventoDAO;
    private final CompradorDAO compradorDAO;

    public EventoServiceImpl(){
        this.eventoDAO=new EventoImpl();
        this.compradorDAO=new CompradorImpl();
    }
    
    //CRUD
    @Override
    public int insertar(Evento evento) {
        return eventoDAO.insertar(evento);
    }

    @Override
    public boolean inactivar() {
        return eventoDAO.inactivar();
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
    
    @Override
    public List<Evento> listarPorDistrito(int id) {
        List<Evento> eventosGeneral = eventoDAO.listar();
        List<Evento> eventosDelDistrito;
        eventosDelDistrito = new ArrayList<>();
        for (int i = 0; i < eventosGeneral.size(); i++) {
            Evento e = eventosGeneral.get(i);
            if (e.getDistrito().getIdDistrito()== id)
                eventosDelDistrito.add(e);
        }
        return eventosDelDistrito;
    }

    @Override
    public List<Evento> buscarPorTexto(String texto) {
        return eventoDAO.buscarPorTexto(texto);
    }

    @Override
    public List<Evento> buscarEventosPorFechas(String inicio, String fin) {
        return eventoDAO.buscarEventosPorFechas(inicio, fin);
    }
    
    //Adicionales
    @Override
    public boolean enviarCorreosCompradoresPorDistritoDeEvento(String asunto, String contenido,int idDistrito){
        try{
            List<String>listaCorreosCompradores=compradorDAO.listarPorDistritoFavorito(idDistrito);
            if (listaCorreosCompradores != null && !listaCorreosCompradores.isEmpty()) {
                boolean resultado=EnvioCorreo.getInstance().enviarEmail(listaCorreosCompradores,asunto,contenido);
                if (resultado) {
                    return true;
                }
            }
            return false;
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error al enviar correos a compradores con el mismo distrito del evento: "+ ex.getMessage());
        }
    }
    // listado de DTOS
    @Override
    public EventoDTO listarEventosDTO(int idEvento){
        Map<String, Object> mapaEventoDTO = eventoDAO.listarEventosDTO(idEvento);
        EventoDTO e = new EventoDTO();
        e.llenarEventoDTO(mapaEventoDTO); // el primero
        return e;
    }
}