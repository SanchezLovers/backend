package pe.edu.pucp.sirgep.domain.infraestructura.models;

import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;

import java.util.ArrayList;

public class Espacio{
    //Atributos
    private int idEspacio;
    private String nombre;
    private ETipoEspacio tipoEspacio;
    private ArrayList<EDiaSemana> listaDiasAtencion;
    private String horarioInicioAtencion;
    private String horarioFinAtencion;
    private String ubicacion;
    private double superficie;
    private double precioReserva;
  
    //Relaciones
    private ArrayList<Reserva> reservas;
    private Distrito distrito;

    //Constructor
    public Espacio(){
        this.reservas = new ArrayList<>();
        this.listaDiasAtencion = new ArrayList<>();
        this.distrito = new Distrito();
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
    public ETipoEspacio getTipoEspacio() {
        return this.tipoEspacio;
    }

    public void setTipoEspacio(ETipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    // Getter y Setter para listaDiasAtencion
    public ArrayList<EDiaSemana> getListaDiasAtencion() {
        return new ArrayList<>(this.listaDiasAtencion);
    }

    public void setListaDiasAtencion(ArrayList<EDiaSemana> listaDiasAtencion) {
        this.listaDiasAtencion = new ArrayList<>(listaDiasAtencion);
    }

    // Getter y Setter para horarioInicioAtencion
    public String getHorarioInicioAtencion() {
        return this.horarioInicioAtencion;
    }

    public void setHorarioInicioAtencion(String horarioInicioAtencion) {
        this.horarioInicioAtencion = horarioInicioAtencion;
    }

    // Getter y Setter para horarioFinAtencion
    public String getHorarioFinAtencion() {
        return this.horarioFinAtencion;
    }

    public void setHorarioFinAtencion(String horarioFinAtencion) {
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
        return new ArrayList<>(this.reservas);
    }
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = new ArrayList<>(reservas);
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
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena+="Espacio:\nNombre: "+this.nombre+" Tipo: "+this.tipoEspacio
                        +" Hora de inicio de atencion: "+this.horarioInicioAtencion+" Hora de fin de atencion: "
                        +this.horarioFinAtencion+"\n"+"Precio de reserva: "+this.precioReserva+"Ubicacion: "
                        +this.distrito.toString()+"\n"+this.ubicacion+" Superficie: "+this.superficie+"\n"+"Dias de atencion: ";
        for(EDiaSemana dia : listaDiasAtencion){
                cadena+=(dia.name())+" ";
        }
        cadena+="\nReservas:\n";
        for(Reserva r:this.reservas){
            cadena+=r.toString()+"\n";
        }
        return cadena;
    }
}