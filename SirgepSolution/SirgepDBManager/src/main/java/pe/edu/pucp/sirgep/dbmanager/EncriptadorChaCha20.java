package pe.edu.pucp.sirgep.dbmanager;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class EncriptadorChaCha20 {
    private static final String PROPERTIES_FILE = "token.properties";

    private static SecretKey loadSecretKey(String tipo) {
        try (InputStream input = EncriptadorChaCha20.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo " + PROPERTIES_FILE);
            }
            Properties properties = new Properties();
            properties.load(input);
            String propertyName = tipo.equals("mail") ?"mail.encriptacion.llave" : "db.encriptacion.llave";
            String base64Key = properties.getProperty(propertyName);
            if (base64Key == null || base64Key.trim().isEmpty()) {
                throw new RuntimeException("La propiedad " + propertyName + " no está definida en " + PROPERTIES_FILE);
            }
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            if (keyBytes.length < 32) {
                System.err.println("⚠ Clave corta, rellenando con ceros.");
                byte[] padded = new byte[32];
                System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
                keyBytes = padded;
            }
            if (keyBytes.length != 32) {
                throw new RuntimeException("La clave debe tener 32 bytes (256 bits).");
            }
            return new SecretKeySpec(keyBytes, "ChaCha20");
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la llave ChaCha20", e);
        }
    }

    public static String encripta(String textoPlano, String tipo) {
        try {
            SecretKey key = loadSecretKey(tipo);
            byte[] nonce = new byte[12];
            new SecureRandom().nextBytes(nonce);

            Cipher cipher = Cipher.getInstance("ChaCha20");
            cipher.init(Cipher.ENCRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, 0));
            byte[] cifrado = cipher.doFinal(textoPlano.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(nonce) + ":" + Base64.getEncoder().encodeToString(cifrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    public static String desencripta(String textoCifrado, String tipo) {
        try {
            SecretKey key = loadSecretKey(tipo);
            String[] partes = textoCifrado.split(":");
            if (partes.length != 2) throw new IllegalArgumentException("Formato inválido");

            byte[] nonce = Base64.getDecoder().decode(partes[0]);
            byte[] cifrado = Base64.getDecoder().decode(partes[1]);

            Cipher cipher = Cipher.getInstance("ChaCha20");
            cipher.init(Cipher.DECRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, 0));
            byte[] plano = cipher.doFinal(cifrado);
            return new String(plano, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }
}