/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Alumno;
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
public class AlumnoDAO {

    private static final String SQL_SELECT = "SELECT carnet_alumno, nombre_alumno, direccion_alumno, telefono_alumno, email_alumno, estatus_alumno FROM alumnos";
    private static final String SQL_INSERT = "INSERT INTO alumnos(nombre_alumno, direccion_alumno, telefono_alumno, email_alumno, estatus_alumno) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE alumnos SET nombre_alumno=?, direccion_alumno=?, telefono_alumno=?, email_alumno=?, estatus_alumno=? WHERE carnet_alumno = ?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet_alumno=?";
    private static final String SQL_QUERY = "SELECT carnet_alumno, nombre_alumno, direccion_alumno, telefono_alumno, email_alumno, estatus_alumno FROM alumnos WHERE carnet_alumno = ?";

    public List<Alumno> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alumno alumno = null;
        List<Alumno> alumnos = new ArrayList<Alumno>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int carnetAlumno = rs.getInt("carnet_alumno");
                String nombreAlumno = rs.getString("nombre_alumno");
                String direccionAlumno = rs.getString("direccion_alumno");
                String telefonoAlumno = rs.getString("telefono_alumno");
                String emailAlumno = rs.getString("email_alumno");
                String estatusAlumno = rs.getString("estatus_alumno");
                
                alumno = new Alumno();
                alumno.setCarnetAlumno(carnetAlumno);
                alumno.setNombreAlumno(nombreAlumno);
                alumno.setDireccionAlumno(direccionAlumno);
                alumno.setTelefonoAlumno(telefonoAlumno);
                alumno.setEmailAlumno(emailAlumno);
                alumno.setEstatusAlumno(estatusAlumno);
                
                alumnos.add(alumno);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return alumnos;
    }

    public int insert(Alumno alumnos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, alumnos.getNombreAlumno());
            stmt.setString(2, alumnos.getDireccionAlumno());
            stmt.setString(3, alumnos.getTelefonoAlumno());
            stmt.setString(4, alumnos.getEmailAlumno());
            stmt.setString(5, alumnos.getEstatusAlumno());

            
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

    public int update(Alumno alumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, alumno.getNombreAlumno());
            stmt.setString(2, alumno.getDireccionAlumno());
            stmt.setString(3, alumno.getTelefonoAlumno());
            stmt.setString(4, alumno.getEmailAlumno());
            stmt.setString(5, alumno.getEstatusAlumno());
            stmt.setInt(6, alumno.getCarnetAlumno());
            
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

    public int delete(Alumno alumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, alumno.getCarnetAlumno());
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
    public Alumno query(Alumno alumno) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Alumno> alumnos = new ArrayList<Alumno>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, alumno.getCarnetAlumno());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int carnetAlumno = rs.getInt("carnet_alumno");
                String nombreAlumno = rs.getString("nombre_alumno");
                String direccionAlumno = rs.getString("direccion_alumno");
                String telefonoAlumno = rs.getString("telefono_alumno");
                String emailAlumno = rs.getString("email_alumno");
                String estatusAlumno = rs.getString("estatus_alumno");
                
                alumno = new Alumno();
                alumno.setCarnetAlumno(carnetAlumno);
                alumno.setNombreAlumno(nombreAlumno);
                alumno.setDireccionAlumno(direccionAlumno);
                alumno.setTelefonoAlumno(telefonoAlumno);
                alumno.setEmailAlumno(emailAlumno);
                alumno.setEstatusAlumno(estatusAlumno);
                
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
        return alumno;
    }
        
}