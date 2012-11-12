/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Bibliografia;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.BibliografiaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="manterBibliografia")
@ViewScoped
public class ManterBibliografia extends CRUDView<Bibliografia, Byte>{

    @EJB
    private BibliografiaFacade bibliografiaDAO;

    public ManterBibliografia() {
    }

    @Override
    protected AbstractFacade<Bibliografia> getFacade() {
        return bibliografiaDAO;
    }

    @Override
    protected Bibliografia criarInstancia() {
        return new Bibliografia();
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null) super.salva();
        else super.edita();
        limpa();
        atualizaListagem();
    }

    public void envia(byte id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void Publicar(byte id){
        if(getSessao().isAdmin()) {
            Bibliografia especie = bibliografiaDAO.find(id);
            especie.setSituacao(Situacao.PUBLICADO);
            bibliografiaDAO.edit(especie);
        }else ServiceUtil.redirect("/Atlas/");
    }
}
