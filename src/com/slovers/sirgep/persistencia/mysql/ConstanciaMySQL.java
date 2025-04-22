package com.slovers.sirgep.persistencia.mysql;

import com.slovers.sirgep.dominio.enums.EMetodoPago;
import com.slovers.sirgep.persistencia.config.DBManager;
import com.slovers.sirgep.persistencia.dao.ConstanciaDAO;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author darkJCdark (Jorge Chamorro)
 */
public class ConstanciaMySQL implements ConstanciaDAO {

    @Override
    public void insertar(Constancia constancia) throws SQLException,IOException{
        String sql = "INSERT INTO Constancia(fecha, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setConstanciaParameters(ps, constancia);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    constancia.setIdConstancia(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Constancia constancia) throws SQLException,IOException {
        String sql = "UPDATE Constancia SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_Constancia=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setConstanciaParameters(ps, constancia);
            ps.setInt(1, constancia.getIdConstancia());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idConstancia) throws SQLException,IOException{
        String sql = "UPDATE Constancia SET activo=0 WHERE id_Constancia=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idConstancia);
            ps.executeUpdate();
        }
    }

    @Override
    public Constancia obtenerPorId(int idConstancia) throws SQLException,IOException {
        String sql = "SELECT * FROM Constancia WHERE id_Constancia=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idConstancia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapConstancia(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Constancia> obtenerTodos() throws SQLException,IOException {
        ArrayList<Constancia> Constancias = new ArrayList<>();
        String sql = "SELECT * FROM Constancia WHERE activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Constancias.add(mapConstancia(rs));
            }
        }
        return Constancias;
    }

    private void setConstanciaParameters(PreparedStatement ps, Constancia constancia) throws SQLException {
        ps.setString(1, constancia.getNombres());
        ps.setString(2, constancia.getPrimerApellido());
        ps.setString(3, constancia.getSegundoApellido());
        ps.setString(4, constancia.getCorreo());
        ps.setString(5, constancia.getUsuario());
        ps.setString(6, constancia.getContrasenia());
        ps.setString(7, constancia.getNumDocumento());
        ps.setString(8, constancia.getTipoDocumento().toString());
    }

    private Constancia mapConstancia(ResultSet rs) throws SQLException {
        Constancia constancia = new Constancia();
        constancia.setIdConstancia(rs.getInt("id_Constancia"));
        constancia.setNombres(rs.getString("nombres"));
        constancia.setPrimerApellido(rs.getString("primer_apellido"));
        constancia.setSegundoApellido(rs.getString("segundo_apellido"));
        constancia.setCorreo(rs.getString("correo"));
        constancia.setUsuario(rs.getString("usuario"));
        constancia.setContrasenia(rs.getString("contrasenia"));
        constancia.setNumDocumento(rs.getString("num_documento"));
        constancia.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
        return constancia;
    }
}