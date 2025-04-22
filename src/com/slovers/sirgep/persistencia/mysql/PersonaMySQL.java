package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.ETipoDocumento;
import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.PersonaDAO;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaMySQL implements PersonaDAO {

    @Override
    public void insertar(Persona persona) throws SQLException,IOException{
        String sql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPersonaParameters(ps, persona);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    persona.setIdPersona(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Persona persona) throws SQLException,IOException {
        String sql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setPersonaParameters(ps, persona);
            ps.setInt(1, persona.getIdPersona());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idPersona) throws SQLException,IOException{
        String sql = "UPDATE Persona SET activo=0 WHERE id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ps.executeUpdate();
        }
    }

    @Override
    public Persona obtenerPorId(int idPersona) throws SQLException,IOException {
        String sql = "SELECT * FROM Persona WHERE id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPersona(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Persona> obtenerTodos() throws SQLException,IOException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                personas.add(mapPersona(rs));
            }
        }
        return personas;
    }

    private void setPersonaParameters(PreparedStatement ps, Persona persona) throws SQLException {
        ps.setString(2, persona.getNombres());
        ps.setString(3, persona.getPrimerApellido());
        ps.setString(4, persona.getSegundoApellido());
        ps.setString(5, persona.getCorreo());
        ps.setString(6, persona.getUsuario());
        ps.setString(7, persona.getContrasenia());
        ps.setString(8, persona.getNumDocumento());
        ps.setString(9, persona.getTipoDocumento().toString());
    }

    private Persona mapPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setIdPersona(rs.getInt("id_persona"));
        persona.setNombres(rs.getString("nombres"));
        persona.setPrimerApellido(rs.getString("primer_apellido"));
        persona.setSegundoApellido(rs.getString("segundo_apellido"));
        persona.setCorreo(rs.getString("correo"));
        persona.setUsuario(rs.getString("usuario"));
        persona.setContrasenia(rs.getString("contrasenia"));
        persona.setNumDocumento(rs.getString("num_documento"));
        persona.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
        return persona;
    }
}
