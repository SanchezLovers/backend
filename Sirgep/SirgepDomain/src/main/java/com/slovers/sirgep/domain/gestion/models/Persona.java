package com.slovers.sirgep.domain.gestion.models;

import com.slovers.sirgep.domain.gestion.enums.ETipoDocumento;
import com.slovers.sirgep.domain.ventas.models.Entrada;
import com.slovers.sirgep.domain.ventas.models.Reserva;

import java.util.ArrayList;

public class Persona{
    //Atributos
    private int idPersona;
    private String nombres="Invitado";
    private String primerApellido="Invitado";
    private String segundoApellido="Invitado";
    private ETipoDocumento tipoDocumento;
    private String numDocumento="Invitado";
    private String correo="Invitado";
    private String usuario="Invitado";
    private String contrasenia="Invitado";

    //Relaciones
    private ArrayList<Entrada> entradas;
    private ArrayList<Reserva> reservas;

    //Constructor
    public Persona(){
        this.entradas = new ArrayList<Entrada>();
        this.reservas = new ArrayList<Reserva>();
    }

    // Getter y Setter para idPersona
    public int getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    // Getter y Setter para nombres
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    // Getter y Setter para primerApellido
    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    // Getter y Setter para segundoApellido
    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    // Getter y Setter para tipoDocumento
    public ETipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(ETipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    // Getter y Setter para numDocumento
    public String getNumDocumento() {
        return this.numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    // Getter y Setter para correo
    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Getter y Setter para usuario
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Getter y Setter para contrasenia
    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Getter y Setter para entradas
    public ArrayList<Entrada> getEntradas() {
        return new ArrayList<Entrada>(this.entradas);
    }

    public void setEntradas(ArrayList<Entrada> entradas) {
        this.entradas = new ArrayList<Entrada>(entradas);
    }

    // Getter y Setter para reservas
    public ArrayList<Reserva> getReservas() {
        return new ArrayList<Reserva>(this.reservas);
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = new ArrayList<Reserva>(reservas);
    }

    //Metodos
    @Override
    public String toString(){
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena+="Persona:\nNombre completo: "+this.nombres+" "+this.primerApellido+" "+this.segundoApellido+"\n";
        cadena+="Documento: "+this.tipoDocumento+" "+this.numDocumento+" "+this.correo+"\nEntradas:\n";
        for(Entrada e:this.entradas){
            cadena+=e.toString()+"\n";
        }
        cadena+="Reservas:\n";
        for(Reserva r:this.reservas){
            cadena+=r.toString()+"\n";
        }
        return cadena;
    }
}