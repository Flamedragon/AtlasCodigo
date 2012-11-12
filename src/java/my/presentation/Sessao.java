/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Entity.Usuario;
import Entity.enumeracoes.Perfil;
import Util.ServiceUtil;
import boundary.UsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author luancorumba
 */
@ManagedBean
@SessionScoped
public class Sessao implements Serializable{
    
    @EJB
    private UsuarioFacade usuarioDAO;

    private Usuario usuario;
    private long parametro;
    
    public Sessao() {
    }
    
    public boolean isAdmin(){
        return usuario != null && usuario.getPerfil() == Perfil.PROFESSOR_GERENCIADOR;
    }

    public boolean isProfessor(){
        return usuario != null && usuario.getPerfil() == Perfil.PROFESSOR_USUARIO;
    }
    
    public boolean isMonitor(){
        return usuario != null && usuario.getPerfil() == Perfil.MONITOR;
    }

    public boolean isAluno(){
        return usuario != null && usuario.getPerfil() == Perfil.ALUNO;
    }

    public void limpar(){
        usuario = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getParametro() {
        return parametro;
    }

    public void setParametro(long parametro) {
        this.parametro = parametro;
    }
}
