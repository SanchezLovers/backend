package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Map;

public class ConstanciaEntradaDTO extends DetalleConstanciaDTO{
    //Atributos
    private DetalleEntradaDTO detalleEntrada;
    
    //Constructor
    public ConstanciaEntradaDTO() {
        detalleEntrada=new DetalleEntradaDTO();
    }
    
    //Propiedades
    public void setDetalleEntrada(DetalleEntradaDTO detalleEntrada) {
        this.detalleEntrada = detalleEntrada;
    }
    public DetalleEntradaDTO getDetalleEntrada() {
        return detalleEntrada;
    }
    
    //Metodos
    public void llenarConstanciaEntrada(Map<String, Object>detalle){
        try{
            if (detalle != null) {
                this.detalleEntrada.llenarDetalleEntrada(detalle);
                this.llenarDetalleConstancia(detalle);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar el detalle de la constancia de la entrada: " + ex.getMessage());
        }
    }
}