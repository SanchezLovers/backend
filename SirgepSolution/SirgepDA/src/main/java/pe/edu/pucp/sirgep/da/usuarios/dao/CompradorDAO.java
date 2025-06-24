package pe.edu.pucp.sirgep.da.usuarios.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public interface CompradorDAO extends BaseDAO<Comprador>{
    public Comprador buscarPorDni(String dni);
    public List<String> listarPorDistritoFavorito(int idDistrito);
    Date obtenerUltimaCompraPorDocumento(String numeroDocumento);
    List<Map<String, Object>> listarCompradoresDTO();//
    
    //Metodos para el Perfil del Comprador
    public Map<String, Object> buscarDetalleCompradorPorId(int idComprador);
    public boolean actualizarDistritoFavoritoPorIdComprador(String nuevoDistrito, int idComprador);
    
    //Creacion de cuenta
    public boolean validarCorreo(String correo);

}