package pe.edu.pucp.sirgep.business.ventas.impl;

import java.util.List;
import pe.edu.pucp.sirgep.business.ventas.service.IEntradaService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EventoDAO;
import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EventoImpl;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.FuncionImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.EntradaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.EntradaImpl;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

public class EntradaServiceImpl implements IEntradaService{

    private final EntradaDAO entradaDAO;
    public EntradaServiceImpl(){
        entradaDAO = new EntradaImpl();
    }
    
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
        if (entradas.size()>0){
            for(int i = 0; i < entradas.size(); i++) {
                Entrada eAux = entradas.get(i);
                if (eAux.getFuncion().getIdFuncion() == id)
                    cant++;
            }
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
}