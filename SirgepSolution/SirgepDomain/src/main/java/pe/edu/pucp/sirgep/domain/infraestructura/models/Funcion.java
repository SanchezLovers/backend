package pe.edu.pucp.sirgep.domain.infraestructura.models;

import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Funcion{
    //Atributos
    private int idFuncion;
    private Date horaInicio;
    private Date horaFin;
    private Date fecha;
    private boolean activo;

    public Date getFecha() {
        return fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
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
    public Date getHoraInicio() {
            return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
            this.horaInicio = horaInicio;
    }

    // Getter y Setter para fechaHoraFin
    public Date getHoraFin() {
            return this.horaFin;
    }

    public void setHoraFin(Date horaFin) {
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
            cadena += this.evento.getIdEvento() + "\n";
            cadena+="Funcion:\nFecha: " +this.fecha +"Hora de Inicio: "+this.horaInicio+" Hora de fin: "+this.horaFin+"\nEntradas:\n";
            /*for(Entrada e:this.entradas){
                cadena+=e.toString()+"\n";
            }*/
            return cadena;
    }
}