package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;
import java.util.Map;

public class DetalleConstanciaDTO {
    //Atributos
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
    
    //Metodos
    public void llenarDetalleConstancia(Map<String, Object> detalle) {
        if (detalle.get("nombresComprador") != null) {
            this.setNombresComprador((String) detalle.get("nombresComprador"));
        }
        if (detalle.get("apellidosComprador") != null) {
            this.setApellidosComprador((String) detalle.get("apellidosComprador"));
        }
        if (detalle.get("correo") != null) {
            this.setCorreo((String) detalle.get("correo"));
        }
        if (detalle.get("tipoDocumento") != null) {
            this.setTipoDocumento((String) detalle.get("tipoDocumento"));
        }
        if (detalle.get("numDocumento") != null) {
            this.setNumDocumento((String) detalle.get("numDocumento"));
        }
        if (detalle.get("fecha") != null) {
            this.setFecha((Date) detalle.get("fecha"));
        }
        if (detalle.get("metodoPago") != null) {
            this.setMetodoPago((String) detalle.get("metodoPago"));
        }
        if (detalle.get("monto") != null) {
            this.setMonto((double) detalle.get("monto"));
        }
        if (detalle.get("detallePago") != null) {
            this.setDetallePago((String) detalle.get("detallePago"));
        }
    }
}