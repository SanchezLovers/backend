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
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery(),Statement.RETURN_GENERATED_KEYS)){
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id=rs.getInt(1);
                        this.setId(entity, id);
                        conn.commit();
                        System.out.println("Se inserto un registro de "+entity.getClass().getSimpleName()+" con ID="+id);
                        return id;
                    }
                }
                return -1;//No se registro correctamente
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(IOException|SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }
    }

    @Override
    public T buscar(int id) {
        try (Connection conn = DBManager.getInstance().getConnection();
               PreparedStatement pst = conn.prepareStatement(getSelectByIdQuery())) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        T entity = this.createFromResultSet(rs);
                        System.out.println("Se busco un registro con ID="+id);
                        return entity;
                    }
                    return null;
                }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al buscar la entidad con ID=" + id + " ", e);
        }
    }

    @Override
    public List<T> listar() {
        List<T> entities = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection();
              PreparedStatement pst = conn.prepareStatement(this.getSelectAllQuery());
              ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                entities.add(this.createFromResultSet(rs));
            }
            System.out.println("Se listo los registros");
            return entities;
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        }
    }

    @Override
    public boolean actualizar(T entity) {
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getUpdateQuery())) {
                this.setUpdateParameters(ps, entity);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se actualizo un registro de " + entity.getClass().getSimpleName());
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al actualizar " + entity.getClass().getSimpleName(), e);
        }
    }

    @Override
    public boolean eliminarLogico(int id) {
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino logicamente un registro con ID=" + id);
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico " , e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        }
    }
    
    @Override
    public boolean eliminarFisico(int id) {
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteFisicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino fisicamente un registro con ID=" + id);
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        }
    }
}