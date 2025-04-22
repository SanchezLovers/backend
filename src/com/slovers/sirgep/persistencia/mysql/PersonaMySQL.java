package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.models.gestion.Persona;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.PersonaDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaMySQL implements PersonaDAO {

    @Override
    public void insertar(Persona persona) {
        String sql = "INSERT INTO Persona(nombre, apellido, correo, dni, activo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setPersonaParameters(ps, persona);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    persona.setIdPersona(rs.getInt(1));
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE Persona SET nombre=?, apellido=?, correo=?, dni=?, activo=? WHERE id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setPersonaParameters(ps, persona);
            ps.setInt(6, persona.getIdPersona());
            ps.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idPersona) {
        String sql = "UPDATE Persona SET activo=0 WHERE id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ps.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona obtenerPorId(int idPersona) {
        String sql = "SELECT * FROM Persona WHERE id_persona=? AND activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPersona(rs);
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Persona> obtenerTodos() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                personas.add(mapPersona(rs));
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return personas;
    }

    private void setPersonaParameters(PreparedStatement ps, Persona persona) throws SQLException {
        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getApellido());
        ps.setString(3, persona.getCorreo());
        ps.setString(4, persona.getDni());
        ps.setBoolean(5, persona.isActivo());
    }

    private Persona mapPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setIdPersona(rs.getInt("id_persona"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellido(rs.getString("apellido"));
        persona.setCorreo(rs.getString("correo"));
        persona.setDni(rs.getString("dni"));
        persona.setActivo(rs.getBoolean("activo"));
        return persona;
    }
}
