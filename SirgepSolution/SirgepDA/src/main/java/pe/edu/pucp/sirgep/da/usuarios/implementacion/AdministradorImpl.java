package pe.edu.pucp.sirgep.da.usuarios.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.usuarios.dao.AdministradorDAO;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoAdministrador;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Administrador;

import java.io.IOException;
import java.sql.*;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

public class AdministradorImpl extends BaseImpl<Administrador> implements AdministradorDAO {
    private final PersonaImpl personaDAO;
    
    public AdministradorImpl() {
        this.personaDAO = new PersonaImpl();
    }
    
    @Override
    protected String getInsertQuery(){
        String sql = "INSERT INTO Administrador(tipo_administrador,id_persona_admin,activo)"
                   + "VALUES (?,?,'A')";
        return sql;
    }
    @Override
    protected String getSelectByIdQuery(){
        String sql = "SELECT id_persona,nombres,primer_apellido,"
                + "segundo_apellido,correo,usuario,contrasenia,num_documento,"
                + "tipo_documento,tipo_administrador "
                + "FROM Persona P, Administrador A "
                + "WHERE P.id_persona = A.id_persona_admin AND id_persona_admin=?";
        return sql;
    }
    @Override
    protected String getSelectAllQuery(){
        String sql = "SELECT id_persona,nombres,primer_apellido,"
                + "segundo_apellido,correo,usuario,contrasenia,num_documento,"
                + "tipo_documento,tipo_administrador "
                + "FROM Persona P, Administrador A "
                + "WHERE P.id_persona = A.id_persona_admin AND P.activo='A'";
        return sql;
    }
    @Override
    protected String getUpdateQuery(){
        String sql = "UPDATE Administrador "
                   + "SET tipo_administrador=?"
                   + "WHERE id_persona_admin=?";
        return sql;
    }
    @Override
    protected String getDeleteLogicoQuery(){
        String sql = "UPDATE Administrador SET activo='E' WHERE id_persona_admin=?";
        return sql;
    }
    @Override
    protected String getDeleteFisicoQuery(){
        String query = "DELETE FROM Administrador WHERE id_persona_admin=?";
        return query;
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Administrador administrador){
        try{
            ps.setString(1, administrador.getTipoAdministrador().toString());
            ps.setInt(2, administrador.getIdPersona());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected Administrador createFromResultSet(ResultSet rs){
        try{
            Administrador persona = new Administrador();
            persona.setIdPersona(rs.getInt("id_persona"));
            persona.setNombres(rs.getString("nombres"));
            persona.setPrimerApellido(rs.getString("primer_apellido"));
            persona.setSegundoApellido(rs.getString("segundo_apellido"));
            persona.setCorreo(rs.getString("correo"));
            persona.setUsuario(rs.getString("usuario"));
            persona.setContrasenia(rs.getString("contrasenia"));
            persona.setNumDocumento(rs.getString("num_documento"));
            persona.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
            persona.setTipoAdministrador(ETipoAdministrador.valueOf(rs.getString("tipo_administrador")));
            return persona;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Administrador administrador){
        setInsertParameters(ps, administrador);//son los mismos
    }
    @Override
    protected void setId(Administrador entity, int id){
        entity.setIdPersona(id);
    }

    /*Sobrecarga necesaria para primero insertar a la persona*/
    @Override
    public int insertar(Administrador entity) {
        int id=-1;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            id = personaDAO.insertar(entity);//Insercion inicial de la persona
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery(),Statement.RETURN_GENERATED_KEYS)){
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                conn.commit();
                System.out.println("Se inserto un registro de "+entity.getClass().getSimpleName()+" con ID="+id);
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(IOException|SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return id;
        }
    }
    
    /*Sobrecarga necesaria para actualizar tambien a la persona*/
    @Override
    public boolean actualizar(Administrador entity) {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            resultado = personaDAO.actualizar(entity);//Actualizacion incial de la persona
            try (PreparedStatement ps = conn.prepareStatement(this.getUpdateQuery())) {
                this.setUpdateParameters(ps, entity);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se actualizo un registro de " + entity.getClass().getSimpleName());
                resultado=true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al actualizar " + entity.getClass().getSimpleName(), e);
        }finally{
            return resultado;
        }
    }

    /*Sobrecarga necesaria para eliminar tambien a la persona*/
    @Override
    public boolean eliminarLogico(int id) {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino logicamente un registro con ID=" + id);
                resultado=personaDAO.eliminarLogico(id);//Eliminacion de Persona, luego de eliminar Administrador
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico " , e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
    
    /*Sobrecarga necesaria para eliminar tambien a la persona*/
    @Override
    public boolean eliminarFisico(int id) {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteFisicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino fisicamente un registro con ID=" + id);
                resultado=personaDAO.eliminarFisico(id);//Eliminacion de Persona, luego de eliminar  Administrador
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
}