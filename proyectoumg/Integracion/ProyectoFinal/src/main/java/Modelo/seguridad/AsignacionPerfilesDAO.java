package Modelo.seguridad;
import Controlador.seguridad.AsignacionPerfil;
import Controlador.seguridad.Perfil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionPerfilesDAO {

    private Connection connection;

    // Constructor que recibe la conexión
    public AsignacionPerfilesDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar una asignación de perfil
    public boolean agregarAsignacion(AsignacionPerfil asignacion) {
        String sql = "INSERT INTO asignacion_perfiles (id_usuario, id_perfil, fecha_asignacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, asignacion.getIdUsuario());
            stmt.setInt(2, asignacion.getIdPerfil());
            stmt.setTimestamp(3, asignacion.getFechaAsignacion());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las asignaciones
    public List<AsignacionPerfil> obtenerAsignaciones() {
        List<AsignacionPerfil> asignaciones = new ArrayList<>();
        String sql = "SELECT * FROM asignacion_perfiles";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AsignacionPerfil asignacion = new AsignacionPerfil();
                asignacion.setIdAsignacion(rs.getInt("id_asignacion"));
                asignacion.setIdUsuario(rs.getInt("id_usuario"));
                asignacion.setIdPerfil(rs.getInt("id_perfil"));
                asignacion.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
                asignaciones.add(asignacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    // Método para obtener una asignación por su ID
    public AsignacionPerfil obtenerAsignacionPorId(int idAsignacion) {
        String sql = "SELECT * FROM asignacion_perfiles WHERE id_asignacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAsignacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AsignacionPerfil asignacion = new AsignacionPerfil();
                    asignacion.setIdAsignacion(rs.getInt("id_asignacion"));
                    asignacion.setIdUsuario(rs.getInt("id_usuario"));
                    asignacion.setIdPerfil(rs.getInt("id_perfil"));
                    asignacion.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
                    return asignacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para eliminar una asignación por ID
    public boolean eliminarAsignacion(int idAsignacion) {
        String sql = "DELETE FROM asignacion_perfiles WHERE id_asignacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAsignacion);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar una asignación de perfil
    public boolean actualizarAsignacion(AsignacionPerfil asignacion) {
        String sql = "UPDATE asignacion_perfiles SET id_usuario = ?, id_perfil = ?, fecha_asignacion = ? WHERE id_asignacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, asignacion.getIdUsuario());
            stmt.setInt(2, asignacion.getIdPerfil());
            stmt.setTimestamp(3, asignacion.getFechaAsignacion());
            stmt.setInt(4, asignacion.getIdAsignacion());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
