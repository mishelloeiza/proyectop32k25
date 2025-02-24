/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Maestro;
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
public class MaestroDAO {

    private static final String SQL_SELECT = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maestro, email_maestro, estatus_maestro FROM maestros";
    private static final String SQL_INSERT = "INSERT INTO maestros(nombre_maestro, direccion_maestro, telefono_maestro, email_maestro, estatus_maestro) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE maestros SET nombre_maestro=?, direccion_maestro=?, telefono_maestro=?, email_maestro=?, estatus_maestro=? WHERE codigo_maestro = ?";
    private static final String SQL_DELETE = "DELETE FROM maestros WHERE codigo_maestro =?";
    private static final String SQL_QUERY = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maestro, email_maestro, estatus_maestro FROM maestros WHERE codigo_maestro = ?";

    public List<Maestro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Maestro maestro = null;
        List<Maestro> maestros = new ArrayList<Maestro>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoMaestro = rs.getInt("codigo_maestro");
                String nombreMaestro = rs.getString("nombre_maestro");
                String direccionMaestro = rs.getString("direccion_maestro");
                String telefonoMaestro = rs.getString("telefono_maestro");
                String emailMaestro = rs.getString("email_maestro");
                String estatusMaestro = rs.getString("estatus_maestro");
                
                maestro = new Maestro();
                maestro.setCodigoMaestro(codigoMaestro);
                maestro.setNombreMaestro(nombreMaestro);
                maestro.setDireccionMaestro(direccionMaestro);
                maestro.setTelefonoMaestro(telefonoMaestro);
                maestro.setEmailMaestro(emailMaestro);
                maestro.setEstatusMaestro(estatusMaestro);
                
                maestros.add(maestro);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return maestros;
    }

    public int insert(Maestro maestros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, maestros.getNombreMaestro());
            stmt.setString(2, maestros.getDireccionMaestro());
            stmt.setString(3, maestros.getTelefonoMaestro());
            stmt.setString(4, maestros.getEmailMaestro());
            stmt.setString(5, maestros.getEstatusMaestro());

            
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

    public int update(Maestro maestro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, maestro.getNombreMaestro());
            stmt.setString(2, maestro.getDireccionMaestro());
            stmt.setString(3, maestro.getTelefonoMaestro());
            stmt.setString(4, maestro.getEmailMaestro());
            stmt.setString(5, maestro.getEstatusMaestro());
            stmt.setInt(6, maestro.getCodigoMaestro());
            
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

    public int delete(Maestro maestro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, maestro.getCodigoMaestro());
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
    public Maestro query(Maestro maestro) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Maestro> maestros = new ArrayList<Maestro>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, maestro.getCodigoMaestro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoMaestro = rs.getInt("codigo_maestro");
                String nombreMaestro = rs.getString("nombre_maestro");
                String direccionMaestro = rs.getString("direccion_maestro");
                String telefonoMaestro = rs.getString("telefono_maestro");
                String emailMaestro = rs.getString("email_maestro");
                String estatusMaestro = rs.getString("estatus_maestro");
                
                maestro = new Maestro();
                maestro.setCodigoMaestro(codigoMaestro);
                maestro.setNombreMaestro(nombreMaestro);
                maestro.setDireccionMaestro(direccionMaestro);
                maestro.setTelefonoMaestro(telefonoMaestro);
                maestro.setEstatusMaestro(estatusMaestro);
                
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
        return maestro;
    }
        
}
