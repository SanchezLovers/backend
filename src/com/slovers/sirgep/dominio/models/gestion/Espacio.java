package com.slovers.sirgep.dominio.models.gestion;

import java.time.LocalTime;
import java.util.ArrayList;
import com.slovers.sirgep.dominio.models.ventas.Reserva;
import com.slovers.sirgep.dominio.enums.ETipoEspacio;
import com.slovers.sirgep.dominio.enums.EDiaSemana;
import com.slovers.sirgep.dominio.interfaces.IConsultar;

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

    public Espacio setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
        return this;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return this.nombre;
    }

    public Espacio setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    // Getter y Setter para tipoEspacio
    public ETipoEspacio getTipoEspacio() {
        return this.tipoEspacio;
    }

    public Espacio setTipoEspacio(ETipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
        return this;
    }

    // Getter y Setter para listaDiasAtencion
    public ArrayList<EDiaSemana> getListaDiasAtencion() {
        return new ArrayList<EDiaSemana>(this.listaDiasAtencion);
    }

    public Espacio setListaDiasAtencion(ArrayList<EDiaSemana> listaDiasAtencion) {
        this.listaDiasAtencion = new ArrayList<EDiaSemana>(listaDiasAtencion);
        return this;
    }

    // Getter y Setter para horarioInicioAtencion
    public LocalTime getHorarioInicioAtencion() {
        return this.horarioInicioAtencion;
    }

    public Espacio setHorarioInicioAtencion(LocalTime horarioInicioAtencion) {
        this.horarioInicioAtencion = horarioInicioAtencion;
        return this;
    }

    // Getter y Setter para horarioFinAtencion
    public LocalTime getHorarioFinAtencion() {
        return this.horarioFinAtencion;
    }

    public Espacio setHorarioFinAtencion(LocalTime horarioFinAtencion) {
        this.horarioFinAtencion = horarioFinAtencion;
        return this;
    }
  
    // Getter y Setter para ubicacion
    public String getUbicacion() {
        return this.ubicacion;
    }

    public Espacio setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }

    // Getter y Setter para superficie
    public double getSuperficie() {
        return this.superficie;
    }

    public Espacio setSuperficie(double superficie) {
        this.superficie = superficie;
        return this;
    }

    // Getter y Setter para precioReserva
    public double getPrecioReserva() {
        return this.precioReserva;
    }

    public Espacio setPrecioReserva(double precioReserva) {
        this.precioReserva = precioReserva;
        return this;
    }

    // Getter y Setter para reservas
    public ArrayList<Reserva> getReservas() {
        return new ArrayList<Reserva>(this.reservas);
    }

    public Espacio setReservas(ArrayList<Reserva> reservas) {
        this.reservas = new ArrayList<Reserva>(reservas);
        return this;
    }

    // Getter y Setter para distrito
    public Distrito getDistrito() {
        return this.distrito;
    }

    public Espacio setDistrito(Distrito distrito) {
        this.distrito = distrito;
        return this;
    }
	
    //Metodos
    public String ToString() {
        String cadena="Nombre: "+this.nombre+" Tipo: "+this.tipoEspacio
		+" Hora de inicio de atencion: "+this.horarioInicioAtencion+" Hora de fin de atencion: "
		+this.horarioFinAtencion+"\n"+"Precio de reserva: "+this.precioReserva+"Ubicacion: "
		+this.ubicacion+" Superficie: "+this.superficie+"\n"+"Dias de atencion: ";
	for(EDiaSemana dia : listaDiasAtencion){
		cadena+=(dia.name())+" ";
	}
	cadena+="\n";
        return cadena;
    }
}
