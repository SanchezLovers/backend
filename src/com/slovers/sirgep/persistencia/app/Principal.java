package com.slovers.sirgep.persistencia.app;

import com.slovers.sirgep.dominio.enums.EMetodoPago;
import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.dominio.models.gestion.Administrador;
import com.slovers.sirgep.dominio.models.gestion.Espacio;
import com.slovers.sirgep.dominio.models.gestion.Departamento;
import com.slovers.sirgep.dominio.models.gestion.Provincia;
import com.slovers.sirgep.dominio.models.gestion.Distrito;

import com.slovers.sirgep.dominio.models.ventas.Constancia;
import com.slovers.sirgep.dominio.models.ventas.Comprador;

import com.slovers.sirgep.dominio.enums.ETipoEspacio;
import com.slovers.sirgep.dominio.enums.ETipoAdministrador;
import com.slovers.sirgep.dominio.enums.ETipoDocumento;

import com.slovers.sirgep.persistencia.mysql.EspacioMySQL;
import com.slovers.sirgep.persistencia.mysql.AdministradorMySQL;
import com.slovers.sirgep.persistencia.mysql.ConstanciaMySQL;

import com.slovers.sirgep.persistencia.config.DBManager;

import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Principal{
    public static void main(String[] args) throws Exception,SQLException, IOException{
        /*
//        

        Connection con = DBManager.getInstance().getConnection();
        
        
        //Implementación de pruebas DAO y MySQL
        
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
        espacio.setIdEspacio(1);
        espacio.setNombre("Cancha de Futbol Universitaria");
        espacio.setPrecioReserva(6);
        espacio.setSuperficie(400);
        espacio.setTipoEspacio(ETipoEspacio.CANCHA);
        espacio.setUbicacion("Avenida Unviersitaria 1023");
        //Insertar el espacio
        System.out.println(espacio.getHorarioFinAtencion());
        //esp.insertar(espacio);
        //System.out.println("Insertado con ID: " + espacio.getIdEspacio());

        // Obtener todos
        //ArrayList<Espacio> espacios = esp.obtenerTodos();
        //for (Espacio e : espacios) {
        //    System.out.println(e.getNombre() + " - " + e.getUbicacion());
        //}
        
        
        //PERSONA 
        //ADMINISTRADOR
        Administrador admin = new Administrador();
//        
        admin.setNombres("Benny");
        admin.setContrasenia("benz123");
        admin.setPrimerApellido("Blanco");
        admin.setSegundoApellido("Baca");
        admin.setCorreo("Beny@admin.com");
        admin.setNumDocumento("123456");
        admin.setTipoDocumento(ETipoDocumento.DNI);
        admin.setTipoAdministrador(ETipoAdministrador.REGIONAL);
        
        AdministradorMySQL aSql = new AdministradorMySQL();
//        aSql.insertar(admin);
        
*/
        //Prueba de Constancia
        Constancia constancia= new Constancia();
        SimpleDateFormat fechaConstancia = new SimpleDateFormat("yyyy-MM-dd");//import java.text.SimpleDateFormat;
        //constancia.setIdConstancia(7);//Comentar, si se va a usar el insert
        constancia.setFecha(fechaConstancia.parse("2022-12-13"));//Necesita throws Exception
        constancia.setMetodoPago(EMetodoPago.TARJETA);
        constancia.setTotal(90);
        constancia.setDetallePago("Pago via TARJETA – codigo de operacion 83638793402.");
        ConstanciaMySQL constanciaMySQL = new ConstanciaMySQL();
       //constanciaMySQL.insertar(constancia);
        //constanciaMySQL.actualizar(constancia);
        //constanciaMySQL.eliminar(7);
        //constancia=constanciaMySQL.obtenerPorId(6);
        //System.out.println(constancia);
        ArrayList<Constancia> constancias=constanciaMySQL.obtenerTodosActivos();
        for(Constancia c : constancias){
            System.out.println(c);
        }
    }
}