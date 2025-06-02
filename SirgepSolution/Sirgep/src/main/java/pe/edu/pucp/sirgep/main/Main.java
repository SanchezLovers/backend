package pe.edu.pucp.sirgep.main;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ventas.enums.EMetodoPago;
import pe.edu.pucp.sirgep.domain.ventas.models.Constancia;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DepartamentoImpl;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.AdministradorImpl;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ConstanciaImpl;

import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 
 * @author Ana Gabriela 
 */

public class Main{

    public static void main(String[] args) throws Exception,SQLException, IOException{
        Connection con = DBManager.getInstance().getConnection();
        //Implementación de pruebas DAO y MySQL
//        probarEspacio();
        
        probarAdministrador();
        
//        probarEvento();

//        probarDepartamento();
//        probarProvincia();
        
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

    static void probarEspacio(){
     //Clase Espacio
        EspacioImpl esp = new EspacioImpl();
        Espacio espacio =  new Espacio();


        Distrito distrito = devuelveDistrito();

        espacio.setDistrito(distrito);
        espacio.setHorarioInicioAtencion(LocalTime.NOON);
        espacio.setHorarioFinAtencion(LocalTime.MIDNIGHT);
        espacio.setNombre("PRUEBA LAB Cancha de Futbol Universitaria");
        espacio.setPrecioReserva(6);
        espacio.setSuperficie(400);
        espacio.setTipoEspacio(ETipoEspacio.CANCHA);
        espacio.setUbicacion("Avenida Unviersitaria 1023");
        //----------------------------------------------------------------------
        //INSERT
        //Insertar el espacio
        /*
        try {
            esp.insertar(espacio);
            System.out.println("Espacio "+ espacio.getNombre() +" insertado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        //System.out.println("Insertado con ID: " + espacio.getIdEspacio());
        

        esp.insertar(espacio);
        System.out.println("Espacio "+ espacio.getNombre() +" insertado."); 


        //----------------------------------------------------------------------
        //UPDATE
        //esp.actualizar funciona
        Espacio espacioModificar =  espacio;
        
        espacioModificar.setIdEspacio(6);
        espacioModificar.setNombre("ELIMINAR");
        espacioModificar.setPrecioReserva(8);
        espacioModificar.setSuperficie(6000);
        /*
        try {
            esp.actualizar(espacioModificar);
            System.out.println("Espacio " + espacioModificar.getNombre()+ " ha sido modificado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        //----------------------------------------------------------------------
        //Obtener todos
        /*
        try {
            ArrayList<Espacio> espacios = esp.obtenerTodos();
            System.out.println("Lista de Todos los espacios independiente al estado:");
            for (Espacio e : espacios) {
        esp.actualizar(espacioModificar);
        System.out.println("Espacio " + espacioModificar.getNombre()+ " ha sido modificado.");
        
        //----------------------------------------------------------------------
        //Obtener todos
        
        List<Espacio> espacios = esp.listar();
        System.out.println("Lista de Todos los espacios independiente al estado:");
        for (Espacio e : espacios) {
            System.out.println(e.getNombre() + " - " + e.getUbicacion());
        }
        */
        
        //----------------------------------------------------------------------
        //obtener por id
        /*
        try {
            Espacio espacioId1 = esp.obtenerPorId(6);
            System.out.println("Se ha obtenido : " + espacioId1.getNombre() +
                    " Mediante el ID: " + espacioId1.getIdEspacio()); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        
        Espacio espacioId1 = esp.buscar(2);
        System.out.println("Se ha obtenido : " + espacioId1.getNombre() +" Mediante el ID: " + espacioId1.getIdEspacio()); 
        

        //----------------------------------------------------------------------
        //Eliminado lógico 
        esp.eliminarLogico(espacioModificar.getIdEspacio());

        
        //Eliminado Fisico
//        esp.eliminarFisico(espacio.getIdEspacio());

    }
    
    static void probarAdministrador(){
        //**********************************************************************
        //PERSONA 
        //ADMINISTRADOR
        Administrador admin = new Administrador();
//        
        admin.setIdPersona(1);
        admin.setNombres("Melanie PRUEBA");
        admin.setContrasenia("benz123");
        admin.setPrimerApellido("Blanco");
        admin.setSegundoApellido("Baca");
        admin.setCorreo("Beny@admin.com");
        admin.setNumDocumento("123456");
        admin.setTipoDocumento(ETipoDocumento.DNI);
        admin.setTipoAdministrador(ETipoAdministrador.REGIONAL);
        
        AdministradorImpl adminSql = new AdministradorImpl();
        //INSERTAR
        /*
        try {
            aSql.insertar(admin);
            System.out.println("Persona "+ admin.getNombres()+" insertada correctamente.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        */
        //ACTUALIZAR 
        admin.setIdPersona(6);
        admin.setNombres("ELIMINAR");
        admin.setContrasenia("anac123");
        admin.setPrimerApellido("Cris");
        admin.setSegundoApellido("tina");
        admin.setCorreo("anaC@admin.com");
        admin.setNumDocumento("123456");
        admin.setTipoAdministrador(ETipoAdministrador.MUNICIPAL);
        
        adminSql.actualizar(admin);
        System.out.println("Administrador "+ admin.getNombres()+" actualizado correctamente.");
        
        //CONSULTAR POR ID
        Administrador adminConsulta = adminSql.buscar(admin.getIdPersona());
        System.out.println("Persona "+ adminConsulta.getNombres()+" obtenida mediante su ID: " + adminConsulta.getIdPersona());
        
        //CONSULTAR TODOS
        List<Administrador> admins = adminSql.listar(); //ELIMINAR
        System.out.println("Lista de Todos los adminsitradores independiente al estado:");
        for (Administrador a : admins) {
            System.out.println(a.getNombres() + " - " + a.getTipoAdministrador());
        }
        
        adminSql.eliminarLogico(admin.getIdPersona());
        System.out.println("Estado de actividad de Adminsitrador " + admin.getNombres() +" marcado como 'E' (eliminado)");

        /*
        try {
            aSql.actualizar(admin);
            System.out.println("Persona "+ admin.getNombres()+" actualizada correctamente.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        
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
        constanciaMySQL.eliminarLogico(7);
        
        //Obtener por ID
        constancia=constanciaMySQL.buscar(6);
        System.out.println(constancia);
        
        //Obtener TODOS
        List<Constancia> constancias=constanciaMySQL.listar();
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
            Departamento d = depaMySQL.buscar(departamento.getIdDepartamento());
            System.out.println("obtenido por ID: "); 
            System.out.println(d); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        // obtener Todos
        
        try {
            List<Departamento> depas = depaMySQL.listar();
            System.out.println("Lista de Departamentos idependientemente de la columna Activo:");
            for(Departamento d : depas){
                System.out.println(d);
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        
        //eliminar logico
        try {
            depaMySQL.eliminarLogico(departamento.getIdDepartamento());
            System.out.println("Departamento "+ departamento.getNombre() +" con estado E (Eliminado)."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
