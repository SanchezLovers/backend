package pe.edu.pucp.sirgep.da.ventas.dao;
import java.sql.SQLException;
import java.util.Date;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface ReservaDAO extends BaseDAO<Reserva>{

    List<Reserva> listarPorDiaYEspacio(int idEspacio, Date fecha);

    List<Map<String, Object>>listarDetalleReservasPorComprador(int IdComprador);

    public List<Reserva> listarPorFecha(Date fecha, boolean activo);
    public List<Reserva> listarPorHorario(String horaInicio, String horaFin, Date fecha, boolean activo);
    public List<Reserva> listarPorDistrito(int id_distrito, boolean activo);
    public List<Reserva> listarPorEspacio(int id_espacio, boolean activo);
    public List<Reserva> listarPorPersona(int id_persona, boolean activo);
    public boolean cancelarReserva(int id) throws SQLException;
}