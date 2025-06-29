package pe.edu.pucp.sirgep.business.usuarios.dtos;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static final String SECRET_KEY = loadSecretKey();
    // Leer la clave secreta desde el archivo token.properties
    private static String loadSecretKey() {
        try (InputStream input = AES.class.getClassLoader().getResourceAsStream("token.properties")) {
            if (input == null) {
                System.err.println("No se encontró el archivo token.properties");
                return null;
            }
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("encryption.key");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKeySpec createKey(String myKey) throws Exception {
        if (myKey == null || myKey.isEmpty()) {
            throw new IllegalArgumentException("La clave secreta no puede ser null o vacía.");
        }
        byte[] keyBytes = myKey.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        byte[] keyFinal = new byte[16]; // AES-128
        System.arraycopy(keyBytes, 0, keyFinal, 0, 16);
        return new SecretKeySpec(keyFinal, "AES");
    }
    public static String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKey = createKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec secretKey = createKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(strToDecrypt);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}