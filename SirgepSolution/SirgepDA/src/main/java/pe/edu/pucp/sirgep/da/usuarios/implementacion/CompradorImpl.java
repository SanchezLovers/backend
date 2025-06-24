package pe.edu.pucp.sirgep.da.usuarios.implementacion;

import pe.edu.pucp.sirgep.dbmanager.DBManager;
import pe.edu.pucp.sirgep.da.usuarios.dao.CompradorDAO;
import pe.edu.pucp.sirgep.domain.usuarios.enums.ETipoDocumento;
import pe.edu.pucp.sirgep.domain.usuarios.models.Comprador;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                + "WHERE P.id_persona = C.id_persona_comprador AND P.activo='A' AND C.es_registrado=1";
        return sql;
    }

    @Override
    protected String getUpdateQuery() {
        String sql = "UPDATE Comprador "
                + "SET  monto_billetera = ?, es_registrado = ? "
                + "WHERE id_persona_comprador = ?";
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
        try {
            ps.setBoolean(1, (comprador.getRegistrado() == 1 ? true : false));
            ps.setInt(2, comprador.getIdPersona());
            ps.setDouble(3, comprador.getMonto());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Comprador createFromResultSet(ResultSet rs) {
        try {
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
            persona.setRegistrado(rs.getBoolean("es_registrado") ? 1 : 0);
            return persona;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Comprador comprador) {
        try {
            ps.setDouble(1, comprador.getMonto());
            ps.setBoolean(2, (comprador.getRegistrado() == 1 ? true : false));
            ps.setInt(3, comprador.getIdPersona());

        } catch (SQLException e) {
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
        int id = -1;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            id = personaDAO.insertar(entity);//Insercion inicial de la persona

            if (entity.getMonto() == 0) {
                entity.setMonto(0);
            }

            try (PreparedStatement pst = conn.prepareStatement(this.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
                this.setInsertParameters(pst, entity);
                pst.executeUpdate();
                conn.commit();
                System.out.println("Se inserto un registro de " + entity.getClass().getSimpleName() + " con ID=" + id);
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query insertado", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar " + entity.getClass().getSimpleName() + " ", e);
        } finally {
            return id;
        }
    }

    /*Sobrecarga necesaria para actualizar tambien a la persona*/
    @Override
    public boolean actualizar(Comprador entity) {
        boolean resultado = false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            resultado = personaDAO.actualizar(entity);//Actualizacion incial de la persona
            try (PreparedStatement ps = conn.prepareStatement(this.getUpdateQuery())) {
                this.setUpdateParameters(ps, entity);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se actualizo un registro de " + entity.getClass().getSimpleName());
                resultado = true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar " + entity.getClass().getSimpleName(), e);
        } finally {
            return resultado;
        }
    }

    /*Sobrecarga necesaria para eliminar tambien a la persona*/
    @Override
    public boolean eliminarLogico(int id) {
        boolean resultado = false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteLogicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino logicamente un registro con ID=" + id);
                resultado = personaDAO.eliminarLogico(id);//Eliminacion de Persona, luego de eliminar Administrador
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado lógico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar logicamente la entidad", e);
        } finally {
            return resultado;
        }
    }

    /*Sobrecarga necesaria para eliminar tambien a la persona*/
    @Override
    public boolean eliminarFisico(int id) {
        boolean resultado = false;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteFisicoQuery())) {
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se elimino fisicamente un registro con ID=" + id);
                resultado = personaDAO.eliminarFisico(id);//Eliminacion de Persona, luego de eliminar  Administrador
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de eliminado físico ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar fisicamente la entidad", e);
        } finally {
            return resultado;
        }
    }

    @Override
    public Comprador buscarPorDni(String dni) {
        Comprador comprador = null;
        String sql
                = "SELECT p.id_persona, p.nombres, p.primer_apellido, p.segundo_apellido, "
                + "       p.num_documento, p.correo, p.usuario, p.contrasenia, p.tipo_documento, c.es_registrado, c.monto_billetera "
                + "FROM   Persona   p "
                + "JOIN   Comprador c ON c.id_persona_comprador = p.id_persona "
                + "WHERE  p.num_documento = ?";

        try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    comprador = new Comprador();
                    comprador.setIdPersona(rs.getInt("id_persona"));
                    comprador.setNombres(rs.getString("nombres"));
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

                    comprador.setRegistrado(rs.getBoolean("es_registrado") ? 1 : 0);
                    comprador.setMonto(rs.getDouble("monto_billetera"));   // ahora sí existe
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por dni", e);
        }
        return comprador;
    }
    
    @Override
    public List<String> listarPorDistritoFavorito(int idDistrito){
        List<String>listaCompradores=null;
        String sql = "SELECT P.correo FROM Comprador C JOIN Persona P ON C.id_persona_comprador=P.id_persona "
                + "WHERE id_distrito_favorito="+idDistrito;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            listaCompradores=new ArrayList<>();
            while (rs.next()) {
                listaCompradores.add(rs.getString("correo"));
            }
            System.out.println("Se listo los correos de los compradores por distrito favorito");
        }catch(Exception ex){
            throw new RuntimeException("Error al listar los correos de los compradores por distrito favorito: "+ ex.getMessage());
        }finally{
            return listaCompradores;
        }
    }
    
    //Metodos para el Perfil del Comprador
    @Override
    public Map<String, Object> buscarDetalleCompradorPorId(int idComprador) {
        Map<String, Object> detalleComprador = null;
        String sql = """
                     SELECT 
                         C.id_persona_comprador,
                         P.tipo_documento,
                         P.num_documento,
                         C.monto_billetera,
                         P.nombres,
                         P.primer_apellido,
                         P.segundo_apellido,
                         D.nombre AS distrito_favorito,
                         PR.nombre AS provincia_favorita,
                         DEP.nombre AS departamento_favorito,
                         P.correo,
                         P.contrasenia
                     FROM 
                         Comprador C
                     INNER JOIN 
                         Persona P ON C.id_persona_comprador = P.id_persona
                     LEFT JOIN 
                         Distrito D ON C.id_distrito_favorito = D.id_distrito
                     LEFT JOIN 
                         Provincia PR ON D.Provincia_id_provincia = PR.id_provincia
                     LEFT JOIN 
                         Departamento DEP ON PR.Departamento_id_departamento = DEP.id_departamento
                     WHERE 
                         P.activo = 'A' AND C.id_persona_comprador = 
                 """ + idComprador;
        try (Connection conn = DBManager.getInstance().getConnection(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                detalleComprador = new HashMap<>();
                detalleComprador.put("idComprador", rs.getInt("id_persona_comprador"));
                detalleComprador.put("tipoDocumento", rs.getString("tipo_documento"));
                detalleComprador.put("numeroDocumento", rs.getString("num_documento"));
                detalleComprador.put("montoBilletera", rs.getDouble("monto_billetera"));
                detalleComprador.put("nombres", rs.getString("nombres"));
                detalleComprador.put("primerApellido", rs.getString("primer_apellido"));
                detalleComprador.put("segundoApellido", rs.getString("segundo_apellido"));
                detalleComprador.put("distritoFavorito", rs.getString("distrito_favorito"));
                detalleComprador.put("provinciaFavorita", rs.getString("provincia_favorita"));
                detalleComprador.put("departamentoFavorito", rs.getString("departamento_favorito"));
                detalleComprador.put("correo", rs.getString("correo"));
                detalleComprador.put("contrasenia", rs.getString("contrasenia"));
                System.out.println("Se busco el detalle del comprador correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el detalle del comprador: ", e);
        } finally {
            return detalleComprador;
        }
    }

    @Override
    public boolean actualizarDistritoFavoritoPorIdComprador(String nuevoDistrito, int idComprador) {
        boolean resultado = false;
        String sql = "UPDATE Comprador C JOIN (SELECT id_distrito FROM Distrito WHERE nombre COLLATE "
                + "utf8mb4_general_ci LIKE '%"+nuevoDistrito+"%' LIMIT 1) D ON 1=1 SET C.id_distrito_favorito = D.id_distrito "
                + "WHERE C.id_persona_comprador = " + idComprador;
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
                conn.commit();
                System.out.println("Se actualizo el distrito favorito de un comprador");
                resultado = true;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el query de actualizado: ", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el distrito favorito de un comprador: ", e);
        } finally {
            return resultado;
        }
    }
    
    @Override
    public Date obtenerUltimaCompraPorDocumento(String numeroDocumento) {
        String sql = "{CALL ObtenerUltimaCompraPorDocumento(?, ?)}";
        Date fechaUltimaCompra = null;

        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setString(1, numeroDocumento);
            stmt.registerOutParameter(2, java.sql.Types.DATE);

            stmt.execute();
            fechaUltimaCompra = stmt.getDate(2);

            System.out.println("Última compra obtenida correctamente para documento: " + numeroDocumento);

        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener la última compra por documento", ex);
        }

        return fechaUltimaCompra;
    }
    
    @Override
    public List<Map<String, Object>> listarCompradoresDTO() {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = """
            SELECT P.id_persona, P.nombres, P.primer_apellido, P.segundo_apellido,
                   P.tipo_documento, P.num_documento, P.correo,
                   (
                       SELECT MAX(C.fecha)
                       FROM Constancia C
                       WHERE C.detalle_pago LIKE CONCAT('%', P.num_documento, '%')
                   ) AS ultima_compra
            FROM Persona P
            JOIN Comprador C ON P.id_persona = C.id_persona_comprador
            WHERE P.activo = 'A'
        """;

        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("id", rs.getInt("id_persona"));
                fila.put("nombres", rs.getString("nombres"));
                fila.put("primerApellido", rs.getString("primer_apellido"));
                fila.put("segundoApellido", rs.getString("segundo_apellido"));
                fila.put("tipoDocumento", rs.getString("tipo_documento"));
                fila.put("numDocumento", rs.getString("num_documento"));
                fila.put("correo", rs.getString("correo"));
                fila.put("ultima_compra", rs.getDate("ultima_compra")); // <-- aquí capturas la fecha
                lista.add(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar compradores DTO", e);
        }

        return lista;
    }

    @Override
    public boolean validarCorreo(String correo) {
        boolean devolver=true;
        try (Connection conn = DBManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try(CallableStatement pst=conn.prepareCall("{Call validar_correo_comprador(?,?)}")){
                pst.setString(1, correo);
//                pst.setBoolean(2, devolver);
                
                pst.registerOutParameter(2, Types.BOOLEAN);
                pst.execute();
                
                devolver=pst.getBoolean(2);
            }catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al ejecutar el stored procedure", e);
            }finally {
                conn.setAutoCommit(true);
            }
        }catch(SQLException e) {
            throw new RuntimeException("Error al ejectuar");
        }finally{
            return devolver;
        }
    }
}
