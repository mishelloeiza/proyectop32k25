package Controlador.bancos;


//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 
public  class  tipo_pago{

    private int idTipoPago;
    private String tipoPago;
    private  String Status ;

    
    
    public tipo_pago() {
    }

    public tipo_pago(int idTipoPago, String tipoPago, String Status) {
        this.idTipoPago = idTipoPago;
        this.tipoPago = tipoPago;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "tipo_pago{" + "idTipoPago=" + idTipoPago + ", tipoPago=" + tipoPago + ", Status=" + Status + '}';
    }

  
    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }



}

