/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bancos;

import Modelo.seguridad.*;
import Controlador.bancos.tipo_pago;
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
public class tipo_pagoDAO {

    private static final String SQL_SELECT = "SELECT id_tipo_pago, tipo_pago, status FROM tipo_pago";
    private static final String SQL_INSERT = "INSERT INTO tipo_pago(tipo_pago, status) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE tipo_pago SET  tipo_pago=?, status=? WHERE id_tipo_pago = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_pago WHERE id_tipo_pago=?";
    private static final String SQL_QUERY = "SELECT id_tipo_pago, tipo_pago, status FROM tipo_pago WHERE id_tipo_pago = ?";

    public List<tipo_pago> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_pago tipo_pago = null;
        List<tipo_pago> list_tipo_pagos = new ArrayList<tipo_pago>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPago = rs.getInt("id_tipo_pago");
                String tipoPago = rs.getString("tipo_pago");
                String Status = rs.getString("status");
                
                tipo_pago = new tipo_pago();
                tipo_pago.setIdTipoPago(idTipoPago);
                tipo_pago.setTipoPago(tipoPago);
                tipo_pago.setStatus(Status);
              
                
                list_tipo_pagos.add(tipo_pago);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_pagos;
    }

    public int insert(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipo_pago.getTipoPago());
            stmt.setString(2, tipo_pago.getStatus());
         


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

    public int update(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipo_pago.getTipoPago());
            stmt.setString(2, tipo_pago.getStatus());
            stmt.setInt(3,tipo_pago.getIdTipoPago());
           

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

    public int delete(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipo_pago.getIdTipoPago());
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
    public tipo_pago query(tipo_pago tipo_pago) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tipo_pago> list_tipo_pago = new ArrayList<tipo_pago>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipo_pago.getIdTipoPago());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoPago = rs.getInt("id_tipo_pago");
                String tipoPago = rs.getString("tipo_pago");
                String Status = rs.getString("status");
                
                tipo_pago = new tipo_pago();
                tipo_pago.setIdTipoPago(idTipoPago);
                tipo_pago.setTipoPago(tipoPago);
                tipo_pago.setStatus(Status);
              
                
               
                
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
        return tipo_pago;
    }
            
}