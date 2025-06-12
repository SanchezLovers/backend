package pe.edu.pucp.sirgep.business.ventas.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.edu.pucp.sirgep.business.ventas.service.IReservaService;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDAO;
import pe.edu.pucp.sirgep.da.infraestructura.implementacion.EspacioImpl;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.da.ubicacion.implementacion.DistritoImpl;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.da.usuarios.implementacion.CompradorImpl;
import pe.edu.pucp.sirgep.da.ventas.dao.ReservaDAO;
import pe.edu.pucp.sirgep.da.ventas.implementacion.ReservaImpl;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Espacio;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Evento;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Entrada;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class ReservaServiceImpl implements IReservaService {
    private final ReservaDAO reservaDAO;
    private final CompradorDAO compradorDAO;
    private final EspacioDAO espacioDAO;
    private final DistritoDAO distritoDAO;
    
    public ReservaServiceImpl(){
        reservaDAO = new ReservaImpl();
        compradorDAO = new CompradorImpl();
        espacioDAO = new EspacioImpl() ;
        distritoDAO = new DistritoImpl();
    }
    
    //Metodos del CRUD
    @Override
    public int insertar(Reserva reserva) {
        return reservaDAO.insertar(reserva);
    }

    @Override
    public Reserva buscar(int id) {
        return reservaDAO.buscar(id);
    }

    @Override
    public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    @Override
    public boolean actualizar(Reserva reserva) {
        return reservaDAO.actualizar(reserva);
    }

    @Override
    public boolean eliminarLogico(int id) {
        return reservaDAO.eliminarLogico(id);
    }

    @Override
    public boolean eliminarFisico(int id) {
        return reservaDAO.eliminarFisico(id);
    }
    
    //Metodos adicionales
    @Override
    public Comprador buscarCompradorDeReserva(int idComprador){
        try {
            return compradorDAO.buscar(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la reserva: " + ex.getMessage());
        }
    }
    
    @Override
    public Espacio buscarEspacioDeReserva(int idEspacio){
        boolean resultado=false;
        try {
            return espacioDAO.buscar(idEspacio);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la espacio de la reserva: " + ex.getMessage());
        }
    }
    
    @Override
    public Distrito buscarDistritoDeReserva(int idEntrada){
        Distrito resultado=null;
        try {
            resultado=distritoDAO.buscar(idEntrada);
            if(resultado==null){
                throw new RuntimeException("Error al buscar el distrito de la reserva: ");
            }
            return resultado;
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la reserva: " + ex.getMessage());
        }
    }
    

    //Metodos para crear libro de Excel para las reservas
    @Override
    public void crearLibroExcelReservas(int idComprador) {
        XSSFWorkbook libro=new XSSFWorkbook();//Archivo.xlsx
        String nombreArchivo=crearHojalReservas(libro,idComprador);
        exportarLibroReservas(libro,nombreArchivo);
    }
    
    @Override
    public String crearHojalReservas(XSSFWorkbook libro,int idComprador) {
        XSSFSheet hoja=libro.createSheet("Reservas");//Nombre
        try{
            String nombreArchivo=crearEncabezadoHojaReservas(hoja,idComprador);
            llenarTablaReservas(hoja,idComprador);
            return nombreArchivo;
        }catch(Exception ex){
            throw new RuntimeException("Error al llenar la hoja excel de las reservas: " + ex.getMessage());
        }
    }

    @Override
    public String crearEncabezadoHojaReservas(XSSFSheet hoja,int idComprador) {
        Comprador comprador=compradorDAO.buscar(idComprador);
        String nombreArchivo="Lista_Reservas_"+comprador.getNombres()+".xlsx";
        //Configuracion de estilo
        XSSFCellStyle estiloColorFondo = hoja.getWorkbook().createCellStyle();
        XSSFFont fontBlanca = hoja.getWorkbook().createFont();
        fontBlanca.setColor(IndexedColors.WHITE.getIndex());
        fontBlanca.setBold(true);
        estiloColorFondo.setFont(fontBlanca);
        estiloColorFondo.setFillForegroundColor(IndexedColors.RED.getIndex());
        estiloColorFondo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estiloColorFondo.setAlignment(HorizontalAlignment.CENTER); //Centrado
        //Configuracion de celda
        XSSFRow fila=hoja.createRow(0);
        XSSFCell celda=fila.createCell(0);
        celda.setCellValue("Lista de Reservas del Comprador "+comprador.getNombres()+" "+comprador.getSegundoApellido());
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//Combinar celdas
        // Estilo centrado + negrita
        XSSFCellStyle estilo = hoja.getWorkbook().createCellStyle();
        XSSFFont fuente = hoja.getWorkbook().createFont();
        fuente.setBold(true);
        estilo.setFont(fuente);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        celda.setCellStyle(estilo);
        //Tabla
        fila=hoja.createRow(2);
        celda=fila.createCell(0);
        celda.setCellValue("Nro Reserva");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(1);
        celda.setCellValue("Espacio");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(2);
        celda.setCellValue("Categoria");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(3);
        celda.setCellValue("Ubicacion");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(4);
        celda.setCellValue("Distrito");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(5);
        celda.setCellValue("Fecha");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(6);
        celda.setCellValue("Hora Inicio");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(7);
        celda.setCellValue("Hora Fin");
        celda.setCellStyle(estiloColorFondo);
        return nombreArchivo;
    }
    
    @Override
    public void llenarTablaReservas(XSSFSheet hoja,int idComprador) {
        List<Reserva> listaReservas=reservaDAO.listarReservasPorComprador(idComprador);//Incompleto
        int posicion=3;
        for (Reserva reserva: listaReservas) {
            XSSFRow registro=hoja.createRow(posicion++);
            llenarFilaReserva(registro,reserva);
        }
        for (int i = 0; i < 8; i++) {
            hoja.autoSizeColumn(i);
        }
    }

    @Override
    public void llenarFilaReserva(XSSFRow registro, Reserva reserva) {
        Espacio espacio=espacioDAO.buscar(reserva.getEspacio().getIdEspacio());
        if(espacio!=null){
            Distrito distrito=distritoDAO.buscar(espacio.getDistrito().getIdDistrito());
            if(distrito!=null){
                XSSFCell celda=registro.createCell(0);
                celda.setCellValue(reserva.getNumReserva());
                celda=registro.createCell(1);
                celda.setCellValue(espacio.getNombre());
                celda=registro.createCell(2);
                celda.setCellValue(espacio.getTipoEspacio().toString());
                celda=registro.createCell(3);
                celda.setCellValue(espacio.getUbicacion());
                celda=registro.createCell(4);
                celda.setCellValue(distrito.getNombre());
                celda=registro.createCell(5);
                celda.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha()));
//                celda=registro.createCell(6);
//                celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(reserva.getHorarioIni()));
//                celda=registro.createCell(7);
//                celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(reserva.getHorarioFin()));
            }
        }
    }

    @Override
    public void exportarLibroReservas(XSSFWorkbook libro,String nombreArchivo) {
        try{
            String userHome = System.getProperty("user.home");
            File downloads = new File(userHome, "Downloads");
            File descargas = new File(userHome, "Descargas");
            File carpetaDestino;
            if (downloads.exists()) {
                carpetaDestino = downloads;
            } else if (descargas.exists()) {
                carpetaDestino = descargas;
            } else {
                carpetaDestino = new File(userHome); // fallback: usa la carpeta del usuario
            }
            nombreArchivo = new File(carpetaDestino, nombreArchivo).getAbsolutePath();
            OutputStream output=new FileOutputStream(nombreArchivo);//Ruta y nombre
            libro.write(output);//Exporto los bytes
            libro.close();//Libero bytes
            output.close();//Libero bytes
        }catch(Exception ex){
            throw new RuntimeException("Error al exportar el libro excel de las reservas: " + ex.getMessage());
        }
    }
}