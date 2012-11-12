/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Entity.Usuario;
import Util.ServiceUtil;
import boundary.UsuarioFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author luancorumba
 */
@ManagedBean
@RequestScoped
public class LoginMB {
    
    @EJB
    private UsuarioFacade usuarioDAO;
    
    @ManagedProperty("#{sessao}")
    private Sessao sessao;
    
    private String login,senha;

    public LoginMB() {
    }
    
    
    public void login(){
        Usuario usuarioTemp = usuarioDAO.getUsuarioPorLogin(login);
        if(usuarioTemp==null){
            ServiceUtil.addErroMessage("Usuário não existe", "Você deve estar cadastrado para acessar o sistema");
        }else if(senha.equals(usuarioDAO.getUsuarioPorLogin(login).getSenha())){
            sessao.setUsuario(usuarioTemp);
            ServiceUtil.addInfoMessage("Bem vindo!", "Você está logado no Atlas.");
        }else{
            ServiceUtil.addErroMessage("Senha incorreta", "Após 3 tentativas você será impedido de acessar o sistema por uma hora.");
        }
    }

    public void logout(){
        sessao.limpar();
        ServiceUtil.addInfoMessage("Até breve!",null);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
