package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EspacioDTO;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDiaSemDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioDiaSemImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DepartamentoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.dao.ProvinciaDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DepartamentoImpl;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DistritoImpl;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.ProvinciaImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

public class EspacioServiceImpl implements IEspacioService {
    private final EspacioDAO espacioDAO;
    private final CompradorDAO compradorDAO;
    private final DepartamentoDAO depaDAO;
    private final ProvinciaDAO provDAO;
    private final DistritoDAO distDAO;
    private final EspacioDiaSemDAO diaSemDAO;

    public EspacioServiceImpl(){
        this.espacioDAO=new EspacioImpl();
        this.compradorDAO=new CompradorImpl();
        depaDAO = new DepartamentoImpl();
        provDAO = new ProvinciaImpl();
        distDAO = new DistritoImpl();
        diaSemDAO = new EspacioDiaSemImpl();
    }
    
    //CRUD
    @Override
    public int insertar(Espacio espacio) {
        return espacioDAO.insertar(espacio);
    }

    @Override
    public Espacio buscar(int id) {
        return espacioDAO.buscar(id);
    }

    @Override
    public List<Espacio> listar() {
        return espacioDAO.listar();
    }

    @Override
    public boolean actualizar(Espacio espacio) {
        return espacioDAO.actualizar(espacio);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return espacioDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return espacioDAO.eliminarFisico(id);
    }
    
    @Override
    public List<Espacio> buscarPorTexto(String texto) {
        return espacioDAO.buscarPorTexto(texto);
    }
    
    @Override
    public List<Espacio> buscarPorCategoria(String texto){
        return espacioDAO.buscarPorCategoria(texto);
    }
    @Override
    public List<Espacio> buscarPorDistrito(int id){
        return espacioDAO.buscarPorDistrito(id);
    }
    @Override
    public List<Espacio> buscarPorDistritoCate(int id, String cad){
        return espacioDAO.buscarPorDistritoCategoria(id, cad);
    }
    
    //Adicionales
    public void mapearDTO(EspacioDTO espDTO, Espacio esp, Departamento depa, Provincia prov, Distrito dist){
        /*Datos Espacio*/
        espDTO.setIdEspacio(esp.getIdEspacio());
        espDTO.setNombre(esp.getNombre());
        espDTO.setTipo(esp.getTipoEspacio());
        espDTO.setUbicacion(esp.getUbicacion());
        espDTO.setPrecioReserva(esp.getPrecioReserva());
        espDTO.setSuperficie(esp.getSuperficie());
        espDTO.setHoraInicio(esp.getHorarioInicioAtencion());
        espDTO.setHoraFin(esp.getHorarioFinAtencion());
        espDTO.setDias(diaSemDAO.listarPorEspacio(esp.getIdEspacio()));
        /*Datos Departamento*/
        espDTO.setIdDepartamento(depa.getIdDepartamento());
        espDTO.setNombreDepartamento(depa.getNombre());
        espDTO.setDepartamentos(depaDAO.listar());
        /*Datos Provincia*/
        espDTO.setIdProvincia(prov.getIdProvincia());
        espDTO.setNombreProvincia(prov.getNombre());
        espDTO.setProvincias(provDAO.listarPorDepa(depa.getIdDepartamento()));
        /*Datos Distrito*/
        espDTO.setIdDistrito(dist.getIdDistrito());
        espDTO.setNombreDistrito(dist.getNombre());
        espDTO.setDistritos(distDAO.listarPorProv(prov.getIdProvincia()));
    }
    
    @Override
    public EspacioDTO llenarEspacioDTOEdicion(int idEspacio){
        EspacioDTO espDTO = new EspacioDTO();
        Espacio esp = espacioDAO.buscar(idEspacio);
        Distrito dist = distDAO.buscar(esp.getDistrito().getIdDistrito());
        Provincia prov = provDAO.buscar(dist.getProvincia().getIdProvincia());
        Departamento depa = depaDAO.buscar(prov.getDepartamento().getIdDepartamento());
        // ----------------------------------------------------------------
        mapearDTO(espDTO,esp,depa,prov,dist);
        return espDTO;
    }
    
    @Override
    public boolean enviarCorreosCompradoresPorDistritoDeEspacio(String asunto, String contenido, int idDistrito) {
        try {
            List<String> listaCorreosCompradores = compradorDAO.listarPorDistritoFavorito(idDistrito);
            if (listaCorreosCompradores != null && !listaCorreosCompradores.isEmpty()) {
                EnvioCorreo correo = new EnvioCorreo();
                boolean resultado = correo.enviarEmail(listaCorreosCompradores, asunto, contenido);
                if (!resultado) {
                    throw new RuntimeException("No se enviaron los correos a los compradores con el mismo distrito del evento");
                }
                return true;
            } else {
                throw new RuntimeException("No hay compradores con el mismo distrito favorito (lista vacía o null)");
            }
        } catch (Exception ex) {
            ex.printStackTrace();  // ✅ Asegúrate que esto sí esté en el log del servidor
            throw new RuntimeException("Error técnico: " + ex.getClass().getName() + " - " + ex.getMessage(), ex);
        }
    }
}