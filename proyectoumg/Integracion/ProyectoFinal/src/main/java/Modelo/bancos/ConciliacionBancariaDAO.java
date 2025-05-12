package Modelo.bancos;
// Cambios clave: Date -> Timestamp y LocalDate -> LocalDateTime
import Controlador.bancos.conciliacion_bancaria;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConciliacionBancariaDAO {

    private static final String SQL_SELECT = "SELECT id_conciliacion, id_tipo_cuenta, fecha, status FROM conciliacion_bancaria";
    private static final String SQL_INSERT = "INSERT INTO conciliacion_bancaria(id_tipo_cuenta, fecha, status) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE conciliacion_bancaria SET id_tipo_cuenta=?, fecha=?, status=? WHERE id_conciliacion=?";
    private static final String SQL_DELETE = "DELETE FROM conciliacion_bancaria WHERE id_conciliacion=?";
    private static final String SQL_QUERY  = "SELECT id_conciliacion, id_tipo_cuenta, fecha, status FROM conciliacion_bancaria WHERE id_conciliacion=?";

    private static final Logger logger = Logger.getLogger(ConciliacionBancariaDAO.class.getName());

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
                conciliacion_bancaria conciliacion = new conciliacion_bancaria();
                conciliacion.setId_conciliacion(rs.getInt("id_conciliacion"));
                conciliacion.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                conciliacion.setFecha(timestamp != null ? timestamp.toLocalDateTime() : null);
                conciliacion.setStatus(rs.getString("status"));
                conciliaciones.add(conciliacion);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener conciliaciones: ", ex);
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
            stmt.setInt(1, conciliacion.getId_tipo_cuenta());
            stmt.setTimestamp(2, conciliacion.getFecha() != null ? Timestamp.valueOf(conciliacion.getFecha()) : null);
            stmt.setString(3, conciliacion.getStatus());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al insertar conciliación: ", ex);
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
            stmt.setInt(1, conciliacion.getId_tipo_cuenta());
            stmt.setTimestamp(2, conciliacion.getFecha() != null ? Timestamp.valueOf(conciliacion.getFecha()) : null);
            stmt.setString(3, conciliacion.getStatus());
            stmt.setInt(4, conciliacion.getId_conciliacion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al actualizar conciliación: ", ex);
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
            logger.log(Level.SEVERE, "Error al eliminar conciliación: ", ex);
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
                conciliacion.setId_conciliacion(rs.getInt("id_conciliacion"));
                conciliacion.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                conciliacion.setFecha(timestamp != null ? timestamp.toLocalDateTime() : null);
                conciliacion.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al consultar conciliación: ", ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return conciliacion;
    }
}
