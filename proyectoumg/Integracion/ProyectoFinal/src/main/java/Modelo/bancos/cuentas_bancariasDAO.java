package Modelo.bancos;
//CREADO POR Gabriela Pinto  9959-23-1087

import Modelo.Conexion;
import Controlador.bancos.cuentas_bancarias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cuentas_bancariasDAO {

    private static final String SQL_SELECT = "SELECT id_cuenta, id_banco, id_tipo_cuenta, id_tipo_moneda, saldo FROM cuentas_bancarias";
    private static final String SQL_INSERT = "INSERT INTO cuentas_bancarias(id_banco, id_tipo_cuenta, id_tipo_moneda, saldo) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cuentas_bancarias SET id_banco=?, id_tipo_cuenta=?, id_tipo_moneda=?, saldo=? WHERE id_cuenta = ?";
    private static final String SQL_QUERY = "SELECT id_cuenta, id_banco, id_tipo_cuenta, id_tipo_moneda, saldo FROM cuentas_bancarias WHERE id_cuenta = ?";
    private static final String SQL_DELETE = "DELETE FROM cuentas_bancarias WHERE id_cuenta = ?";
    private static final String SQL_EXISTE = "SELECT COUNT(*) FROM cuentas_bancarias WHERE id_cuenta = ?";

    public List<cuentas_bancarias> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cuentas_bancarias cuentaBancaria = null;
        List<cuentas_bancarias> listCuentasBancarias = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCuenta = rs.getInt("id_cuenta");
                int idBanco = rs.getInt("id_banco");
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                int idTipoMoneda = rs.getInt("id_tipo_moneda");
                double saldo = rs.getDouble("saldo");

                cuentaBancaria = new cuentas_bancarias();
                cuentaBancaria.setId_cuenta(idCuenta);
                cuentaBancaria.setId_banco(idBanco);
                cuentaBancaria.setId_tipo_cuenta(idTipoCuenta);
                cuentaBancaria.setId_tipo_moneda(idTipoMoneda);
                cuentaBancaria.setSaldo(saldo);

                listCuentasBancarias.add(cuentaBancaria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listCuentasBancarias;
    }

    public int insert(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cuentaBancaria.getId_banco());
            stmt.setInt(2, cuentaBancaria.getId_tipo_cuenta());
            stmt.setInt(3, cuentaBancaria.getId_tipo_moneda());
            stmt.setDouble(4, cuentaBancaria.getSaldo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, cuentaBancaria.getId_banco());
            stmt.setInt(2, cuentaBancaria.getId_tipo_cuenta());
            stmt.setInt(3, cuentaBancaria.getId_tipo_moneda());
            stmt.setDouble(4, cuentaBancaria.getSaldo());
            stmt.setInt(5, cuentaBancaria.getId_cuenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cuentaBancaria.getId_cuenta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public cuentas_bancarias query(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, cuentaBancaria.getId_cuenta());
            rs = stmt.executeQuery();
            if (rs.next()) {
                cuentaBancaria = new cuentas_bancarias();
                cuentaBancaria.setId_cuenta(rs.getInt("id_cuenta"));
                cuentaBancaria.setId_banco(rs.getInt("id_banco"));
                cuentaBancaria.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                cuentaBancaria.setId_tipo_moneda(rs.getInt("id_tipo_moneda"));
                cuentaBancaria.setSaldo(rs.getDouble("saldo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cuentaBancaria;
    }

    public boolean existeTipoCuenta(String cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, cuentaBancaria);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
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
