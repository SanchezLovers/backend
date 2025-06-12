package pe.edu.pucp.sirgep.da.ventas.dao;

import java.util.List;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EntradaDAO extends BaseDAO<Entrada>{
    List<Entrada>listarEntradasPorComprador(int IdComprador);
}