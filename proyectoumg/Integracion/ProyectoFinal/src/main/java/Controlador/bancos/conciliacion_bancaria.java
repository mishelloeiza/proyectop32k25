package Controlador.bancos;

// Mishel

import java.time.LocalDateTime;

// Modelo para tabla conciliacion_bancaria
public class conciliacion_bancaria {
    private int id_conciliacion;
    private int id_tipo_cuenta;
    private LocalDateTime fecha;
    private String status;

    public conciliacion_bancaria() {
    }

    public conciliacion_bancaria(int id_conciliacion, int id_tipo_cuenta, LocalDateTime fecha, String status) {
        this.id_conciliacion = id_conciliacion;
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.fecha = fecha;
        this.status = status;
    }

    public int getId_conciliacion() {
        return id_conciliacion;
    }

    public void setId_conciliacion(int id_conciliacion) {
        this.id_conciliacion = id_conciliacion;
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "conciliacion_bancaria{" +
                "id_conciliacion=" + id_conciliacion +
                ", id_tipo_cuenta=" + id_tipo_cuenta +
                ", fecha=" + fecha +
                ", status='" + status + '\'' +
                '}';
    }
}
