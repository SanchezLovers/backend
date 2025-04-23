package com.slovers.sirgep.persistencia.app;

import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.dominio.models.gestion.Administrador;
import com.slovers.sirgep.dominio.models.gestion.Espacio;
import com.slovers.sirgep.dominio.models.gestion.Departamento;
import com.slovers.sirgep.dominio.models.gestion.Provincia;
import com.slovers.sirgep.dominio.models.gestion.Distrito;
import com.slovers.sirgep.dominio.models.ventas.Comprador;
import com.slovers.sirgep.dominio.enums.ETipoEspacio;
import com.slovers.sirgep.dominio.enums.ETipoAdministrador;
import com.slovers.sirgep.dominio.enums.ETipoDocumento;
import com.slovers.sirgep.persistencia.mysql.EspacioMySQL;
import com.slovers.sirgep.persistencia.mysql.AdministradorMySQL;
import com.slovers.sirgep.persistencia.config.DBManager;
import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author Ana Gabriela 
 */

public class Principal{
    public static void main(String[] args) throws SQLException, IOException{
//        

        Connection con = DBManager.getInstance().getConnection();
        
        
        //Implementación de pruebas DAO y MySQL
//        probarEspacio();
        
        probarAdministrador();
        
//        probarEvento();
//        probarDepartamento();
//        probarProvincia();
        
    }

    static void probarEspacio(){
     //Clase Espacio
        EspacioMySQL esp = new EspacioMySQL();
        Espacio espacio =  new Espacio();
        
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
        
        espacio.setDistrito(distrito);
        espacio.setHorarioInicioAtencion(LocalTime.NOON);
        espacio.setHorarioFinAtencion(LocalTime.MIDNIGHT);
        espacio.setNombre("PRUEBA Cancha de Futbol Universitaria");
        espacio.setPrecioReserva(6);
        espacio.setSuperficie(400);
        espacio.setTipoEspacio(ETipoEspacio.CANCHA);
        espacio.setUbicacion("Avenida Unviersitaria 1023");
        //----------------------------------------------------------------------
        //INSERT
        //Insertar el espacio
        
        try {
            esp.insertar(espacio);
            System.out.println("Espacio "+ espacio.getNombre() +" insertado."); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
        //System.out.println("Insertado con ID: " + espacio.getIdEspacio());
        
        //----------------------------------------------------------------------
        //UPDATE
        //esp.actualizar funciona
        Espacio espacioModificar =  espacio;
        
        espacioModificar.setIdEspacio(4);
//        espacioModificar.setNombre("ELIMINAR");
        espacioModificar.setPrecioReserva(8);
        espacioModificar.setSuperficie(4100);
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
            System.out.println(e.getNombre() + " - " + e.getUbicacion());
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        //----------------------------------------------------------------------
        //obtener por id
        /*
        try {
            Espacio espacioId1 = esp.obtenerPorId(2);
            System.out.println("Se ha obtenido : " + espacioId1.getNombre() +
                    " Mediante el ID: " + espacioId1.getIdEspacio()); 
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        
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
        
        AdministradorMySQL adminSql = new AdministradorMySQL();
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
        
        /*
        try {
            adminSql.actualizar(admin);
            System.out.println("Administrador "+ admin.getNombres()+" actualizado correctamente.");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        //CONSULTAR POR ID
        /*
        try {
            Administrador adminConsulta = adminSql.obtenerPorId(admin.getIdPersona());
            System.out.println("Persona "+ adminConsulta.getNombres()+ 
                    " obtenida mediante su ID: " + adminConsulta.getIdPersona());
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        //CONSULTAR TODOS
        /*
        try {
            ArrayList<Administrador> admins = adminSql.obtenerTodos();
            System.out.println("Lista de Todos los adminsitradores independiente al estado:");
            for (Administrador a : admins) {
            System.out.println(a.getNombres() + " - " + a.getTipoAdministrador());
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        */
        try {
            adminSql.eliminar(admin.getIdPersona());
            System.out.println("Estado de actividad de Adminsitrador " + admin.getNombres() + 
                    " marcado como 'E' (eliminado)");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        
    }

}
