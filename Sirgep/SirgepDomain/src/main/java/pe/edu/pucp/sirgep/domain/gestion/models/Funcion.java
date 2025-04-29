package pe.edu.pucp.sirgep.domain.gestion.models;

import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import java.time.LocalTime;
import java.util.ArrayList;

public class Funcion{
    //Atributos
    private int idFuncion;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    //Relaciones
    private ArrayList<Entrada> entradas;
    private Evento evento;

    //Constructor
    public Funcion(){
            this.entradas = new ArrayList<Entrada>();
    }

    // Getter y Setter para idFuncion
    public int getIdFuncion() {
            return this.idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
            this.idFuncion = idFuncion;
    }

    // Getter y Setter para fechaHoraInicio
    public LocalTime getHoraInicio() {
            return this.horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
    }

    // Getter y Setter para fechaHoraFin
    public LocalTime getHoraFin() {
            return this.horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
            this.horaFin = horaFin;
    }

    // Getter y Setter para entradas
    public ArrayList<Entrada> getEntradas() {
            return new ArrayList<Entrada>(this.entradas);
    }

    public void setEntradas(ArrayList<Entrada> entradas) {
            this.entradas = new ArrayList<Entrada>(entradas);
    }

    // Getter y Setter para evento
    public Evento getEvento() {
            return this.evento;
    }

    public void setEvento(Evento evento) {
            this.evento = evento;
    }

    //Metodos
    @Override
    public String toString(){
            String cadena="---------------------------------------------------------------------"+ "\n";
            cadena += this.evento.toString() + "\n";
            cadena+="Funcion:\nHora de Inicio: "+this.horaInicio+" Hora de fin: "+this.horaFin+"\nEntradas:\n";
            for(Entrada e:this.entradas){
                cadena+=e.toString()+"\n";
            }
            return cadena;
    }
}