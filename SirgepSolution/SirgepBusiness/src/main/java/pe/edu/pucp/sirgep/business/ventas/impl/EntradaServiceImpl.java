package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;

import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DistritoImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.EntradaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.EntradaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

public class EntradaServiceImpl implements IEntradaService{
    //Atributos
    private final EntradaDAO entradaDAO;
    private final CompradorDAO compradorDAO;
    private final FuncionDAO funcionDAO;
    private final EventoDAO eventoDAO;
    private final DistritoDAO distritoDAO;
    
    //Constructor
    public EntradaServiceImpl(){
        entradaDAO = new EntradaImpl();
        compradorDAO = new CompradorImpl();
        funcionDAO = new FuncionImpl() ;
        eventoDAO = new EventoImpl();
        distritoDAO = new DistritoImpl();
    }
    
    //Metodos del CRUD
    @Override
    public int insertar(Entrada entrada) {
        return entradaDAO.insertar(entrada);
    }

    @Override
    public Entrada buscar(int id) {
        return entradaDAO.buscar(id);
    }

    @Override
    public List<Entrada> listar() {
        return entradaDAO.listar();
    }

    public int cantidadDispo(int id, int cantEntradas) {
        int cant=0;
        List<Entrada> entradas = entradaDAO.listar();
        for(int i = 0; i < entradas.size(); i++) {
            Entrada eAux = entradas.get(id);
            if (eAux.getFuncion().getIdFuncion() == id)
                cant++;
        }
//        List<>
        return cantEntradas-cant;
    }

    @Override
    public boolean actualizar(Entrada entrada) {
        return entradaDAO.actualizar(entrada);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return entradaDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return entradaDAO.eliminarFisico(id);
    }
    
    //Metodos adicionales
    public Comprador buscarCompradorDeEntrada(int idComprador){
        try {
            return compradorDAO.buscar(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la entrada: " + ex.getMessage());
        }
    }
    
    public Funcion buscarFuncionDeEntrada(int idFuncion){
        boolean resultado=false;
        try {
            return funcionDAO.buscar(idFuncion);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    public Evento buscarEventoDeEntrada(int idEntrada){
        boolean resultado=false;
        try {
            return eventoDAO.buscar(idEntrada);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    public Distrito buscarDistritoDeEntrada(int idEntrada){
        Distrito resultado=null;
        try {
            resultado=distritoDAO.buscar(idEntrada);
            if(resultado==null){
                throw new RuntimeException("Error al buscar el distrito de la entrada: ");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la entrada: " + ex.getMessage());
        }
    }
}