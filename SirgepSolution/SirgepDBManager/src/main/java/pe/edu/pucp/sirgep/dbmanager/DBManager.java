package pe.edu.pucp.sirgep.dbmanager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eric
 */
public class DBManager {
    private static DBManager dbManager;
    
    private String host;
    private int puerto;
    private String esquema;
    private String usuario;
    private String password;
    
    private DBManager() throws IOException {
        cargarProperties();
    }
    
    public synchronized static DBManager getInstance()  {
        if (dbManager == null) {
            createInstance();
        }
        return dbManager;
    }
    
    private static void createInstance() {
        try {
            dbManager = new DBManager();
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection()  {
        try {
            /* 
            Por ahora creamos una conexion cada vez que se necesita acceder a la base de datos, 
            por ser una aplicacion academica es una practica aceptable, en un sistema productivo
            se debe usar un pool de conexiones.
            */
            Class.forName("com.mysql.cj.jdbc.Driver");
            String cadenaConexion = cadenaConexion(host, puerto, esquema);
            return DriverManager.getConnection(cadenaConexion, usuario, password);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    private void cargarProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.err.println("No se pudo abrir el archivo db.properties");
                return;
            }
            
            properties.load(input);
            
            host = properties.getProperty("db.host");
            puerto = Integer.parseInt(properties.getProperty("db.puerto"));
            esquema = properties.getProperty("db.esquema");
            usuario = properties.getProperty("db.usuario");
            password = properties.getProperty("db.password");
        }
        catch (IOException e) {
            System.err.println("No se pudo cargar el archivo db.properties");
            throw e;
        }
    }
    
    private String cadenaConexion(String host, int puerto, String esquema) {
        return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true", host, puerto, esquema);
    }
}
