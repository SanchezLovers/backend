package pe.edu.pucp.sirgep.domain.gestion.models;

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
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena+=this.departamento.toString()+"\n";
        cadena += "Provincia: " + this.nombre+"\nDistritos:\n";
        for(Distrito d:this.distritos){
            cadena+=d.toString()+"\n";
        }
        return cadena;
    }
}