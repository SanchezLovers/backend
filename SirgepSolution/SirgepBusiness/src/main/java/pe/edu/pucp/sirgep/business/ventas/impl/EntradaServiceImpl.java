package pe.edu.pucp.sirgep.business.ventas.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaEntradaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleEntradaDTO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

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

public class EntradaServiceImpl implements IEntradaService {

    //Atributos
    private final EntradaDAO entradaDAO;
    private final CompradorDAO compradorDAO;
    private final FuncionDAO funcionDAO;
    private final EventoDAO eventoDAO;
    private final DistritoDAO distritoDAO;

    //Constructor
    public EntradaServiceImpl() {
        entradaDAO = new EntradaImpl();
        compradorDAO = new CompradorImpl();
        funcionDAO = new FuncionImpl();
        eventoDAO = new EventoImpl();
        distritoDAO = new DistritoImpl();
    }

    //Metodos del CRUD
    @Override
    public int insertar(Entrada entrada) {
        return entradaDAO.insertar(entrada);
    }
    
    @Override
    public boolean inactivar() {
        return entradaDAO.inactivar();
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
        int cant = 0;
        List<Entrada> entradas = entradaDAO.listar();
        if (entradas.size() > 0) {
            for (int i = 0; i < entradas.size(); i++) {
                Entrada eAux = entradas.get(i);
                if (eAux.getFuncion().getIdFuncion() == id) {
                    cant++;
                }
            }
        }
//        List<>
        return cantEntradas - cant;
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
    public Comprador buscarCompradorDeEntrada(int idComprador) {
        try {
            return compradorDAO.buscar(idComprador);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la entrada: " + ex.getMessage());
        }
    }

    @Override
    public Funcion buscarFuncionDeEntrada(int idFuncion) {
        boolean resultado = false;
        try {
            return funcionDAO.buscar(idFuncion);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }

    @Override
    public Evento buscarEventoDeEntrada(int idEntrada) {
        boolean resultado = false;
        try {
            return eventoDAO.buscar(idEntrada);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la funcion de la entrada: " + ex.getMessage());
        }
    }

    @Override
    public Distrito buscarDistritoDeEntrada(int idEntrada) {
        Distrito resultado = null;
        try {
            resultado = distritoDAO.buscar(idEntrada);
            if (resultado == null) {
                throw new RuntimeException("Error al buscar el distrito de la entrada: ");
            }
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la entrada: " + ex.getMessage());
        }
    }

    //Metodos para crear libro de Excel para las entradas
    @Override
    public boolean crearLibroExcelEntradas(int idComprador, String fechaInicio, String fechaFin, String estado) {
        boolean resultado = false;
        try {
            XSSFWorkbook libro = new XSSFWorkbook();//Archivo.xlsx
            String nombreArchivo = crearHojalEntradas(libro, idComprador, fechaInicio, fechaFin, estado);
            if (nombreArchivo != null) {
                resultado = exportarLibroEntradas(libro, nombreArchivo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el excel de la lista de entradas: " + e.getMessage());
        }
        return resultado;
    }

    private String crearHojalEntradas(XSSFWorkbook libro, int idComprador, String fechaInicio, String fechaFin, String estado) {
        XSSFSheet hoja = libro.createSheet("Entradas");//Nombre
        try {
            String nombreArchivo = crearEncabezadoHojaEntradas(hoja, idComprador, fechaInicio, fechaFin, estado);
            boolean resultado = llenarTablaEntradas(hoja, idComprador, fechaInicio, fechaFin, estado);
            if (!resultado) {
                nombreArchivo = null;
            }
            return nombreArchivo;
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar la hoja excel de las entradas: " + ex.getMessage());
        }
    }
    
    private void escribirFiltrosEnEncabezado(XSSFSheet hoja, String fechaInicio, String fechaFin, String estado) {
        String textoFiltro = construirTextoFiltro(fechaInicio, fechaFin, estado);
        XSSFRow fila = hoja.createRow(2);
        XSSFCell celda = fila.createCell(0);
        celda.setCellValue(textoFiltro);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 0, 6));
        XSSFCellStyle estilo = crearEstiloFiltro(hoja);
        celda.setCellStyle(estilo);
    }

    private String construirTextoFiltro(String fechaInicio, String fechaFin, String estado) {
        boolean tieneFechaInicio = fechaInicio != null && !fechaInicio.isBlank();
        boolean tieneFechaFin = fechaFin != null && !fechaFin.isBlank();
        boolean tieneEstado = estado != null && !estado.isBlank();
        if (!tieneFechaInicio && !tieneFechaFin && !tieneEstado) return "Todas las entradas de hasta un año";
        StringBuilder texto = new StringBuilder("Filtro aplicado a las entradas de hasta un año: ");
        if (tieneFechaInicio && tieneFechaFin) {
            texto.append("desde ").append(fechaInicio).append(" hasta ").append(fechaFin);
        } else if (tieneFechaInicio) texto.append("desde ").append(fechaInicio);
        else if (tieneFechaFin) texto.append("hasta ").append(fechaFin);
        else texto.append("sin rango de fechas");
        if (tieneEstado) texto.append(", estado: ").append(estado);
        return texto.toString();
    }

    private XSSFCellStyle crearEstiloFiltro(XSSFSheet hoja) {
        XSSFCellStyle estilo = hoja.getWorkbook().createCellStyle();
        estilo.setAlignment(HorizontalAlignment.LEFT);
        XSSFFont fuente = hoja.getWorkbook().createFont();
        fuente.setItalic(true);
        estilo.setFont(fuente);
        return estilo;
    }

    private void escribirCabeceraTablaEntradas(XSSFSheet hoja, int filaIndex, XSSFCellStyle estiloColorFondo) {
        XSSFRow fila = hoja.createRow(filaIndex);
        String[] encabezados = {
            "Nro Entrada", "Evento", "Ubicacion", "Distrito",
            "Fecha", "Hora Inicio", "Hora Fin"
        };
        for (int i = 0; i < encabezados.length; i++) {
            XSSFCell celda = fila.createCell(i);
            celda.setCellValue(encabezados[i]);
            celda.setCellStyle(estiloColorFondo);
        }
    }

    private String crearEncabezadoHojaEntradas(XSSFSheet hoja, int idComprador, String fechaInicio, String fechaFin, String estado) {
        Comprador comprador = compradorDAO.buscar(idComprador);
        if (comprador == null) {
            return null;
        }
        String nombreArchivo = generarNombreArchivo(comprador);
        XSSFCellStyle estiloCabeceraTabla = crearEstiloCabeceraTabla(hoja);
        escribirTituloPrincipal(hoja, comprador);
        escribirFiltrosEnEncabezado(hoja, fechaInicio, fechaFin, estado);
        escribirCabeceraTablaEntradas(hoja, 4, estiloCabeceraTabla);
        return nombreArchivo;
    }

    private String generarNombreArchivo(Comprador comprador) {
        return "Lista_Entradas_" + comprador.getNombres() + ".xlsx";
    }

    private void escribirTituloPrincipal(XSSFSheet hoja, Comprador comprador) {
        XSSFRow fila = hoja.createRow(0);
        XSSFCell celda = fila.createCell(0);
        String texto = "Lista de Entradas del Comprador " + comprador.getNombres() + " " + comprador.getSegundoApellido();
        celda.setCellValue(texto);
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        XSSFCellStyle estilo = hoja.getWorkbook().createCellStyle();
        XSSFFont fuente = hoja.getWorkbook().createFont();
        fuente.setBold(true);
        estilo.setFont(fuente);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        celda.setCellStyle(estilo);
    }

    private XSSFCellStyle crearEstiloCabeceraTabla(XSSFSheet hoja) {
        XSSFCellStyle estilo = hoja.getWorkbook().createCellStyle();

        XSSFFont font = hoja.getWorkbook().createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);

        estilo.setFont(font);
        estilo.setFillForegroundColor(IndexedColors.RED.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estilo.setAlignment(HorizontalAlignment.CENTER);

        return estilo;
    }
    
    public boolean llenarTablaEntradas(XSSFSheet hoja, int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<DetalleEntradaDTO> listaDetalleEntradas = listarPorComprador(idComprador, fechaInicio, fechaFin, estado);
        if (listaDetalleEntradas.isEmpty()) {
            return false;
        }
        int posicion = 5;
        for (DetalleEntradaDTO detalleEntrada : listaDetalleEntradas) {
            XSSFRow registro = hoja.createRow(posicion++);
            llenarFilaDetalleEntrada(registro, detalleEntrada);
        }
        for (int i = 0; i < 7; i++) {
            hoja.autoSizeColumn(i);
        }
        return true;
    }

    private void llenarFilaDetalleEntrada(XSSFRow registro, DetalleEntradaDTO detalleEntrada) {
        XSSFCell celda = registro.createCell(0);
        celda.setCellValue(detalleEntrada.getNumEntrada());
        celda = registro.createCell(1);
        celda.setCellValue(detalleEntrada.getNombreEvento());
        celda = registro.createCell(2);
        celda.setCellValue(detalleEntrada.getUbicacion());
        celda = registro.createCell(3);
        celda.setCellValue(detalleEntrada.getNombreDistrito());
        celda = registro.createCell(4);
        celda.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(detalleEntrada.getFechaFuncion()));
        celda = registro.createCell(5);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleEntrada.getHoraInicio()));
        celda = registro.createCell(6);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleEntrada.getHoraFin()));
    }

    private boolean exportarLibroEntradas(XSSFWorkbook libro, String nombreArchivo) {
        try {
            String userHome = System.getProperty("user.home");
            File downloads = new File(userHome, "Downloads");
            File descargas = new File(userHome, "Descargas");
            File carpetaDestino = downloads.exists() ? downloads : (descargas.exists() ? descargas : new File(userHome));
            File archivoDestino = new File(carpetaDestino, nombreArchivo);
            if (archivoDestino.exists()) {// Si el archivo ya existe, intenta con otro nombre
                String baseName = nombreArchivo.replace(".xlsx", "");
                String nuevoNombre = baseName + "_" + System.currentTimeMillis() + ".xlsx";
                archivoDestino = new File(carpetaDestino, nuevoNombre);
            }
            try (OutputStream output = new FileOutputStream(archivoDestino)) {// Escribe y cierra el archivo
                libro.write(output);
            }
            libro.close();
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Error al exportar el libro excel de las entradas: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<DetalleEntradaDTO> listarPorComprador(int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<DetalleEntradaDTO> listaDetalleEntradas = null;
        try {
            List<Map<String, Object>> lista = entradaDAO.listarPorComprador(idComprador, fechaInicio, fechaFin, estado);
            if (lista != null) {
                listaDetalleEntradas = new ArrayList<>();
                for (Map<String, Object> detalle : lista) {
                    DetalleEntradaDTO detalleEntradaDTO = new DetalleEntradaDTO();
                    detalleEntradaDTO.llenarDetalleEntrada(detalle);
                    listaDetalleEntradas.add(detalleEntradaDTO);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las entradas: " + ex.getMessage());
        }
        return listaDetalleEntradas;
    }

    @Override
    public ConstanciaEntradaDTO buscarConstanciaEntrada(int idConstancia) {
        ConstanciaEntradaDTO constanciaEntradaDTO = null;
        try {
            Map<String, Object> detalle = entradaDAO.buscarConstanciaEntrada(idConstancia);
            if (detalle != null) {
                constanciaEntradaDTO = new ConstanciaEntradaDTO();
                constanciaEntradaDTO.llenarConstanciaEntrada(detalle);
                return constanciaEntradaDTO;
            } else {
                throw new RuntimeException("Constancia de la entrada no encontrada");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la constancia de la entrada: " + ex.getMessage());
        }
    }

    @Override
    public List<DetalleEntradaDTO> listarDetalleEntradas() {
        List<DetalleEntradaDTO> listaDetalleEntradas = null;
        try {
            List<Map<String, Object>> lista = entradaDAO.listarDetalleEntradas();
            if (lista != null) {
                listaDetalleEntradas = new ArrayList<>();
                for (Map<String, Object> detalle : lista) {
                    DetalleEntradaDTO detalleEntradaDTO = new DetalleEntradaDTO();
                    detalleEntradaDTO.llenarDetalleEntrada(detalle);
                    listaDetalleEntradas.add(detalleEntradaDTO);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las entradas: " + ex.getMessage());
        } finally {
            return listaDetalleEntradas;
        }
    }

    @Override
    public List<DetalleEntradaDTO> buscarPorTexto(String texto) {
        List<DetalleEntradaDTO> listaDetalleEntradas = null;
        try {
            List<Map<String, Object>> lista = entradaDAO.buscarPorTexto(texto);
            if (lista != null) {
                listaDetalleEntradas = new ArrayList<>();
                for (Map<String, Object> detalle : lista) {
                    DetalleEntradaDTO detalleEntradaDTO = new DetalleEntradaDTO();
                    detalleEntradaDTO.llenarDetalleEntrada(detalle);
                    listaDetalleEntradas.add(detalleEntradaDTO);
                }
            }
            return listaDetalleEntradas;
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar las entradas: " + ex.getMessage());
        } finally {
            return listaDetalleEntradas;
        }
    }
}