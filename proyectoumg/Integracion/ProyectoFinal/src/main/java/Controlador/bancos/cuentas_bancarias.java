package Controlador.bancos;

// Gabriela Pinto García 9959-23-1087
// Nombres de los getter/setter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 

public class cuentas_bancarias {

    private int id_cuenta;
    private int id_banco;
    private int id_tipo_cuenta;
    private int id_tipo_moneda;
    private double saldo;

    // Nuevos atributos para mostrar info relacionada
    private String tipo_moneda;
    private double tasa_cambio;

    public cuentas_bancarias() {
    }

    public cuentas_bancarias(int id_cuenta, int id_banco, int id_tipo_cuenta, int id_tipo_moneda, double saldo) {
        this.id_cuenta = id_cuenta;
        this.id_banco = id_banco;
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.id_tipo_moneda = id_tipo_moneda;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "cuentas_bancarias{" +
                "id_cuenta=" + id_cuenta +
                ", id_banco=" + id_banco +
                ", id_tipo_cuenta=" + id_tipo_cuenta +
                ", id_tipo_moneda=" + id_tipo_moneda +
                ", saldo=" + saldo +
                ", tipo_moneda='" + tipo_moneda + '\'' +
                ", tasa_cambio=" + tasa_cambio +
                '}';
    }

    // Getters y setters normales

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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Getters y setters nuevos

    public String getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(String tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public double getTasa_cambio() {
        return tasa_cambio;
    }

    public void setTasa_cambio(double tasa_cambio) {
        this.tasa_cambio = tasa_cambio;
    }
}
