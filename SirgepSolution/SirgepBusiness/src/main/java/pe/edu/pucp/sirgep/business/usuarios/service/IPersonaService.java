/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.usuarios.service;

/**
 *
 * @author Ana Gabriela
 */
public interface IPersonaService {
    int validarCuenta(String correo, String passcode);
    public boolean enviarCorreoRecuperacion(String asunto, String contenido);
    public String obtenerNombreUsuario(int id);
}
