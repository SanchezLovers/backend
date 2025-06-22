/*
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

public class FuncionDATest {
    private FuncionDAO es;
    
    @Test
    public void testListar() {
        this.es= new FuncionImpl();
        System.out.println("Ejecutando test listar...");
        List<Funcion> lista = this.es.listar();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        assertNotNull(lista);
    }
}
//
//import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
//
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.TestMethodOrder;
//import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
//import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
//import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
//import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

//public class FuncionDATest {
//    private FuncionDAO es;
//    
//    @Test
//    @Order(1)
//    public void testListar() {
//        this.es= new FuncionImpl();
//        System.out.println("Ejecutando test listar...");
//        List<Funcion> lista = this.es.listar();
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i).toString());
//        }
//        assertNotNull(lista);
//    }
//}
*/