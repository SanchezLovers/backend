package pe.edu.pucp.sirgep.business.infraestructura.service;

import java.util.List;
import pe.edu.pucp.sirgep.business.infraestructura.dtos.EspacioDTO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;

public interface IEspacioService {
    //CRUD
    int insertar(Espacio espacio);
    Espacio buscar(int id);
    List<Espacio> listar();
    boolean actualizar(Espacio espacio);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    List<Espacio> buscarPorTexto(String texto);
    List<Espacio> buscarPorCategoria(String texto);
    List<Espacio> buscarPorDistrito(int id);
    List<Espacio> buscarPorDistritoCate(int id, String cat);
    public EspacioDTO llenarEspacioDTOEdicion(int idEspacio);
    //Adicionales
    boolean enviarCorreosCompradoresPorDistritoDeEspacio(String asunto, String contenido,int idDistrito);
}