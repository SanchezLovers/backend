package pe.edu.pucp.sirgep.domain.usuarios.models;

import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;

public class Administrador extends Persona {
    //Atributos
    private ETipoAdministrador tipoAdministrador;
    
    //Constructor
    public Administrador(){
        
    }
    
    //Propiedades
    //Getter y Setter para tipoAdministrador
    public void setTipoAdministrador(ETipoAdministrador tipoAdministrador){
            this.tipoAdministrador=tipoAdministrador;
    }
    public ETipoAdministrador getTipoAdministrador(){
            return this.tipoAdministrador;
    }
    
    //Metodos
    @Override
    public String toString() {
        String cadena="---------------------------------------------------------------------"+ "\n";
        cadena += "Tipo de administrador: " + this.tipoAdministrador+"\n";
        cadena += super.toString() + "\n";
        return cadena;
    }
}
