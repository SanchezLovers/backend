package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import pe.edu.pucp.sirgep.da.infraestructura.dao.FuncionDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Funcion;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionImpl implements FuncionDAO{
    @Override
    public void insertar(Funcion funcion) throws SQLException, IOException{
        String query = "INSERT INTO Funcion(id_funcion, hora_inicio, hora_fin, "
                + "Evento_idEvento) VALUES(?, ?, ?, ?)";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                setFuncionParameters(ps,funcion);
                ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        funcion.setIdFuncion(rs.getInt(1));
                    }
                }
            }
        }
    }
    
    @Override
    public void actualizar(Funcion funcion) throws SQLException, IOException{
        String query = "UPDATE Funcion SET id_funcion=?, hora_inicio=?, hora_fin=?, "
                + "Evento_idEvento=? WHERE id_funcion=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                setFuncionParameters(ps, funcion);
                ps.setInt(9, funcion.getIdFuncion());
                ps.executeUpdate();
            }
        }
    }
    
    @Override
    public void eliminar(int id) throws SQLException, IOException{
        String query = "UPDATE Funcion SET activo='A' WHERE id_funcion=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }
    
    @Override
    public Funcion obtenerPorId(int id) throws SQLException, IOException{
        String query = "SELECT * FROM Funcion f JOIN Evento e "
                + "ON f.Evento_idEvento=e.id_evento WHERE f.id_funcion=?";
        try(Connection con=DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) return mapFuncion(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Funcion> obtenerTodos() throws SQLException, IOException{
        ArrayList<Funcion> funciones = new ArrayList<>();
        String query = "SELECT * FROM Funcion f JOIN Evento e "
                + "ON f.Evento_idEvento=e.id_evento";
        try(Connection con=DBManager.getInstance().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                funciones.add(mapFuncion(rs));
            }
        }
        return funciones;
    }
    
    public void setFuncionParameters(PreparedStatement ps,Funcion funcion) throws SQLException{
        ps.setInt(1, funcion.getIdFuncion());
        ps.setTime(2, java.sql.Time.valueOf(funcion.getHoraInicio()));
        ps.setTime(3, java.sql.Time.valueOf(funcion.getHoraFin()));
        ps.setInt(4, funcion.getEvento().getIdEvento());
        ps.setString(5, "A");
    }
    
    
    public Funcion mapFuncion(ResultSet rs) throws SQLException{
        Funcion funcion = new Funcion();
        funcion.setIdFuncion(rs.getInt("id_funcion"));
        funcion.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
        funcion.setHoraFin(rs.getTime("hora_fin").toLocalTime()); 
        return funcion;
    }
}