package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.dominio.models.gestion.Persona;

public class PersonaMySQL implements PersonaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public void insertar(Persona persona) {
        try {
            con = MySQLConexion.getConexion();
            String sql = "INSERT INTO persona(nombres, primer_apellido, segundo_apellido, num_documento, correo, usuario, contrasenia, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());
            ps.setString(4, persona.getNumDocumento());
            ps.setString(5, persona.getCorreo());
            ps.setString(6, persona.getUsuario());
            ps.setString(7, persona.getContrasenia());
            ps.setString(8, persona.getTipoDocumento().name());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
    }

    @Override
    public void actualizar(Persona persona) {
        try {
            con = MySQLConexion.getConexion();
            String sql = "UPDATE persona SET nombres=?, primer_apellido=?, segundo_apellido=?, num_documento=?, correo=?, usuario=?, contrasenia=?, tipo_usuario=? WHERE id_persona=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());
            ps.setString(4, persona.getNumDocumento());
            ps.setString(5, persona.getCorreo());
            ps.setString(6, persona.getUsuario());
            ps.setString(7, persona.getContrasenia());
            ps.setString(8, persona.getTipoDocumento().name());
            ps.setInt(9, persona.getIdPersona());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
    }

    @Override
    public void eliminar(int idPersona) {
        try {
            con = MySQLConexion.getConexion();
            String sql = "DELETE FROM persona WHERE id_persona=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
    }

    @Override
    public Persona obtenerPorId(int idPersona) {
        Persona persona = null;
        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT * FROM persona WHERE id_persona=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            rs = ps.executeQuery();
            if (rs.next()) {
                persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setPrimerApellido(rs.getString("primer_apellido"));
                persona.setSegundoApellido(rs.getString("segundo_apellido"));
                persona.setNum
