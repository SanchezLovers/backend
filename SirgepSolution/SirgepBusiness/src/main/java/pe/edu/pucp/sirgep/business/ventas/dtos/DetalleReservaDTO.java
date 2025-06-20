package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.sql.Time;
import java.util.Date;
import java.util.Map;

public class DetalleReservaDTO {
    //Atributos
    private int numReserva;
    private String nombreEspacio;
    private String categoria;
    private String ubicacion;
    private String nombreDistrito;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private double superficie;
    private char estado;

    //Constructor
    public DetalleReservaDTO() {
    }
    
    //Propiedades
    public int getNumReserva() {
        return numReserva;
    }
    public double getSuperficie() {
        return superficie;
    }
    public String getNombreEspacio() {
        return nombreEspacio;
    }
    public String getCategoria() {    
        return categoria;
    }
    public char getEstado() {
        return estado;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getNombreDistrito() {
        return nombreDistrito;
    }
    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Date getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }
    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    
    //Metodos
    public void llenarDetalleReserva(Map<String, Object> detalle) {
        this.setNumReserva((int) detalle.get("numReserva"));
        this.setNombreEspacio((String) detalle.get("nombreEspacio"));
        this.setCategoria((String) detalle.get("categoria"));
        this.setUbicacion((String) detalle.get("ubicacion"));
        this.setNombreDistrito((String) detalle.get("nombreDistrito"));
        this.setFecha((Date) detalle.get("fecha"));
        this.setHoraInicio((Time) detalle.get("horaInicio"));
        this.setHoraFin((Time) detalle.get("horaFin"));
        this.setSuperficie((double) detalle.get("superficie"));
        this.setEstado((char) detalle.get("estado"));
    }
}