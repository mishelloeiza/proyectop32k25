/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Asignacioncursosalumno;
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
public class AsignacioncursosalumnoDAO {

    private static final String SQL_SELECT = "SELECT codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, carnet_alumno, nota_asignacioncursoalumnos FROM asignacioncursosalumnos";
    private static final String SQL_INSERT = "INSERT INTO asignacioncursosalumnos(codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, carnet_alumno, nota_asignacioncursoalumnos) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE asignacioncursosalumnos SET codigo_carrera=?, codigo_sede=?, codigo_jornada=? , codigo_seccion=? , codigo_aula=? , codigo_curso=? , carnet_alumno =?, nota_asignacioncursoalumnos =? WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND carnet_alumno=?";
    private static final String SQL_DELETE = "DELETE FROM asignacioncursosalumnos WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND carnet_alumno=?";
    private static final String SQL_QUERY = "SELECT codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, carnet_alumno, nota_asignacioncursoalumnos FROM asignacioncursosalumnos WHERE codigo_carrera=? AND codigo_sede=? AND codigo_jornada=? AND codigo_seccion=? AND codigo_aula=? AND codigo_curso=? AND carnet_alumno=?";

    public List<Asignacioncursosalumno> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignacioncursosalumno asignacioncursosalumno = null;
        List<Asignacioncursosalumno> asignacioncursosalumnos = new ArrayList<Asignacioncursosalumno>();

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
                int carnetAlumno = rs.getInt("carnet_alumno");
                float notaAsignacioncursoalumno = rs.getFloat("nota_asignacioncursoalumnos");
                
                asignacioncursosalumno = new Asignacioncursosalumno();
                asignacioncursosalumno.setCodigoCarrera(codigoCarrera);
                asignacioncursosalumno.setCodigoSede(codigoSede);
                asignacioncursosalumno.setCodigoJornada(codigoJornada);
                asignacioncursosalumno.setCodigoSeccion(codigoSeccion);
                asignacioncursosalumno.setCodigoAula(codigoAula);
                asignacioncursosalumno.setCodigoCurso(codigoCurso);
                asignacioncursosalumno.setCarnetAlumno(carnetAlumno);
                asignacioncursosalumno.setNotaAsignacioncursoalumno(notaAsignacioncursoalumno);
                asignacioncursosalumnos.add(asignacioncursosalumno);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return asignacioncursosalumnos;
    }

    public int insert(Asignacioncursosalumno asignacioncursosalumnos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, asignacioncursosalumnos.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosalumnos.getCodigoSede());
            stmt.setInt(3, asignacioncursosalumnos.getCodigoJornada());
            stmt.setInt(4, asignacioncursosalumnos.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosalumnos.getCodigoAula());
            stmt.setInt(6, asignacioncursosalumnos.getCodigoCurso());
            stmt.setInt(7, asignacioncursosalumnos.getCarnetAlumno());
            stmt.setFloat(8, asignacioncursosalumnos.getNotaAsignacioncursoalumno());
            
            
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

    public int update(Asignacioncursosalumno asignacioncursosalumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, asignacioncursosalumno.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosalumno.getCodigoSede());
            stmt.setInt(3, asignacioncursosalumno.getCodigoJornada());
            stmt.setInt(4, asignacioncursosalumno.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosalumno.getCodigoAula());
            stmt.setInt(6, asignacioncursosalumno.getCodigoCurso());
            stmt.setInt(7, asignacioncursosalumno.getCarnetAlumno());
            stmt.setFloat(8, asignacioncursosalumno.getNotaAsignacioncursoalumno());
            
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

    public int delete(Asignacioncursosalumno asignacioncursosalumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, asignacioncursosalumno.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosalumno.getCodigoSede());
            stmt.setInt(3, asignacioncursosalumno.getCodigoJornada());
            stmt.setInt(4, asignacioncursosalumno.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosalumno.getCodigoAula());
            stmt.setInt(6, asignacioncursosalumno.getCodigoCurso());
            stmt.setInt(7, asignacioncursosalumno.getCarnetAlumno());
            stmt.setFloat(8, asignacioncursosalumno.getNotaAsignacioncursoalumno());
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
    public Asignacioncursosalumno query(Asignacioncursosalumno asignacioncursosalumno) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Asignacioncursosalumno> asignacioncursosalumnos = new ArrayList<Asignacioncursosalumno>();
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
            stmt.setInt(1, asignacioncursosalumno.getCodigoCarrera());
            stmt.setInt(2, asignacioncursosalumno.getCodigoSede());
            stmt.setInt(3, asignacioncursosalumno.getCodigoJornada());
            stmt.setInt(4, asignacioncursosalumno.getCodigoSeccion());
            stmt.setInt(5, asignacioncursosalumno.getCodigoAula());
            stmt.setInt(6, asignacioncursosalumno.getCodigoCurso());
            stmt.setInt(7, asignacioncursosalumno.getCarnetAlumno());
            stmt.setFloat(8, asignacioncursosalumno.getNotaAsignacioncursoalumno());
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
                int carnetAlumno = rs.getInt("carnet_alumno");
                float notaAsignacioncursoalumno = rs.getFloat("nota_asignacioncursoalumnos");
                
                asignacioncursosalumno = new Asignacioncursosalumno();
                asignacioncursosalumno.setCodigoCarrera(codigoCarrera);
                asignacioncursosalumno.setCodigoSede(codigoSede);
                asignacioncursosalumno.setCodigoJornada(codigoJornada);
                asignacioncursosalumno.setCodigoSeccion(codigoSeccion);
                asignacioncursosalumno.setCodigoAula(codigoAula);
                asignacioncursosalumno.setCodigoCurso(codigoCurso);
                asignacioncursosalumno.setCarnetAlumno(carnetAlumno);
                asignacioncursosalumno.setNotaAsignacioncursoalumno(notaAsignacioncursoalumno);
                
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

    return asignacioncursosalumno;
    }
}