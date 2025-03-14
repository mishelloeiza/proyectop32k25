/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author c2307
 */
public class Facultad {
    private int codigoFacultad;
    private String nombreFacultad;
    private String estatusFacultad;

    @Override
    public String toString() {
        return "Facultad{" + "codigoFacultad=" + codigoFacultad + ", nombreFacultad=" + nombreFacultad + ", estatusFacultad=" + estatusFacultad + '}';
    }

    public int getCodigoFacultad() {
        return codigoFacultad;
    }

    public void setCodigoFacultad(int codigoFacultad) {
        this.codigoFacultad = codigoFacultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getEstatusFacultad() {
        return estatusFacultad;
    }

    public void setEstatusFacultad(String estatusFacultad) {
        this.estatusFacultad = estatusFacultad;
    }

    public Facultad(int codigoFacultad, String nombreFacultad, String estatusFacultad) {
        this.codigoFacultad = codigoFacultad;
        this.nombreFacultad = nombreFacultad;
        this.estatusFacultad = estatusFacultad;
    }
    
    public Facultad() {
        
    }
}