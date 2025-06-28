//package pe.edu.pucp.ventas;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.MethodOrderer;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleEntradaDTO;
//import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
//import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
//import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
//import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
//import pe.edu.pucp.sirgep.ws.ventas.EntradaWS;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class EntradaWSTest {
//    private Entrada entrada;
//    private EntradaWS entradaWS;
//
//    @BeforeEach
//    public void setUp() {
//        this.entradaWS = new EntradaWS();
//    }
//
//@Test
//@Order(1)
//public void testInsertar() {
//    System.out.println("Ejecutando test insertar...");
//    String fechaInicio = "2025-05-12";
//    String fechaFin = "2025-10-03";
//    String estado="Canceladas";
//    List<DetalleEntradaDTO> resultado = entradaWS.listarDetalleEntradasFiltradaPorComprador(40, fechaInicio, fechaFin, estado);
//    assertNotNull(resultado, "La lista de resultados no debe ser null");
//    System.out.println("Tamaño de resultado: " + resultado.size());
//}
//
//}