package pe.edu.pucp.sirgep.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pe.edu.pucp.sirgep.business.infraestructura.jobs.EstadoJob;

@WebListener
public class ListenerServidor implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.err.println("Aplicacion Iniciada...");
        EstadoJob.iniciar();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplicación finalizada.");
    }
}