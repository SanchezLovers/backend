package pe.edu.pucp.sirgep.da.usuarios.dao;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

public interface CompradorDAO extends BaseDAO<Comprador>{
    public Comprador buscarPorDni(String dni);
}