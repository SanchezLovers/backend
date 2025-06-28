package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;
import java.util.Map;

public class DetalleConstanciaDTO {
    //Atributos
    private int idConstancia;
    private String nombresComprador;
    private String apellidosComprador;
    private String correo;
    private String tipoDocumento;
    private String numDocumento;
    private Date fecha;
    private String metodoPago;
    private double monto;
    private String detallePago;

    //Constructor
    public DetalleConstanciaDTO() {
    }

    //Propiedades
    public int getIdConstancia() {
        return idConstancia;
    }
    public String getNombresComprador() {
        return nombresComprador;
    }
    public String getApellidosComprador() {
        return apellidosComprador;
    }
    public String getCorreo() {
        return correo;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public String getNumDocumento() {
        return numDocumento;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public double getMonto() {
        return monto;
    }
    public String getDetallePago() {
        return detallePago;
    }
    public void setNombresComprador(String nombresComprador) {
        this.nombresComprador = nombresComprador;
    }
    public void setApellidosComprador(String apellidosComprador) {
        this.apellidosComprador = apellidosComprador;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public void setDetallePago(String detallePago) {
        this.detallePago = detallePago;
    }
    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }
    
    //Metodos
    public void llenarDetalleConstancia(Map<String, Object> detalle) {
        try {
            setIntSiExiste(detalle, "idConstancia", this::setIdConstancia);
            setStringSiExiste(detalle, "nombresComprador", this::setNombresComprador);
            setStringSiExiste(detalle, "apellidosComprador", this::setApellidosComprador);
            setStringSiExiste(detalle, "correo", this::setCorreo);
            setStringSiExiste(detalle, "tipoDocumento", this::setTipoDocumento);
            setStringSiExiste(detalle, "numDocumento", this::setNumDocumento);
            setDateUtilSiExiste(detalle, "fechaConstancia", this::setFecha);
            setStringSiExiste(detalle, "metodoPago", this::setMetodoPago);
            setDoubleSiExiste(detalle, "monto", this::setMonto);
            setStringSiExiste(detalle, "detallePago", this::setDetallePago);
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la constancia: " + ex.getMessage(), ex);
        }
    }

    private void setIntSiExiste(Map<String, Object> map, String key, java.util.function.IntConsumer setter) {
        if (map.get(key) != null) {
            setter.accept((int) map.get(key));
        }
    }

    private void setStringSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<String> setter) {
        if (map.get(key) != null) {
            setter.accept((String) map.get(key));
        }
    }

    private void setDateUtilSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<java.util.Date> setter) {
        if (map.get(key) != null) {
            setter.accept((java.util.Date) map.get(key));
        }
    }

    private void setDoubleSiExiste(Map<String, Object> map, String key, java.util.function.DoubleConsumer setter) {
        if (map.get(key) != null) {
            setter.accept((double) map.get(key));
        }
    }
}