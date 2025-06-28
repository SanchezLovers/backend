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
        try {
            setIntSiExiste(detalle, "idConstancia", this::setIdConstancia);
            setIntSiExiste(detalle, "numEntrada", this::setNumEntrada);
            setStringSiExiste(detalle, "nombreEvento", this::setNombreEvento);
            setStringSiExiste(detalle, "ubicacion", this::setUbicacion);
            setStringSiExiste(detalle, "nombreDistrito", this::setNombreDistrito);
            setDateSqlSiExiste(detalle, "fechaFuncion", this::setFechaFuncion);
            setTimeSiExiste(detalle, "horaInicio", this::setHoraInicio);
            setTimeSiExiste(detalle, "horaFin", this::setHoraFin);
            setCharSiExiste(detalle, "estado", this::setEstado);
            setDateUtilSiExiste(detalle, "fechaConstancia", this::setFechaConstancia);
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la entrada: " + ex.getMessage(), ex);
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

    private void setDateSqlSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<java.sql.Date> setter) {
        if (map.get(key) != null) {
            setter.accept((java.sql.Date) map.get(key));
        }
    }

    private void setDateUtilSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<java.util.Date> setter) {
        if (map.get(key) != null) {
            setter.accept((java.util.Date) map.get(key));
        }
    }

    private void setTimeSiExiste(Map<String, Object> map, String key, java.util.function.Consumer<java.util.Date> setter) {
        if (map.get(key) != null) {
            java.sql.Time time = (java.sql.Time) map.get(key);
            setter.accept(new java.util.Date(time.getTime()));
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