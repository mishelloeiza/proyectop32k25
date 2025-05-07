package Controlador.bancos;

// MISHEL LOEIZA 9959-23-3457
// Nombres de los getter, setter y variables igual al nombre en base de datos
// Nombre del archivo Java como se llama la tabla 
// Se usó encapsulate field para getter y setter

public class tipo_moneda {

    private int id_tipo_moneda;
    private String tipo_moneda;
    private double tasa_cambio_usd;

    public tipo_moneda() {
    }

    public tipo_moneda(int id_tipo_moneda, String tipo_moneda, double tasa_cambio_usd) {
        this.id_tipo_moneda = id_tipo_moneda;
        this.tipo_moneda = tipo_moneda;
        this.tasa_cambio_usd = tasa_cambio_usd;
    }

    @Override
    public String toString() {
        return "tipo_moneda{" + 
               "id_tipo_moneda=" + id_tipo_moneda + 
               ", tipo_moneda='" + tipo_moneda + '\'' + 
               ", tasa_cambio_usd=" + tasa_cambio_usd + 
               '}';
    }

    public int getId_tipo_moneda() {
        return id_tipo_moneda;
    }

    public void setId_tipo_moneda(int id_tipo_moneda) {
        this.id_tipo_moneda = id_tipo_moneda;
    }

    public String getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(String tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public double getTasa_cambio_usd() {
        return tasa_cambio_usd;
    }

    public void setTasa_cambio_usd(double tasa_cambio_usd) {
        this.tasa_cambio_usd = tasa_cambio_usd;
    }
}

