package pe.edu.pucp.sirgep.business.ventas.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;//Para crear libro de Excel
import org.apache.poi.xssf.usermodel.XSSFWorkbook;//Para crear libro de Excel

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
        Workbook libro=new XSSFWorkbook();//Archivo.xlsx
        String nombreArchivo=crearHojalEntradas(libro,idComprador);
        exportarLibroEntradas(libro,nombreArchivo);
    }
    
    @Override
    public String crearHojalEntradas(Workbook libro,int idComprador) {
        Sheet hoja=libro.createSheet("Entradas");//Nombre
        try{
            String nombreArchivo=crearEncabezadoHojaEntradas(hoja,idComprador);
            llenarTablaEntradas(hoja,idComprador);
            return nombreArchivo;
        }catch(Exception ex){
            throw new RuntimeException("Error al llenar la hoja excel de las entradas: " + ex.getMessage());
        }
    }

    @Override
    public String crearEncabezadoHojaEntradas(Sheet hoja,int idComprador) {
        Comprador comprador=compradorDAO.buscar(idComprador);
        String nombreArchivo="Lista_Entradas_"+comprador.getNombres()+".xlsx";
        Row fila=hoja.createRow(0);
        Cell celda=fila.createCell(0);
        celda.setCellValue("Lista de Entradas del Comprador "+comprador.getNombres()+" "+comprador.getSegundoApellido());
        fila=hoja.createRow(2);
        celda=fila.createCell(0);
        celda.setCellValue("Nro Entrada");
        celda=fila.createCell(1);
        celda.setCellValue("Evento");
        celda=fila.createCell(2);
        celda.setCellValue("Ubicacion");
        celda=fila.createCell(3);
        celda.setCellValue("Distrito");
        celda=fila.createCell(4);
        celda.setCellValue("Fecha");
        celda=fila.createCell(5);
        celda.setCellValue("Hora Inicio");
        celda=fila.createCell(6);
        celda.setCellValue("Hora Fin");
        return nombreArchivo;
    }
    
    @Override
    public void llenarTablaEntradas(Sheet hoja,int idComprador) {
        List<Entrada> listaEntradas=entradaDAO.listarEntradasPorComprador(idComprador);//Incompleto
        int posicion=4;
        for (Entrada entreda: listaEntradas) {
            Row registro=hoja.createRow(posicion++);
            llenarFilaEntrada(registro,entreda);
        }
    }

    @Override
    public void llenarFilaEntrada(Row registro, Entrada entrada) {
        Funcion funcion=funcionDAO.buscar(entrada.getFuncion().getIdFuncion());
        if(funcion!=null){
            Evento evento=eventoDAO.buscar(funcion.getEvento().getIdEvento());
            if(evento!=null){
                Distrito distrito=distritoDAO.buscar(evento.getDistrito().getIdDistrito());
                if(distrito!=null){
                    Cell celda=registro.createCell(0);
                    celda.setCellValue(entrada.getNumEntrada());
                    celda=registro.createCell(1);
                    celda.setCellValue(evento.getNombre());
                    celda=registro.createCell(2);
                    celda.setCellValue(evento.getUbicacion());
                    celda=registro.createCell(3);
                    celda.setCellValue(distrito.getNombre());
                    celda=registro.createCell(4);
                    celda.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(funcion.getFecha()));
                    celda=registro.createCell(5);
                    celda.setCellValue(funcion.getHoraInicio().toString());
                    celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(funcion.getHoraInicio()));
                    celda=registro.createCell(6);
                    celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(funcion.getHoraFin()));
                    //celda.setCellValue(funcion.getHoraFin().toString());
                }
            }
        }
    }

    @Override
    public void exportarLibroEntradas(Workbook libro,String nombreArchivo) {
        try{
            OutputStream output=new FileOutputStream(nombreArchivo);//Ruta y nombre
            libro.write(output);//Exporto los bytes
            libro.close();//Libero bytes
            output.close();//Libero bytes
        }catch(Exception ex){
            throw new RuntimeException("Error al exportar el libro excel de las entradas: " + ex.getMessage());
        }
    }
}