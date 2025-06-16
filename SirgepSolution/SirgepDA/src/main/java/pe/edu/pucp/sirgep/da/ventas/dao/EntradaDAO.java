package pe.edu.pucp.sirgep.da.ventas.dao;

import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EntradaDAO extends BaseDAO<Entrada>{
    List<Map<String, Object>>listarDetalleEntradasPorComprador(int IdComprador);
}