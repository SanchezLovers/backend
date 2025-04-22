package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.models.gestion.Provincia;
import com.slovers.sirgep.dominio.models.gestion.Departamento;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.ProvinciaDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ana c
 */
public class ProvinciaMySQL implements ProvinciaDAO {

    @Override
    public void insertar(Provincia provincia) throws SQLException, IOException {
        String sql = "INSERT INTO Provincia(nombre, id_departamento, activo) VALUES (?, ?, 1)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, provincia.getNombre());
            ps.setInt(2, provincia.getDepartamento().getIdDepartamento());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    provincia.setIdProvincia(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Provincia provincia) throws SQLException, IOException {
        String sql = "UPDATE Provincia SET nombre=?, id_departamento=? WHERE id_provincia=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, provincia.getNombre());
            ps.setInt(2, provincia.getDepartamento().getIdDepartamento());
            ps.setInt(3, provincia.getIdProvincia());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Provincia SET activo=0 WHERE id_provincia=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Provincia obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Provincia WHERE id_provincia=? AND activo=1";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Provincia p = new Provincia();
                    p.setIdProvincia(rs.getInt("id_provincia"));
                    p.setNombre(rs.getString("nombre"));

                    Departamento d = new Departamento();
                    d.setIdDepartamento(rs.getInt("id_departamento"));
                    p.setDepartamento(d);

                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Provincia> obtenerTodos() throws SQLException, IOException {
        ArrayList<Provincia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Provincia WHERE activo=1";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Provincia p = new Provincia();
                p.setIdProvincia(rs.getInt("id_provincia"));
                p.setNombre(rs.getString("nombre"));

                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt("id_departamento"));
                p.setDepartamento(d);

                lista.add(p);
            }
        }
        return lista;
    }
}
