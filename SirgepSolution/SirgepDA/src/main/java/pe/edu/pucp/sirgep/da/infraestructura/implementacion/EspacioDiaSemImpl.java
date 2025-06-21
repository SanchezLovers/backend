/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;
import pe.edu.pucp.sirgep.da.infraestructura.dao.EspacioDiaSemDAO;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.enums.EDiaSemana;
import pe.edu.pucp.sirgep.domain.infraestructura.models.EspacioDiaSem;

public class EspacioDiaSemImpl extends BaseImpl<EspacioDiaSem> implements EspacioDiaSemDAO{

    @Override
    protected String getInsertQuery() {
        /* Siempre se inserta un dia activo */
        String query = "INSERT INTO Espacio_has_eDiaSemana(Espacio_id_espacio, eDiaSemana_dia_semana, activo) VALUES(?,?,'A');";
        return query;
    }

    @Override
    protected String getSelectByIdQuery() {
        String query = "SELECT Espacio_id_espacio, eDiaSemana_dia_semana, activo FROM Espacio_has_eDiaSemana WHERE Espacio_id_espacio=?;";
        return query;
    }

    @Override
    protected String getSelectAllQuery() {
        String query = "SELECT Espacio_id_espacio, eDiaSemana_dia_semana, activo FROM Espacio_has_eDiaSemana;";
        return query;
    }

    @Override
    protected String getUpdateQuery() {
        String query = "UPDATE Espacio_has_eDiaSemana SET eDiaSemana_dia_semana=? WHERE Espacio_id_espacio=?;";
        return query;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String query = "UPDATE Espacio_has_eDiaSemana SET activo='I' WHERE Espacio_id_espacio=?;";
        return query;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Espacio_has_eDiaSemana WHERE Espacio_id_espacio=?;";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, EspacioDiaSem entity) {
        try{
            ps.setInt(1, entity.getIdEspacio());
            ps.setString(2, entity.getDia().toString());
            ps.setString(3, String.valueOf(entity.getActivo()));
        }
        catch(SQLException ex){
            throw new RuntimeException("ERROR AL INSERTAR DIA EN ESPACIO " + ex.getMessage());
        }
    }

    @Override
    protected EspacioDiaSem createFromResultSet(ResultSet rs) {
        try{
            EspacioDiaSem diaSem = new EspacioDiaSem();
            int idEspacio = rs.getInt("Espacio_id_espacio");
            String dia = rs.getString("eDiaSemana_dia_semana");
            char activo = rs.getString("activo").charAt(0);
            // Seteamos los valores corrspondientes
            diaSem.setDia(EDiaSemana.valueOf(dia));
            diaSem.setActivo(activo);
            diaSem.setIdEspacio(idEspacio);
            return diaSem;
        }
        catch(SQLException ex){
            throw new RuntimeException("ERROR AL CREAR RS EN UN DIA EN ESPACIO" + ex.getMessage());
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, EspacioDiaSem entity) {
        try {
            ps.setInt(1, entity.getIdEspacio());
            ps.setString(2, entity.getDia().toString());
        } catch (SQLException ex) {
            Logger.getLogger(EspacioDiaSemImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void setId(EspacioDiaSem entity, int id) {
        entity.setIdEspacio(id);
    }
    
    // override del insertado pq' el ID no es autoincremental
    @Override
    public boolean insertarDia(EspacioDiaSem entity){
        int rows = 0;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery())){
                this.setInsertParameters(pst, entity);
                rows = pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        conn.commit();
                        System.out.println("Se inserto un registro de "+entity.getClass().getSimpleName());
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
            return rows>0;
        }
    }

    public String getSelectAllQueryByEspacio(){
        return getSelectByIdQuery(); // es lo mismo!, selecciona a todos los días con el id del espacio como parámetro
        // lo pongo así para que se entienda mejor, pero podría ponerse directamente
    }
    
    @Override
    public List<EspacioDiaSem> listarPorEspacio(int idEspacio) {
        List<EspacioDiaSem> entities = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection();
                PreparedStatement pst = conn.prepareStatement(this.getSelectAllQueryByEspacio())) {
            pst.setInt(1, idEspacio); // le ponemos que busque solo en el espacio directamente
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                entities.add(this.createFromResultSet(rs));
            }
            System.out.println("Se listo los días mediante el ID del espacio");
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las entidades", e);
        } finally {
            return entities;
        }
    }

}
