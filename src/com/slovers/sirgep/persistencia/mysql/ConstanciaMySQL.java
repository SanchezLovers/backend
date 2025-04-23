package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.EMetodoPago;
import com.slovers.sirgep.dominio.models.ventas.Constancia;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.ConstanciaDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
*/
/**
 * @author darkJCdark (Jorge Chamorro)
 */
public class ConstanciaMySQL implements ConstanciaDAO {
    //Atributos
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    //Metodos CRUD
    @Override
    public int insertar(Constancia constancia) {
        int resultado=0;
        try {
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos la sentencia SQL
            String sql = "INSERT INTO Constancia(fecha, metodo_pago, igv, total, detalle_pago, activo) VALUES (?, ?, 0.18, ?, ?, 'A')";
            pst = con.prepareStatement(sql);
            this.setPreparedStatement(constancia);
            resultado=pst.executeUpdate();
            System.out.println("Se ha registrado la constancia...");
        }catch(IOException | SQLException ex){
            System.out.println("Error al insertar la constancia: "+ex.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar Connection: "+ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Constancia constancia){
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos la sentencia SQL
            String sql = "UPDATE Constancia SET fecha=?, metodo_pago=?, total=?, detalle_pago=? WHERE id_constancia=?";
            pst = con.prepareStatement(sql);
            this.setPreparedStatement(constancia);
            pst.setInt(5, constancia.getIdConstancia());
            resultado = pst.executeUpdate();
            System.out.println("Se ha actualizado la constancia...");
        }catch(IOException|SQLException ex){
            System.out.println("Error al actualizar la constancia: "+ex.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar Connection: "+ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int idConstancia){
        int resultado = 0;
        try{
            //Establecer una conexion con la BD
            con = DBManager.getInstance().getConnection();
            //Ejecutamos la sentencia SQL
            String sql = "UPDATE Constancia SET activo='I' WHERE id_constancia=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idConstancia);
            resultado = pst.executeUpdate();
            System.out.println("Se ha eliminado la constancia...");
        }catch(IOException|SQLException ex){
            System.out.println("Error al eliminar la constancia: "+ex.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar Connection: "+ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public Constancia obtenerPorId(int idConstancia){
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Constancia WHERE id_Constancia=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idConstancia);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Se ha obtenido la constancia...");
                return setConstancia();
            }
        }catch(IOException|SQLException ex){
            System.out.println("Error al obtener la constancia: "+ex.getMessage());
        }finally{
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar ResultSet: "+ex.getMessage());
            }
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar Connection: "+ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public ArrayList<Constancia> obtenerTodosActivos(){
        ArrayList<Constancia> constancias = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM Constancia WHERE activo = 'A'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Constancia constancia=this.setConstancia();
                constancias.add(constancia);
            }
            System.out.println("Se ha obtenido las constancias...");
        }catch(IOException|SQLException ex){
            System.out.println("Error al obtener las constancias: "+ex.getMessage());
        }finally{
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar ResultSet: "+ex.getMessage());
            }
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar Connection: "+ex.getMessage());
            }
        }
        return constancias;
    }
    
    //Metodos complementarios
    private void setPreparedStatement(Constancia constancia) throws SQLException {
        pst.setDate(1, new Date(constancia.getFecha().getTime()));////import java.sql.Date;
        pst.setString(2, constancia.getMetodoPago().toString());
        pst.setDouble(3, constancia.getTotal());
        pst.setString(4, constancia.getDetallePago());
    }
    private Constancia setConstancia() throws SQLException {
        Constancia constancia = new Constancia();
        constancia.setIdConstancia(rs.getInt("id_constancia"));
        constancia.setFecha(rs.getDate("fecha"));
        constancia.setMetodoPago(EMetodoPago.valueOf(rs.getString("metodo_pago")));
        constancia.setTotal(rs.getDouble("total"));
        constancia.setDetallePago(rs.getString("detalle_pago"));
        return constancia;
    }
}