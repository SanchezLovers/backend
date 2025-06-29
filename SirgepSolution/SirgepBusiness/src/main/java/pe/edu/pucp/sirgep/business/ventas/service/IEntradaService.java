package pe.edu.pucp.sirgep.business.ventas.service;

import java.time.LocalDate;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaEntradaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleEntradaDTO;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;

public interface IEntradaService {
    //Metodos CRUD
    int insertar(Entrada reserva);
    Entrada buscar(int id);
    List<Entrada> listar();
    boolean actualizar(Entrada entrada);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    
    boolean inactivar();
    //Metodos adicionales
    public Comprador buscarCompradorDeEntrada(int idComprador);
    public Funcion buscarFuncionDeEntrada(int idFuncion);
    public Evento buscarEventoDeEntrada(int idEntrada);
    public Distrito buscarDistritoDeEntrada(int idEntrada);
    
    boolean crearLibroExcelEntradas(int idComprador, String fechaInicio, String fechaFin, String estado);
    
    ConstanciaEntradaDTO buscarConstanciaEntrada(int idConstancia);

    List<DetalleEntradaDTO> listarDetalleEntradas();
    List<DetalleEntradaDTO> buscarPorTexto(String texto);
    
    List<DetalleEntradaDTO> listarPorComprador(int idComprador,String fechaInicio, String fechaFin, String estado);
}