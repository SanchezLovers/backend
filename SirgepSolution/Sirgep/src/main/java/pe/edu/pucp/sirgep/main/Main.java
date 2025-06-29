package pe.edu.pucp.sirgep.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.PersonaImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import static pe.edu.pucp.sirgep.dbmanager.EncriptadorChaCha20.desencripta;
import static pe.edu.pucp.sirgep.dbmanager.EncriptadorChaCha20.encripta;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class Main {
    public static void main(String[] args) throws Exception, SQLException, IOException {
        Connection con = DBManager.getInstance().getConnection();
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256); // 256 bits
        SecretKey key = keyGen.generateKey();
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Llave ChaCha20 (Base64): " + base64Key);
        String original = base64Key;
        System.out.println("Texto original: " + original);
        String cifrado = encripta(original,"mail");
        System.out.println("Texto cifrado: " + cifrado);
        String descifrado = desencripta(cifrado,"mail");
        System.out.println("Texto descifrado: " + descifrado);
    }
}
