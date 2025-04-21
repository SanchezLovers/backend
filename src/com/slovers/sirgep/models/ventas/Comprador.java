package models.ventas;

import models.gestion.Persona;

public class Comprador extends Persona{
	//Atributos
	private boolean esRegistrado;

	public Comprador(){
		esRegistrado = false;
    }

	// Getter y Setter para esRegistrado
    public boolean isRegistrado() {
        return this.esRegistrado;
    }
    public void setEsRegistrado(boolean esRegistrado) {
        this.esRegistrado = esRegistrado;
    }
    // Metodos
    @Override
    public String ToString() {
        String cadena = super.ToString() + "\n";
        cadena += "Registrado: " + (this.esRegistrado ? "Sí" : "No");
        return cadena;
    }


    //Listar reservas
    
    public String listarReservas() {
        if (!this.esRegistrado) {
            return "Este comprador no está registrado. No puede visualizar sus reservas.";
        }

        String cadena = "Reservas realizadas por " + this.getNombres() + " " + this.getPrimerApellido() + ":\n";
        for (Reserva r : this.getReservas()) {
            cadena += r.ToString() + "\n";
        }
        return cadena;
    }

}
