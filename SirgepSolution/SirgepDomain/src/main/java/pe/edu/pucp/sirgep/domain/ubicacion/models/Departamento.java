package pe.edu.pucp.sirgep.domain.ubicacion.models;

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
    
    //Propiedades
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
    
    //Metodos
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena += "Departamento: " + this.nombre+"\n";
        cadena+="Provincias:\n";
        for(Provincia p:this.provincias){
            cadena+=p.toString()+"\n";
        }
        return cadena;
    }
}