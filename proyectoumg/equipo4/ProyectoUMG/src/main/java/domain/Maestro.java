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
public class Maestro {
    private int codigoMaestro;
    private String nombreMaestro;
    private String direccionMaestro;
    private String telefonoMaestro;
    private String emailMaestro;
    private String estatusMaestro;
    
    @Override
    public String toString() {
        return "Maestros{" + "codigoMaestro=" + codigoMaestro + ", nombreMaestro=" + nombreMaestro + ", direccionMaestro=" + direccionMaestro + ", telefonoMaestro=" + telefonoMaestro + ", emailMaestro=" + emailMaestro + ", estatusMaestro=" + estatusMaestro + '}';
    }


    public int getCodigoMaestro() {
        return codigoMaestro;
    }

    public void setCodigoMaestro(int codigoMaestro) {
        this.codigoMaestro = codigoMaestro;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public String getDireccionMaestro() {
        return direccionMaestro;
    }

    public void setDireccionMaestro(String direccionMaestro) {
        this.direccionMaestro = direccionMaestro;
    }

    public String getTelefonoMaestro() {
        return telefonoMaestro;
    }

    public void setTelefonoMaestro(String telefonoMaestro) {
        this.telefonoMaestro = telefonoMaestro;
    }

    public String getEmailMaestro() {
        return emailMaestro;
    }

    public void setEmailMaestro(String emailMaestro) {
        this.emailMaestro = emailMaestro;
    }

    public String getEstatusMaestro() {
        return estatusMaestro;
    }

    public void setEstatusMaestro(String estatusMaestro) {
        this.estatusMaestro = estatusMaestro;
    }
    
    public Maestro(int codigoMaestro, String nombreMaestro, String direccionMaestro, String telefonoMaestro, String emailMaestro, String estatusMaestro) {
        this.codigoMaestro = codigoMaestro;
        this.nombreMaestro = nombreMaestro;
        this.direccionMaestro = direccionMaestro;
        this.telefonoMaestro = telefonoMaestro;
        this.emailMaestro = emailMaestro;
        this.estatusMaestro = estatusMaestro;
    }

    public Maestro() {
        
    }


}
