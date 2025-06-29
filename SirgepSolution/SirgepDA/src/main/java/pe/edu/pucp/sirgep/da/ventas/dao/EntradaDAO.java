package pe.edu.pucp.sirgep.da.ventas.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;

public interface EntradaDAO extends BaseDAO<Entrada>{
    
    void llenarMapaDetalleEntrada(Map<String, Object>detalleEntrada,ResultSet rs);
    Map<String, Object> buscarConstanciaEntrada(int numEntrada);

    List<Map<String, Object>> listarDetalleEntradas();
    List<Map<String, Object>> buscarPorTexto(String texto);
    
    List<Map<String, Object>> listarPorComprador(int idComprador,String fechaInicio, String fechaFin, String estado);
    boolean inactivar();
}