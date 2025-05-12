/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.bancos;

import Modelo.seguridad.*;
import Controlador.bancos.detalle_movimientos_bancarios;
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
public class detalle_movimientos_bancariosDAO {

    private static final String SQL_SELECT = "SELECT id_detalle, id_movimiento, id_tipo_operacion, id_tipo_pago, tipo_movimiento, monto FROM detalle_movimientos_bancarios";
    private static final String SQL_INSERT = "INSERT INTO detalle_movimientos_bancarios(id_movimiento, id_tipo_operacion, id_tipo_pago, tipo_movimiento, monto) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE detalle_movimientos_bancarios SET id_movimiento=?, id_tipo_operacion=?, id_tipo_pago=?, tipo_movimiento=?, monto=? WHERE id_detalle=?";
    private static final String SQL_DELETE = "DELETE FROM detalle_movimientos_bancarios WHERE id_detalle=?";
    private static final String SQL_QUERY  = "SELECT id_detalle, id_movimiento, id_tipo_operacion, id_tipo_pago, tipo_movimiento, monto FROM detalle_movimientos_bancarios WHERE id_detalle=?";

    public List<detalle_movimientos_bancarios> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        detalle_movimientos_bancarios detalle = null;
        List<detalle_movimientos_bancarios> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                detalle = new detalle_movimientos_bancarios();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdMovimiento(rs.getInt("id_movimiento"));
                detalle.setIdTipoOperacion(rs.getInt("id_tipo_operacion"));
                detalle.setIdTipoPago(rs.getInt("id_tipo_pago"));
                detalle.setTipoMovimiento(rs.getString("tipo_movimiento"));
                detalle.setMonto(rs.getFloat("monto"));
                detalles.add(detalle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return detalles;
    }

    public int insert(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, detalle.getIdMovimiento());
            stmt.setInt(2, detalle.getIdTipoOperacion());
            stmt.setInt(3, detalle.getIdTipoPago());
            stmt.setString(4, detalle.getTipoMovimiento());
            stmt.setFloat(5, detalle.getMonto());

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

    public int update(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, detalle.getIdMovimiento());
            stmt.setInt(2, detalle.getIdTipoOperacion());
            stmt.setInt(3, detalle.getIdTipoPago());
            stmt.setString(4, detalle.getTipoMovimiento());
            stmt.setFloat(5, detalle.getMonto());
            stmt.setInt(6, detalle.getIdDetalle());

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

    public int delete(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, detalle.getIdDetalle());

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

    public detalle_movimientos_bancarios query(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, detalle.getIdDetalle());
            rs = stmt.executeQuery();

            while (rs.next()) {
                detalle = new detalle_movimientos_bancarios();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdMovimiento(rs.getInt("id_movimiento"));
                detalle.setIdTipoOperacion(rs.getInt("id_tipo_operacion"));
                detalle.setIdTipoPago(rs.getInt("id_tipo_pago"));
                detalle.setTipoMovimiento(rs.getString("tipo_movimiento"));
                detalle.setMonto(rs.getFloat("monto"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return detalle;
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