package com.slovers.sirgep.app;

import com.slovers.sirgep.models.gestion.Persona;
import com.slovers.sirgep.models.ventas.Comprador;

public class Principal{
    public static void main(String[] args){
        Persona per = new Comprador();
        
        per.setNombres("Julio Pepe");
        per.setContrasenia("ContraseniaSegura");
        per.setPrimerApellido("Perez");
        per.setSegundoApellido("Sanchez");
        
        System.out.println(per);
        
    }
}