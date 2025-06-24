package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.sql.Time;
import java.util.Date;
import java.util.Map;

public class DetalleReservaDTO {
    //Atributos
    private int IdConstancia;
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
    public int getIdConstancia() {
        return IdConstancia;
    }
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
    public void setIdConstancia(int IdConstancia) {
        this.IdConstancia = IdConstancia;
    }

    //Metodos
    public void llenarDetalleReserva(Map<String, Object> detalle) {
        try{
            int id = (int) detalle.get("idConstancia");
            if (detalle.get("idConstancia") != null) {
                this.setIdConstancia((int) detalle.get("idConstancia"));
            }
            if (detalle.get("numReserva") != null) {
                this.setNumReserva((int) detalle.get("numReserva"));
            }
            if (detalle.get("nombreEspacio") != null) {
                this.setNombreEspacio((String) detalle.get("nombreEspacio"));
            }
            if (detalle.get("categoria") != null) {
                this.setCategoria((String) detalle.get("categoria"));
            }
            if (detalle.get("ubicacion") != null) {
                this.setUbicacion((String) detalle.get("ubicacion"));
            }
            if (detalle.get("nombreDistrito") != null) {
                this.setNombreDistrito((String) detalle.get("nombreDistrito"));
            }
            if (detalle.get("fecha_reserva") != null) {
                this.setFecha((java.sql.Date) detalle.get("fecha_reserva"));
            }
            if (detalle.get("horaInicio") != null) {
                this.setHoraInicio(new Date(((java.sql.Time) detalle.get("horaInicio")).getTime()));
            }
            if (detalle.get("horaFin") != null) {
                this.setHoraFin(new Date(((java.sql.Time) detalle.get("horaFin")).getTime()));
            }
            if (detalle.get("superficie") != null) {
                this.setSuperficie((double) detalle.get("superficie"));
            }
            if (detalle.get("estado") != null) {
                this.setEstado((char) detalle.get("estado"));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la reserva: " + ex.getMessage());
        }
    }
}