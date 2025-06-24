package pe.edu.pucp.sirgep.da.ventas.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface ReservaDAO extends BaseDAO<Reserva>{
    List<Map<String, Object>>listarDetalleReservasPorComprador(int IdComprador);

    public boolean cancelarReserva(int id) throws SQLException;

    List<Reserva> listarPorMesYAnio(int mes, int anio);
    List<Reserva> listarPorDiaYEspacio(int idEspacio, Date fecha);
    List<Map<String, Object>> listarTodos();
    List<Map<String, Object>> listarDetalleReservasPorFecha(Date fecha, boolean activo);
    List<Map<String, Object>> listarPorDistrito(int idDistrito, boolean activo);
    //Metodos para buscar la constancia de la reserva
    void llenarMapaReserva(Map<String, Object>reserva,ResultSet rs);
    void llenarMapaDetalleReserva(Map<String, Object>detalleReserva,ResultSet rs);
    Map<String, Object> buscarConstanciaReserva(int numReserva);
}