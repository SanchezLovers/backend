package pe.edu.pucp.sirgep.business.usuarios.dtos;

public class DetalleComprador {
    private int idComprador;
    private String tipoDocumento;
    private String numeroDocumento;
    private double montoBilletera;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String distritoFavorito;
    private String provinciaFavorita;
    private String departamentoFavorito;
    private String correo;
    private String contrasenia;
    
    //Comprador
    public DetalleComprador() {
    }
    
    // Propiedades
    public int getIdComprador() {
        return idComprador;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public double getMontoBilletera() {
        return montoBilletera;
    }
    public String getNombres() {
        return nombres;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public String getDistritoFavorito() {
        return distritoFavorito;
    }
    public String getProvinciaFavorita() {
        return provinciaFavorita;
    }
    public String getDepartamentoFavorito() {
        return departamentoFavorito;
    }
    public String getCorreo() {
        return correo;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public void setMontoBilletera(double montoBilletera) {
        this.montoBilletera = montoBilletera;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public void setDistritoFavorito(String distritoFavorito) {
        this.distritoFavorito = distritoFavorito;
    }
    public void setProvinciaFavorita(String provinciaFavorita) {
        this.provinciaFavorita = provinciaFavorita;
    }
    public void setDepartamentoFavorito(String departamentoFavorito) {
        this.departamentoFavorito = departamentoFavorito;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}