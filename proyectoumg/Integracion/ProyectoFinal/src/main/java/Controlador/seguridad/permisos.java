package Controlador.seguridad;

/**
 *
 * @author MISHEL LOEIZA 9959-23-3457
 */
public class Permisos {//validacion 
    private boolean puedeMantenimiento;
    private boolean puedeProcesos;

    public boolean isPuedeMantenimiento() {
        return puedeMantenimiento;
    }

    public void setPuedeMantenimiento(boolean puedeMantenimiento) {
        this.puedeMantenimiento = puedeMantenimiento;
    }

    public boolean isPuedeProcesos() {
        return puedeProcesos;
    }

    public void setPuedeProcesos(boolean puedeProcesos) {
        this.puedeProcesos = puedeProcesos;
    }
}
