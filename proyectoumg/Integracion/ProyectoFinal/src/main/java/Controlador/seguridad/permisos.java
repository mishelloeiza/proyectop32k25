package Controlador.seguridad;

/**
 *
 * @author cdavi
 */
public class Permisos {
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
