package pe.edu.pucp.sirgep.domain.infraestructura.models;

import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    //Atributos
    private int idEvento;
    private String nombre;
    private String ubicacion;
    private String referencia;
    private String descripcion;
//    private String urlImagen;
    private byte[] archivoImagen;
    private int cantEntradasDispo;
    private int cantEntradasVendidas;
    private double precioEntrada;
    private Date fecha;
	
    //Relaciones
    private ArrayList<Funcion> funciones;
    private Distrito distrito;

    //Constructor
    public Evento(){
        this.funciones = new ArrayList<Funcion>();
    }

    // Getter y Setter para idEvento
    public int getIdEvento() {
        return this.idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public byte[] getArchivo() {
        return archivoImagen;
    }

    public void setArchivo(byte[] archivo) {
        this.archivoImagen = archivo;
    }

    // Getter y Setter para ubicacion
    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Getter y Setter para referencia
    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    // Getter y Setter para descripción
    public String getDescripción() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // Getter y Setter para url

    // Getter y Setter para cantEntradasDispo
    public int getCantEntradasDispo() {
        return this.cantEntradasDispo;
    }

    public void setCantEntradasDispo(int cantEntradasDispo) {
        this.cantEntradasDispo = cantEntradasDispo;
    }

    // Getter y Setter para cantEntradasVendidas
    public int getCantEntradasVendidas() {
        return this.cantEntradasVendidas;
    }

    public void setCantEntradasVendidas(int cantEntradasVendidas) {
        this.cantEntradasVendidas = cantEntradasVendidas;
    }

    // Getter y Setter para precioEntrada
    public double getPrecioEntrada() {
        return this.precioEntrada;
    }
    
    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    // Getter y Setter para fecha
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para funciones
    public ArrayList<Funcion> getFunciones() {
        return new ArrayList<Funcion>(this.funciones);
    }

    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = new ArrayList<Funcion>(funciones);
    }

    // Getter y Setter para distrito
    public Distrito getDistrito() {
        return this.distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    //Metodo
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena += "Evento: " + this.nombre + "\n";
        cadena += "Fecha: " + this.fecha + "\n";
        cadena+=this.distrito.toString()+ "\n";
        cadena += "Descripción: " + this.descripcion + "\n";
        cadena += "Ubicación: " + this.ubicacion + "\n";
        cadena += "Referencia: " + this.referencia + "\n";
        cadena += "Precio entrada: " + this.precioEntrada + "\n";
        cadena += "Entradas disponibles: " + this.cantEntradasDispo + "\n";
        cadena += "Entradas vendidas: " + this.cantEntradasVendidas+ "\nFunciones:\n";
        cadena += "\nFunciones:\n";
        for(Funcion f:this.funciones){
            cadena+=f.toString()+"\n";
        }
        return cadena;
    }
}