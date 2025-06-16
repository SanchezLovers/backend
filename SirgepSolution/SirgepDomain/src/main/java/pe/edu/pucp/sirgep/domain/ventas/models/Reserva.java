package pe.edu.pucp.sirgep.domain.ventas.models;

import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

import java.time.LocalTime;
import java.util.Date;

public class Reserva extends Constancia{
    //Atributos
    private int numReserva;
    private LocalTime horarioIni;
    private LocalTime horarioFin;
    private Date fechaReserva;
    private char activo;

    //Relaciones
    private Espacio espacio;
    private Persona persona;

    //Constructor
    public Reserva() {       
        this.persona = new Persona();
        this.espacio = new Espacio();
        this.fechaReserva = new Date();
    }

    //Getter y Setter para numReserva
    public int getNumReserva() {
        return this.numReserva;
    }
  
    public Reserva setNumReserva(int numReserva) {
        this.numReserva = numReserva;
        return this;
    }

    //Getter y Setter para horarioIni
    public LocalTime getHorarioIni() {
        return this.horarioIni;
    }

    public Reserva setHorarioIni(LocalTime horarioIni) {
        this.horarioIni = horarioIni;
        return this;
    }

    //Getter y Setter para horarioFin
    public LocalTime getHorarioFin() {
        return this.horarioFin;
    }

    public Reserva setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
        return this;
    }

    //Getter y Setter para fechaReserva
    public Date getFechaReserva() {
        return this.fechaReserva;
    }
    
    public Reserva setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }

    //Getter y Setter para espacio
    public Espacio getEspacio() {
        return this.espacio;
    }

    public Reserva setEspacio(Espacio espacio) {
        this.espacio = espacio;
        return this;
    }

    //Getter y Setter para persona
    public Persona getPersona() {
        return this.persona;
    }

    public Reserva setPersona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    @Override
    public String toString(){
        String cadena="";
        cadena+=persona.toString()+"\n";
        cadena+=espacio.toString()+"\n";
        return cadena;
    }
}