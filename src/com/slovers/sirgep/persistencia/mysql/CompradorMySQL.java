package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.dominio.models.gestion.Comprador;
import com.slovers.sirgep.persistencia.mysql.personaDAO;

public class CompradorMySQL implements CompradorDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private PersonaDAO personaDAO = new PersonaMySQL();

    @Override
    public void insertar(Comprador comprador) {
        personaDAO.insertar(comprador); // Inserta en persona
        try {
            con = MySQLConexion.getConexion();
            String sql = "INSERT INTO comprador(id_persona, es_registrado) VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, comprador.getIdPersona());
            ps.setBoolean(2, comprador.isEsRegistrado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
    }

    @Override
    public void actualizar(Comprador comprador) {
        personaDAO.actualizar(comprador);
        try {
            con = MySQLConexion.getConexion();
            String sql = "UPDATE comprador SET es_registrado=? WHERE id_persona=?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, comprador.isEsRegistrado());
            ps.setInt(2, comprador.getIdPersona());
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
            String sql = "DELETE FROM comprador WHERE id_persona=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPersona);
            ps.executeUpdate();
            personaDAO.eliminar(idPersona); // Elimina también la persona base
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
    }

    @Override
    public Comprador obtenerPorId(int idPersona) {
        Comprador comprador = null;
        Persona p = personaDAO.obtenerPorId(idPersona);
        if (p != null) {
            comprador = new Comprador(p); // Suponiendo que tienes un constructor que recibe Persona
            try {
                con = MySQLConexion.getConexion();
                String sql = "SELECT * FROM comprador WHERE id_persona=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, idPersona);
                rs = ps.executeQuery();
                if (rs.next()) {
                    comprador.setEsRegistrado(rs.getBoolean("es_registrado"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (con != null) con.close(); } catch (Exception e) { }
            }
        }
        return comprador;
    }

    @Override
    public List<Comprador> obtenerTodos() {
        List<Comprador> lista = new ArrayList<>();
        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT * FROM comprador";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comprador c = (Comprador) personaDAO.obtenerPorId(rs.getInt("id_persona"));
                c.setEsRegistrado(rs.getBoolean("es_registrado"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { }
        }
        return lista;
    }
}
