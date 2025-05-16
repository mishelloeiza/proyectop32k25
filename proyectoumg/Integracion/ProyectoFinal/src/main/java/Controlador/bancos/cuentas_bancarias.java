package Controlador.bancos;

//Gabriela Pinto García 9959-23-1087
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 
public class cuentas_bancarias {

    private int id_cuenta;
    private int id_banco;
    private int id_tipo_cuenta;
    private int id_tipo_moneda;
    private float saldo;
    // private int status; // ← Agregado

    public cuentas_bancarias() {
    }
    @Override
    public String toString() {
        return "cuentas_bancarias{" + "id_cuenta=" + id_cuenta + ", id_banco=" + id_banco + ", id_tipo_cuenta=" + id_tipo_cuenta + ", id_tipo_moneda=" + id_tipo_moneda + ", saldo=" + saldo + '}';
    }

    public cuentas_bancarias(int id_cuenta, int id_banco, int id_tipo_cuenta, int id_tipo_moneda, float saldo) {
        this.id_cuenta = id_cuenta;
        this.id_banco = id_banco;
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.id_tipo_moneda = id_tipo_moneda;
        this.saldo = saldo;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public int getId_tipo_moneda() {
        return id_tipo_moneda;
    }

    public void setId_tipo_moneda(int id_tipo_moneda) {
        this.id_tipo_moneda = id_tipo_moneda;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setId_cuenta_bancaria(int idCuentaSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
    