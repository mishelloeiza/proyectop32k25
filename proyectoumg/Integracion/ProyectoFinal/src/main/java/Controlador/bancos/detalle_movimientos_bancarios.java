package Controlador.bancos;



public  class  detalle_movimientos_bancarios{

    private int idDetalle;
    private int idMovimiento;
    private int idTipoOperacion;
    private int idTipoPago;
    private String tipoMovimiento;
    private float Monto;


    @Override
    public String toString() {
        return "detalle_movimientos_bancarios{" + "idDetalle=" + idDetalle + ", idMovimiento=" + idMovimiento + ", idTipoOperacion=" + idTipoOperacion + ", idTipoPago=" + idTipoPago + ", tipoMovimiento=" + tipoMovimiento + ", Monto=" + Monto + '}';
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

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public detalle_movimientos_bancarios() {
    }

    public detalle_movimientos_bancarios(int idDetalle, int idMovimiento, int idTipoOperacion, int idTipoPago, String tipoMovimiento, float Monto) {
        this.idDetalle = idDetalle;
        this.idMovimiento = idMovimiento;
        this.idTipoOperacion = idTipoOperacion;
        this.idTipoPago = idTipoPago;
        this.tipoMovimiento = tipoMovimiento;
        this.Monto = Monto;
    }

}

