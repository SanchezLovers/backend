package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
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
    
    //Metodos adicionales
    public Comprador buscarCompradorDeEntrada(int idComprador);
    public Funcion buscarFuncionDeEntrada(int idFuncion);
    public Evento buscarEventoDeEntrada(int idEntrada);
    public Distrito buscarDistritoDeEntrada(int idEntrada);
    
    //Metodos para crear libro de Excel para las entradas
    public void crearLibroExcelEntradas(int idComprador);
    public String crearHojalEntradas(XSSFWorkbook libro,int idComprador);
    public String crearEncabezadoHojaEntradas(XSSFSheet hoja,int idComprador);
    public void llenarTablaEntradas(XSSFSheet hoja,int idComprador);
    public void llenarFilaDetalleEntrada(XSSFRow registro,DetalleEntradaDTO detalleEntrada);
    public List<DetalleEntradaDTO> listarDetalleEntradasPorComprador(int idComprador);
    public void exportarLibroEntradas(XSSFWorkbook libro,String nombreArchivo);
    
    public ConstanciaEntradaDTO buscarConstanciaEntrada(int idConstancia);

    public List<DetalleEntradaDTO> listarDetalleEntradas();
    public List<DetalleEntradaDTO> buscarPorTexto(String texto);
}