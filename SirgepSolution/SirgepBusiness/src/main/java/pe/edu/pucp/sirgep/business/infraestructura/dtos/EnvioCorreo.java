package pe.edu.pucp.sirgep.business.infraestructura.dtos;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

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

    public boolean enviarEmail(List<String> listaCorreosCompradores, String asunto, String contenido, String rutaLogo) {
        boolean resultado = false;
        session = Session.getInstance(properties, autenticador);
        correo = new MimeMessage(session);

        try {
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

            // Parte 1: Cuerpo HTML con referencia al logo (ya incluida en 'contenido')
            MimeBodyPart cuerpoHtml = new MimeBodyPart();
            cuerpoHtml.setContent(contenido, "text/html");

            // Parte 2: Imagen del logo como recurso embebido
            MimeBodyPart imagenLogo = new MimeBodyPart();
            DataSource fds = new FileDataSource(rutaLogo);
            imagenLogo.setDataHandler(new DataHandler(fds));
            imagenLogo.setHeader("Content-ID", "<logo>");
            imagenLogo.setDisposition(MimeBodyPart.INLINE); // Mostrar dentro del cuerpo

            // Parte 3: Ensamblar todo
            Multipart contenidoCorreo = new MimeMultipart();
            contenidoCorreo.addBodyPart(cuerpoHtml);
            contenidoCorreo.addBodyPart(imagenLogo);

            correo.setContent(contenidoCorreo);

            // Enviar
            Transport.send(correo);
            JOptionPane.showMessageDialog(null, "Correo enviado");
            resultado = true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al enviar el correo: " + e.getMessage());
        }

        return resultado;
    }

}
