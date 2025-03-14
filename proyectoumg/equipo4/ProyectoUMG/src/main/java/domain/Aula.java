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
public class Aula {
    
    private int codigoAula;
    private String nombreAula;
    private String estatusAula;
    
    @Override
    public String toString() {
        return "Aula{" + "codigoAula=" + codigoAula + ", nombreAula=" + nombreAula + ", estatusAula=" + estatusAula + '}';
    }

    public int getCodigoAula() {
        return codigoAula;
    }

    public void setCodigoAula(int codigoAula) {
        this.codigoAula = codigoAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getEstatusAula() {
        return estatusAula;
    }

    public void setEstatusAula(String estatusAula) {
        this.estatusAula = estatusAula;
    }

    public Aula(int codigoAula, String nombreAula, String estatusAula) {
        this.codigoAula = codigoAula;
        this.nombreAula = nombreAula;
        this.estatusAula = estatusAula;
    }
    
    public Aula() {
        
    }
}
