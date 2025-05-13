package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import pe.edu.pucp.sirgep.da.ubicacion.dao.DepartamentoDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

import java.sql.*;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

public class DepartamentoImpl extends BaseImpl<Departamento> implements DepartamentoDAO {

    
    public DepartamentoImpl(){

    }
    
    @Override
    protected String getInsertQuery(){
        String sql = "INSERT INTO Departamento(id_departamento,nombre,activo) "
                   + "VALUES (?,?,'A')";
        return sql;
    }
    
    @Override
    protected String getSelectByIdQuery(){
        String sql = "SELECT id_departamento,nombre "
                + "FROM Departamento "
                + "WHERE id_departamento=?";
        return sql;
    }
    
    @Override
    protected String getSelectAllQuery(){
        String sql = "SELECT id_departamento,nombre "
                + "FROM Departamento "
                + "WHERE activo='A'";
        return sql;
    }
    
    @Override
    protected String getUpdateQuery(){
        String sql = "UPDATE Departamento "
                   + "SET nombre=? "
                   + "WHERE id_departamento=?";
        return sql;
    }
    
    @Override
    protected String getDeleteLogicoQuery(){
        String sql = "UPDATE Departamento SET activo='E' WHERE id_departamento=?";
        return sql;
    }
    
    @Override
    protected String getDeleteFisicoQuery(){
        String query = "DELETE FROM Departamento WHERE id_departamento=?";
        return query;
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Departamento departamento){
        try{
            ps.setInt(1, departamento.getIdDepartamento());
            ps.setString(2, departamento.getNombre());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected Departamento createFromResultSet(ResultSet rs){
         try{
            Departamento departamento = new Departamento();
            departamento.setIdDepartamento(rs.getInt("id_departamento"));
            departamento.setNombre(rs.getString("nombre"));
            return departamento;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Departamento departamento){
        try{
            ps.setString(1, departamento.getNombre());
            ps.setInt(2, departamento.getIdDepartamento());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void setId(Departamento departamento, int id){
        departamento.setIdDepartamento(id);
    }
    
}