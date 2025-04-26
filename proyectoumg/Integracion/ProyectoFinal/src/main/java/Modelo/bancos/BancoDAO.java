/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bancos;

import Modelo.bancos.*;
import Controlador.bancos.bancos;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {

    private static final String SQL_SELECT = "SELECT id_banco, nombre FROM bancos";
    private static final String SQL_INSERT = "INSERT INTO bancos(nombre) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE bancos SET nombre = ? WHERE id_banco = ?";
    private static final String SQL_DELETE = "DELETE FROM bancos WHERE id_banco = ?";
    private static final String SQL_QUERY = "SELECT id_banco, nombre FROM bancos WHERE id_banco = ?";

    public List<bancos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        bancos banco = null;
        List<bancos> list_bancos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_banco = rs.getInt("id_banco");
                String nombre = rs.getString("nombre");
                
                banco = new bancos();
                banco.setId_banco(id_banco);
                banco.setNombre(nombre);
                
                list_bancos.add(banco);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_bancos;
    }

    public int insert(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, banco.getNombre());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, banco.getNombre());
            stmt.setInt(2, banco.getId_banco());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, banco.getId_banco());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public bancos query(bancos banco) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, banco.getId_banco());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id_banco = rs.getInt("id_banco");
                String nombre = rs.getString("nombre");
                
                banco.setId_banco(id_banco);
                banco.setNombre(nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return banco;
    }
}
