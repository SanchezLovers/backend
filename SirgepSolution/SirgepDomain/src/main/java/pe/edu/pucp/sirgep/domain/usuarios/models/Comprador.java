package pe.edu.pucp.sirgep.domain.usuarios.models;

import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class Comprador extends Persona{
    //Atributos
    private boolean esRegistrado;
    //private double monto_billetera;

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
    public String toString() {
        String cadena = super.toString() + "\n";
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
            cadena += r.toString() + "\n";
        }
        return cadena;
    }

}
