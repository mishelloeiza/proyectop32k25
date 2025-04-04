/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






package Modelo.seguridad;


import Controlador.seguridad.RelPerfUsu;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class RelPerfUsuDAO {

    private static final String SQL_SELECT = "SELECT usuario_codigo, perfil_codigo FROM relperfusu";
    private static final String SQL_INSERT = "INSERT INTO relperfusu(usuario_codigo, perfil_codigo) VALUES(?,?)";
    //private static final String SQL_UPDATE = "UPDATE relperfusu SET consultar_rpa=? WHERE usuario_codigo=?,perfil_codigo=?";
    //private static final String SQL_DELETE = "DELETE FROM relperfusu WHERE usuario_codigo=?,perfil_codigo=?";
    private static final String SQL_QUERY = "SELECT usuario_codigo, perfil_codigo FROM relperfusu WHERE usuario_codigo= ?,perfil_codigo=?";

    public List<RelPerfUsu> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RelPerfUsu relPerfUsu = null;
        List<RelPerfUsu> list_relPerfUsu = new ArrayList<RelPerfUsu>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int usuario_codigo = rs.getInt("aplicacion_codigo");
                int perfil_codigo = rs.getInt("perfil_codigo");
                
                relPerfUsu = new RelPerfUsu();
                relPerfUsu.setUsuario_codigo(usuario_codigo);
                relPerfUsu.setPerfil_codigo(perfil_codigo);
                
              
                list_relPerfUsu.add(relPerfUsu);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_relPerfUsu;
    }

    public int insert(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, relPerfUsu.getUsuario_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
         
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
/*
    public int update(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, relPerfUsu.getConsultar_rpa());
            stmt.setString(2, relPerfUsu.getActualizar_rpa());
            stmt.setString(3, relPerfUsu.getEliminar_rpa());
            stmt.setString(4, relPerfUsu.getImprimir_rpa());
            stmt.setString(5, relPerfUsu.getInsertar_rpa());
         
            //comodin del where
            stmt.setInt(6,relPerfUsu.getAplicacion_codigo());
            stmt.setInt(7,relPerfUsu.getPerfil_codigo());
           

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }*/

    /*public int delete(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, relPerfUsu.getAplicacion_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }*/

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public RelPerfUsu query(RelPerfUsu relPerfUsu) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPerfUsu> list_relPerfUsu = new ArrayList<RelPerfUsu>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, relPerfUsu.getUsuario_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
             int usuario_codigo = rs.getInt("usuario_codigo");
                int perfil_codigo = rs.getInt("perfil_codigo");
                
                relPerfUsu = new RelPerfUsu();
                relPerfUsu.setUsuario_codigo(usuario_codigo);
                relPerfUsu.setPerfil_codigo(perfil_codigo);
                
                
                
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

       
        return relPerfUsu;
    }
            
}