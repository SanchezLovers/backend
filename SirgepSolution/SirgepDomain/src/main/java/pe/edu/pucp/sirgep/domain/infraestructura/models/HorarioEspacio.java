/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.domain.infraestructura.models;

import java.util.Date;

/**
 *
 * @author Ana Gabriela
 */
public class HorarioEspacio {
    private boolean disponible;
    private Date horaIni;
    private Espacio espacio;

    public HorarioEspacio(boolean disponible, Date horaIni) {
        this.disponible = disponible;
        this.horaIni = horaIni;
    }

    public HorarioEspacio() {
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Date horaIni) {
        this.horaIni = horaIni;
    }

    
}
