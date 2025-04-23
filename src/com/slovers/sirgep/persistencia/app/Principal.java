package com.slovers.sirgep.persistencia.app;

import com.slovers.sirgep.dominio.enums.EEstadoReserva;
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
import com.slovers.sirgep.dominio.models.ventas.Reserva;

import com.slovers.sirgep.persistencia.mysql.EspacioMySQL;
import com.slovers.sirgep.persistencia.mysql.AdministradorMySQL;
import com.slovers.sirgep.persistencia.mysql.ConstanciaMySQL;

import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.mysql.ReservaMySQL;

import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Principal{
    public static void main(String[] args) throws Exception,SQLException, IOException{
        
        /*
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
*/
        //Prueba de Reserva
        
        Constancia constancia=new Reserva();
        SimpleDateFormat fechaConstancia = new SimpleDateFormat("yyyy-MM-dd");//import java.text.SimpleDateFormat;
        constancia.setFecha(fechaConstancia.parse("2022-12-13"));//Necesita throws Exception
        constancia.setMetodoPago(EMetodoPago.TARJETA);
        constancia.setTotal(90);
        constancia.setDetallePago("Pago via TARJETA – codigo de operacion 83638793402.");
        ConstanciaMySQL constanciaMySQL = new ConstanciaMySQL();
        constanciaMySQL.insertar(constancia);
        ReservaMySQL reservaMySQL = new ReservaMySQL();
        Reserva reserva=(Reserva)constancia;
        reserva.setHorarioIni(LocalTime.of(14, 0));
        reserva.setHorarioFin(LocalTime.of(15, 0));
        reserva.setEstado(EEstadoReserva.ACTIVO);
        SimpleDateFormat fechaReserva = new SimpleDateFormat("yyyy-MM-dd");//import java.text.SimpleDateFormat;
        reserva.setFechaReserva(fechaReserva.parse("2024-11-23"));
        Espacio espacio=new Espacio();
        espacio.setIdEspacio(1);
        reserva.setEspacio(espacio);
        Persona persona=new Persona();
        persona.setIdPersona(1);
        reserva.setPersona(persona);
        reservaMySQL.insertar(reserva);
    }
}