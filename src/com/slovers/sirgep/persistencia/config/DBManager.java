package com.slovers.sirgep.persistencia.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager instance;
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    private DBManager() throws IOException {
        String pathFile = "com/slovers/sirgep/persistencia/config/config.properties";
        try{
            InputStream input = DBManager.class.getClassLoader().getResourceAsStream(pathFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String linea;
            linea = reader.readLine();
            URL = linea.split("=")[1];
            linea = reader.readLine();
            USER = linea.split("=")[1];
            linea = reader.readLine();
            PASSWORD = linea.split("=")[1]; 
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            System.out.println("Se conecto a la BD correctamente.");
        } catch (ClassNotFoundException e){
            System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public synchronized static DBManager getInstance() throws IOException {
        if(instance==null){
            instance = new DBManager();
        }
        return instance;
    }
    
   public Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL, USER, PASSWORD);
   } 
}
