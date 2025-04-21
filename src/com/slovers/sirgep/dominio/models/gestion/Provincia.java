package com.slovers.sirgep.dominio.models.gestion;

import java.util.ArrayList;

public class Provincia{
    //Atributos
    private int idProvincia;
    private String nombre;

    //Relaciones
    private Departamento departamento;
    private ArrayList<Distrito> distritos;

    //Constructor
    public Provincia() {
        this.distritos = new ArrayList<>();
    }

    // Getter y Setter para idProvincia
    public int getIdProvincia() {
        return this.idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para departamento
    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Getter y Setter para distritos
    public ArrayList<Distrito> getDistritos() {
        return new ArrayList<Distrito>(this.distritos);
    }

    public void setDistritos(ArrayList<Distrito> distritos) {
        this.distritos = new ArrayList<Distrito>(distritos);
    }
    //Metodo
    public String ToString() {
        String cadena = "Provincia: " + this.nombre;
        return cadena;
    }
}
