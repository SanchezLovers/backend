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

public class EnvioCorreo {

    //Atributos
    private static String emailOrigen = "sirgep.oficial@gmail.com";//Esto debe estar en un archivo de configuracion
    private static String passwordOrigen = "psqv xpfc zkus djee";//Esto debe estar en un archivo de configuracion
    private Authenticator autenticador;
    private Properties properties;
    private Session session;
    private MimeMessage correo;
//    private File[] adjuntos;
//    private String nombresArchivos;

    //Consructor
    public EnvioCorreo() {
        //nombresArchivos = "";
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", emailOrigen);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");
        autenticador = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailOrigen, passwordOrigen);
            }
        };
    }

    public boolean enviarEmail(List<String> listaCorreosCompradores, String asunto, String contenidoHtml) {
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
            correo.setRecipients(Message.RecipientType.TO, destinatarios);
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