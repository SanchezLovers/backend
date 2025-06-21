package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.sql.Time;
import java.util.Date;
import java.util.Map;

public class DetalleEntradaDTO {
    //Atributos
    private int numEntrada;
    private String nombreEvento;
    private String ubicacion;
    private String nombreDistrito;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private char estado;

    //Constructor
    public DetalleEntradaDTO() {
    }
    
    //Propiedades
    public int getNumEntrada() {
        return numEntrada;
    }
    public void setNumEntrada(int numEntrada) {
        this.numEntrada = numEntrada;
    }
    public String getNombreEvento() {
        return nombreEvento;
    }
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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
    public char getEstado() {
        return estado;
    }
    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    //Metodos
    public void llenarDetalleEntrada(Map<String, Object> detalle) {
        try{
            if (detalle.get("numEntrada") != null) {
                this.setNumEntrada((int) detalle.get("numEntrada"));
            }
            if (detalle.get("nombreEvento") != null) {
                this.setNombreEvento((String) detalle.get("nombreEvento"));
            }
            if (detalle.get("ubicacion") != null) {
                this.setUbicacion((String) detalle.get("ubicacion"));
            }
            if (detalle.get("nombreDistrito") != null) {
                this.setNombreDistrito((String) detalle.get("nombreDistrito"));
            }
            if (detalle.get("fecha") != null) {
                this.setFecha((Date) detalle.get("fecha"));
            }
            if (detalle.get("horaInicio") != null) {
                this.setHoraInicio((Time) detalle.get("horaInicio"));
            }
            if (detalle.get("horaFin") != null) {
                this.setHoraFin((Time) detalle.get("horaFin"));
            }
            if (detalle.get("estado") != null) {
                this.setEstado((char) detalle.get("estado"));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la entrada: " + ex.getMessage());
        }
    }
}