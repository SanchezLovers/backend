/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.dao.HorarioEspacioDAO;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.HorarioEspacio;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

/**
 *
 * @author Ana Gabriela
 */
public class HorarioEspacioImpl implements HorarioEspacioDAO{

    private EspacioDAO eDao;
    private ReservaDAO rDao;
    
    public HorarioEspacioImpl(){
        eDao =  new EspacioImpl();
        rDao =  new ReservaImpl();
    }
    
    @Override
    public boolean validarDia(Date dia, int idEspacio){
        String[] dias = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        String diaSemana = dias[cal.get(Calendar.DAY_OF_WEEK) - 1];
        
        boolean esValido = false;
        String sql = "SELECT COUNT(*) FROM Espacio_has_eDiaSemana WHERE Espacio_id_espacio = ? AND eDiaSemana_dia_semana = ?";

        try (Connection con = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);
            ps.setString(2, diaSemana);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    esValido = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            return false;
        }

        return esValido;
}
    
    @Override
    public List<HorarioEspacio> listarHorasDisponibles(int idEspacio, Date dia) {
        Espacio espacio = eDao.buscar(idEspacio);
        
        if (!validarDia(dia, idEspacio)) return null;
        //con eso ya tenemos los datos que necesito del espacio
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // HH: formato 24 h
        List<HorarioEspacio> listaHorarios = new ArrayList<>();
        try {
            Date horaIni  = formatter.parse(espacio.getHorarioInicioAtencion());
            Date horaF  = formatter.parse(espacio.getHorarioFinAtencion());
            
            //variable auxiliar para la suma de horas
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(horaIni);
            HorarioEspacio he;
            Date inicio = horaIni;
            while (inicio.before(horaF) || inicio.equals(horaF)){
                //guardo el horario en un objeto
                he =  new HorarioEspacio(true, inicio);
                listaHorarios.add(he);
                
                //añadir una hora
                calendar.add(Calendar.HOUR_OF_DAY, 1);
                inicio = calendar.getTime();
            }
            //hasta aquí tengo las horas en un arreglo
            //ahora la disponibilidad
            //listo las reservas de ese espacio para el día seleccionado
            List<Reserva> reservasDelDia = rDao.listarPorDiaYEspacio(idEspacio, dia);
            Reserva raux;
            HorarioEspacio haux;
            for (int k = 0; k < reservasDelDia.size(); k++) {
                raux = reservasDelDia.get(k);
                for (int j = 0; j < listaHorarios.size(); j++) {
                    haux = listaHorarios.get(j);
                    // aquí tengo que pasar el date a localtime
                    Instant instant = haux.getHoraIni().toInstant();
                    LocalDateTime dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalTime horaDelDate = dateTime.toLocalTime();
                    if (raux.getHorarioIni().equals(horaDelDate)){
                        int c=0;
                        while(raux.getHorarioFin().isAfter(horaDelDate)){
                            listaHorarios.get(j+c).setDisponible(false);
                            dateTime= dateTime.plus(1, ChronoUnit.HOURS);
                            horaDelDate = dateTime.toLocalTime();
                            c++;
                        }
                        break;
                    }
                }
            }
            return listaHorarios;
        } catch (ParseException ex) {
            Logger.getLogger(HorarioEspacioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
