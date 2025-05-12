package Modelo.bancos;
// Made By Ruddyard Castro 9959-23-1409
import Controlador.bancos.movimiento_bancario;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimientoBancarioDAO {
    private static final String SQL_SELECT = "SELECT id_movimiento_bancario, id_tipo_cuenta, fecha FROM movimientos_bancarios";
    private static final String SQL_INSERT = "INSERT INTO movimientos_bancarios(id_tipo_cuenta, fecha) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE movimientos_bancarios SET id_tipo_cuenta=?, fecha=? WHERE id_movimiento_bancario = ?";
    private static final String SQL_DELETE = "DELETE FROM movimientos_bancarios WHERE id_movimiento_bancario=?";
    private static final String SQL_QUERY = "SELECT id_movimiento_bancario, id_tipo_cuenta, fecha FROM movimientos_bancarios WHERE id_movimiento_bancario = ?";

    public List<movimiento_bancario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<movimiento_bancario> movimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

                movimiento_bancario movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_tipo_cuenta(idTipoCuenta);
                movimiento.setFecha(fecha);

                movimientos.add(movimiento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimientos;
    }

    public int insert(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, movimiento.getId_tipo_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, movimiento.getId_tipo_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setInt(3, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public movimiento_bancario query(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

                movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_tipo_cuenta(idTipoCuenta);
                movimiento.setFecha(fecha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimiento;
    }
}