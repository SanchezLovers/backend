package pe.edu.pucp.sirgep.business.usuarios.impl;

import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.business.usuarios.dtos.DetalleComprador;
import pe.edu.pucp.sirgep.business.usuarios.service.ICompradorService;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public class CompradorServiceImpl implements ICompradorService{
    private final CompradorDAO compradorDAO;
    
    public CompradorServiceImpl(){
        this.compradorDAO=new CompradorImpl();
        
    }
    
    //Metodos CRUD
    @Override
    public int insertar(Comprador comprador) {
        return compradorDAO.insertar(comprador);
    }
    @Override
    public Comprador buscar(int id) {
        return compradorDAO.buscar(id);
    }
    @Override
    public List<Comprador> listar() {
        return compradorDAO.listar();
    }
    @Override
    public boolean actualizar(Comprador comprador) {
        return compradorDAO.actualizar(comprador);
    }
    @Override
    public boolean eliminarLogico(int id) {
        return compradorDAO.eliminarLogico(id);
    }
    @Override
    public boolean eliminarFisico(int id) {
        return compradorDAO.eliminarFisico(id);
    }
    
    //Metodos adicionales
    @Override
    public Comprador buscarPorDni(String dni) {
        return compradorDAO.buscarPorDni(dni);
    }
    
    //Metodos del perfil del comprador
    @Override
    public DetalleComprador buscarDetalleCompradorPorId(int idComprador) {
        DetalleComprador detalle=null;
        try {
            Map<String, Object> fila = compradorDAO.buscarDetalleCompradorPorId(idComprador);
            if (fila != null) {
                detalle = new DetalleComprador();
                detalle.setIdComprador((int) fila.get("idComprador"));
                detalle.setTipoDocumento((String) fila.get("tipoDocumento"));
                detalle.setNumeroDocumento((String) fila.get("numeroDocumento"));
                detalle.setMontoBilletera((double) fila.get("montoBilletera"));
                detalle.setNombres((String) fila.get("nombres"));
                detalle.setPrimerApellido((String) fila.get("primerApellido"));
                detalle.setSegundoApellido((String) fila.get("segundoApellido"));
                detalle.setDistritoFavorito((String) fila.get("distritoFavorito"));
                detalle.setProvinciaFavorita((String) fila.get("provinciaFavorita"));
                detalle.setDepartamentoFavorito((String) fila.get("departamentoFavorito"));
                detalle.setCorreo((String) fila.get("correo"));
                detalle.setContrasenia((String) fila.get("contrasenia"));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el detalle del comprador: " + ex.getMessage());
        }finally{
            return detalle;
        }
    }
    
    @Override
    public boolean actualizarDistritoFavoritoPorIdComprador(String nuevoDistrito,int idComprador){
        return compradorDAO.actualizarDistritoFavoritoPorIdComprador(nuevoDistrito,idComprador);
    }
    
    @Override
    public boolean validarCorreo(String correo){
        return compradorDAO.validarCorreo(correo);
    }
}