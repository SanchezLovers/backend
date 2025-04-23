package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.models.ventas.Entrada;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.EntradaDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author willi
 */
public class EntradaMySQL implements EntradaDAO{
    @Override
    public void insertar(Entrada entrada) throws SQLException, IOException{
        String query="INSERT INTO Entrada(num_entrada, Persona_id_persona, "
                + "id_constancia_entrada, Funcion_id_funcion) VALUES(?, ?, ?, ?)";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                setEntradaParameters(ps, entrada);
                ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        entrada.setNumEntrada(rs.getInt(1));
                    }
                }
            }
        }
    }
    
    @Override
    public void actualizar(Entrada entrada) throws SQLException, IOException{
        String query = "UPDATE Entrada SET Persona_id_persona=?, id_constancia_entrada=?, "
                + "Funcion_id_funcion=? WHERE num_entrada=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                setEntradaParameters(ps, entrada);
                ps.setInt(4, entrada.getNumEntrada());
                ps.executeUpdate();
            }
        }
    }
    
    @Override
    public void eliminar(int id) throws SQLException, IOException{
        String query = "UPDATE Entrada SET activo='E' WHERE num_entrada=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }
    
    @Override
    public ArrayList<Entrada> obtenerTodos() throws SQLException, IOException{
        ArrayList<Entrada> entradas = new ArrayList<>();
        String query = "SELECT * FROM Entrada e "
                + "JOIN Persona p ON e.Persona_id_persona=p.id_persona "
                + "JOIN Constancia c ON e.id_constancia_entrada=c.id_constancia "
                + "JOIN Funcion f ON e.Funcion_id_funcion=f.id_funcion";
        try(Connection con = DBManager.getInstance().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                entradas.add(mapEntrada(rs));
            }
        }
        return entradas;
    }
    
    @Override
    public Entrada obtenerPorId(int id) throws SQLException, IOException{
        String query = "SELECT * FROM Entrada e "
                + "JOIN Persona p ON e.Persona_id_persona=p.id_persona "
                + "JOIN Constancia c ON e.id_constancia_entrada=c.id_constancia "
                + "JOIN Funcion f ON e.Funcion_id_funcion=f.id_funcion "
                + "WHERE e.num_entrada=?";
        try(Connection con = DBManager.getInstance().getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) return mapEntrada(rs);
                }
            }
        }
        return null;
    }
    
    public void setEntradaParameters(PreparedStatement ps, Entrada entrada) throws SQLException{
        ps.setInt(1, entrada.getNumEntrada());
        ps.setInt(2, entrada.getPersona().getIdPersona());
        ps.setInt(3, entrada.getIdConstancia());
        ps.setInt(4, entrada.getFuncion().getIdFuncion());
        ps.setString(5, "A");
    }
    
    public Entrada mapEntrada(ResultSet rs) throws SQLException{
        Entrada entrada = new Entrada();
        entrada.setNumEntrada(rs.getInt("num_entrada"));
        return entrada;
    }
}
