    package datos;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost/umg2?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "usuprueba";
    private static final String JDBC_PASS = "123456";
   
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
}
/*
  package datos;

import java.sql.*;
 
public class Conexion {
    // *indica que se dirije a la local host de la base de datos instalada  ip local 
    //*SSL usa una capa de seguridad que permite la encriptacion secure socket libery 
    private static final String JDBC_URL = "jdbc:mysql://localhost/umg2?useSSL=false&serverTimezone=UTC";
    //Crea un usuario 
    private static final String JDBC_USER = "usuprueba";
    private static final String JDBC_PASS = "123456";
   
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
}*/