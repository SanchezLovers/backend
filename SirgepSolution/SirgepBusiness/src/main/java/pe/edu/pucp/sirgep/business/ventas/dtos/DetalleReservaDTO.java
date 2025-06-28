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
        try {
            setIntSiExiste(detalle, "idConstancia", this::setIdConstancia);
            setIntSiExiste(detalle, "numReserva", this::setNumReserva);
            setStringSiExiste(detalle, "nombreEspacio", this::setNombreEspacio);
            setStringSiExiste(detalle, "categoria", this::setCategoria);
            setStringSiExiste(detalle, "ubicacion", this::setUbicacion);
            setStringSiExiste(detalle, "nombreDistrito", this::setNombreDistrito);
            setDateSiExiste(detalle, "fecha_reserva", this::setFecha);
            setTimeSiExiste(detalle, "horaInicio", this::setHoraInicio);
            setTimeSiExiste(detalle, "horaFin", this::setHoraFin);
            setDoubleSiExiste(detalle, "superficie", this::setSuperficie);
            setCharSiExiste(detalle, "estado", this::setEstado);
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la reserva: " + ex.getMessage(), ex);
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

    private void setDateSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<java.sql.Date> setter) {
        if (map.get(key) != null) {
            setter.accept((java.sql.Date) map.get(key));
        }
    }

    private void setTimeSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<Date> setter) {
        if (map.get(key) != null) {
            java.sql.Time time = (java.sql.Time) map.get(key);
            setter.accept(new Date(time.getTime()));
        }
    }

    private void setDoubleSiExiste(Map<String, Object> map, String key, java.util.function.DoubleConsumer setter) {
        if (map.get(key) != null) {
            setter.accept((double) map.get(key));
        }
    }

    private void setCharSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<Character> setter) {
        if (map.get(key) != null) {
            String val = map.get(key).toString();
            if (!val.isEmpty()) {
                setter.accept(val.charAt(0));
            }
        }
    }
}