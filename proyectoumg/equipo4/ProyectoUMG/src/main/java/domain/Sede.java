/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author visitante
 */
public class Sede {
    
    private int codigoSede;
   private String nombreSede;
   private String estatusSede;

    public Sede(int codigoSede, String nombreSede, String estatusSede) {
        this.codigoSede = codigoSede;
        this.nombreSede = nombreSede;
        this.estatusSede = estatusSede;
    }

    public Sede() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getEstatusSede() {
        return estatusSede;
    }

    public void setEstatusSede(String estatusSede) {
        this.estatusSede = estatusSede;
    }

    @Override
    public String toString() {
        return "Sedes{" + "codigoSede=" + codigoSede + ", nombreSede=" + nombreSede + ", estatusSede=" + estatusSede + '}';
    }
   
}