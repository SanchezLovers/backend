package pe.edu.pucp.sirgep.da.usuarios.implementacion;

import pe.edu.pucp.sirgep.da.usuarios.dao.PersonaDAO;
import pe.edu.pucp.sirgep.domain.usuarios.models.Persona;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

import java.sql.*;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;

public class PersonaImpl extends BaseImpl<Persona> implements PersonaDAO {

    @Override
    protected String getInsertQuery() {
        String sql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, "
                + "contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'A')";
        return sql;
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT * FROM Persona WHERE id_persona=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String sql = "SELECT * FROM Persona WHERE activo='A'";
        return sql;
    }

    @Override
    protected String getUpdateQuery() {
        String sql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, "
                + "usuario=?, contrasenia=?, num_documento=?, tipo_documento=? WHERE id_persona=?";
        return sql;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String sql = "UPDATE Persona SET activo='E' WHERE id_persona=?";
        return sql;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Persona WHERE id_persona=?";
        return query;
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Persona persona) {
        try{
            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());
            ps.setString(4, persona.getCorreo());
            ps.setString(5, persona.getUsuario());
            ps.setString(6, persona.getContrasenia());
            ps.setString(7, persona.getNumDocumento());
            ps.setString(8, persona.getTipoDocumento().toString());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Persona createFromResultSet(ResultSet rs) {
        try{
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
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Persona persona) {
        try{
            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getPrimerApellido());
            ps.setString(3, persona.getSegundoApellido());
            ps.setString(4, persona.getCorreo());
            ps.setString(5, persona.getUsuario());
            ps.setString(6, persona.getContrasenia());
            ps.setString(7, persona.getNumDocumento());
            ps.setString(8, persona.getTipoDocumento().toString());
            ps.setInt(9, persona.getIdPersona());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Persona entity, int id) {
        entity.setIdPersona(id);
    }
    
    
    @Override
    public int validarCuenta(String correo, String passcode) {
        int devolver=-1;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(CallableStatement pst=conn.prepareCall("{Call verificar_usuario(?,?,?)}")){
                pst.setString(1, correo);
                pst.setString(2, passcode);
                pst.registerOutParameter(3, Types.INTEGER);
                pst.execute();
                devolver=pst.getInt(3);
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el stored procedure", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al ejectuar");
        }finally{
            return devolver;
        }
    }
}