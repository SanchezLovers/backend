package pe.edu.pucp.sirgep.main;


import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DepartamentoImpl;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.AdministradorImpl;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ConstanciaImpl;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.PersonaImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.PersonaDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;

public class Main{
    public static void main(String[] args) throws Exception,SQLException, IOException{
        Connection con = DBManager.getInstance().getConnection();
        //Implementación de pruebas DAO y MySQL
//        probarEspacio();
        
//        probarAdministrador();
        
//        probarEvento();

//        probarDepartamento();
        probarConstancia();
    }
    
    static Distrito devuelveDistrito(){

        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(1);
        departamento.setNombre("Lima");
        
        Provincia provincia = new Provincia();
        provincia.setDepartamento(departamento);
        provincia.setIdProvincia(1);
        provincia.setNombre("Lima");
        
        Distrito distrito = new Distrito();
        distrito.setNombre("San Miguel");
        distrito.setIdDistrito(1);
        distrito.setProvincia(provincia);
        return distrito;
    }
    
    
    static void probarEvento(){
        Evento evento =  new Evento();
        
        EventoImpl evSQL = new EventoImpl();
        
        Distrito distrito = devuelveDistrito();
        
        evento.setCantEntradasDispo(20);
        evento.setCantEntradasVendidas(0);
        evento.setDistrito(distrito);
        evento.setIdEvento(7);
        evento.setNombre("Festival De Comida Chilena");
        evento.setPrecioEntrada(5.5);
        evento.setUbicacion("Avenida La Paz 123");
        evento.setReferencia("Anfiteatro San Miguel");
        evento.setFecha(new Date());
        
        //INSERTAR
        
        try {
            evSQL.insertar(evento);
            System.out.println("Evento "+ evento.getNombre() +" insertado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //UPDATE
        evento.setNombre("ELIMINAR");
        
        try {
            evSQL.actualizar(evento);
            System.out.println("Evento "+ evento.getNombre() +" actualizado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        
        //Obtener por Id
        
        try {
            evSQL.obtenerPorId(1);
            System.out.println("Evento "+ evento.getNombre() +" obtenido por su "
                    + "ID: " + evento.getIdEvento()); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //ObtenerTodos
        try {
            ArrayList<Evento> eventos = evSQL.obtenerTodos();
            System.out.println("Lista de Todos los Eventos independiente al estado:");
            for (Evento e : eventos) {
            System.out.println(e.getNombre() + " - " + e.getUbicacion());
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //ELIMINAR
        try {
            evSQL.eliminar(evento.getIdEvento());
            System.out.println("Espacio "+ evento.getNombre() +" tiene Activo = E.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    static void probarEspacio(){
     //Clase Espacio
        EspacioImpl esp = new EspacioImpl();
        Espacio espacio =  new Espacio();


        Distrito distrito = devuelveDistrito();

        espacio.setDistrito(distrito);
        espacio.setHorarioInicioAtencion(LocalTime.NOON);
        espacio.setHorarioFinAtencion(LocalTime.MIDNIGHT);
        espacio.setNombre("PRUEBA 2 Cancha de Futbol Universitaria");
        espacio.setPrecioReserva(6);
        espacio.setSuperficie(400);
        espacio.setTipoEspacio(ETipoEspacio.CANCHA);
        espacio.setUbicacion("Avenida Unviersitaria 1023");
        //----------------------------------------------------------------------
        //INSERT
        //Insertar el espacio

        esp.insertar(espacio);
        System.out.println("Espacio "+ espacio.getNombre() +" insertado."); 


        //----------------------------------------------------------------------
        //UPDATE
//        esp.actualizar funciona
        Espacio espacioModificar =  espacio;

        espacioModificar.setIdEspacio(4);
    //        espacioModificar.setNombre("ELIMINAR");
        espacioModificar.setPrecioReserva(8);
        espacioModificar.setSuperficie(4100);
        
        esp.actualizar(espacioModificar);
        System.out.println("Espacio " + espacioModificar.getNombre()+ " ha sido modificado.");
        
        //----------------------------------------------------------------------
        //Obtener todos
        
        List<Espacio> espacios = esp.listar();
        System.out.println("Lista de Todos los espacios independiente al estado:");
        for (Espacio e : espacios) {
            System.out.println(e.getNombre() + " - " + e.getUbicacion());
        }
        
        //----------------------------------------------------------------------
        //obtener por id
        
        Espacio espacioId1 = esp.buscar(2);
        System.out.println("Se ha obtenido : " + espacioId1.getNombre() +" Mediante el ID: " + espacioId1.getIdEspacio()); 
        

        //----------------------------------------------------------------------
        //Eliminado lógico 
        /*
        try {
            esp.eliminar(espacioModificar.getIdEspacio());
            System.out.println("Espacio "+ espacioModificar.getNombre() +" tiene Activo = E.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        //Eliminado Fisico

        try {
            esp.eliminarFisico(espacio.getIdEspacio());
            System.out.println("Espacio "+ espacio.getNombre()+ " ha sido permanenentemente eliminado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
    }
    
    static void probarAdministrador(){
        //**********************************************************************
        //PERSONA 
        //ADMINISTRADOR
        Administrador admin = new Administrador();
//        
        admin.setIdPersona(1);
        admin.setNombres("Benny");
        admin.setContrasenia("benz123");
        admin.setPrimerApellido("Benny");
        admin.setSegundoApellido("Blanco");
        admin.setCorreo("benny@admin.com");
        admin.setNumDocumento("123455");
        admin.setTipoDocumento(ETipoDocumento.DNI);
        admin.setTipoAdministrador(ETipoAdministrador.MUNICIPAL);
        
        AdministradorImpl adminSql = new AdministradorImpl();
        //INSERTAR
        /*
        try {
            adminSql.insertar(admin);
            System.out.println("Administrador " + admin.getTipoAdministrador() + " "
                    + admin.getNombres()+" insertado correctamente.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        
        //ACTUALIZAR 
        admin.setIdPersona(3);
        admin.setNombres("ELIMINAR");
        admin.setContrasenia("ELIMINAR");
        admin.setPrimerApellido("Cris");
        admin.setSegundoApellido("tina");
        admin.setCorreo("anaC@admin.com");
        admin.setNumDocumento("123456");
        admin.setTipoAdministrador(ETipoAdministrador.MUNICIPAL);
        
        
        try {
            adminSql.actualizar(admin);
            System.out.println("Administrador "+ admin.getNombres()+" actualizado correctamente.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //CONSULTAR POR ID
        
        try {
            Administrador adminConsulta = adminSql.obtenerPorId(admin.getIdPersona());
            System.out.println("Persona "+ adminConsulta.getNombres()+ 
                    " obtenida mediante su ID: " + adminConsulta.getIdPersona());
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //CONSULTAR TODOS
        
        try {
            ArrayList<Administrador> admins = adminSql.obtenerTodos();
            System.out.println("Lista de Todos los adminsitradores independiente al estado:");
            for (Administrador a : admins) {
            System.out.println(a.getNombres() + " - " + a.getTipoAdministrador());
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        //ELIMINAR
        
        try {
            adminSql.eliminar(admin.getIdPersona());
            System.out.println("Estado de actividad de Adminsitrador " + admin.getNombres() + 
                    " marcado como 'E' (eliminado)");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static void probarConstancia(){
        
        //Prueba de Constancia
        Constancia constancia= new Constancia();
        SimpleDateFormat fechaConstancia = new SimpleDateFormat("yyyy-MM-dd");//import java.text.SimpleDateFormat;
        constancia.setIdConstancia(1);//Comentar, si se va a usar el insert
        try{
            constancia.setFecha(fechaConstancia.parse("2025-12-13"));
        }catch(ParseException p){}
        constancia.setMetodoPago(EMetodoPago.TARJETA);
        constancia.setTotal(90);
        constancia.setDetallePago("PAGO PRUEBA");
        ConstanciaImpl constanciaMySQL = new ConstanciaImpl();
        
        //Insertar 
        constanciaMySQL.insertar(constancia);
        
        constancia.setDetallePago("Fallido (F)");
        //Actualizar
        constanciaMySQL.actualizar(constancia);
        
        //Eliminar
        constanciaMySQL.eliminar(7);
        
        //Obtener por ID
        constancia=constanciaMySQL.obtenerPorId(6);
        System.out.println(constancia);
        
        //Obtener TODOS
        ArrayList<Constancia> constancias=constanciaMySQL.obtenerTodosActivos();
        for(Constancia c : constancias){
            System.out.println(c);
        }
    }
    
    static void probarDepartamento(){
        //Probando rama de Ana Cristina:
        
        Departamento departamento = new Departamento();
        
        departamento.setIdDepartamento(17);
        departamento.setNombre("TesteoDepa");
        
        DepartamentoImpl depaMySQL = new DepartamentoImpl();
        //inserts

        departamento.setNombre("TestDepa2");
        departamento.setIdDepartamento(19);
        try {
            depaMySQL.insertar(departamento);
            System.out.println("Departamento "+ departamento.getNombre() +" insertado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //actualizar
        
        departamento.setIdDepartamento(17);
        departamento.setNombre("ELIMINAR");
        try {
            depaMySQL.actualizar(departamento);
            System.out.println("Departamento "+ departamento.getNombre() +" actualizado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        // obtener por ID
        try {
            Departamento d = depaMySQL.obtenerPorId(departamento.getIdDepartamento());
            System.out.println("obtenido por ID: "); 
            System.out.println(d); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        // obtener Todos
        
        try {
            ArrayList<Departamento> depas = depaMySQL.obtenerTodos();
            System.out.println("Lista de Departamentos idependientemente de la columna Activo:");
            for(Departamento d : depas){
                System.out.println(d);
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        
        //eliminar logico
        try {
            depaMySQL.eliminar(departamento.getIdDepartamento());
            System.out.println("Departamento "+ departamento.getNombre() +" con estado E (Eliminado)."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
}