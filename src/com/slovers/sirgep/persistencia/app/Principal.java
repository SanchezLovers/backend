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

import java.util.ArrayList;
import java.time.LocalTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Principal{
    public static void main(String[] args) throws Exception,SQLException, IOException{
        
//        

        Connection con = DBManager.getInstance().getConnection();
        Espacio e = new Espacio();
        Distrito d = new Distrito();
        Provincia p = new Provincia();
        Departamento departamento = new Departamento();
        ArrayList<EDiaSemana> listaDiasAtencion = new ArrayList<>();
        
        listaDiasAtencion.add(EDiaSemana.LUNES);
        listaDiasAtencion.add(EDiaSemana.MARTES);
        listaDiasAtencion.add(EDiaSemana.MIERCOLES);
        listaDiasAtencion.add(EDiaSemana.JUEVES);
        listaDiasAtencion.add(EDiaSemana.VIERNES);
        listaDiasAtencion.add(EDiaSemana.SABADO);
        
        departamento.setNombre("Lima");
        departamento.setIdDepartamento(1);
        
        p.setDepartamento(departamento);
        p.setIdProvincia(1);
        p.setNombre("Lima");
        
        d.setNombre("Miraflores");
        d.setProvincia(p);
        d.setIdDistrito(1);
        


    }
}