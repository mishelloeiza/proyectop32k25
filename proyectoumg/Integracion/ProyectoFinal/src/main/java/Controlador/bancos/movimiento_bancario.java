package Controlador.bancos;

//Ruddyard Castro 9959-23-1409

import java.time.LocalDateTime;

// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 

public class movimiento_bancario {
    private int id_movimiento_bancario;
    private int id_tipo_cuenta; // 
    private LocalDateTime fecha;

    public movimiento_bancario() {
    }

    public movimiento_bancario(int id_movimiento_bancario, int id_tipo_cuenta, LocalDateTime fecha) {
        this.id_movimiento_bancario = id_movimiento_bancario;
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.fecha = fecha;
    }

   
    public int getId_movimiento_bancario() {
        return id_movimiento_bancario;
    }

    public void setId_movimiento_bancario(int id_movimiento_bancario) {
        this.id_movimiento_bancario = id_movimiento_bancario;
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
     @Override
    public String toString() {
        return "movimiento_bancario{" + "id_movimiento_bancario=" + id_movimiento_bancario + ", id_tipo_cuenta=" + id_tipo_cuenta + ", fecha=" + fecha + '}';
    }

    public Object getId_conciliacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getId_cuenta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
