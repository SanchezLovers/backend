package pe.edu.pucp.sirgep.domain.infraestructura.models;

public class Calificacion {
    private int id;
    private int puntaje;
    private char servicio;
    private String texto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public char getServicio() {
        return servicio;
    }

    public void setServicio(char servicio) {
        this.servicio = servicio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }   
}