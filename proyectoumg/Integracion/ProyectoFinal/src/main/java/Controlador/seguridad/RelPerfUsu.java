/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.seguridad;

/**
 *
 * @author visitante
 */
public class RelPerfUsu {
 
    int usuario_codigo;
    int perfil_codigo;
    

    @Override
    public String toString() {
        return "RelPerfApl{" + "usuario_codigo=" + usuario_codigo + ", perfil_codigo=" + perfil_codigo + '}';
    }

    public void setUsuario_codigo(int usuario_codigo) {
        this.usuario_codigo = usuario_codigo;
    }

    public void setPerfil_codigo(int perfil_codigo) {
        this.perfil_codigo = perfil_codigo;
    }

    public int getUsuario_codigo() {
        return usuario_codigo;
    }

    public int getPerfil_codigo() {
        return perfil_codigo;
    }


    public RelPerfUsu(int usuario_codigo, int perfil_codigo) {
        this.usuario_codigo = usuario_codigo;
        this.perfil_codigo = perfil_codigo;
    }
    
    public RelPerfUsu() { //no contiene nada
    }
    
    

}
