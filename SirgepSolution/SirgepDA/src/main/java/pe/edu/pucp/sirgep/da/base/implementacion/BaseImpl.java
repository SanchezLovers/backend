package pe.edu.pucp.sirgep.da.base.implementacion;

import java.io.IOException;
import pe.edu.pucp.sirgep.da.base.dao.BaseDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseImpl<T> implements BaseDAO<T> {
    protected abstract String getInsertQuery();
    protected abstract String getSelectByIdQuery();
    protected abstract String getSelectAllQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteLogicoQuery();
    protected abstract String getDeleteFisicoQuery();
    
    protected abstract void setInsertParameters(PreparedStatement ps, T entity);
    protected abstract T createFromResultSet(ResultSet rs);
    protected abstract void setUpdateParameters(PreparedStatement ps, T entity);
    protected abstract void setId(T entity, int id);
    
    @Override
    public int insertar(T entity) {
        int id=-1;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery(),Statement.RETURN_GENERATED_KEYS)){
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        id=rs.getInt(1);
                        this.setId(entity, id);
                        conn.commit();
                        System.out.println("Se inserto un registro de "+entity.getClass().getSimpleName()+" con ID="+id);
                    }
                }
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return id;
        }
    }

    @Override
    public T buscar(int id) {
        T entity=null;
        try (Connection conn = DBManager.getInstance().getConnection();
               PreparedStatement pst = conn.prepareStatement(getSelectByIdQuery())) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        entity = this.createFromResultSet(rs);
                        System.out.println("Se busco un registro con ID="+id);
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la entidad con ID=" + id + " ", e);
        }finally{
            return entity;
        }
    }

    @Override
    public List<T> listar() {
        List<T> entities=null;
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(this.getSelectAllQuery());
              ResultSet rs = pst.executeQuery()) {
            entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(this.createFromResultSet(rs));
            }
            System.out.println("Se listo los registros");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }finally{
            return entities;
        }
    }

    
    
    @Override
    public boolean actualizar(T entity) {
        boolean resultado=false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
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
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar " + entity.getClass().getSimpleName(), e);
        }finally{
            return resultado;
        }
    }

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
                resultado=true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico " , e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
    
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
                resultado=true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
}