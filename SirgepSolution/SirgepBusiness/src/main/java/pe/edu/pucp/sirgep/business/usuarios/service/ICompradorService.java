package pe.edu.pucp.sirgep.business.usuarios.service;

import java.util.List;
import pe.edu.pucp.sirgep.business.usuarios.dtos.DetalleComprador;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public interface ICompradorService{
    //Metodos CRUD
    int insertar(Comprador comprador);
    Comprador buscar(int id);
    List<Comprador> listar();
    boolean actualizar(Comprador comprador);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    
    //Metodos adicionales
    Comprador buscarPorDni(String dni);
    public boolean validarCorreo(String correo);

    
    //Metodos del perfil del comprador
    public DetalleComprador buscarDetalleCompradorPorId(int idComprador);
    public boolean actualizarDistritoFavoritoPorIdComprador(String nuevoDistrito,int idComprador);
}