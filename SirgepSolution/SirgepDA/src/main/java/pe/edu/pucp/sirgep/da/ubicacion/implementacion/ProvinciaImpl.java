package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import pe.edu.pucp.sirgep.da.ubicacion.dao.ProvinciaDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

public class ProvinciaImpl extends BaseImpl<Provincia> implements ProvinciaDAO {

    
    @Override
    protected String getInsertQuery() {
        String sql="INSERT INTO Provincia (id_provincia, nombre,Departamento_id_departamento, activo) "
               + "VALUES (?,?,?,'A')";
       return sql;
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT id_provincia, nombre, Departamento_id_departamento "
                +"FROM Provincia "
                + "WHERE id_provincia=? ";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String sql = "SELECT id_provincia, nombre, Departamento_id_departamento "
                +"FROM Provincia "
                +"WHERE activo='A'";
        return sql;
    }

    @Override
    protected String getUpdateQuery() {
        String sql="UPDATE Provincia SET nombre=? WHERE id_provincia=?";
        return sql;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String sql= "UPDATE Provincia SET activo='E' WHERE id_provincia=?";
        return sql;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Provincia WHERE id_provincia=?";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Provincia provincia) {
        try{
            ps.setInt(1, provincia.getIdProvincia());
            ps.setString(2, provincia.getNombre());
            ps.setInt(3, provincia.getDepartamento().getIdDepartamento());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Provincia createFromResultSet(ResultSet rs) {
        try{
        Provincia provincia= new Provincia();
        provincia.setIdProvincia(rs.getInt("id_provincia"));
        provincia.setNombre(rs.getString("nombre"));
        
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(rs.getInt("Departamento_id_departamento"));
        provincia.setDepartamento(departamento);
        
        return provincia;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Provincia provincia) {
        try{
            ps.setString(1, provincia.getNombre());
            ps.setInt(2,provincia.getIdProvincia());
        } catch (SQLException e) {
            throw new RuntimeException(e);        
        }
    }

    @Override
    protected void setId(Provincia provincia, int id) {
        provincia.setIdProvincia(id);
    }

    @Override
    public int insertar(Provincia entity) {
        
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery())){
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                System.out.println("Se ejecuto insert provincia"+entity.getIdProvincia());
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return entity.getIdProvincia();
        }
    }

    public String getListarPorDepa(){
        String query = "SELECT id_provincia, nombre, Departamento_id_departamento FROM Provincia WHERE Departamento_id_departamento=?;";
        return query;
    }
    
    @Override
    public List<Provincia> listarPorDepa(int idDepartamento) {
        List<Provincia> entities=null;
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(this.getListarPorDepa())) {
            pst.setInt(1, idDepartamento);
            ResultSet rs = pst.executeQuery();
            entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(this.createFromResultSet(rs));
            }
            System.out.println("Se listo los registros de provincia por Departamento");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return entities;
        }
    }
}