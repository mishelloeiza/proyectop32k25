/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author c2307
 */
public class Asignacioncursosalumno {
    
    private int codigoCarrera;
    private int codigoSede;
    private int codigoJornada;
    private int codigoSeccion;
    private int codigoAula;
    private int codigoCurso;
    private int carnetAlumno;
    private float notaAsignacioncursoalumno;
    @Override
    public String toString() {
        return "Asignacioncursosalumno{" + "codigoCarrera=" + codigoCarrera + ", codigoSede=" + codigoSede + ", codigoJornada=" + codigoJornada + ", codigoSeccion=" + codigoSeccion + ", codigoAula=" + codigoAula + ", codigoCurso=" + codigoCurso + ", carnetAlumno=" + carnetAlumno + '}';
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public int getCodigoJornada() {
        return codigoJornada;
    }

    public void setCodigoJornada(int codigoJornada) {
        this.codigoJornada = codigoJornada;
    }

    public int getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(int codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public int getCodigoAula() {
        return codigoAula;
    }

    public void setCodigoAula(int codigoAula) {
        this.codigoAula = codigoAula;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCarnetAlumno() {
        return carnetAlumno;
    }

    public void setCarnetAlumno(int carnetAlumno) {
        this.carnetAlumno = carnetAlumno;
    }
    
    public float getNotaAsignacioncursoalumno() {
        return notaAsignacioncursoalumno;
    }

    public void setNotaAsignacioncursoalumno(float notaAsignacioncursoalumno) {
        this.notaAsignacioncursoalumno = notaAsignacioncursoalumno;
    }
    
    public Asignacioncursosalumno(int codigoCarrera, int codigoSede, int codigoJornada, int codigoSeccion, int codigoAula, int codigoCurso, int carnetAlumno, float notaAsignacioncursoalumno) {
        this.codigoCarrera = codigoCarrera;
        this.codigoSede = codigoSede;
        this.codigoJornada = codigoJornada;
        this.codigoSeccion = codigoSeccion;
        this.codigoAula = codigoAula;
        this.codigoCurso = codigoCurso;
        this.carnetAlumno = carnetAlumno;
        this.notaAsignacioncursoalumno = notaAsignacioncursoalumno;
    }

    public Asignacioncursosalumno() {
        
    }

}