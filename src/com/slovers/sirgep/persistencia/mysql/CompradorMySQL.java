package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.ETipoDocumento;
import com.slovers.sirgep.dominio.models.gestion.Comprador;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.CompradorDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompradorMySQL implements CompradorDAO {

    @Override
    public void insertar(Comprador comprador) throws SQLException, IOException {
        String personaSql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
        String compradorSql = "INSERT INTO Comprador(id_persona_comprador, es_registrado) VALUES (?, ?)";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql, Statement.RETURN_GENERATED_KEYS)) {
                setPersonaParameters(psPersona, comprador);
                psPersona.executeUpdate();
                try (ResultSet rs = psPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        comprador.setIdPersona(rs.getInt(1));
                    }
                }
            }

            try (PreparedStatement psComprador = con.prepareStatement(compradorSql)) {
                psComprador.setInt(1, comprador.getIdPersona());
                psComprador.setBoolean(2, comprador.isEsRegistrado());
                psComprador.executeUpdate();
            }
        }
    }

    @Override
    public void actualizar(Comprador comprador) throws SQLException, IOException {
        String personaSql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_persona=?";
        String compradorSql = "UPDATE Comprador SET es_registrado=? WHERE id_persona_comprador=?";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql)) {
                setPersonaParameters(psPersona, comprador);
                psPersona.setInt(10, comprador.getIdPersona());
                psPersona.executeUpdate();
            }

            try (PreparedStatement psComprador = con.prepareStatement(compradorSql)) {
                psComprador.setBoolean(1, comprador.isEsRegistrado());
                psComprador.setInt(2, comprador.getIdPersona());
                psComprador.executeUpdate();
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
    public Comprador obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Persona p JOIN Comprador c ON p.id_persona = c.id_persona_comprador WHERE p.id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapComprador(rs);
            }
        }
        return null;
    }

    @Override
    public List<Comprador> obtenerTodos() throws SQLException, IOException {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM Persona p JOIN Comprador c ON p.id_persona = c.id_persona_comprador WHERE p.activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                compradores.add(mapComprador(rs));
            }
        }
        return compradores;
    }

    private void setPersonaParameters(PreparedStatement ps, Comprador comprador) throws SQLException {
        ps.setString(1, comprador.getNombres());
        ps.setString(2, comprador.getPrimerApellido());
        ps.setString(3, comprador.getSegundoApellido());
        ps.setString(4, comprador.getCorreo());
        ps.setString(5, comprador.getUsuario());
        ps.setString(6, comprador.getContrasenia());
        ps.setString(7, comprador.getNumDocumento());
        ps.setString(8, comprador.getTipoDocumento().name());
        ps.setInt(9, 1); // activo
    }

    private Comprador mapComprador(ResultSet rs) throws SQLException {
        Comprador comprador = new Comprador();
        comprador.setIdPersona(rs.getInt("id_persona"));
        comprador.setNombres(rs.getString("nombres"));
        comprador.setPrimerApellido(rs.getString("primer_apellido"));
        comprador.setSegundoApellido(rs.getString("segundo_apellido"));
        comprador.setCorreo(rs.getString("correo"));
        comprador.setUsuario(rs.getString("usuario"));
        comprador.setContrasenia(rs.getString("contrasenia"));
        comprador.setNumDocumento(rs.getString("num_documento"));
        comprador.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
        comprador.setEsRegistrado(rs.getBoolean("es_registrado"));
        return comprador;
    }
}

