package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;
import java.util.Map;

public class ReservaDTO {
    //Atributos
    private int numReserva;
    private int idConstancia;
    private Date fechaReserva;
    private Date fechaConstancia;
    private String distrito;
    private String espacio;
    private String correo;
    private char activo;

    //Constructor
    public ReservaDTO() {
    }

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }
    
    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fecha) {
        this.fechaReserva = fecha;
    }

    public String getDistrito() {
        return distrito;
    }

    public Date getFechaConstancia() {
        return fechaConstancia;
    }

    public void setFechaConstancia(Date fechaConstancia) {
        this.fechaConstancia = fechaConstancia;
    }
    
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }
    
    public void llenarReserva(Map<String, Object> reserva) {
        try{
            if (reserva.get("num_reserva") != null) {
                this.setNumReserva((int) reserva.get("num_reserva"));
            }
            if (reserva.get("fecha_reserva") != null) {
                this.setFechaReserva((Date) reserva.get("fecha_reserva"));
            }
            if (reserva.get("fecha_constancia") != null) {
                this.setFechaConstancia((Date) reserva.get("fecha_constancia"));
            }
            if (reserva.get("id_constancia_reserva") != null) {
                this.setIdConstancia((int) reserva.get("id_constancia_reserva"));
            }
            if (reserva.get("activo") != null) {
                this.setActivo((char) reserva.get("activo"));
            }
            if (reserva.get("nombre_espacio") != null) {
                this.setEspacio((String) reserva.get("nombre_espacio"));
            }
            if (reserva.get("nombre_distrito") != null) {
                this.setDistrito((String) reserva.get("nombre_distrito"));
            }
            if (reserva.get("correo") != null) {
                this.setCorreo((String) reserva.get("correo"));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la reserva: " + ex.getMessage());
        }
    }
}
