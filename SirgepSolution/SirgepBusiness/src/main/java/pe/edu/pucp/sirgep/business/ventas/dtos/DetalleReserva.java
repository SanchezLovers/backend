package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;

public class DetalleReserva {
    //Atributos
    private int numReserva;
    private String nombreEspacio;
    private String categoria;
    private String ubicacion;
    private String nombreDistrito;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;

    //Constructor
    public DetalleReserva() {
    }
    
    //Propiedades
    public int getNumReserva() {
        return numReserva;
    }
    public String getNombreEspacio() {
        return nombreEspacio;
    }
    public String getCategoria() {    
        return categoria;
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
}