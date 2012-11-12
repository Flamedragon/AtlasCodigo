/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Duvida;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.DuvidaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="manterDuvida")
@ViewScoped
public class ManterDuvida extends CRUDView<Duvida, Long>{

    @EJB
    private DuvidaFacade duvidaFacade;

    public ManterDuvida() {
    }

    @Override
    protected AbstractFacade<Duvida> getFacade() {
        return duvidaFacade;
    }

    @Override
    protected Duvida criarInstancia() {
        return new Duvida();
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

    public void envia(long id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void Publicar(long id){
        if(getSessao().isAdmin()) {
            Duvida duvida = duvidaFacade.find(id);
            duvida.setSituacao(Situacao.PUBLICADO);
            duvidaFacade.edit(duvida);
        }else ServiceUtil.redirect("/Atlas/");
    }
}
