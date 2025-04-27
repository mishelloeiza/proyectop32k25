package Modelo.bancos;
//CREADO POR MISHEL LOEIZA 9959-23-3457
import Modelo.Conexion;
import Controlador.bancos.tipo_moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipo_monedaDAO {

    private static final String SQL_SELECT = "SELECT id_tipo_moneda, tipo_moneda, tasa_cambio_usd FROM tipo_moneda";
    private static final String SQL_INSERT = "INSERT INTO tipo_moneda(tipo_moneda, tasa_cambio_usd) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tipo_moneda SET tipo_moneda=?, tasa_cambio_usd=? WHERE id_tipo_moneda = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_moneda WHERE id_tipo_moneda=?";
    private static final String SQL_QUERY = "SELECT id_tipo_moneda, tipo_moneda, tasa_cambio_usd FROM tipo_moneda WHERE id_tipo_moneda = ?";
    private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tipo_moneda WHERE tipo_moneda = ?"; // Consulta para verificar existencia

    public List<tipo_moneda> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_moneda tipo_moneda = null;
        List<tipo_moneda> list_tipo_monedas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tipo_moneda = rs.getInt("id_tipo_moneda");
                String tipoMoneda = rs.getString("tipo_moneda");
                double tasaCambioUsd = rs.getDouble("tasa_cambio_usd");

                tipo_moneda = new tipo_moneda();
                tipo_moneda.setId_tipo_moneda(id_tipo_moneda);
                tipo_moneda.setTipo_moneda(tipoMoneda);
                tipo_moneda.setTasa_cambio_usd(tasaCambioUsd);

                list_tipo_monedas.add(tipo_moneda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_monedas;
    }

    public int insert(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipo_moneda.getTipo_moneda());
            stmt.setDouble(2, tipo_moneda.getTasa_cambio_usd());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipo_moneda.getTipo_moneda());
            stmt.setDouble(2, tipo_moneda.getTasa_cambio_usd());
            stmt.setInt(3, tipo_moneda.getId_tipo_moneda());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipo_moneda.getId_tipo_moneda());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public tipo_moneda query(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipo_moneda.getId_tipo_moneda());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id_tipo_moneda = rs.getInt("id_tipo_moneda");
                String tipoMoneda = rs.getString("tipo_moneda");
                double tasaCambioUsd = rs.getDouble("tasa_cambio_usd");

                tipo_moneda = new tipo_moneda();
                tipo_moneda.setId_tipo_moneda(id_tipo_moneda);
                tipo_moneda.setTipo_moneda(tipoMoneda);
                tipo_moneda.setTasa_cambio_usd(tasaCambioUsd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipo_moneda;
    }

    // Método para verificar si el tipo de moneda ya existe
    public boolean existeTipoMoneda(String tipoMoneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, tipoMoneda); // Compara el tipo de moneda ingresado
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1); // Si el conteo es mayor a 0, significa que ya existe
                if (count > 0) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }
}

