package pe.edu.pucp.sirgep.domain.ventas.models;

import pe.edu.pucp.sirgep.domain.gestion.models.Funcion;
import pe.edu.pucp.sirgep.domain.gestion.models.Persona;

public class Entrada extends Constancia{
    //Atributos
    private int numEntrada;

    //Relaciones
    private Funcion funcion;
    private Persona persona;

    //Constructor
    public Entrada(){

    }

    // Getter y Setter para numEntrada
    public int getNumEntrada() {
        return this.numEntrada;
    }

    public void setNumEntrada(int numEntrada) {
        this.numEntrada = numEntrada;
    }

    // Getter y Setter para funcion
    public Funcion getFuncion() {
        return this.funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    // Getter y Setter para persona
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //Metodos
    public String toString(){
        String cadena="Numero de entrada: "+this.numEntrada+"\n";
        cadena+="Comprador: "+this.persona+"\n";
        cadena+="Funcion: "+this.funcion+"\n";
        return cadena;
    }
}
