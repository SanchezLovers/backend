package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Map;

public class ConstanciaReservaDTO extends DetalleConstanciaDTO {
    //Atributos
    private DetalleReservaDTO detalleReserva;
    
    //Constructor
    public ConstanciaReservaDTO() {
        detalleReserva=new DetalleReservaDTO();
    }
    
    //Propiedades
    public void setDetalleReserva(DetalleReservaDTO detalleReserva) {
        this.detalleReserva = detalleReserva;
    }
    public DetalleReservaDTO getDetalleReserva() {
        return detalleReserva;
    }
    
    //Metodos
    public void llenarConstanciaReserva(Map<String, Object>detalle){
        try{
            if (detalle != null) {
                this.detalleReserva.llenarDetalleReserva(detalle);
                this.llenarDetalleConstancia(detalle);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la constancia de la reserva: " + ex.getMessage());
        }
    }
}