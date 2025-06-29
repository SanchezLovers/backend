/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.sirgep.business.ventas.dtos;

/**
 *
 * @author Ana Gabriela
 */
public class EspacioRepDTO {

    String nombre;
    int cantReservas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantReservas() {
        return cantReservas;
    }

    public void setCantReservas(int cantReservas) {
        this.cantReservas = cantReservas;
    }

    public EspacioRepDTO(String nombre, int cantReservas) {
        this.nombre = nombre;
        this.cantReservas = cantReservas;
    }
}
