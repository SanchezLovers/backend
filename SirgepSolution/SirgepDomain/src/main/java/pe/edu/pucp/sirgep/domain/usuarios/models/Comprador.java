package pe.edu.pucp.sirgep.domain.usuarios.models;

import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class Comprador extends Persona{
    //Atributos
    private int esRegistrado;
    private double monto;

    public Comprador(){
        esRegistrado = 0;
    }

	// Getter y Setter para esRegistrado
    public int isRegistrado() {
        return this.esRegistrado;
    }
    public void setEsRegistrado(int esRegistrado) {
        this.esRegistrado = esRegistrado;
    }
    // Metodos
    @Override
    public String toString() {
        String cadena = super.toString() + "\n";
        //cadena += "Registrado: " + (this.esRegistrado ? "Sí" : "No");
        return cadena;
    }
    public double getMonto_billetera() {
        return monto;
    }

    public void setMonto_billetera(double monto) {
        this.monto = monto;
    }

    //Listar reservas
    
    public String listarReservas() {
        if (this.esRegistrado==0) {
            return "Este comprador no está registrado. No puede visualizar sus reservas.";
        }

        String cadena = "Reservas realizadas por " + this.getNombres() + " " + this.getPrimerApellido() + ":\n";
        for (Reserva r : this.getReservas()) {
            cadena += r.toString() + "\n";
        }
        return cadena;
    }

}
