package Modelo.bancos;
//CREADO POR Gabriela Pinto  9959-23-1087
import Modelo.Conexion;
import Controlador.bancos.tipo_cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipo_cuentaDAO {

    private static final String SQL_SELECT = "SELECT id_tipo_cuenta, tipo_cuenta, status FROM tipo_cuenta";
    private static final String SQL_INSERT = "INSERT INTO tipo_cuenta(tipo_cuenta, status) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tipo_cuenta SET tipo_cuenta=?, status=? WHERE id_tipo_cuenta = ?";
    private static final String SQL_QUERY = "SELECT id_tipo_cuenta, tipo_cuenta, status FROM tipo_cuenta WHERE id_tipo_cuenta = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_cuenta WHERE id_tipo_cuenta = ?"; // ← Agregado
    private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tipo_cuenta WHERE tipo_cuenta = ?"; // ← Agregado

    public List<tipo_cuenta> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_cuenta tipoCuenta = null;
        List<tipo_cuenta> listTipoCuentas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                String tipoCuentaStr = rs.getString("tipo_cuenta");
                int status = rs.getInt("status"); // ← Agregado

                tipoCuenta = new tipo_cuenta();
                tipoCuenta.setId_tipo_cuenta(idTipoCuenta);
                tipoCuenta.setTipo_cuenta(tipoCuentaStr);
                tipoCuenta.setStatus(status); // ← Agregado

                listTipoCuentas.add(tipoCuenta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listTipoCuentas;
    }

    public int insert(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipoCuenta.getTipo_cuenta());
            stmt.setInt(2, tipoCuenta.getStatus()); // ← Agregado

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipoCuenta.getTipo_cuenta());
            stmt.setInt(2, tipoCuenta.getStatus()); // ← Agregado
            stmt.setInt(3, tipoCuenta.getId_tipo_cuenta()); // ← Reordenado correctamente

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipoCuenta.getId_tipo_cuenta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public tipo_cuenta query(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipoCuenta.getId_tipo_cuenta());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                String tipoCuentaStr = rs.getString("tipo_cuenta");
                int status = rs.getInt("status"); // ← Agregado

                tipoCuenta = new tipo_cuenta();
                tipoCuenta.setId_tipo_cuenta(idTipoCuenta);
                tipoCuenta.setTipo_cuenta(tipoCuentaStr);
                tipoCuenta.setStatus(status); // ← Agregado
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipoCuenta;
    }

    // Método para verificar si el tipo de cuenta ya existe
    public boolean existeTipoCuenta(String tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, tipoCuenta); // Compara el tipo de cuenta ingresado
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
