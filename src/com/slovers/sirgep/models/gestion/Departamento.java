package com.slovers.sirgep.models.gestion;

import java.util.ArrayList;

public class Departamento{
    //Atributos
    private int idDepartamento;
    private String nombre;

    //Relaciones
    private ArrayList<Provincia> provincias;

    //Constructor
    public Departamento(){
        this.provincias = new ArrayList<Provincia>();
    }

    // Getter y Setter para idDepartamento
    public int getIdDepartamento() {
        return this.idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para provincias
    public ArrayList<Provincia> getProvincias() {
        return new ArrayList<Provincia>(this.provincias);
    }

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = new ArrayList<Provincia>(provincias);
    }
    //Metodo
    public String ToString() {
        String cadena = "Departamento: " + this.nombre;
        return cadena;
    }

}
