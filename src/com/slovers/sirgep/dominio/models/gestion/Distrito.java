package com.slovers.sirgep.dominio.models.gestion;

import java.util.ArrayList;

public class Distrito{
    //Atributos
    private int idDistrito;
    private String nombre;

    //Relaciones
    private Provincia provincia;
    private ArrayList<Espacio> espacios;
    private ArrayList<Evento> eventos;

    //Constructor
    public Distrito(){
        this.espacios = new ArrayList<Espacio>();
        this.eventos = new ArrayList<Evento>();
    }

    // Getter y Setter para idDistrito
    public int getIdDistrito() {
        return this.idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para provincia
    public Provincia getProvincia() {
        return this.provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    // Getter y Setter para espacios
    public ArrayList<Espacio> getEspacios() {
        return new ArrayList<Espacio>(this.espacios);
    }

    public void setEspacios(ArrayList<Espacio> espacios) {
        this.espacios = new ArrayList<Espacio>(espacios);
    }

    // Getter y Setter para eventos
    public ArrayList<Evento> getEventos() {
        return new ArrayList<Evento>(this.eventos);
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = new ArrayList<Evento>(eventos);
    }
    //metodo
    public String ToString() {
        String cadena = "Distrito: " + this.nombre;
        return cadena;
    }
}
