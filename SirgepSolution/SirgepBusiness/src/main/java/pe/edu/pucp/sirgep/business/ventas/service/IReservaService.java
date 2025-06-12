package pe.edu.pucp.sirgep.business.ventas.service;

import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public interface IReservaService {
    //Metodos CRUD
    int insertar(Reserva reserva);
    Reserva buscar(int id);
    List<Reserva> listar();
    boolean actualizar(Reserva reserva);
    boolean eliminarLogico(int id);
    boolean eliminarFisico(int id);
    
    //Metodos adicionales
    public Comprador buscarCompradorDeReserva(int idComprador);
    public Espacio buscarEspacioDeReserva(int idEspacio);
    public Distrito buscarDistritoDeReserva(int idEntrada);
    
    //Metodos para crear libro de Excel para las entradas
    public void crearLibroExcelReservas(int idComprador);
    public String crearHojalReservas(XSSFWorkbook libro,int idComprador);
    public String crearEncabezadoHojaReservas(XSSFSheet hoja,int idComprador);
    public void llenarTablaReservas(XSSFSheet hoja,int idComprador);
    public void llenarFilaReserva(XSSFRow registro,Reserva entrada);
    public void exportarLibroReservas(XSSFWorkbook libro,String nombreArchivo);
}