package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.ETipoAdministrador;
import com.slovers.sirgep.dominio.enums.ETipoDocumento;
import com.slovers.sirgep.dominio.models.gestion.Administrador;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.AdministradorDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author italo
 */
public class AdministradorMySQL implements AdministradorDAO {

    @Override
    public void insertar(Administrador admin) throws SQLException, IOException {
        String personaSql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
        String adminSql = "INSERT INTO Administrador(id_persona_admin, tipo_administrador) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql, Statement.RETURN_GENERATED_KEYS)) {
                setPersonaParameters(psPersona, admin);
                psPersona.executeUpdate();
                try (ResultSet rs = psPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        admin.setIdPersona(rs.getInt(1));
                    }
                }
            }

            try (PreparedStatement psAdmin = con.prepareStatement(adminSql)) {
                psAdmin.setInt(1, admin.getIdPersona());
                psAdmin.setString(2, admin.getTipoAdministrador().name());
                psAdmin.executeUpdate();
            }
        }
    }

    @Override
    public void actualizar(Administrador admin) throws SQLException, IOException {
        String personaSql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_persona=?";
        String adminSql = "UPDATE Administrador SET tipo_administrador=? WHERE id_persona_admin=?";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql)) {
                setPersonaParameters(psPersona, admin);
                psPersona.setInt(10, admin.getIdPersona());
                psPersona.executeUpdate();
            }

            try (PreparedStatement psAdmin = con.prepareStatement(adminSql)) {
                psAdmin.setString(1, admin.getTipoAdministrador().name());
                psAdmin.setInt(2, admin.getIdPersona());
                psAdmin.executeUpdate();
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Persona SET activo=0 WHERE id_persona=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Administrador obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Persona p JOIN Administrador a ON p.id_persona = a.id_persona_admin WHERE p.id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapAdministrador(rs);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Administrador> obtenerTodos() throws SQLException, IOException {
        ArrayList<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM Persona p JOIN Administrador a ON p.id_persona = a.id_persona_admin WHERE p.activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                administradores.add(mapAdministrador(rs));
            }
        }
        return administradores;
    }

    private void setPersonaParameters(PreparedStatement ps, Administrador admin) throws SQLException {
        ps.setString(1, admin.getNombres());
        ps.setString(2, admin.getPrimerApellido());
        ps.setString(3, admin.getSegundoApellido());
        ps.setString(4, admin.getCorreo());
        ps.setString(5, admin.getUsuario());
        ps.setString(6, admin.getContrasenia());
        ps.setString(7, admin.getNumDocumento());
        ps.setString(8, admin.getTipoDocumento().name());
        ps.setInt(9, 1); // activo
    }

    private Administrador mapAdministrador(ResultSet rs) throws SQLException {
        Administrador admin = new Administrador();
        admin.setIdPersona(rs.getInt("id_persona"));
        admin.setNombres(rs.getString("nombres"));
        admin.setPrimerApellido(rs.getString("primer_apellido"));
        admin.setSegundoApellido(rs.getString("segundo_apellido"));
        admin.setCorreo(rs.getString("correo"));
        admin.setUsuario(rs.getString("usuario"));
        admin.setContrasenia(rs.getString("contrasenia"));
        admin.setNumDocumento(rs.getString("num_documento"));
        admin.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
        admin.setTipoAdministrador(ETipoAdministrador.valueOf(rs.getString("tipo_administrador")));
        return admin;
    }
}
