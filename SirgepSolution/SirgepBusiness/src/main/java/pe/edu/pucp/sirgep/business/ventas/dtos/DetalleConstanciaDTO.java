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
        this.setNombresComprador((String) detalle.get("nombresComprador"));
        this.setApellidosComprador(((String) detalle.get("apellidosComprador")));
        this.setCorreo((String) detalle.get("correo"));
        this.setTipoDocumento((String) detalle.get("tipoDocumento"));
        this.setNumDocumento((String) detalle.get("numDocumento"));
        this.setFecha((Date) detalle.get("fecha"));
        this.setMetodoPago((String) detalle.get("metodoPago"));
        this.setMonto((double) detalle.get("monto"));
        this.setDetallePago((String) detalle.get("detallePago"));
    }
}