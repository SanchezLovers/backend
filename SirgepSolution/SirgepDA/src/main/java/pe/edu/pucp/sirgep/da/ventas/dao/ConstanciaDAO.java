package pe.edu.pucp.sirgep.da.ventas.dao;

import java.sql.ResultSet;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import java.util.Map;

public interface ConstanciaDAO extends BaseDAO<Constancia> {
    public void llenarMapaDetalleConstancia(Map<String, Object>detalleConstancia,ResultSet rs);
}