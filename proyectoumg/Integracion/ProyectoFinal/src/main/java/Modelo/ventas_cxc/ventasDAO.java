/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ventas_cxc;

import Modelo.seguridad.*;
import Controlador.ventas_cxc.ventas;
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
public class ventasDAO {

    private static final String SQL_SELECT = "SELECT  no_venta,id_cliente, id_producto,cantidad,precio_unitario,total  FROM ventas";
    private static final String SQL_INSERT = "INSERT INTO ventas(id_cliente,no_venta,id_producto, cantidad,precio_unitario,total) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE ventas SET  no_venta=?,id_cliente=?, id_producto=?,cantidad=?,precio_unitario=?,total=? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM ventas WHERE id_cliente =?";
    private static final String SQL_QUERY = "SELECT no_venta,id_cliente, id_producto,cantidad,precio_unitario,total FROM ventas WHERE id_venta = ?";

    public List<ventas> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ventas aplicacion = null;
        List<ventas> list_aplicaciones = new ArrayList<ventas>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String no_venta = rs.getString("no_venta");
                int id_producto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                Double precio_unitario=rs.getDouble("precio_unitario");
                Double total=rs.getDouble("total");
                
                aplicacion = new ventas();
                aplicacion.setId_cliente(id_cliente);
                aplicacion.setNo_venta(no_venta);
                aplicacion.setId_producto(id_producto);
                aplicacion.setCantidad(cantidad);
                aplicacion.setPrecio_unitario(precio_unitario);
                aplicacion.setTotal(total);
              
                
                list_aplicaciones.add(aplicacion);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_aplicaciones;
    }

    public int insert(ventas aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getId_cliente());
            stmt.setString(2, aplicacion.getNo_venta());
            stmt.setInt(3, aplicacion.getId_producto());
            stmt.setInt(4, aplicacion.getCantidad());
            stmt.setDouble(5, aplicacion.getPrecio_unitario());
             stmt.setDouble(6, aplicacion.getTotal());
            
         


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

    public int update(ventas aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
             stmt.setInt(1, aplicacion.getId_cliente());
            stmt.setString(2, aplicacion.getNo_venta());
            stmt.setInt(3, aplicacion.getId_producto());
            stmt.setInt(4, aplicacion.getCantidad());
            stmt.setDouble(5, aplicacion.getPrecio_unitario());
             stmt.setDouble(6, aplicacion.getTotal());
            //comodin del where
            stmt.setInt(7,aplicacion.getId_cliente());
           

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

    public int delete(ventas aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getId_cliente());
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
    public ventas query(ventas aplicacion) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ventas> list_aplicacion = new ArrayList<ventas>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, aplicacion.getId_cliente());
            rs = stmt.executeQuery();
            while (rs.next()) {
             int id_cliente = rs.getInt("id_cliente");
                String no_venta = rs.getString("no_venta");
                int id_producto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                Double precio_unitario=rs.getDouble("precio_unitario");
                Double total=rs.getDouble("total");
                
                aplicacion = new ventas();
                aplicacion.setId_cliente(id_cliente);
                aplicacion.setNo_venta(no_venta);
                aplicacion.setId_producto(id_producto);
                aplicacion.setCantidad(cantidad);
                aplicacion.setPrecio_unitario(precio_unitario);
                aplicacion.setTotal(total);
              
                
               
                
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
        return aplicacion;
    }
            
}