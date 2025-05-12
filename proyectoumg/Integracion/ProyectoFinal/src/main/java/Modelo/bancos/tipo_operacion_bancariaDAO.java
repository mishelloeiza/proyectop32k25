/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bancos;

import Modelo.seguridad.*;
import Controlador.bancos.tipo_operacion_bancaria;
import Modelo.Conexion;
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
public class tipo_operacion_bancariaDAO {

    private static final String SQL_SELECT = "SELECT id_tipo_operacion, tipo_operacion, descripcion FROM tipo_operacion_bancaria";
    private static final String SQL_INSERT = "INSERT INTO tipo_operacion_bancaria(tipo_operacion, descripcion) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE tipo_operacion_bancaria SET  tipo_operacion=?, descripcion=? WHERE id_tipo_operacion = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_operacion_bancaria WHERE id_tipo_operacion=?";
    private static final String SQL_QUERY = "SELECT id_tipo_operacion, tipo_operacion, descripcion FROM tipo_operacion_bancaria WHERE id_tipo_operacion = ?";

    public List<tipo_operacion_bancaria> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_operacion_bancaria tipo_operacion_bancaria = null;
        List<tipo_operacion_bancaria> list_tipo_operaciones_bancarias = new ArrayList<tipo_operacion_bancaria>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tipo_operacion = rs.getInt("id_tipo_operacion");
                String tipo_operacion = rs.getString("tipo_operacion");
                String descripcion = rs.getString("descripcion");
                
                tipo_operacion_bancaria = new tipo_operacion_bancaria();
                tipo_operacion_bancaria.setId_tipo_operacion(id_tipo_operacion);
                tipo_operacion_bancaria.setTipo_operacion(tipo_operacion);
                tipo_operacion_bancaria.setDescripcion(descripcion);
              
                
                list_tipo_operaciones_bancarias.add(tipo_operacion_bancaria);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_operaciones_bancarias;
    }

    public int insert(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipo_operacion_bancaria.getTipo_operacion());
            stmt.setString(2, tipo_operacion_bancaria.getDescripcion());
         


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

    public int update(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipo_operacion_bancaria.getTipo_operacion());
            stmt.setString(2, tipo_operacion_bancaria.getDescripcion());
            stmt.setInt(3,tipo_operacion_bancaria.getId_tipo_operacion());
           

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

    public int delete(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipo_operacion_bancaria.getId_tipo_operacion());
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
    public tipo_operacion_bancaria query(tipo_operacion_bancaria tipo_operacion_bancaria) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tipo_operacion_bancaria> list_tipo_operacion_bancaria = new ArrayList<tipo_operacion_bancaria>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipo_operacion_bancaria.getId_tipo_operacion());
            rs = stmt.executeQuery();
            while (rs.next()) {
             int id_tipo_operacion = rs.getInt("id_tipo_operacion");
                String tipo_operacion = rs.getString("tipo_operacion");
                String descripcion = rs.getString("descripcion");
                
                tipo_operacion_bancaria = new tipo_operacion_bancaria();
                tipo_operacion_bancaria.setId_tipo_operacion(id_tipo_operacion);
                tipo_operacion_bancaria.setTipo_operacion(tipo_operacion);
                tipo_operacion_bancaria.setDescripcion(descripcion);
              
                
               
                
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
        return tipo_operacion_bancaria;
    }
            
}