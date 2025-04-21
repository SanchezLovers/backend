package com.slovers.sirgep.dominio.models.gestion;

import com.slovers.sirgep.dominio.enums.ETipoAdministrador;
import com.slovers.sirgep.dominio.interfaces.IConsultar;

public class Administrador extends Persona implements IConsultar{
	//Atributos
	private ETipoAdministrador tipoAdministrador;

	//Constructor
	public Administrador(){
		
	}

	//Propiedades
	public void setTipoAdministrador(ETipoAdministrador tipoAdministrador){
		this.tipoAdministrador=tipoAdministrador;
	}
	public ETipoAdministrador getTipoAdministrador(){
		return this.tipoAdministrador;
	}
	
	//Metodos
	@Override
	public String toString() {
        	String cadena = super.toString() + "\n";
	        cadena += "Tipo de administrador: " + this.tipoAdministrador;
        	return cadena;
        }
	public String listarEntradas() {
	        String cadena = "Entradas atendidas por " + this.getNombres() + " " + this.getPrimerApellido() + ":\n";
        	for (Entrada r : this.getEntradas()) {
	            cadena += r.ToString() + "\n";
        	}
	        return cadena;
	}
	public String listarReservas() {
	        String cadena = "Reservas atendidas por " + this.getNombres() + " " + this.getPrimerApellido() + ":\n";
        	for (Reserva r : this.getReservas()) {
	            cadena += r.ToString() + "\n";
        	}
	        return cadena;
	}
}
