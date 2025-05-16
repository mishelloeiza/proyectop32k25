/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bancos;

import Modelo.seguridad.*;
import Controlador.bancos.cuentas_bancarias;
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author visitante
 */
public class cuentas_bancariasDAO {

    private static final String SQL_SELECT = "SELECT id_cuenta, id_banco, id_tipo_cuenta, id_tipo_moneda, saldo FROM cuentas_bancarias";
    private static final String SQL_INSERT = "INSERT INTO cuentas_bancarias(id_banco, id_tipo_cuenta, id_tipo_moneda, saldo) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE cuentas_bancarias SET id_banco=?, id_tipo_cuenta=?, id_tipo_moneda=?, saldo=? WHERE id_cuenta=?";
    private static final String SQL_DELETE = "DELETE FROM cuentas_bancarias WHERE id_cuenta=?";
    private static final String SQL_QUERY  = "SELECT id_cuenta, id_banco, id_tipo_cuenta, id_tipo_moneda, saldo FROM cuentas_bancarias WHERE id_cuenta=?";

    public List<cuentas_bancarias> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cuentas_bancarias cuenta = null;
        List<cuentas_bancarias> cuentas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cuenta = new cuentas_bancarias();
                cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                cuenta.setId_banco(rs.getInt("id_banco"));
                cuenta.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                cuenta.setId_tipo_moneda(rs.getInt("id_tipo_moneda"));
                cuenta.setSaldo(rs.getFloat("saldo"));
                cuentas.add(cuenta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cuentas;
    }

    public int insert(cuentas_bancarias cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cuenta.getId_banco());
            stmt.setInt(2, cuenta.getId_tipo_cuenta());
            stmt.setInt(3, cuenta.getId_tipo_moneda());
            stmt.setFloat(4, cuenta.getSaldo());

            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros insertados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(cuentas_bancarias cuenta) {
        System.out.print("primera linea ");
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        System.out.print("segunda linea ");
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, cuenta.getId_banco());
            stmt.setInt(2, cuenta.getId_tipo_cuenta());
            stmt.setInt(3, cuenta.getId_tipo_moneda());
            stmt.setFloat(4, cuenta.getSaldo());
            stmt.setInt(5, cuenta.getId_cuenta());
            
            System.out.print("tercera linea ");
            
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(cuentas_bancarias cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cuenta.getId_cuenta());

            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public cuentas_bancarias query(cuentas_bancarias cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, cuenta.getId_cuenta());
            rs = stmt.executeQuery();

            while (rs.next()) {
                cuenta = new cuentas_bancarias();
                cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                cuenta.setId_banco(rs.getInt("id_banco"));
                cuenta.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                cuenta.setId_tipo_moneda(rs.getInt("id_tipo_moneda"));
                cuenta.setSaldo(rs.getFloat("saldo"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cuenta;
    }
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reporte/banco/"+ "ReporteDetalleMovimientosBancarios.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Detalle de movimientos bancarios");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}