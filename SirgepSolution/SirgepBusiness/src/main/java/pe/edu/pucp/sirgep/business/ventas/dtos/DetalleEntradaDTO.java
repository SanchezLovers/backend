package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class DetalleEntradaDTO {
    //Atributos
    private int IdConstancia;
    private int numEntrada;
    private String nombreEvento;
    private String ubicacion;
    private String nombreDistrito;
    private Date fechaFuncion;
    private Date horaInicio;
    private Date horaFin;
    private char estado;
    private Date fechaConstancia;

    //Constructor
    public DetalleEntradaDTO() {
    }
    
    //Propiedades
    public int getIdConstancia() {
        return IdConstancia;
    }
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
    public Date getFechaFuncion() {
        return fechaFuncion;
    }
    public void setFechaFuncion(Date fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
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
    public void setIdConstancia(int IdConstancia) {
        this.IdConstancia = IdConstancia;
    }
    public Date getFechaConstancia() {
        return fechaConstancia;
    }

    public void setFechaConstancia(Date fechaConstancia) {
        this.fechaConstancia = fechaConstancia;
    }
    
    //Metodos
    public void llenarDetalleEntrada(Map<String, Object> detalle) {
        try{
            int id = (int) detalle.get("idConstancia");
            if (detalle.get("idConstancia") != null) {
                this.setIdConstancia((int) detalle.get("idConstancia"));
            }
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
            if (detalle.get("fechaFuncion") != null) {
                this.setFechaFuncion((java.sql.Date) detalle.get("fechaFuncion"));
            }
            if (detalle.get("horaInicio") != null) {
                this.setHoraInicio(new Date(((java.sql.Time) detalle.get("horaInicio")).getTime()));
            }
            if (detalle.get("horaFin") != null) {
                this.setHoraFin(new Date(((java.sql.Time) detalle.get("horaFin")).getTime()));
            }
            if (detalle.get("estado") != null) {
                this.setEstado((char) detalle.get("estado"));
            }
            if (detalle.get("fechaConstancia") != null) {
                this.setFechaConstancia((Date) detalle.get("fechaConstancia"));
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la entrada: " + ex.getMessage());
        }
    }
}