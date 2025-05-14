package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import java.io.IOException;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

import java.sql.*;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;

public class DistritoImpl extends BaseImpl<Distrito> implements  DistritoDAO{


    @Override
    protected String getInsertQuery() {
       String sql="INSERT INTO Distrito (id_distrito, nombre,Provincia_id_provincia, activo) "
               + "VALUES (?,?,?,'A')";
       return sql;
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT id_distrito, nombre, Provincia_id_provincia "
                +"FROM Distrito "
                + "WHERE id_distrito=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String sql = "SELECT id_distrito, nombre, Provincia_id_provincia "
                +"FROM Distrito "
                +"WHERE activo='A'";
        return sql;
    }

    @Override
    protected String getUpdateQuery() {
        String sql="UPDATE Distrito SET nombre=? WHERE id_distrito=?";
        return sql;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String sql= "UPDATE Distrito SET activo='E' WHERE id_distrito=?";
        return sql;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Distrito WHERE id_distrito=?";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Distrito distrito) {
        try{
            ps.setInt(1, distrito.getIdDistrito());
            ps.setString(2, distrito.getNombre());
            ps.setInt(3, distrito.getProvincia().getIdProvincia());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Distrito createFromResultSet(ResultSet rs) {
        try{
        Distrito distrito= new Distrito();
        distrito.setIdDistrito(rs.getInt("id_distrito"));
        distrito.setNombre(rs.getString("nombre"));
        
        Provincia provincia = new Provincia();
        provincia.setIdProvincia(rs.getInt("Provincia_id_provincia"));
        distrito.setProvincia(provincia);
        return distrito;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Distrito distrito) {
        try{
            ps.setString(1, distrito.getNombre());
            ps.setInt(2,distrito.getIdDistrito());
        } catch (SQLException e) {
            throw new RuntimeException(e);        
        }

    }

    @Override
    protected void setId(Distrito distrito, int id) {
        distrito.setIdDistrito(id);
    }

    @Override
    public int insertar(Distrito entity) {
        
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery())){
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                System.out.println("Se ejecuto insert provincia"+entity.getIdDistrito());
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(IOException|SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return entity.getIdDistrito();
        }
    }
}
