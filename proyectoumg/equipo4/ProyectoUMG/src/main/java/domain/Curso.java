/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author c2307
 */
public class Curso {
    private int codigoCurso;
    private String nombreCurso;
    private String estatusCurso;

    @Override
    public String toString() {
        return "Curso{" + "codigoCurso=" + codigoCurso + ", nombreCurso=" + nombreCurso + ", estatusCurso=" + estatusCurso + '}';
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getEstatusCurso() {
        return estatusCurso;
    }

    public void setEstatusCurso(String estatusCurso) {
        this.estatusCurso = estatusCurso;
    }

    public Curso(int codigoCurso, String nombreCurso, String estatusCurso) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.estatusCurso = estatusCurso;
    }
    
    public Curso() {
        
    }
}