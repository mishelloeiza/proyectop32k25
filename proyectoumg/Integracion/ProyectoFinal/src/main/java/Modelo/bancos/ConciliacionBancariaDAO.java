package Modelo.bancos;

import Controlador.bancos.conciliacion_bancaria;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//modificado por Anderson Rodriguez
public class ConciliacionBancariaDAO {

    private static final String SQL_SELECT = "SELECT id_conciliacion, id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status FROM conciliacion_bancaria";
    private static final String SQL_INSERT = "INSERT INTO conciliacion_bancaria(id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE conciliacion_bancaria SET id_cuenta=?, id_movimiento_bancario=?, fecha=?, saldo=?, saldo_actualizado=?, status=? WHERE id_conciliacion = ?";
    private static final String SQL_DELETE = "DELETE FROM conciliacion_bancaria WHERE id_conciliacion=?";
    private static final String SQL_QUERY = "SELECT id_conciliacion, id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status FROM conciliacion_bancaria WHERE id_conciliacion = ?";

    public List<conciliacion_bancaria> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<conciliacion_bancaria> conciliaciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_conciliacion");
                int idCuenta = rs.getInt("id_cuenta");
                int idMovimiento = rs.getInt("id_movimiento_bancario");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                float saldo = rs.getFloat("saldo");
                float saldoActualizado = rs.getFloat("saldo_actualizado");
                String status = rs.getString("status");

                conciliacion_bancaria conciliacion = new conciliacion_bancaria();
                conciliacion.setId_conciliacion(id);
                conciliacion.setId_cuenta(idCuenta);
                conciliacion.setId_movimiento_bancario(idMovimiento);
                conciliacion.setFecha(fecha);
                conciliacion.setSaldo(saldo);
                conciliacion.setSaldo_actualizado(saldoActualizado);
                conciliacion.setStatus(status);

                conciliaciones.add(conciliacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return conciliaciones;
    }

    public int insert(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, conciliacion.getId_cuenta());
            stmt.setInt(2, conciliacion.getId_movimiento_bancario());
            stmt.setTimestamp(3, Timestamp.valueOf(conciliacion.getFecha()));
            stmt.setFloat(4, conciliacion.getSaldo());
            stmt.setFloat(5, conciliacion.getSaldo_actualizado());
            stmt.setString(6, conciliacion.getStatus());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, conciliacion.getId_cuenta());
            stmt.setInt(2, conciliacion.getId_movimiento_bancario());
            stmt.setTimestamp(3, Timestamp.valueOf(conciliacion.getFecha()));
            stmt.setFloat(4, conciliacion.getSaldo());
            stmt.setFloat(5, conciliacion.getSaldo_actualizado());
            stmt.setString(6, conciliacion.getStatus());
            stmt.setInt(7, conciliacion.getId_conciliacion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, conciliacion.getId_conciliacion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public conciliacion_bancaria query(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, conciliacion.getId_conciliacion());
            rs = stmt.executeQuery();
            if (rs.next()) {
                conciliacion = new conciliacion_bancaria();
                conciliacion.setId_conciliacion(rs.getInt("id_conciliacion"));
                conciliacion.setId_cuenta(rs.getInt("id_cuenta"));
                conciliacion.setId_movimiento_bancario(rs.getInt("id_movimiento_bancario"));
                conciliacion.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                conciliacion.setSaldo(rs.getFloat("saldo"));
                conciliacion.setSaldo_actualizado(rs.getFloat("saldo_actualizado"));
                conciliacion.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return conciliacion;
    }
}

