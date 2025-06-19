package pe.edu.pucp.sirgep.business.infraestructura.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public class EspacioServiceImpl implements IEspacioService {
    private final EspacioDAO espacioDAO;
    private final CompradorDAO compradorDAO;

    public EspacioServiceImpl(){
        this.espacioDAO=new EspacioImpl();
        this.compradorDAO=new CompradorImpl();
    }
    
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
    
    //Metodo para el envio de correo a los compradores registrados con el mismo distrito
    @Override
    public boolean enviarCorreosCompradoresPorDistritoDeEspacio(Espacio espacio){
        boolean resultado=false;
        try{
            List<String>listaCorreosCompradores=compradorDAO.listarPorDistritoFavorito(espacio.getDistrito().getIdDistrito());
            if (listaCorreosCompradores != null) {
                EnvioCorreo correo=new EnvioCorreo();
                String asunto="";
                String contenido="";
                String rutaLogo="";
                resultado=correo.enviarEmail(listaCorreosCompradores,asunto,contenido,rutaLogo);
                if (!resultado) {
                    throw new RuntimeException("No se enviaron los correos");
                }
            }
        }catch(Exception ex){
            throw new RuntimeException("Error al enviar correos a compradores con el mismo distrito del espacio: "+ ex.getMessage());
        }finally{
            return resultado;
        }
    }
}