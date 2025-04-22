package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.models.gestion.Distrito;
import com.slovers.sirgep.dominio.models.gestion.Provincia;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.DistritoDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author ana c
 */
public class DistritoMySQL implements DistritoDAO {

    @Override
    public void insertar(Distrito distrito) throws SQLException, IOException {
        String sql = "INSERT INTO Distrito(nombre, id_provincia, activo) VALUES (?, ?, 1)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, distrito.getNombre());
            ps.setInt(2, distrito.getProvincia().getIdProvincia());
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
        String sql = "UPDATE Distrito SET nombre=?, id_provincia=?, activo=1 WHERE id_distrito=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, distrito.getNombre());
            ps.setInt(2, distrito.getProvincia().getIdProvincia());
            ps.setInt(3, distrito.getIdDistrito());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idDistrito) throws SQLException, IOException {
        String sql = "UPDATE Distrito SET activo=0 WHERE id_distrito=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDistrito);
            ps.executeUpdate();
        }
    }

    @Override
    public Distrito obtenerPorId(int idDistrito) throws SQLException, IOException {
        String sql = "SELECT * FROM Distrito WHERE id_distrito=? AND activo=1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDistrito);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapDistrito(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Distrito> obtenerTodos() throws SQLException, IOException {
        ArrayList<Distrito> distritos = new ArrayList<>();
        String sql = "SELECT * FROM Distrito WHERE activo=1";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                distritos.add(mapDistrito(rs));
            }
        }
        return distritos;
    }

    private Distrito mapDistrito(ResultSet rs) throws SQLException {
        Distrito distrito = new Distrito();
        distrito.setIdDistrito(rs.getInt("id_distrito"));
        distrito.setNombre(rs.getString("nombre"));

        Provincia provincia = new Provincia();
        provincia.setIdProvincia(rs.getInt("id_provincia"));
        distrito.setProvincia(provincia);

        return distrito;
    }
}
