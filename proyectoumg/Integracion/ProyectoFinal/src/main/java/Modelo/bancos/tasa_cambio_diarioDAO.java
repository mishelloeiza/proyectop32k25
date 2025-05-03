package Modelo.bancos;
// Cambio por Ruddyard Eduardo Castro Chavez 9959-23-1409
import Controlador.bancos.tasa_cambio_diario;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class tasa_cambio_diarioDAO {

    private static final String SQL_SELECT = "SELECT id_tasa_cambio_diario, valor_promedio_dia, fecha_hora FROM tasas_cambio_diario";
    private static final String SQL_INSERT = "INSERT INTO tasas_cambio_diario(valor_promedio_dia, fecha_hora) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tasas_cambio_diario SET valor_promedio_dia=?, fecha_hora=? WHERE id_tasa_cambio_diario = ?";
    private static final String SQL_DELETE = "DELETE FROM tasas_cambio_diario WHERE id_tasa_cambio_diario=?";
    private static final String SQL_QUERY = "SELECT id_tasa_cambio_diario, valor_promedio_dia, fecha_hora FROM tasas_cambio_diario WHERE id_tasa_cambio_diario = ?";
    //private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tasa_cambio_diario WHERE fecha_hora = ?";

    public List<tasa_cambio_diario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tasa_cambio_diario> tasas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_tasa_cambio_diario");
                float valor = rs.getFloat("valor_promedio_dia");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                tasa_cambio_diario tasa = new tasa_cambio_diario();
                tasa.setId_tasa_cambio_diario(id);
                tasa.setValor_promedio_dia(valor);
                tasa.setFecha_hora(fechaHora);

                tasas.add(tasa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tasas;
    }

    public int insert(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setFloat(1, tasa.getValor_promedio_dia());
            stmt.setTimestamp(2, Timestamp.valueOf(tasa.getFecha_hora()));
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setFloat(1, tasa.getValor_promedio_dia());
            stmt.setTimestamp(2, Timestamp.valueOf(tasa.getFecha_hora()));
            stmt.setInt(3, tasa.getId_tasa_cambio_diario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tasa.getId_tasa_cambio_diario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public tasa_cambio_diario query(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tasa.getId_tasa_cambio_diario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_tasa_cambio_diario");
                float valor = rs.getFloat("valor_promedio_dia");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                tasa = new tasa_cambio_diario();
                tasa.setId_tasa_cambio_diario(id);
                tasa.setValor_promedio_dia(valor);
                tasa.setFecha_hora(fechaHora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tasa;
    }
/*
    public boolean existeFechaHora(LocalDateTime fechaHora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaHora));
            rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }*/
}