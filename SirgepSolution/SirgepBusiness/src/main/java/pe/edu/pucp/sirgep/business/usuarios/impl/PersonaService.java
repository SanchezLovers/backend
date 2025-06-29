package pe.edu.pucp.sirgep.business.usuarios.impl;

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
}