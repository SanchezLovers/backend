package pe.edu.pucp.sirgep.da.infraestructura.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.domain.infraestructura.models.Calificacion;

public class CalificacionImpl {
    protected String getInsertQuery(){
        String cadena = "Insert into Calificacion (puntaje, servicio,"
                + " texto) values (?,?,?)";
        return cadena;
    }

    public CalificacionImpl() {
    }
    
    protected void setInsertParameters(PreparedStatement ps, Calificacion c){
        try{
            ps.setInt(1, c.getPuntaje());
            ps.setString(2, String.valueOf(c.getServicio()));
            ps.setString(3, c.getTexto());
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    protected void setId(Calificacion c, int id) {
        c.setId(id);
    }
    
    public int insertar(Calificacion c) {
        int id=-1;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pst=conn.prepareStatement(this.getInsertQuery(),Statement.RETURN_GENERATED_KEYS)){
                this.setInsertParameters(pst, c);
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        id=rs.getInt(1);
                        this.setId(c, id);
                        conn.commit();
                        System.out.println("Se inserto un registro de Calificación con ID="+id);
                    }
                }
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar calificacion. ", e);
        }finally{
            return id;
        }
    }
}
