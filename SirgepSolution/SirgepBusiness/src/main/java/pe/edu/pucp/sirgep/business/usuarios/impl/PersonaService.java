/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.usuarios.impl;

import pe.edu.pucp.sirgep.business.usuarios.service.IPersonaService;
import pe.edu.pucp.sirgep.da.usuarios.dao.PersonaDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.PersonaImpl;

/**
 *
 * @author Ana Gabriela
 */
public class PersonaService implements IPersonaService{
    PersonaDAO pdao;
    public PersonaService(){
        pdao = new PersonaImpl();
    }
    
    public int validarCuenta(String correo, String passcode) {
        return pdao.validarCuenta(correo, passcode);
    }

}
