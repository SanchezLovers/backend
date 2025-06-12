package pe.edu.pucp.sirgep.business.ventas.dtos;

public class ConstanciaEntrada{
    //Atributos
    private DetalleEntrada detalleEntrada;
    private DetalleConstancia detalleComprador;
    
    //Constructor
    public ConstanciaEntrada() {
    }
    
    //Propiedades
    public DetalleEntrada getDetalleEntrada() {
        return detalleEntrada;
    }
    public DetalleConstancia getDetalleComprador() {
        return detalleComprador;
    }
    public void setDetalleEntrada(DetalleEntrada detalleEntrada) {
        this.detalleEntrada = detalleEntrada;
    }
    public void setDetalleComprador(DetalleConstancia detalleComprador) {
        this.detalleComprador = detalleComprador;
    }
}