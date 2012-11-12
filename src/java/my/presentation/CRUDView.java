/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Util.ServiceUtil;
import boundary.AbstractFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Kamilla
 */
public abstract class CRUDView<T,E> implements Serializable{

    private T instancia;

    private E id;

    private List<T> listagem;
    
    @ManagedProperty("#{sessao}")
    private Sessao sessao;

    public CRUDView(){}

    protected abstract AbstractFacade<T> getFacade();

    protected abstract T criarInstancia();

    public T getEntidade(){
        if(instancia ==  null){
            if(id!=null) instancia = getFacade().find(id);
            else instancia = criarInstancia();
        }
        return instancia;
    }

    public void atualizaListagem(){
        listagem = getFacade().findAll();
    }

    public List<T> getListagem(){
        if(listagem == null) atualizaListagem();
        return listagem;
    }

    public void salva(){ //CRUD deve analizar se o usuário tem permissão
        getFacade().create(instancia);
    }

    public void busca(E codigo){
        instancia = getFacade().find(codigo);
    }

    public void edita(){
        getFacade().edit(instancia);
    }

    public void remove(E codigo){
        busca(codigo);
        getFacade().remove(instancia);
        limpa();
        atualizaListagem();
    }

    public void limpa(){
        instancia = null;
        id = null;
        getFacade().close();
    }

    public E getId() {
        return id;
    }

    protected void setId(E id) {
        this.id = id;
    }
    
    public void preLoad(){
        if(!(sessao.isAdmin() || sessao.isMonitor())) ServiceUtil.redirect("/Atlas/");
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
