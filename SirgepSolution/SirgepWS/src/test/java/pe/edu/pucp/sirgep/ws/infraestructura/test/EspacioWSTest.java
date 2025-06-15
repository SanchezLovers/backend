///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package pe.edu.pucp.sirgep.ws.infraestructura.test;
//
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestMethodOrder;
//import pe.edu.pucp.sirgep.business.infraestructura.impl.EspacioServiceImpl;
//import pe.edu.pucp.sirgep.business.infraestructura.service.IEspacioService;
//import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
//
///**
// *
// * @author benny
// */
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//
//public class EspacioWSTest {
//    private final IEspacioService espacioService;
//    public EspacioWSTest() {
//        espacioService = new EspacioServiceImpl();
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//        
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    @Test
//    @Order(1)
//    public void listarTest(){
//        List<Espacio> espacios = espacioService.listar();
//        
//        for(Espacio e : espacios){
//            System.out.println(e.getNombre());
//        }
//        
//        assert(espacios!=null);
//    }
//    
//}
