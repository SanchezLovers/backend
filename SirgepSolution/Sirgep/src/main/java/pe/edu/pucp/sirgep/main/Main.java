package pe.edu.pucp.sirgep.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.sirgep.business.infraestructura.dtos.EnvioCorreo;
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaEntradaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaReservaDTO;
import pe.edu.pucp.sirgep.business.ventas.impl.EntradaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.impl.ReservaServiceImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;

public class Main {
    public static void main(String[] args) throws Exception, SQLException, IOException {
        Connection con = DBManager.getInstance().getConnection();
        ConstanciaReservaDTO d=new ReservaServiceImpl().buscarConstanciaReserva(1);
        EnvioCorreo envioCorreo = new EnvioCorreo();
        List<String> listaCorreosCompradores = new ArrayList<>();
        String asunto = "¡Bienvenid@ al equipo de desarrollo de SIRGEP!";
        String contenido = """
            <html>
              <body style="font-family: Arial, sans-serif; color: black; line-height: 1.6; padding: 10px;">
                <p style="color: black; font-size: 16px;"><strong>¡Hola!</strong></p>

                <p style="color: black;">
                  Nos complace darte la bienvenida al equipo de desarrollo de la aplicación web del
                  <strong style="color: black;">Sistema Integral de Reservas y Gestión de Espacios Públicos (SIRGEP)</strong>.
                </p>

                <p style="color: black;">
                  Este sistema tiene como objetivo transformar la manera en que los ciudadanos del Perú acceden a espacios públicos
                  y adquieren entradas para eventos. Tu participación será fundamental para lograr una solución moderna,
                  segura y eficiente.
                </p>

                <h3 style="color: black;">¿Qué pasos siguen?</h3>
                <ul style="color: black; padding-left: 20px;">
                  <li>Próximamente te enviaremos los accesos a los repositorios y herramientas de trabajo.</li>
                  <li>También recibirás los lineamientos de arquitectura y buenas prácticas de codificación.</li>
                  <li>Ante cualquier duda, puedes escribirnos a este correo o contactarnos por los canales internos del equipo.</li>
                </ul>

                <p style="color: black;">
                  Gracias por formar parte de este reto. Estamos seguros de que, trabajando juntos, construiremos una plataforma con
                  verdadero impacto social.
                </p>

                <p style="color: black;">
                  ¡Bienvenid@ nuevamente!<br/>
                  <strong style="color: black;">Equipo de Desarrollo de SIRGEP</strong>
                </p>

                <div style="text-align: left; margin-top: 20px;">
                  <img src='cid:logo' width='120' alt='Logo de SIRGEP'/>
                </div>

                <hr style="border: none; border-top: 1px solid black; margin-top: 30px;"/>
                <p style="font-size: 12px; color: black; text-align: center;">
                  Sistema Integral de Reservas y Gestión de Espacios Públicos (SIRGEP)
                </p>
              </body>
            </html>
            """;
        /*
        String rutaLogo = "D:\\Local\\frontend\\Sirgep\\SirgepPresentacion\\Images\\grl\\Logo.png";
        listaCorreosCompradores.add("a20206234@pucp.edu.pe");
        listaCorreosCompradores.add("a20221299@pucp.edu.pe");
        listaCorreosCompradores.add("a20222031@pucp.edu.pe");
        listaCorreosCompradores.add("a20220557@pucp.edu.pe");
        listaCorreosCompradores.add("a20206016@pucp.edu.pe");
        listaCorreosCompradores.add("a20206016@pucp.edu.pe");
        listaCorreosCompradores.add("a20220453@pucp.edu.pe");
        boolean resultado = envioCorreo.enviarEmail(listaCorreosCompradores, asunto, contenido,rutaLogo);
        if (resultado) {
            System.out.println("Correo enviado!");
        }
        */
    }
}