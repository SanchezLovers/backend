package pe.edu.pucp.sirgep.business.ventas.dtos;

import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;

public class DetalleConstancia {
    //Atributos
    private Constancia constancia;
    private Comprador comprador;
    
    //Constructor
    public DetalleConstancia() {
    }
    
    //Propiedades
    public Constancia getConstancia() {
        return constancia;
    }
    public Comprador getComprador() {
        return comprador;
    }
    public void setConstancia(Constancia constancia) {
        this.constancia = constancia;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}