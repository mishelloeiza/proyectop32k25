/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Asignacioncursosmaestro;
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
public class AsignacioncursosmaestroDAO {

    private static final String SQL_SELECT = "SELECT codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, codigo_maestro FROM asignacioncursosmaestros";
    private static final String SQL_INSERT = "INSERT INTO asignacioncursosmaestros(codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, codigo_maestro) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE asignacioncursosmaestros SET codigo_carrera=?, codigo_sede=?, codigo_jornada=? , codigo_seccion=? , codigo_aula=? , codigo_curso=? , codigo_maestro=? WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND codigo_maestro=?";
    private static final String SQL_DELETE = "DELETE FROM asignacioncursosmaestros WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND codigo_maestro=?";
    private static final String SQL_QUERY = "SELECT codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, codigo_maestro FROM asignacioncursosmaestros WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND codigo_maestro=?";

    public List<Asignacioncursosmaestro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignacioncursosmaestro asignacioncursosmaestro = null;
        List<Asignacioncursosmaestro> asignacioncursosmaestros = new ArrayList<Asignacioncursosmaestro>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoCarrera = rs.getInt("codigo_carrera");
                int codigoSede = rs.getInt("codigo_sede");
                int codigoJornada = rs.getInt("codigo_jornada");
                int codigoSeccion = rs.getInt("codigo_seccion");
                int codigoAula = rs.getInt("codigo_aula");
                int codigoCurso = rs.getInt("codigo_curso");
                int codigoMaestro = rs.getInt("codigo_maestro");
                
                asignacioncursosmaestro = new Asignacioncursosmaestro();
                asignacioncursosmaestro.setCodigoCarrera(codigoCarrera);
                asignacioncursosmaestro.setCodigoSede(codigoSede);
                asignacioncursosmaestro.setCodigoJornada(codigoJornada);
                asignacioncursosmaestro.setCodigoSeccion(codigoSeccion);
                asignacioncursosmaestro.setCodigoAula(codigoAula);
                asignacioncursosmaestro.setCodigoCurso(codigoCurso);
                asignacioncursosmaestro.setCodigoMaestro(codigoMaestro);
                
                asignacioncursosmaestros.add(asignacioncursosmaestro);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return asignacioncursosmaestros;
    }

    public int insert(Asignacioncursosmaestro asignacioncursosmaestros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, asignacioncursosmaestros.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosmaestros.getCodigoSede());
            stmt.setInt(3, asignacioncursosmaestros.getCodigoJornada());
            stmt.setInt(4, asignacioncursosmaestros.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosmaestros.getCodigoAula());
            stmt.setInt(6, asignacioncursosmaestros.getCodigoCurso());
            stmt.setInt(7, asignacioncursosmaestros.getCodigoMaestro());
            
            
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

    public int update(Asignacioncursosmaestro asignacioncursosmaestro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, asignacioncursosmaestro.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosmaestro.getCodigoSede());
            stmt.setInt(3, asignacioncursosmaestro.getCodigoJornada());
            stmt.setInt(4, asignacioncursosmaestro.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosmaestro.getCodigoAula());
            stmt.setInt(6, asignacioncursosmaestro.getCodigoCurso());
            stmt.setInt(7, asignacioncursosmaestro.getCodigoMaestro());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Asignacioncursosmaestro asignacioncursosmaestro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, asignacioncursosmaestro.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosmaestro.getCodigoSede());
            stmt.setInt(3, asignacioncursosmaestro.getCodigoJornada());
            stmt.setInt(4, asignacioncursosmaestro.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosmaestro.getCodigoAula());
            stmt.setInt(6, asignacioncursosmaestro.getCodigoCurso());
            stmt.setInt(7, asignacioncursosmaestro.getCodigoMaestro());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Asignacioncursosmaestro query(Asignacioncursosmaestro asignacioncursosmaestro) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Asignacioncursosmaestro> asignacioncursosmaestros = new ArrayList<Asignacioncursosmaestro>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            if (conn != null) {
            System.out.println("Conexión a la base de datos establecida.");
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return null;
        }
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, asignacioncursosmaestro.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosmaestro.getCodigoSede());
            stmt.setInt(3, asignacioncursosmaestro.getCodigoJornada());
            stmt.setInt(4, asignacioncursosmaestro.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosmaestro.getCodigoAula());
            stmt.setInt(6, asignacioncursosmaestro.getCodigoCurso());
            stmt.setInt(7, asignacioncursosmaestro.getCodigoMaestro());
            System.out.println("Parámetros establecidos para la consulta.");
            rs = stmt.executeQuery();
            if (rs != null) {
                System.out.println("ResultSet inicializado correctamente.");
            } else {
                System.out.println("ResultSet es nulo.");
            }
            while (rs.next()) {
                int codigoCarrera = rs.getInt("codigo_carrera");
                int codigoSede = rs.getInt("codigo_sede");
                int codigoJornada = rs.getInt("codigo_jornada");
                int codigoSeccion = rs.getInt("codigo_seccion");
                int codigoAula = rs.getInt("codigo_aula");
                int codigoCurso = rs.getInt("codigo_curso");
                int codigoMaestro = rs.getInt("codigo_maestro");
                
                asignacioncursosmaestro = new Asignacioncursosmaestro();
                asignacioncursosmaestro.setCodigoCarrera(codigoCarrera);
                asignacioncursosmaestro.setCodigoSede(codigoSede);
                asignacioncursosmaestro.setCodigoJornada(codigoJornada);
                asignacioncursosmaestro.setCodigoSeccion(codigoSeccion);
                asignacioncursosmaestro.setCodigoAula(codigoAula);
                asignacioncursosmaestro.setCodigoCurso(codigoCurso);
                asignacioncursosmaestro.setCodigoMaestro(codigoMaestro);
                
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error en el bloque try: " + ex.getMessage());
        } /*finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }*/
        finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                System.out.println("Error al cerrar ResultSet: " + ex.getMessage());
            }
        } else {
            System.out.println("ResultSet es nulo en finally.");
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                System.out.println("Error al cerrar PreparedStatement: " + ex.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                System.out.println("Error al cerrar Connection: " + ex.getMessage());
            }
        }
    }

    return asignacioncursosmaestro;
    }
}