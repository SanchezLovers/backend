package pe.edu.pucp.sirgep.da.ventas.implementacion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.da.ventas.dao.ConstanciaDAO;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;

public class ConstanciaImpl extends BaseImpl<Constancia> implements ConstanciaDAO{
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Constancia(fecha, metodo_pago, igv, total, detalle_pago, activo) VALUES(?,?,?,?,?,?)";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT fecha, metodo_pago, igv, total, detalle_pago, activo FROM Constancia WHERE id_constancia = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id_constancia, fecha, metodo_pago, igv, total, detalle_pago, activo FROM Constancia";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Constancia SET "
                + "fecha = ?,"
                + "metodo_pago = ?,"
                + "igv = ?,"
                + "total = ?,"
                + "detalle_pago = ?"
                + " WHERE id_constancia = ?";
    }

    @Override
    protected String getDeleteLogicoQuery() {
        return "UPDATE Constancia SET activo = 'E' WHERE id_constancia = ?";
    }
        
    @Override
    protected String getDeleteFisicoQuery() {
        return "DELETE FROM Constancia WHERE id_constancia = ?";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Constancia constancia) {
        try{
            ps.setDate(1, new Date(constancia.getFecha().getTime()));
            ps.setString(2, constancia.getMetodoPago().toString());
            ps.setDouble(3, constancia.getIgv());
            ps.setDouble(4, constancia.getTotal());
            ps.setString(5, constancia.getDetallePago());
            ps.setString(6, String.valueOf('A')); // es activo
        }catch(SQLException e){
            System.out.println("Se encontro un error a la hora de insertar parametros: " + e.getMessage());
        }
    }

    @Override
    protected Constancia createFromResultSet(ResultSet rs) {
        Constancia aux = new Constancia();
        try{
            aux.setFecha(rs.getDate("fecha"));
            aux.setMetodoPago(EMetodoPago.valueOf(rs.getString("metodo_pago")));
            aux.setIgv(rs.getDouble("igv"));
            aux.setTotal(rs.getDouble("total"));
            aux.setDetallePago(rs.getString("detalle_pago"));
            //aux.set(rs.getString("activo").charAt(0)); preguntar sobre el activo?
        }
        catch(SQLException e){
            System.out.println("Se encontro un error a la hora de crear desde RS: " + e.getMessage());
        }
        return aux;
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Constancia entity) {
        try{
            ps.setDate(1, new Date(entity.getFecha().getTime()));
            ps.setString(2, entity.getMetodoPago().toString());
            ps.setDouble(3, entity.getIgv());
            ps.setDouble(4, entity.getTotal());
            ps.setString(5, entity.getDetallePago());
            ps.setInt(6, entity.getIdConstancia());
        }catch(SQLException e){
            System.out.println("Se encontro un error a la hora de MODIFICAR tabla: " + e.getMessage());
        }
    }
    @Override
    protected void setId(Constancia entity, int id) {
        entity.setIdConstancia(id);
    }
    
    @Override
    public void llenarMapaDetalleConstancia(Map<String, Object> detalleConstancia, ResultSet rs) {
        try {
            agregarIntSiNoNulo(detalleConstancia, rs, "id_constancia", "idConstancia");
            agregarStringSiNoNulo(detalleConstancia, rs, "nombres_comprador", "nombresComprador");
            agregarApellidos(detalleConstancia, rs);
            agregarStringSiNoNulo(detalleConstancia, rs, "correo", "correo");
            agregarStringSiNoNulo(detalleConstancia, rs, "tipo_documento", "tipoDocumento");
            agregarStringSiNoNulo(detalleConstancia, rs, "num_documento", "numDocumento");
            agregarDateSiNoNulo(detalleConstancia, rs, "fecha", "fechaConstancia");
            agregarStringSiNoNulo(detalleConstancia, rs, "metodo_pago", "metodoPago");
            agregarMonto(detalleConstancia, rs);
            agregarStringSiNoNulo(detalleConstancia, rs, "detalle_pago", "detallePago");
        } catch (SQLException ex) {
            throw new RuntimeException("Error al llenar el mapa del detalle de la constancia: " + ex.getMessage(), ex);
        }
    }

    private void agregarIntSiNoNulo(Map<String, Object> mapa, ResultSet rs, String columna, String claveMapa) throws SQLException {
        String temp = rs.getString(columna);
        if (temp != null) {
            mapa.put(claveMapa, rs.getInt(columna));
        }
    }

    private void agregarStringSiNoNulo(Map<String, Object> mapa, ResultSet rs, String columna, String claveMapa) throws SQLException {
        String valor = rs.getString(columna);
        if (valor != null) {
            mapa.put(claveMapa, valor);
        }
    }

    private void agregarDateSiNoNulo(Map<String, Object> mapa, ResultSet rs, String columna, String claveMapa) throws SQLException {
        java.sql.Date fecha = rs.getDate(columna);
        if (fecha != null) {
            mapa.put(claveMapa, fecha);
        }
    }

    private void agregarApellidos(Map<String, Object> mapa, ResultSet rs) throws SQLException {
        String primerApellido = rs.getString("primer_apellido");
        String segundoApellido = rs.getString("segundo_apellido");
        if (primerApellido != null || segundoApellido != null) {
            mapa.put("apellidosComprador",
                    (primerApellido != null ? primerApellido : "") + " "+ (segundoApellido != null ? segundoApellido : ""));
        }
    }

    private void agregarMonto(Map<String, Object> mapa, ResultSet rs) throws SQLException {
        double monto = rs.getDouble("total");
        if (!rs.wasNull()) {
            mapa.put("monto", monto);
        }
    }
}