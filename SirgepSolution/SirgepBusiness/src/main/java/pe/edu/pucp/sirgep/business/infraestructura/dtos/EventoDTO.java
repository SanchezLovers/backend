/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.business.infraestructura.dtos;

import java.util.Map;

/**
 *
 * @author benny
 */
public class EventoDTO {
    private int idEvento;
    private String nombre;
    private String fecha_inicio;
    private String fecha_fin;
    private String ubicacion;
    private String referencia;
    private int cantEntradasDispo;
    private int cantEntradasVendidas;
    private double precioEntrada;
    private String urlImagen;
    private String archivoImagen;
    private String descripcion;
    // Departamento
    private int idDepa;
    private String nombreDepa;
    // Provincia
    private int idProv;
    private String nombreProv;
    // Distrito
    private int idDist;
    private String nombreDist;

    public EventoDTO() {
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

    public String getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(String archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdDepa() {
        return idDepa;
    }

    public void setIdDepa(int idDepa) {
        this.idDepa = idDepa;
    }

    public String getNombreDepa() {
        return nombreDepa;
    }

    public void setNombreDepa(String nombreDepa) {
        this.nombreDepa = nombreDepa;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public int getIdDist() {
        return idDist;
    }

    public void setIdDist(int idDist) {
        this.idDist = idDist;
    }

    public String getNombreDist() {
        return nombreDist;
    }

    public void setNombreDist(String nombreDist) {
        this.nombreDist = nombreDist;
    }

    public void llenarEventoDTO(Map<String, Object> map) {
        if(map.get("id_evento") != null){
            this.idEvento = (int) map.get("id_evento");
        }
        if(map.get("nombre") != null){
            this.nombre = (String) map.get("nombre");
        }
        if(map.get("fecha_inicio") != null){
            Object fechaInicioObj = map.get("fecha_inicio");
            if (fechaInicioObj instanceof java.sql.Date) {
                this.fecha_inicio = fechaInicioObj.toString();
            } else {
                this.fecha_inicio = (String) fechaInicioObj;
            }
        }
        if(map.get("fecha_fin") != null){
            Object fechaFinObj = map.get("fecha_fin");
            if (fechaFinObj instanceof java.sql.Date) {
                this.fecha_fin = fechaFinObj.toString();
            } else {
                this.fecha_fin = (String) fechaFinObj;
            }
        }
        if(map.get("ubicacion") != null){   
            this.ubicacion = (String) map.get("ubicacion");
        }
        if(map.get("referencia") != null){
            this.referencia = (String) map.get("referencia");
        }
        if(map.get("cant_entradas_dispo") != null){
            this.cantEntradasDispo = (int) map.get("cant_entradas_dispo");
        }
        if(map.get("cant_entradas_vendidas") != null){
            this.cantEntradasVendidas = (int) map.get("cant_entradas_vendidas");
        }
        if(map.get("precio_entradas") != null){
            this.precioEntrada = (double) map.get("precio_entradas");
        }
        if(map.get("descripcion") != null){
            this.descripcion = (String) map.get("descripcion");
        }
        if(map.get("id_distrito") != null){
            this.idDist = (int) map.get("id_distrito");
        }
        if(map.get("nombre_distrito") != null){
            this.nombreDist = (String) map.get("nombre_distrito");
        }
        if(map.get("id_provincia") != null){
            this.idProv = (int) map.get("id_provincia");
        }
        if(map.get("nombre_provincia") != null){
            this.nombreProv = (String) map.get("nombre_provincia");
        }
        if(map.get("id_departamento") != null){
            this.idDepa = (int) map.get("id_departamento");
        }
        if(map.get("nombre_departamento") != null){
            this.nombreDepa = (String) map.get("nombre_departamento");
        }
        if(map.get("url_imagen") != null){
            this.nombreDepa = (String) map.get("url_imagen");
        }
    }
    
}
