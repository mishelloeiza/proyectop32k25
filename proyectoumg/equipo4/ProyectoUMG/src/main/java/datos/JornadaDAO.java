/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Jornada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class JornadaDAO {

    private static final String SQL_SELECT = "SELECT codigo_jornada, nombre_jornada, estatus_jornada FROM jornadas";
    private static final String SQL_INSERT = "INSERT INTO jornadas(nombre_jornada, estatus_jornada) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE jornadas SET nombre_jornada=?, estatus_jornada=? WHERE codigo_jornada = ?";
    private static final String SQL_DELETE = "DELETE FROM jornadas WHERE codigo_jornada=?";
    private static final String SQL_QUERY = "SELECT codigo_jornada, nombre_jornada, estatus_jornada FROM jornadas WHERE codigo_jornada = ?";

    public List<Jornada> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jornada jornada = null;
        List<Jornada> jornadas = new ArrayList<Jornada>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoJornada = rs.getInt("codigo_jornada");
                String nombreJornada = rs.getString("nombre_jornada");
                String estatusJornada = rs.getString("estatus_jornada");
                
                jornada = new Jornada();
                jornada.setCodigoJornada(codigoJornada);
                jornada.setNombreJornada(nombreJornada);
                jornada.setEstatusJornada(estatusJornada);
                
                jornadas.add(jornada);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return jornadas;
    }

    public int insert(Jornada jornadas) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, jornadas.getNombreJornada());
            stmt.setString(2, jornadas.getEstatusJornada());

            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Jornada jornada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, jornada.getNombreJornada());
            stmt.setString(2, jornada.getEstatusJornada());
            stmt.setInt(3, jornada.getCodigoJornada());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Jornada jornada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, jornada.getCodigoJornada());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Jornada query(Jornada jornada) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Jornada> jornadas = new ArrayList<Jornada>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, jornada.getCodigoJornada());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoJornada = rs.getInt("codigo_jornada");
                String nombreJornada = rs.getString("nombre_jornada");
                String estatusJornada = rs.getString("estatus_jornada");
                
                jornada = new Jornada();
                jornada.setCodigoJornada(codigoJornada);
                jornada.setNombreJornada(nombreJornada);
                jornada.setEstatusJornada(estatusJornada);
                
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return vendedores;  // Si se utiliza un ArrayList
        return jornada;
    }
        
}