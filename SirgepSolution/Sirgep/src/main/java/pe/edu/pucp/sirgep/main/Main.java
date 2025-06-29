package pe.edu.pucp.sirgep.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import pe.edu.pucp.sirgep.business.usuarios.dtos.AES;
import pe.edu.pucp.sirgep.business.usuarios.impl.PersonaService;
import pe.edu.pucp.sirgep.dbmanager.DBManager;

public class Main {
    public static void main(String[] args) throws Exception, SQLException, IOException {
        Connection con = DBManager.getInstance().getConnection();
        //String password = "jorge123";
        String password = "RenaNahui22";
        String encrypted = AES.encrypt(password);
        String decrypted = AES.decrypt(encrypted);
        System.out.println("Original: " + password);
        System.out.println("Encriptado: " + encrypted);
        System.out.println("Desencriptado: " + decrypted);
    }
}