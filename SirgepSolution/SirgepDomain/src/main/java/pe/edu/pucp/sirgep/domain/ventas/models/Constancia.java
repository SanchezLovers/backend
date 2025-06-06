package pe.edu.pucp.sirgep.domain.ventas.models;

import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;

import java.util.Date;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class Constancia{
    //Atributos
    private int idConstancia;
    private Date fecha;
    private EMetodoPago metodoPago;
    private String detallePago;
    private double igv = 0.18;
    private double total;
    
    //Constructor
    public Constancia(){
        
    }

    // Getter y Setter para correo
    public int getIdConstancia() {
        return this.idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }

    // Getter y Setter para numero
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para metodoPago
    public EMetodoPago getMetodoPago() {
        return this.metodoPago;
    }

    public void setMetodoPago(EMetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    // Getter y Setter para detallePago
    public String getDetallePago() {
        return this.detallePago;
    }

    public void setDetallePago(String detallePago) {
        this.detallePago = detallePago;
    }

    // Getter y Setter para igv

    public double getIgv() {
        return this.igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    // Getter y Setter para total

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //Metodos
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena += "Fecha: " + this.fecha.toString() + "\n";
        cadena += "Método de pago: " + this.metodoPago + "\n";
        cadena += "Detalle de pago: " + this.detallePago + "\n";
        cadena += "IGV: " + (this.igv*100)+"%" + "\n";
        cadena += "Total: " + this.total+ "\n";
        return cadena;
    }
}