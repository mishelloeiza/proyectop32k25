/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Facultad;
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
public class FacultadDAO {

    private static final String SQL_SELECT = "SELECT codigo_facultad, nombre_facultad, estatus_facultad FROM facultades";
    private static final String SQL_INSERT = "INSERT INTO facultades(nombre_facultad, estatus_facultad) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE facultades SET nombre_facultad=?, estatus_facultad=? WHERE codigo_facultad = ?";
    private static final String SQL_DELETE = "DELETE FROM facultades WHERE codigo_facultad=?";
    private static final String SQL_QUERY = "SELECT codigo_facultad, nombre_facultad, estatus_facultad FROM facultades WHERE codigo_facultad = ?";

    public List<Facultad> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Facultad facultad = null;
        List<Facultad> facultades = new ArrayList<Facultad>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoFacultad = rs.getInt("codigo_facultad");
                String nombreFacultad = rs.getString("nombre_facultad");
                String estatusFacultad = rs.getString("estatus_facultad");
                
                facultad = new Facultad();
                facultad.setCodigoFacultad(codigoFacultad);
                facultad.setNombreFacultad(nombreFacultad);
                facultad.setEstatusFacultad(estatusFacultad);
                
                facultades.add(facultad);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return facultades;
    }

    public int insert(Facultad facultades) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, facultades.getNombreFacultad());
            stmt.setString(2, facultades.getEstatusFacultad());

            
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

    public int update(Facultad facultad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, facultad.getNombreFacultad());
            stmt.setString(2, facultad.getEstatusFacultad());
            stmt.setInt(3, facultad.getCodigoFacultad());
            
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

    public int delete(Facultad facultad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, facultad.getCodigoFacultad());
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
    public Facultad query(Facultad facultad) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Facultad> facultades = new ArrayList<Facultad>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, facultad.getCodigoFacultad());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoFacultad = rs.getInt("codigo_facultad");
                String nombreFacultad = rs.getString("nombre_facultad");
                String estatusFacultad = rs.getString("estatus_facultad");
                
                facultad = new Facultad();
                facultad.setCodigoFacultad(codigoFacultad);
                facultad.setNombreFacultad(nombreFacultad);
                facultad.setEstatusFacultad(estatusFacultad);
                
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
        return facultad;
    }
        
}