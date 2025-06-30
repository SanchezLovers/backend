package pe.edu.pucp.sirgep.business.usuarios.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.business.usuarios.dtos.EncriptadorAES;
import pe.edu.pucp.sirgep.business.usuarios.service.IPersonaService;
import pe.edu.pucp.sirgep.da.usuarios.dao.PersonaDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.PersonaImpl;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class PersonaService implements IPersonaService{
    PersonaDAO pdao;
    public PersonaService(){
        pdao = new PersonaImpl();
    }
    
    public int validarCuenta(String correo, String password) {
        String encrypted = EncriptadorAES.encrypt(password);
        return pdao.validarCuenta(correo, encrypted);
    }

    @Override
    public String obtenerNombreUsuario(int id){
        Persona persona = pdao.buscar(id);
        if(persona!=null)
            return  persona.getUsuario();
        return null;
    }

    @Override
    public boolean enviarCorreoRecuperacion(String asunto, String contenido) {
        Properties properties = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            String emailDestino = properties.getProperty("config.emailOrigen");
            List<String> destinatario = List.of(emailDestino);
            boolean resultado = EnvioCorreo.getInstance().enviarEmail(destinatario,asunto,contenido);
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar correo de recuperación: "+e.getMessage());
        }
    }
}