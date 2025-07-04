package pe.edu.pucp.sirgep.business.ventas.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import pe.edu.pucp.sirgep.business.ventas.dtos.ConstanciaReservaDTO;

import pe.edu.pucp.sirgep.business.ventas.dtos.DetalleReservaDTO;
import pe.edu.pucp.sirgep.business.ventas.dtos.ReservaDTO;
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
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;
import pe.edu.pucp.sirgep.domain.ventas.models.Reserva;

public class ReservaServiceImpl implements IReservaService {
    private final ReservaDAO reservaDAO;
    private final CompradorDAO compradorDAO;
    private final EspacioDAO espacioDAO;
    private final DistritoDAO distritoDAO;

    public ReservaServiceImpl() {
        reservaDAO = new ReservaImpl();
        compradorDAO = new CompradorImpl();
        espacioDAO = new EspacioImpl();
        distritoDAO = new DistritoImpl();
    }

    //Metodos del CRUD
    @Override
    public int insertar(Reserva reserva) {
        reserva.setHorarioIni(LocalTime.parse(reserva.getIniString()));
        reserva.setHorarioFin(LocalTime.parse(reserva.getFinString()));
        return reservaDAO.insertar(reserva);
    }

    @Override
    public Reserva buscar(int id) {
        return reservaDAO.buscar(id);
    }

    @Override
    public boolean inactivar() {
        return reservaDAO.inactivar();
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
    public Comprador buscarCompradorDeReserva(int idComprador) {
        try {
            return compradorDAO.buscar(idComprador);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el comprador de la reserva: " + ex.getMessage());
        }
    }

    @Override
    public Espacio buscarEspacioDeReserva(int idEspacio) {
        boolean resultado = false;
        try {
            return espacioDAO.buscar(idEspacio);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la espacio de la reserva: " + ex.getMessage());
        }
    }

    @Override
    public Distrito buscarDistritoDeReserva(int idEntrada) {
        Distrito resultado = null;
        try {
            resultado = distritoDAO.buscar(idEntrada);
            if (resultado == null) {
                throw new RuntimeException("Error al buscar el distrito de la reserva: ");
            }
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar el distrito de la reserva: " + ex.getMessage());
        }
    }

    //Metodos adicionales para el listado de reservas por filtros
    @Override
    public List<ReservaDTO> listarTodos() {
        List<ReservaDTO> listaFinal = null;
        try {
            // Llamas al DAO que devuelve List<Map<String, Object>>
            List<Map<String, Object>> lista = reservaDAO.listarTodos();

            if (lista != null) {
                listaFinal = new ArrayList<>();
                for (Map<String, Object> reserva : lista) {
                    ReservaDTO reservaDTO = new ReservaDTO();
                    reservaDTO.llenarReserva(reserva);
                    listaFinal.add(reservaDTO);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las reservas por fecha: " + ex.getMessage(), ex);
        }
        return listaFinal;
    }
    
    @Override
    public List<ReservaDTO> listarDetalleReservasPorFecha(Date fecha, boolean activo) {
        List<ReservaDTO> listaFinal = null;

        try {
            // Llamas al DAO que devuelve List<Map<String, Object>>
            List<Map<String, Object>> lista = reservaDAO.listarDetalleReservasPorFecha(fecha, activo);

            if (lista != null) {
                listaFinal = new ArrayList<>();
                for (Map<String, Object> reserva : lista) {
                    ReservaDTO reservaDTO = new ReservaDTO();
                    reservaDTO.llenarReserva(reserva);
                    listaFinal.add(reservaDTO);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las reservas por fecha: " + ex.getMessage(), ex);
        }
        return listaFinal;
    }
    
    @Override
    public boolean cancelarReserva(int id) throws SQLException{
        return reservaDAO.cancelarReserva(id);
    }
    public List<ReservaDTO> listarPorDistrito(int id_distrito, boolean activo) {
        List<ReservaDTO> listaFinal = null;

        try {
            // Llamas al DAO que devuelve List<Map<String, Object>>
            List<Map<String, Object>> lista = reservaDAO.listarPorDistrito(id_distrito, activo);

            if (lista != null) {
                listaFinal = new ArrayList<>();
                for (Map<String, Object> reserva : lista) {
                    ReservaDTO reservaDTO = new ReservaDTO();
                    reservaDTO.llenarReserva(reserva);
                    listaFinal.add(reservaDTO);
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las reservas por fecha: " + ex.getMessage(), ex);
        }
        return listaFinal;
    }

    @Override
    public boolean crearLibroExcelReservas(int idComprador, String fechaInicio, String fechaFin, String estado) {
        boolean resultado = false;
        try {
            XSSFWorkbook libro = new XSSFWorkbook();
            String nombreArchivo = crearHojalReservas(libro, idComprador, fechaInicio, fechaFin, estado);
            if (nombreArchivo != null) {
                resultado = exportarLibroReservas(libro, nombreArchivo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el excel de reservas: " + e.getMessage(), e);
        }
        return resultado;
    }

    private String crearHojalReservas(XSSFWorkbook libro, int idComprador, String fechaInicio, String fechaFin, String estado) {
        XSSFSheet hoja = libro.createSheet("Reservas");
        try {
            String nombreArchivo = crearEncabezadoHojaReservas(hoja, idComprador);
            escribirCabeceraTablaReservas(hoja, crearEstiloCabeceraTabla(hoja));
            boolean resultado = llenarTablaReservas(hoja, idComprador, fechaInicio, fechaFin, estado);
            if (!resultado) {
                return null;
            }
            return nombreArchivo;
        } catch (Exception ex) {
            throw new RuntimeException("Error al llenar la hoja excel de las reservas: " + ex.getMessage());
        }
    }

    private String crearEncabezadoHojaReservas(XSSFSheet hoja, int idComprador) {
        Comprador comprador = compradorDAO.buscar(idComprador);
        if (comprador == null) {
            return null;
        }

        String textoTitulo = "Lista de Reservas del Comprador " + comprador.getNombres() + " " + comprador.getSegundoApellido();
        XSSFRow fila = hoja.createRow(0);
        XSSFCell celda = fila.createCell(0);
        celda.setCellValue(textoTitulo);
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        XSSFCellStyle estilo = hoja.getWorkbook().createCellStyle();
        XSSFFont fuente = hoja.getWorkbook().createFont();
        fuente.setBold(true);
        estilo.setFont(fuente);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        celda.setCellStyle(estilo);

        return generarNombreArchivo(comprador);
    }

    private String generarNombreArchivo(Comprador comprador) {
        return "Lista_Reservas_" + comprador.getNombres() + ".xlsx";
    }

    private void escribirCabeceraTablaReservas(XSSFSheet hoja, XSSFCellStyle estiloColorFondo) {
        XSSFRow fila = hoja.createRow(2);
        String[] encabezados = {
            "Nro Reserva", "Espacio", "Categoria", "Ubicacion",
            "Distrito", "Fecha", "Hora Inicio", "Hora Fin"
        };
        for (int i = 0; i < encabezados.length; i++) {
            XSSFCell celda = fila.createCell(i);
            celda.setCellValue(encabezados[i]);
            celda.setCellStyle(estiloColorFondo);
        }
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

    private boolean llenarTablaReservas(XSSFSheet hoja, int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<DetalleReservaDTO> listaDetalleReservas = listarPorComprador(idComprador, fechaInicio, fechaFin, estado);
        if (listaDetalleReservas!=null && !listaDetalleReservas.isEmpty()) {
            int posicion = 3;
            for (DetalleReservaDTO detalleReserva : listaDetalleReservas) {
                XSSFRow registro = hoja.createRow(posicion++);
                llenarFilaReserva(registro, detalleReserva);
            }
            for (int i = 0; i < 8; i++) {
                hoja.autoSizeColumn(i);
            }
            return true;
        }
        return false;
    }

    private void llenarFilaReserva(XSSFRow registro, DetalleReservaDTO detalleReserva) {
        XSSFCell celda = registro.createCell(0);
        celda.setCellValue(detalleReserva.getNumReserva());
        celda = registro.createCell(1);
        celda.setCellValue(detalleReserva.getNombreEspacio());
        celda = registro.createCell(2);
        celda.setCellValue(detalleReserva.getCategoria());
        celda = registro.createCell(3);
        celda.setCellValue(detalleReserva.getUbicacion());
        celda = registro.createCell(4);
        celda.setCellValue(detalleReserva.getNombreDistrito());
        celda = registro.createCell(5);
        celda.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(detalleReserva.getFecha()));
        celda = registro.createCell(6);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleReserva.getHoraInicio()));
        celda = registro.createCell(7);
        celda.setCellValue(new SimpleDateFormat("HH:mm:ss").format(detalleReserva.getHoraFin()));
    }

    private boolean exportarLibroReservas(XSSFWorkbook libro, String nombreArchivo) {
        try {
            String userHome = System.getProperty("user.home");
            File downloads = new File(userHome, "Downloads");
            File descargas = new File(userHome, "Descargas");
            File carpetaDestino = downloads.exists() ? downloads : (descargas.exists() ? descargas : new File(userHome));
            File archivoDestino = new File(carpetaDestino, nombreArchivo);
            if (archivoDestino.exists()) {
                String baseName = nombreArchivo.replace(".xlsx", "");
                String nuevoNombre = baseName + "_" + System.currentTimeMillis() + ".xlsx";
                archivoDestino = new File(carpetaDestino, nuevoNombre);
            }
            try (OutputStream output = new FileOutputStream(archivoDestino)) {
                libro.write(output);
            }
            libro.close();
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Error al exportar el libro excel de las reservas: " + ex.getMessage(), ex);
        }
    }
    
    //Metodos para buscar el detalle de la constancia de la reserva
    @Override
    public ConstanciaReservaDTO buscarConstanciaReserva(int idConstancia){
        ConstanciaReservaDTO constanciaReservaDTO=null;
        try {
            Map<String, Object> detalle=reservaDAO.buscarConstanciaReserva(idConstancia);
            if(detalle!=null){
                constanciaReservaDTO=new ConstanciaReservaDTO();
                constanciaReservaDTO.llenarConstanciaReserva(detalle);
                return constanciaReservaDTO;
            }else{
                throw new RuntimeException("Constancia de la reserva no encontrada");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la constancia de la reserva: " + ex.getMessage());
        }
    }

    @Override
    public List<Reserva> listarPorMesYAnio(int mes, int anio) {
        if(mes<1 || mes>12)
            throw new RuntimeException("Mes incorrecto");
        if(anio<=0)
            throw new RuntimeException("Anio incorrecto");
        
        return reservaDAO.listarPorMesYAnio(mes, anio);
    }

    @Override
    public List<DetalleReservaDTO> listarPorComprador(int idComprador, String fechaInicio, String fechaFin, String estado) {
        List<DetalleReservaDTO> listaDetalleReservas = null;
        try {
            List<Map<String, Object>> lista = reservaDAO.listarPorComprador(idComprador,fechaInicio,fechaFin,estado);
            if (lista != null) {
                listaDetalleReservas = new ArrayList<>();
                for (Map<String, Object> detalle : lista) {
                    DetalleReservaDTO detalleReservaDTO = new DetalleReservaDTO();
                    detalleReservaDTO.llenarDetalleReserva(detalle);
                    listaDetalleReservas.add(detalleReservaDTO);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al listar las reservas: " + ex.getMessage());
        }
        return listaDetalleReservas;
    }
}