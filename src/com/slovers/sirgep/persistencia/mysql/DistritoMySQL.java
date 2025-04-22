package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.models.gestion.Distrito;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.DistritoDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DistritoMySQL implements DistritoDAO {

    @Override
    public void insertar(Distrito distrito) throws SQLException, IOException {
        String sql = "INSERT INTO Distrito(nombre, activo) VALUES (?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, distrito.getNombre());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    distrito.setIdDistrito(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Distrito distrito) throws SQLException, IOException {
        String sql = "UPDATE Distrito SET nombre=? WHERE id_distrito=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, distrito.getNombre());
            ps.setInt(2, distrito.getIdDistrito());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Distrito SET activo='E' WHERE id_distrito=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Distrito obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Distrito WHERE id_distrito=? AND activo='A'";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Distrito d = new Distrito();
                    d.setIdDistrito(rs.getInt("id_distrito"));
                    d.setNombre(rs.getString("nombre"));
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Distrito> obtenerTodos() throws SQLException, IOException {
        ArrayList<Distrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM Distrito WHERE activo='A'";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Distrito d = new Distrito();
                d.setIdDistrito(rs.getInt("id_distrito"));
                d.setNombre(rs.getString("nombre"));
                lista.add(d);
            }
        }
        return lista;
    }
}
