package pe.edu.pucp.sirgep.domain.usuarios.models;

import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class Comprador extends Persona {

    //Atributos
    private int esRegistrado;
    private double monto;

    public Comprador() {
        esRegistrado = 0;
    }
// Getter y Setter para esRegistrado

    public int getRegistrado() {
        return this.esRegistrado;
    }

    public void setRegistrado(int esRegistrado) {
        this.esRegistrado = esRegistrado;
    }

    // Metodos
    @Override
    public String toString() {
        String cadena = super.toString() + "\n";
        //cadena += "Registrado: " + (this.esRegistrado ? "Sí" : "No");
        return cadena;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    //Listar reservas
    public String listarReservas() {
        if (this.esRegistrado == 0) {
            return "Este comprador no está registrado. No puede visualizar sus reservas.";
        }

        String cadena = "Reservas realizadas por " + this.getNombres() + " " + this.getPrimerApellido() + ":\n";
        for (Reserva r : this.getReservas()) {
            cadena += r.toString() + "\n";
        }
        return cadena;
    }

}
