package Controlador.seguridad;


import java.sql.Timestamp;

public class AsignacionPerfil {
    private int id_asignacion;
    private int id_usuario;
    private int id_perfil;
    private Timestamp fecha_Asignacion;
    
    
    public AsignacionPerfil() {
    }

    public AsignacionPerfil(int id_asignacion, int id_usuario, int id_perfil, Timestamp fecha_Asignacion) {
        this.id_asignacion = id_asignacion;
        this.id_usuario = id_usuario;
        this.id_perfil = id_perfil;
        this.fecha_Asignacion = fecha_Asignacion;
    }

   


    public int getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(int id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public Timestamp getFecha_Asignacion() {
        return fecha_Asignacion;
    }

    public void setFecha_Asignacion(Timestamp fecha_Asignacion) {
        this.fecha_Asignacion = fecha_Asignacion;
    }
 @Override
    public String toString() {
        return "AsignacionPerfil{" + "id_asignacion=" + id_asignacion + ", id_usuario=" + id_usuario + ", id_perfil=" + id_perfil + ", fecha_Asignacion=" + fecha_Asignacion + '}';
    }
  
}
