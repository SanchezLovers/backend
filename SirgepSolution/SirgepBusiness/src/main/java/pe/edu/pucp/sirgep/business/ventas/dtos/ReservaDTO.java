/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.business.ventas.dtos;

import java.util.Date;

/**
 *
 * @author willi
 */
public class ReservaDTO {

    //Atributos
    private int codigo;
    private Date fecha;
    private String distrito;
    private String espacio;
    private String correo;
    private char activo;

    //Constructor
    public ReservaDTO() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }
}
