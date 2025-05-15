package Controlador.bancos;



public  class  detalle_movimientos_bancarios{

    private int idDetalle;
    private int idMovimiento;
    private int idTipoOperacion;
    private int idTipoPago;
    private float Monto;
    private String Descripcion;


    @Override
    public String toString() {
        return "detalle_movimientos_bancarios{" + "idDetalle=" + idDetalle + ", idMovimiento=" + idMovimiento + ", idTipoOperacion=" + idTipoOperacion + ", idTipoPago=" + idTipoPago + ", Monto=" + Monto + ", Descripcion=" + Descripcion + '}';
    }

  
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(int idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }
    
    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }
    
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public detalle_movimientos_bancarios() {
    }

    public detalle_movimientos_bancarios(int idDetalle, int idMovimiento, int idTipoOperacion, int idTipoPago, float Monto, String Descripcion) {
        this.idDetalle = idDetalle;
        this.idMovimiento = idMovimiento;
        this.idTipoOperacion = idTipoOperacion;
        this.idTipoPago = idTipoPago;
        this.Monto = Monto;
        this.Descripcion = Descripcion;
    }

}

