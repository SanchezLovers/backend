package pe.edu.pucp.sirgep.business.usuarios.service;

public interface IPersonaService {
    int validarCuenta(String correo, String passcode);
    public boolean enviarCorreoRecuperacion(String asunto, String contenido);
    public String obtenerNombreUsuario(int id);
}