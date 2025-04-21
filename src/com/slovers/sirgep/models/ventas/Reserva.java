package models.ventas;

import java.time.LocalTime;

import models.gestion.Espacio;
import models.gestion.Persona;
import interfaces.IConsultar;

public class Reserva extends Constancia implements IConsultar{
    //Atributos
    private int numReserva;
    private LocalTime horarioIni;
    private LocalTime horarioFin;

    //Relaciones
    private Espacio espacio;
    private Persona persona;

    //Constructor
    public Reserva() {       

    }

    //Getter y Setter para numReserva
    public int getNumReserva() {
        return this.numReserva;
    }
  
    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    //Getter y Setter para horarioIni
    public LocalTime getHorarioIni() {
        return this.horarioIni;
    }

    public void setHorarioIni(LocalTime horarioIni) {
        this.horarioIni = horarioIni;
    }

    //Getter y Setter para horarioFin
    public LocalTime getHorarioFin() {
        return this.horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    //Getter y Setter para espacio
    public Espacio getEspacio() {
        return this.espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    //Getter y Setter para persona
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String ToString(){
        String cadena="Numero de reserva: "+this.numReserva+"\n";
        cadena+=persona.ToString()+"\n";
        cadena+=espacio.ToString()+"\n";
        return cadena;
    }
}
