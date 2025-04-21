package models.gestion;
import enums.ETipoAdministrador;

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
    public String ToString() {
        String cadena = super.ToString() + "\n";
        cadena += "Tipo de administrador: " + this.tipoAdministrador;
        return cadena;
    }
}
