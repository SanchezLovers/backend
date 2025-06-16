package pe.edu.pucp.sirgep.da.ventas.dao;
import java.util.Date;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface ReservaDAO extends BaseDAO<Reserva>{

    List<Reserva> listarPorDiaYEspacio(int idEspacio, Date fecha);

    List<Map<String, Object>>listarDetalleReservasPorComprador(int IdComprador);

}