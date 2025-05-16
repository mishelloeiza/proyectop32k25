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
    private String tipoSaldo;
    private float monto;

    public movimiento_bancario() {
    }

    public movimiento_bancario(int id_movimiento_bancario, int id_tipo_cuenta, LocalDateTime fecha, String tipoSaldo, float monto) {
        this.id_movimiento_bancario = id_movimiento_bancario;
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.fecha = fecha;
        this.tipoSaldo = tipoSaldo;
        this.monto = monto;
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

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "movimiento_bancario{" + "id_movimiento_bancario=" + id_movimiento_bancario + ", id_tipo_cuenta=" + id_tipo_cuenta + ", fecha=" + fecha + ", tipoSaldo=" + tipoSaldo + ", monto=" + monto + '}';
    }
    
}
