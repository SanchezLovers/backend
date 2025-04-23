package com.slovers.sirgep.persistencia.app;

import com.slovers.sirgep.dominio.enums.EDiaSemana;
import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.dominio.models.gestion.Administrador;
import com.slovers.sirgep.dominio.models.gestion.Espacio;
import com.slovers.sirgep.dominio.models.gestion.Departamento;
import com.slovers.sirgep.dominio.models.gestion.Provincia;
import com.slovers.sirgep.dominio.models.gestion.Distrito;

import com.slovers.sirgep.dominio.models.ventas.Constancia;
import com.slovers.sirgep.dominio.models.ventas.Comprador;
import com.slovers.sirgep.dominio.enums.ETipoAdministrador;
import com.slovers.sirgep.dominio.enums.ETipoDocumento;
import com.slovers.sirgep.dominio.enums.ETipoEspacio;

import com.slovers.sirgep.persistencia.mysql.EspacioMySQL;
import com.slovers.sirgep.persistencia.mysql.AdministradorMySQL;
import com.slovers.sirgep.persistencia.mysql.ConstanciaMySQL;

import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.mysql.DepartamentoMySQL;
import com.slovers.sirgep.persistencia.mysql.DistritoMySQL;

import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Principal{
    public static void main(String[] args) throws Exception, SQLException, IOException{

        //Probando Distrito
        
        Distrito distrito = new Distrito();
        Provincia provincia = new Provincia();
        
        provincia.setIdProvincia(1);
        
        distrito.setNombre("DistritoTest");
        distrito.setIdDistrito(101);
        distrito.setProvincia(provincia);
        DistritoMySQL dmysql = new DistritoMySQL();
        //insert
//        distrito.setNombre("Distrito2Test");
//        distrito.setIdDistrito(102);
//        dmysql.insertar(distrito);
        //(1,Lima, provincia)
        
        
        //update
//        distrito.setNombre("TestDistrito");
//        dmysql.actualizar(distrito);
        
//        //delete
//        dmysql.eliminar(101);
//        
//        //select
//        Distrito distSelect = dmysql.obtenerPorId(101);
//        System.out.println("Select por ID: ");
//        System.out.println(distSelect);
//        
        ArrayList<Distrito> distritos = dmysql.obtenerTodos();
//        for(Distrito d : distritos){
//            System.out.println(d);
//        }
    }
}