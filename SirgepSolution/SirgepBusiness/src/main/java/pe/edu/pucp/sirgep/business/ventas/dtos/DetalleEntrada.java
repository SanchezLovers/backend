package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;

public class DetalleEntrada {
    //Atributos
    private int numEntrada;
    private String nombreEvento;
    private String ubicacion;
    private String nombreDistrito;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;

    //Constructor
    public DetalleEntrada() {
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
}