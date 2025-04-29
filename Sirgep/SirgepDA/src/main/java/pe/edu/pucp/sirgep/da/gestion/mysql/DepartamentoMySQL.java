package pe.edu.pucp.sirgep.da.gestion.mysql;

import pe.edu.pucp.sirgep.domain.gestion.models.Departamento;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.gestion.dao.DepartamentoDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DepartamentoMySQL implements DepartamentoDAO {
    @Override
    public void insertar(Departamento departamento) throws SQLException, IOException {
        String sql = "INSERT INTO Departamento(id_departamento, nombre, activo) VALUES (?, ?, 'A')";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, departamento.getIdDepartamento());
            ps.setString(2, departamento.getNombre());
            ps.executeUpdate();

        }
    }

    @Override
    public void actualizar(Departamento departamento) throws SQLException, IOException {
        String sql = "UPDATE Departamento SET nombre=? WHERE id_departamento=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, departamento.getNombre());
            ps.setInt(2, departamento.getIdDepartamento());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Departamento SET activo='E' WHERE id_departamento=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Departamento obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Departamento WHERE id_departamento=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Departamento d = new Departamento();
                    d.setIdDepartamento(rs.getInt("id_departamento"));
                    d.setNombre(rs.getString("nombre"));
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Departamento> obtenerTodos() throws SQLException, IOException {
        ArrayList<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Departamento";
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt("id_departamento"));
                d.setNombre(rs.getString("nombre"));
                lista.add(d);
            }
        }
        return lista;
    }
}