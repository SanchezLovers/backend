package pe.edu.pucp.sirgep.business.infraestructura.dtos;

import java.util.List;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;

public class EnvioCorreo {
    private static EnvioCorreo envioCorreo;
    
    private static String emailOrigen;
    private static String passwordEmailOrigen;
    private Authenticator autenticador;
    private Properties properties;
    private Session session;
    private MimeMessage correo;
//    private File[] adjuntos;
//    private String nombresArchivos;

    private EnvioCorreo() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("No se pudo abrir el archivo config.properties");
                return;
            }
            properties.load(input);
            definirPropiedades();
        }
        catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo config.properties: ",e);
        }
    }
    
    private void definirPropiedades(){
        emailOrigen = properties.getProperty("config.emailOrigen");
        passwordEmailOrigen = properties.getProperty("config.passwordEmailOrigen");
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", emailOrigen);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");
        autenticador = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailOrigen, passwordEmailOrigen);
            }
        };
    }

    public synchronized static EnvioCorreo getInstance()  {
        if (envioCorreo == null) {
            envioCorreo = new EnvioCorreo();
        }
        return envioCorreo;
    }
    
    public boolean enviarEmail(List<String> listaCorreosCompradores, String asunto, String contenidoHtml) {
        if(listaCorreosCompradores.isEmpty()){
            return false;
        }
        try {
            session = Session.getInstance(properties, autenticador);
            correo = new MimeMessage(session);
            // Remitente
            correo.setFrom(new InternetAddress(emailOrigen));
            // Destinatarios
            InternetAddress[] destinatarios = new InternetAddress[listaCorreosCompradores.size()];
            for (int i = 0; i < listaCorreosCompradores.size(); i++) {
                destinatarios[i] = new InternetAddress(listaCorreosCompradores.get(i));
            }
            correo.setRecipients(Message.RecipientType.BCC, destinatarios);
            correo.setSubject(asunto);
            correo.setSentDate(new java.util.Date());
            // Parte: Cuerpo HTML con imagen desde URL
            MimeBodyPart cuerpoHtml = new MimeBodyPart();
            cuerpoHtml.setContent(contenidoHtml, "text/html; charset=utf-8");
            // Multipart para ensamblar contenido
            Multipart contenidoCorreo = new MimeMultipart();
            contenidoCorreo.addBodyPart(cuerpoHtml);
            correo.setContent(contenidoCorreo);
            // Enviar
            Transport.send(correo);
            System.out.println("Correo enviado correctamente");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar los correos: " + e.getMessage());
            throw new RuntimeException("No se enviaron los correos: "+e.getMessage());
        }
    }
}