package pe.edu.pucp.sirgep.business.ventas.dtos;

public class ConstanciaReserva{
    //Atributos
    private DetalleReserva detalleReserva;
    private DetalleConstancia detalleComprador;
    
    //Constructor
    public ConstanciaReserva() {
    }
    
    //Propiedades
    public DetalleReserva getDetalleEntrada() {
        return detalleReserva;
    }
    public DetalleConstancia getDetalleComprador() {
        return detalleComprador;
    }
    public void setDetalleEntrada(DetalleReserva detalleReserva) {
        this.detalleReserva = detalleReserva;
    }
    public void setDetalleComprador(DetalleConstancia detalleComprador) {
        this.detalleComprador = detalleComprador;
    }
}