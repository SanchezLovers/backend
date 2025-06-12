package pe.edu.pucp.sirgep.business.ventas.dtos;

import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class DetalleReserva {
    //Atributos
    private Reserva reserva;
    private Espacio espacio;
    private Distrito distrito;
    
    //Constructor
    public DetalleReserva() {
    }
    
    //Propiedades
    public Reserva getReserva() {
        return reserva;
    }
    public Espacio getEspacio() {
        return espacio;
    }
    public Distrito getDistrito() {
        return distrito;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }
    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
}