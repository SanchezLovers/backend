package pe.edu.pucp.sirgep.domain.ubicacion.models;
import java.util.ArrayList;

public class Departamento{
    //Atributos
    private int idDepartamento;
    private String nombre;
    
    //Constructor
    public Departamento(){
//        this.provincias = new ArrayList<Provincia>();
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
    
    //Metodos
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena += "Departamento: " + this.nombre+"\n";
       /* cadena+="Provincias:\n";
        for(Provincia p:this.provincias){
            cadena+=p.toString()+"\n";
        }*/
        return cadena;
    }
}