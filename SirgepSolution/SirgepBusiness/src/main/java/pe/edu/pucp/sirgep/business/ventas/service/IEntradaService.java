package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
    public String crearHojalEntradas(Workbook libro,int idComprador);
    public String crearEncabezadoHojaEntradas(Sheet hoja,int idComprador);
    public void llenarTablaEntradas(Sheet hoja,int idComprador);
    public void llenarFilaEntrada(Row registro,Entrada entrada);
    public void exportarLibroEntradas(Workbook libro,String nombreArchivo);
}