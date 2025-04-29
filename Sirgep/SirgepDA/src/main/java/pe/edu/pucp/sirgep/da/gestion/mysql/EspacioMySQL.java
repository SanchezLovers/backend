package pe.edu.pucp.sirgep.da.gestion.mysql;

import pe.edu.pucp.sirgep.domain.gestion.enums.ETipoEspacio;
import pe.edu.pucp.sirgep.domain.gestion.models.Espacio;
import pe.edu.pucp.sirgep.domain.gestion.models.Distrito;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.gestion.dao.EspacioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EspacioMySQL implements EspacioDAO {
    //Ana: estoy agrengando el tipo activo, le agregue i a incio
    //      estoy agregando el idDistrito
    @Override 
    public void insertar(Espacio espacio) throws SQLException, IOException {
        String query = "INSERT INTO Espacio(nombre, tipo_espacio, horario_inicio_atencion"
                + ", horario_fin_atencion, ubicacion, superficie, precio_reserva, activo,"
                + "Distrito_id_distrito) "
                + " values(?, ?, ?, ?, ?, ?, ?, 'A', ?)";
        
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {
            
            setEspacioParameters(ps, espacio); //Preparamos el ps con los atributos del objeto
            ps.executeUpdate(); 
            
            //Traer el último ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){
                
                if(rskeys.next()){
                    espacio.setIdEspacio(rskeys.getInt(1));
                }
            }
        } 
    }
    
    //Modificado para que consulte datos de la tabla Espacio
    @Override
    public ArrayList<Espacio> obtenerTodos() throws SQLException, IOException {
        ArrayList<Espacio> espacios = new ArrayList<>();
        String query = "SELECT id_espacio, nombre, tipo_espacio,"
                + " horario_inicio_atencion, horario_fin_atencion, ubicacion, superficie, precio_reserva "
                + " FROM Espacio ";
        try(Connection con = DBManager.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);) {        
            while(rs.next()){
                Espacio espacio = mapEspacio(rs);
                espacios.add(espacio);
            }
        } 
        return espacios;
    }
    
    //Ana: Modifiqué los nombres para que funcionen con la tabla de la bd
    @Override
    public Espacio obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT id_espacio, nombre, tipo_espacio,"
                + " horario_inicio_atencion, horario_fin_atencion, ubicacion, superficie, precio_reserva "
                + " FROM Espacio WHERE id_espacio=?";
        try (Connection conn = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEspacio(rs);
                }
            }
        }
        return null;
    }
    
    //Modificado - nombres de variables
    @Override
    public void actualizar(Espacio espacio) throws SQLException, IOException {
        String query = "UPDATE Espacio SET nombre=?, tipo_espacio=?, horario_inicio_atencion=?,"
                + " horario_fin_atencion=?, ubicacion=?, superficie=?, precio_reserva=? WHERE id_espacio=?";
        
        try (Connection conn = DBManager.getInstance().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(query)) {
            
            setEspacioParameters(ps, espacio);
            ps.setInt(8, espacio.getIdEspacio());
            ps.executeUpdate();
        }
    }
    
    @Override
    public void eliminar(int id) throws SQLException, IOException {
        //Eliminación logica
        String query = "UPDATE Espacio SET activo='E' WHERE id_espacio=?";
        try (Connection conn = DBManager.getInstance().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }
    }
    
    public void eliminarFisico(int id) throws SQLException, IOException{
        String query = "DELETE FROM Espacio WHERE id_espacio=?";
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    //Ana: estoy editanto los parametros 3 y 4 para que funcionen con 
    //  Time para mySQL 
    private void setEspacioParameters(PreparedStatement ps, Espacio e) throws SQLException {
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getTipoEspacio().name());
        //ps.setDate(2, new java.sql.Date(a.getFechaNacimiento().getTime()));// Asumiendo fecha_nacimiento como java.util.Date
        // e.horarios to string antes de convertilos a time y encadeno segundos
        ps.setTime(3,
                Time.valueOf(e.getHorarioInicioAtencion().toString()+":00"));
        ps.setTime(4,
                Time.valueOf(e.getHorarioFinAtencion().toString() + ":00"));
        ps.setString(5, e.getUbicacion());
        ps.setDouble(6, e.getSuperficie());
        ps.setDouble(7, e.getPrecioReserva());
//        int = e.getDistrito().getIdDistrito();
        Distrito dis = e.getDistrito();
        ps.setDouble(8, dis.getIdDistrito());
    }
    
    private Espacio mapEspacio(ResultSet rs) throws SQLException {
        Espacio e = new Espacio();
        e.setIdEspacio(rs.getInt("id_espacio")); //modifiqué el id
        e.setNombre(rs.getString("nombre"));
        e.setTipoEspacio(ETipoEspacio.valueOf(rs.getString("tipo_espacio")));
        e.setHorarioInicioAtencion(rs.getTime("horario_inicio_atencion").toLocalTime());
        e.setHorarioFinAtencion(rs.getTime("horario_fin_atencion").toLocalTime());
        e.setUbicacion(rs.getString("ubicacion"));
        e.setSuperficie(rs.getDouble("superficie"));
        e.setPrecioReserva(rs.getDouble("precio_reserva"));
        
        //e.setFechaNacimiento(new java.util.Date(rs.getDate("fecha_nacimiento").getTime()));// Asumiendo fecha_nacimiento como java.util.Date
        //e.setTipoAlumno(TIPO_ALUMNO.valueOf(rs.getString("tipo_alumno")));
        return e;
    }
}