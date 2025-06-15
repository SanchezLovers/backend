package pe.edu.pucp.sirgep.business.ventas.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;//Para crear libro de Excel
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;//Para crear libro de Excel
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleEntrada;

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
    
    //Metodos adicionales
    @Override
    public Comprador buscarCompradorDeEntrada(int idComprador){
        try {
            return compradorDAO.buscar(idComprador);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la entrada: " + ex.getMessage());
        }
    }
    
    @Override
    public Funcion buscarFuncionDeEntrada(int idFuncion){
        boolean resultado=false;
        try {
            return funcionDAO.buscar(idFuncion);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    @Override
    public Evento buscarEventoDeEntrada(int idEntrada){
        boolean resultado=false;
        try {
            return eventoDAO.buscar(idEntrada);
        }  catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }
    
    @Override
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

    //Metodos para crear libro de Excel para las entradas
    @Override
    public void crearLibroExcelEntradas(int idComprador) {
        XSSFWorkbook libro=new XSSFWorkbook();//Archivo.xlsx
        String nombreArchivo=crearHojalEntradas(libro,idComprador);
        exportarLibroEntradas(libro,nombreArchivo);
    }
    
    @Override
    public String crearHojalEntradas(XSSFWorkbook libro,int idComprador) {
        XSSFSheet hoja=libro.createSheet("Entradas");//Nombre
        try{
            String nombreArchivo=crearEncabezadoHojaEntradas(hoja,idComprador);
            llenarTablaEntradas(hoja,idComprador);
            return nombreArchivo;
        }catch(Exception ex){
            throw new RuntimeException("Error al llenar la hoja excel de las entradas: " + ex.getMessage());
        }
    }

    @Override
    public String crearEncabezadoHojaEntradas(XSSFSheet hoja,int idComprador) {
        Comprador comprador=compradorDAO.buscar(idComprador);
        String nombreArchivo="Lista_Entradas_"+comprador.getNombres()+".xlsx";
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
        celda.setCellValue("Lista de Entradas del Comprador "+comprador.getNombres()+" "+comprador.getSegundoApellido());
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//Combinar celdas
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
        celda.setCellValue("Nro Entrada");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(1);
        celda.setCellValue("Evento");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(2);
        celda.setCellValue("Ubicacion");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(3);
        celda.setCellValue("Distrito");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(4);
        celda.setCellValue("Fecha");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(5);
        celda.setCellValue("Hora Inicio");
        celda.setCellStyle(estiloColorFondo);
        celda=fila.createCell(6);
        celda.setCellValue("Hora Fin");
        celda.setCellStyle(estiloColorFondo);
        return nombreArchivo;
    }
    
    @Override
    public List<DetalleEntrada> listarDetalleEntradasPorComprador(int idComprador){
        List<Map<String, Object>> listaRaw = entradaDAO.listarDetalleEntradasPorComprador(idComprador);
        List<DetalleEntrada> listaFinal = new ArrayList<>();
        for (Map<String, Object> fila : listaRaw) {
            DetalleEntrada detalle = new DetalleEntrada();
            detalle.setNumEntrada((int) fila.get("numEntrada"));
            detalle.setNombreEvento((String) fila.get("nombreEvento"));
            detalle.setUbicacion((String) fila.get("ubicacion"));
            detalle.setNombreDistrito((String) fila.get("nombreDistrito"));
            detalle.setFecha((Date) fila.get("fecha"));
            detalle.setHoraInicio((Time) fila.get("horaInicio"));
            detalle.setHoraFin((Time) fila.get("horaFin"));
            listaFinal.add(detalle);
        }
        return listaFinal;
    }
    
    @Override
    public void llenarTablaEntradas(XSSFSheet hoja,int idComprador) {
//        List<Entrada> listaEntradas=entradaDAO.listarEntradasPorComprador(idComprador);
        List<DetalleEntrada> listaDetalleEntradas=listarDetalleEntradasPorComprador(idComprador);
        int posicion=3;
        for (DetalleEntrada detalleEntrada: listaDetalleEntradas) {
            XSSFRow registro=hoja.createRow(posicion++);
            llenarFilaDetalleEntrada(registro,detalleEntrada);
        }
        for (int i = 0; i < 7; i++) {
            hoja.autoSizeColumn(i);
        }
    }

    @Override
    public void llenarFilaDetalleEntrada(XSSFRow registro, DetalleEntrada detalleEntrada) {
        XSSFCell celda=registro.createCell(0);
        celda.setCellValue(detalleEntrada.getNumEntrada());
        celda=registro.createCell(1);
        celda.setCellValue(detalleEntrada.getNombreEvento());
        celda=registro.createCell(2);
        celda.setCellValue(detalleEntrada.getUbicacion());
        celda=registro.createCell(3);
        celda.setCellValue(detalleEntrada.getNombreDistrito());
        celda=registro.createCell(4);
        celda.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(detalleEntrada.getFecha()));
        celda=registro.createCell(5);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleEntrada.getHoraInicio()));
        celda=registro.createCell(6);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleEntrada.getHoraFin()));
        //celda.setCellValue(funcion.getHoraFin().toString());
    }

    @Override
    public void exportarLibroEntradas(XSSFWorkbook libro,String nombreArchivo) {
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
            throw new RuntimeException("Error al exportar el libro excel de las entradas: " + ex.getMessage());
        }
    }
}