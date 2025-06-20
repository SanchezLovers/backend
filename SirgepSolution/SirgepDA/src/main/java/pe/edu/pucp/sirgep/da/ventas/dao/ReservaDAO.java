package pe.edu.pucp.sirgep.da.ventas.dao;
import java.time.LocalDate;
import java.util.Date;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface ReservaDAO extends BaseDAO<Reserva>{

    List<Reserva> listarPorDiaYEspacio(int idEspacio, Date fecha);

    List<Map<String, Object>>listarDetalleReservasPorComprador(int IdComprador);

    List<Map<String, Object>> listarTodos();
    List<Map<String, Object>> listarDetalleReservasPorFecha(Date fecha, boolean activo);
    List<Map<String, Object>> listarPorDistrito(int idDistrito, boolean activo);
}