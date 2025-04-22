package com.slovers.sirgep.persistencia.app;

import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.dominio.models.ventas.Comprador;
import com.slovers.sirgep.persistencia.config.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal{
    public static void main(String[] args) throws SQLException, IOException{
//        Persona per = new Comprador();
//        
//        per.setNombres("Julio Pepe");
//        per.setContrasenia("ContraseniaSegura");
//        per.setPrimerApellido("Perez");
//        per.setSegundoApellido("Sanchez");
//        
//        System.out.println(per);

        Connection con = DBManager.getInstance().getConnection();
        
    }
}