package pe.edu.pucp.sirgep.domain.infraestructura.models;

import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;

public class EspacioDiaSem {

    private int idEspacio;
    private EDiaSemana dia;
    private char activo;
    
    public EspacioDiaSem() {
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public EDiaSemana getDia() {
        return dia;
    }

    public void setDia(EDiaSemana dia) {
        this.dia = dia;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

}
