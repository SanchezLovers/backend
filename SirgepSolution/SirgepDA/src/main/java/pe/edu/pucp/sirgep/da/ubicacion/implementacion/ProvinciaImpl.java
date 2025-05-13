package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.ubicacion.dao.ProvinciaDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ProvinciaImpl implements ProvinciaDAO {

    @Override
    public void insertar(Provincia provincia) throws SQLException, IOException {
        String sql = "INSERT INTO Provincia(nombre, activo) VALUES (?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, provincia.getNombre());
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
        String sql = "UPDATE Provincia SET nombre=? WHERE id_provincia=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, provincia.getNombre());
            ps.setInt(2, provincia.getIdProvincia());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Provincia SET activo='E' WHERE id_provincia=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Provincia obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Provincia WHERE id_provincia=? AND activo='A'";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Provincia p = new Provincia();
                    p.setIdProvincia(rs.getInt("id_provincia"));
                    p.setNombre(rs.getString("nombre"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Provincia> obtenerTodos() throws SQLException, IOException {
        ArrayList<Provincia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Provincia";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Provincia p = new Provincia();
                p.setIdProvincia(rs.getInt("id_provincia"));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }
        }
        return lista;
    }
}