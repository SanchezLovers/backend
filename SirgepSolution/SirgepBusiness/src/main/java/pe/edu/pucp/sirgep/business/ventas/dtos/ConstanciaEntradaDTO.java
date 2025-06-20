package pe.edu.pucp.sirgep.business.ventas.dtos;

public class ConstanciaEntradaDTO extends DetalleConstanciaDTO{
    //Atributos
    private DetalleEntradaDTO detalleEntrada;
    
    //Constructor
    public ConstanciaEntradaDTO() {
    }
    
    //Propiedades
    public void setDetalleEntrada(DetalleEntradaDTO detalleEntrada) {
        this.detalleEntrada = detalleEntrada;
    }
    public DetalleEntradaDTO getDetalleEntrada() {
        return detalleEntrada;
    }
}