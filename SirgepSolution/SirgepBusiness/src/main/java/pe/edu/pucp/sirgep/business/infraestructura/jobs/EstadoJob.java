package pe.edu.pucp.sirgep.business.infraestructura.jobs;
import java.util.concurrent.*;
import pe.edu.pucp.sirgep.business.infraestructura.impl.EventoServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.impl.FuncionServiceImpl;
import pe.edu.pucp.sirgep.business.infraestructura.service.IEventoService;
import pe.edu.pucp.sirgep.business.infraestructura.service.IFuncionService;
import pe.edu.pucp.sirgep.business.ventas.impl.EntradaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.impl.ReservaServiceImpl;
import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.business.ventas.service.IReservaService;

public class EstadoJob {
    private static final ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(1);
    private static IEventoService eventoService;
    private static IFuncionService funcionService;
    private static IReservaService reservaService;
    private static IEntradaService entradaService;

    public void actualizarEstados() {
        eventoService = new EventoServiceImpl();
        funcionService = new FuncionServiceImpl();
        reservaService = new ReservaServiceImpl();
        entradaService = new EntradaServiceImpl();

        reservaService.inactivar();
        System.out.println("reserva actualizados");
        entradaService.inactivar();
        System.out.println("entrada actualizados");
        eventoService.inactivar();
        System.out.println("Eventos actualizados");
        funcionService.inactivar();
        System.out.println("Funciones actualizados");
    }

    public static void iniciar() {
        Runnable tarea = () -> {
            System.out.println("Se inició la actualización de estados.");
            //llamado de la funcion que actualiza
            new EstadoJob().actualizarEstados();
        };

        //timer para que se vuelva a aejecutar
        scheduler.scheduleAtFixedRate(tarea, 0, 24, TimeUnit.HOURS);
        //el tiempo será 24 horas
    }
}