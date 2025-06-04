package pe.edu.pucp.sirgep.da.usuarios.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

import java.io.IOException;
import java.sql.*;
import pe.edu.pucp.sirgep.da.base.implementacion.BaseImpl;

public class CompradorImpl extends BaseImpl<Comprador> implements CompradorDAO {
    private final PersonaImpl personaDAO;
    
    public CompradorImpl() {
        this.personaDAO = new PersonaImpl();
    }
    
    @Override
    protected String getInsertQuery() {
        String sql = "INSERT INTO Comprador(es_registrado,id_persona_comprador,monto_billetera,activo)"
                   + "VALUES (?,?,?,'A')";
        return sql;    
    }

    @Override
    protected String getSelectByIdQuery() {
        String sql = "SELECT id_persona,nombres,primer_apellido,"
                + "segundo_apellido,correo,usuario,contrasenia,num_documento,"
                + "tipo_documento,es_registrado "
                + "FROM Persona P, Comprador C "
                + "WHERE P.id_persona = C.id_persona_comprador AND "
                + "id_persona_comprador=?";
        return sql;
    }

    @Override
    protected String getSelectAllQuery() {
        String sql = "SELECT id_persona,nombres,primer_apellido,"
                + "segundo_apellido,correo,usuario,contrasenia,num_documento,"
                + "tipo_documento,es_registrado "
                + "FROM Persona P, Comprador C "
                + "WHERE P.id_persona = C.id_persona_comprador AND P.activo='A'";
        return sql;
    }
////////
    @Override
    protected String getUpdateQuery() {
        String sql = "UPDATE Comprador " +
                    "SET  monto_billetera = ?, es_registrado = ? " +
                    "WHERE id_persona_comprador = ?";
       return sql;
    }

    @Override
    protected String getDeleteLogicoQuery() {
        String sql = "UPDATE Comprador SET activo='E' WHERE id_persona_comprador=?";
        return sql;
    }

    @Override
    protected String getDeleteFisicoQuery() {
        String query = "DELETE FROM Comprador WHERE id_persona_comprador=?";
        return query;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Comprador comprador) {
        try{
            ps.setBoolean(1, (comprador.getRegistrado()==1?true:false));
            ps.setInt(2, comprador.getIdPersona());
            ps.setDouble (3, comprador.getMonto());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Comprador createFromResultSet(ResultSet rs) {
        try{
            Comprador persona = new Comprador();
            persona.setIdPersona(rs.getInt("id_persona"));
            persona.setNombres(rs.getString("nombres"));
            persona.setPrimerApellido(rs.getString("primer_apellido"));
            persona.setSegundoApellido(rs.getString("segundo_apellido"));
            persona.setCorreo(rs.getString("correo"));
            persona.setUsuario(rs.getString("usuario"));
            persona.setContrasenia(rs.getString("contrasenia"));
            persona.setNumDocumento(rs.getString("num_documento"));
            persona.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
            persona.setRegistrado(rs.getBoolean("es_registrado")?1:0);
            return persona;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Comprador comprador) {
        try{
            ps.setDouble (1, comprador.getMonto());
            ps.setBoolean(2, (comprador.getRegistrado()==1?true:false));
            ps.setInt(3, comprador.getIdPersona());
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setId(Comprador entity, int id) {
        entity.setIdPersona(id);
    }
    
    /*Sobrecarga necesaria para primero insertar a la persona*/
    @Override
    public int insertar(Comprador entity) {
        int id=-1;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            id = personaDAO.insertar(entity);//Insercion inicial de la persona
            
            
            if (entity.getMonto() == 0) {  
                entity.setMonto(0);           
            }
            
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
        }catch(SQLException e) {
            throw new RuntimeException("Error al insertar "+entity.getClass().getSimpleName()+" ", e);
        }finally{
            return id;
        }
    }
    
    /*Sobrecarga necesaria para actualizar tambien a la persona*/
    @Override
    public boolean actualizar(Comprador entity) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        }finally{
            return resultado;
        }
    }
    
        
    
        @Override
        public Comprador buscarPorDni(String dni) {
            Comprador comprador = null;
            String sql =
                "SELECT p.id_persona, p.nombres, p.primer_apellido, p.segundo_apellido, " +
                "       p.num_documento, p.correo, p.usuario, p.contrasenia, p.tipo_documento, c.es_registrado, c.monto_billetera " +   
                "FROM   Persona   p " +
                "JOIN   Comprador c ON c.id_persona_comprador = p.id_persona " +
                "WHERE  p.num_documento = ?";

            try (Connection con = DBManager.getInstance().getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comprador = new Comprador();
                        comprador.setIdPersona (rs.getInt   ("id_persona"));
                        comprador.setNombres    (rs.getString("nombres"));
                        comprador.setPrimerApellido(rs.getString("primer_apellido"));
                        comprador.setSegundoApellido(rs.getString("segundo_apellido"));
                        comprador.setNumDocumento(rs.getString("num_documento"));
                        comprador.setCorreo(rs.getString("correo"));
                        comprador.setUsuario(rs.getString("usuario"));
                        comprador.setContrasenia(rs.getString("contrasenia"));
                        String td = rs.getString("tipo_documento"); 
                        if (td != null) {
                            comprador.setTipoDocumento(
                                ETipoDocumento.valueOf(td.toUpperCase()) 
                            );
                        }
                        
                        comprador.setRegistrado (rs.getBoolean("es_registrado") ? 1 : 0);
                        comprador.setMonto      (rs.getDouble ("monto_billetera"));   // ahora sí existe
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar por dni", e);
            }
            return comprador;
}
    /*
    @Override
    public void insertar(Comprador comprador) throws SQLException, IOException {
        String personaSql = "INSERT INTO Persona(nombres, primer_apellido, segundo_apellido, correo, usuario, contrasenia, num_documento, tipo_documento, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
        String compradorSql = "INSERT INTO Comprador(id_persona_comprador, es_registrado) VALUES (?, ?)";
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql, Statement.RETURN_GENERATED_KEYS)) {
                setPersonaParameters(psPersona, comprador);
                psPersona.executeUpdate();
                try (ResultSet rs = psPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        comprador.setIdPersona(rs.getInt(1));
                    }
                }
            }

            try (PreparedStatement psComprador = con.prepareStatement(compradorSql)) {
                psComprador.setInt(1, comprador.getIdPersona());
                psComprador.setBoolean(2, comprador.isRegistrado());
                psComprador.executeUpdate();
            }
        }
    }

    @Override
    public void actualizar(Comprador comprador) throws SQLException, IOException {
        String personaSql = "UPDATE Persona SET nombres=?, primer_apellido=?, segundo_apellido=?, correo=?, usuario=?, contrasenia=?, num_documento=?, tipo_documento=?, activo=? WHERE id_persona=?";
        String compradorSql = "UPDATE Comprador SET es_registrado=? WHERE id_persona_comprador=?";

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement psPersona = con.prepareStatement(personaSql)) {
                setPersonaParameters(psPersona, comprador);
                psPersona.setInt(10, comprador.getIdPersona());
                psPersona.executeUpdate();
            }

            try (PreparedStatement psComprador = con.prepareStatement(compradorSql)) {
                psComprador.setBoolean(1, comprador.isRegistrado());
                psComprador.setInt(2, comprador.getIdPersona());
                psComprador.executeUpdate();
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException, IOException {
        String sql = "UPDATE Persona SET activo=0 WHERE id_persona=?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Comprador obtenerPorId(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM Persona p JOIN Comprador c ON p.id_persona = c.id_persona_comprador WHERE p.id_persona=?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapComprador(rs);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Comprador> obtenerTodos() throws SQLException, IOException {
        ArrayList<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM Persona p JOIN Comprador c ON p.id_persona = c.id_persona_comprador WHERE p.activo=1";

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                compradores.add(mapComprador(rs));
            }
        }
        return compradores;
    }

    private void setPersonaParameters(PreparedStatement ps, Comprador comprador) throws SQLException {
        ps.setString(1, comprador.getNombres());
        ps.setString(2, comprador.getPrimerApellido());
        ps.setString(3, comprador.getSegundoApellido());
        ps.setString(4, comprador.getCorreo());
        ps.setString(5, comprador.getUsuario());
        ps.setString(6, comprador.getContrasenia());
        ps.setString(7, comprador.getNumDocumento());
        ps.setString(8, comprador.getTipoDocumento().name());
        ps.setInt(9, 1); // activo
    }

    private Comprador mapComprador(ResultSet rs) throws SQLException {
        Comprador comprador = new Comprador();
        comprador.setIdPersona(rs.getInt("id_persona"));
        comprador.setNombres(rs.getString("nombres"));
        comprador.setPrimerApellido(rs.getString("primer_apellido"));
        comprador.setSegundoApellido(rs.getString("segundo_apellido"));
        comprador.setCorreo(rs.getString("correo"));
        comprador.setUsuario(rs.getString("usuario"));
        comprador.setContrasenia(rs.getString("contrasenia"));
        comprador.setNumDocumento(rs.getString("num_documento"));
        comprador.setTipoDocumento(ETipoDocumento.valueOf(rs.getString("tipo_documento")));
        comprador.setEsRegistrado(rs.getBoolean("es_registrado"));
        return comprador;
    }
    */

    
}