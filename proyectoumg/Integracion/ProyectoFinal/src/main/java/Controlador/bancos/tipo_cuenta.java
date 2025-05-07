package Controlador.bancos;

//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 

public class tipo_cuenta {

    private int id_tipo_cuenta;
    private String tipo_cuenta;
    private int status; // ← Agregado

    public tipo_cuenta() {
    }

    public tipo_cuenta(int id_tipo_cuenta, String tipo_cuenta, int status) { // ← Modificado
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.status = status;
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public int getStatus() { // ← Agregado
        return status;
    }

    public void setStatus(int estatus) { // ← Agregado
        this.status = estatus;
    }

    @Override
    public String toString() {
        return "tipo_cuenta{" + "id_tipo_cuenta=" + id_tipo_cuenta + ", tipo_cuenta=" + tipo_cuenta + ", status=" + status + '}'; // ← Modificado
    }
}
