package pe.edu.pucp.sirgep.da.ventas.dao;
import java.util.List;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface ReservaDAO extends BaseDAO<Reserva>{
    public List<Reserva> listarReservasPorComprador(int IdComprador);
}