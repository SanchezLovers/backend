package pe.edu.pucp.sirgep.da.ubicacion.implementacion;

import java.io.IOException;
import pe.edu.pucp.sirgep.da.ubicacion.dao.DistritoDAO;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Distrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Provincia;
import pe.edu.pucp.sirgep.domain.ubicacion.models.Departamento;

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
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return entity.getIdDistrito();
        }
    }
    public String getListarPorProv(){
        String query = "SELECT id_distrito, nombre, Provincia_id_provincia FROM Distrito WHERE Provincia_id_provincia=?;";
        return query;
    }
    
    @Override
    public List<Distrito> listarPorProv(int idProvincia) {
        List<Distrito> entities=null;
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(this.getListarPorProv())) {
            pst.setInt(1, idProvincia);
            ResultSet rs = pst.executeQuery();
            entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(this.createFromResultSet(rs));
            }
            System.out.println("Se listo los registros de distrito por Provincia");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return entities;
        }
    }

    @Override
    public Distrito buscarDistritoCompleto(int idDistrito) {
        Distrito distrito = null;
        String query = "SELECT d.id_distrito, d.nombre as nombre_distrito, " +
                "p.id_provincia, p.nombre as nombre_provincia, " +
                "dep.id_departamento, dep.nombre as nombre_departamento " +
                "FROM Distrito d " +
                "INNER JOIN Provincia p ON d.Provincia_id_provincia = p.id_provincia " +
                "INNER JOIN Departamento dep ON p.Departamento_id_departamento = dep.id_departamento " +
                "WHERE d.id_distrito = ? AND d.activo = 'A'";

        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idDistrito);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                distrito = new Distrito();
                distrito.setIdDistrito(rs.getInt("id_distrito"));
                distrito.setNombre(rs.getString("nombre_distrito"));

                Provincia provincia = new Provincia();
                provincia.setIdProvincia(rs.getInt("id_provincia"));
                provincia.setNombre(rs.getString("nombre_provincia"));

                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(rs.getInt("id_departamento"));
                departamento.setNombre(rs.getString("nombre_departamento"));

                provincia.setDepartamento(departamento);
                distrito.setProvincia(provincia);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el distrito completo", e);
        }
        return distrito;
    }
}
