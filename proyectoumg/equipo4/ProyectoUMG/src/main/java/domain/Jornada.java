/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author c2307
 */
public class Jornada {
    private int codigoJornada;
    private String nombreJornada;
    private String estatusJornada;

    @Override
    public String toString() {
        return "Jornada{" + "codigoJornada=" + codigoJornada + ", nombreJornada=" + nombreJornada + ", estatusJornada=" + estatusJornada + '}';
    }

    public int getCodigoJornada() {
        return codigoJornada;
    }

    public void setCodigoJornada(int codigoJornada) {
        this.codigoJornada = codigoJornada;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    public String getEstatusJornada() {
        return estatusJornada;
    }

    public void setEstatusJornada(String estatusJornada) {
        this.estatusJornada = estatusJornada;
    }

    public Jornada(int codigoJornada, String nombreJornada, String estatusJornada) {
        this.codigoJornada = codigoJornada;
        this.nombreJornada = nombreJornada;
        this.estatusJornada = estatusJornada;
    }
    
    public Jornada() {
        
    }
}