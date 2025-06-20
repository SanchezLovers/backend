package pe.edu.pucp.sirgep.domain.infraestructura.models;

import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    private int idEvento;
    private String nombre;
    private String fecha_inicio;
    private String fecha_fin;
    private String ubicacion;
    private String referencia;
    private int cantEntradasDispo;
    private int cantEntradasVendidas;
    private double precioEntrada;
//    private String urlImagen;
    private byte[] archivoImagen;
    private String descripcion;
    
//momentaneamente no implementado
    public byte[] getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(byte[] archivoImagen) {
        this.archivoImagen = archivoImagen;
    }
	
    //Relaciones
    private Distrito distrito;

    //Constructor
    public Evento(){
        
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getCantEntradasDispo() {
        return cantEntradasDispo;
    }

    public void setCantEntradasDispo(int cantEntradasDispo) {
        this.cantEntradasDispo = cantEntradasDispo;
    }

    public int getCantEntradasVendidas() {
        return cantEntradasVendidas;
    }

    public void setCantEntradasVendidas(int cantEntradasVendidas) {
        this.cantEntradasVendidas = cantEntradasVendidas;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    //Atributos
    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", nombre=" + nombre + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", ubicacion=" + ubicacion + ", referencia=" + referencia + ", cantEntradasDispo=" + cantEntradasDispo + ", cantEntradasVendidas=" + cantEntradasVendidas + ", precioEntrada=" + precioEntrada + ", descripcion=" + descripcion + ", distrito=" + distrito.getIdDistrito() + '}';
    }

    
}