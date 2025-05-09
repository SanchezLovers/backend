package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DistritoImpl implements DistritoDAO {

    @Override
    public void insertar(Distrito distrito) throws SQLException, IOException {
        String sql = "INSERT INTO Distrito(id_distrito, nombre, Provincia_id_provincia, activo) VALUES (?, ?, ?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, distrito.getIdDistrito());
            ps.setString(2, distrito.getNombre());
            ps.setInt(3, distrito.getProvincia().getIdProvincia());
            ps.executeUpdate();
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
        String sql = "SELECT * FROM Distrito WHERE id_distrito=?";
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
        String sql = "SELECT * FROM Distrito";
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
