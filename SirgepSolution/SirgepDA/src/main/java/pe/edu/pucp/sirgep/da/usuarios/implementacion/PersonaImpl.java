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
/*
    "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, "
                + "contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'A')";*/
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
    /*
    @Override
    public void insertar(Persona persona) throws SQLException,IOException{
        String sql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";

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
            ps.setInt(10, persona.getIdPersona());
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
    public ArrayList<Persona> obtenerTodos() throws SQLException,IOException {
        ArrayList<Persona> personas = new ArrayList<>();
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
        ps.setString(1, persona.getNombres());
        ps.setString(2, persona.getPrimerApellido());
        ps.setString(3, persona.getSegundoApellido());
        ps.setString(4, persona.getCorreo());
        ps.setString(5, persona.getUsuario());
        ps.setString(6, persona.getContrasenia());
        ps.setString(7, persona.getNumDocumento());
        ps.setString(8, persona.getTipoDocumento().toString());
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
*/