package com.slovers.sirgep.models.gestion;

import com.slovers.sirgep.enums.ETipoAdministrador;
import com.slovers.sirgep.interfaces.IConsultar;

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
	//Metodo
	@Override
    public String toString() {
        String cadena = super.toString() + "\n";
        cadena += "Tipo de administrador: " + this.tipoAdministrador;
        return cadena;
    }
}
