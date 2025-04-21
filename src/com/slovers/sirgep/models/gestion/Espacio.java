package models.gestion;

import java.time.LocalTime;
import java.util.ArrayList;
import models.ventas.Reserva;
import enums.EDiaSemana;
import enums.ETipoEspacio;
import interfaces.IConsultar;

public class Espacio implements IConsultar{
    //Atributos
    private int idEspacio;
    private String nombre;
    private ETipoEspacio tipoEspacio;
    private ArrayList<EDiaSemana> listaDiasAtencion;
    private LocalTime horarioInicioAtencion;
    private LocalTime horarioFinAtencion;
    private String ubicacion;
    private double superficie;
    private double precioReserva;
  
    //Relaciones
    private ArrayList<Reserva> reservas;
    private Distrito distrito;

    //Constructor
    public Espacio(){
        this.reservas = new ArrayList<Reserva>();
    }

    // Getter y Setter para idEspacio
    public int getIdEspacio() {
        return this.idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para tipoEspacio
    public TipoEspacio getTipoEspacio() {
        return this.tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    // Getter y Setter para listaDiasAtencion
    public ArrayList<EDiaSemana> getListaDiasAtencion() {
        return new ArrayList<EDiaSemana>(this.listaDiasAtencion);
    }

    public void setListaDiasAtencion(ArrayList<EDiaSemana> listaDiasAtencion) {
        this.listaDiasAtencion = new ArrayList<EDiaSemana>(listaDiasAtencion);
    }

    // Getter y Setter para horarioInicioAtencion
    public LocalTime getHorarioInicioAtencion() {
        return this.horarioInicioAtencion;
    }

    public void setHorarioInicioAtencion(LocalTime horarioInicioAtencion) {
        this.horarioInicioAtencion = horarioInicioAtencion;
    }

    // Getter y Setter para horarioFinAtencion
    public LocalTime getHorarioFinAtencion() {
        return this.horarioFinAtencion;
    }

    public void setHorarioFinAtencion(LocalTime horarioFinAtencion) {
        this.horarioFinAtencion = horarioFinAtencion;
    }

    // Getter y Setter para ubicacion
    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Getter y Setter para superficie
    public double getSuperficie() {
        return this.superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    // Getter y Setter para precioReserva
    public double getPrecioReserva() {
        return this.precioReserva;
    }

    public void setPrecioReserva(double precioReserva) {
        this.precioReserva = precioReserva;
    }

    // Getter y Setter para reservas
    public ArrayList<Reserva> getReservas() {
        return new ArrayList<Reserva>(this.reservas);
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = new ArrayList<Reserva>(reservas);
    }

    // Getter y Setter para distrito
    public Distrito getDistrito() {
        return this.distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
	
    //Metodos
    @Override
    public String ToString() {
        String cadena="Nombre: "+this.nombre+" Tipo: "+this.tipoEspacio
		+" Hora de inicio de atencion: "+this.horarioInicioAtencion+" Hora de fin de atencion: "
		+this.horarioFinAtencion+"\n"+"Precio de reserva: "+this.precioReserva+"Ubicacion: "
		+this.ubicacion+" Superficie: "+this.superficie+"\n"+"Dias de atencion: ";
	for(EDiaSemana dia : listaDiasAtencion){
		cadena+=((String)dia)+" ";
	}
	cadena+="\n";
        return cadena;
    }
}
