/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Habito;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.HabitoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luancorumba
 */
@ManagedBean(name="manterHabito")
@ViewScoped
public class ManterHabito extends CRUDView<Habito, Integer>{

    @EJB
    private HabitoFacade habitoFacade;

    public ManterHabito() {
    }

    @Override
    protected AbstractFacade<Habito> getFacade() {
        return habitoFacade;
    }

    @Override
    protected Habito criarInstancia() {
        return new Habito();
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

    public void envia(int id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void Publicar(int id){
        if(getSessao().isAdmin()) {
            Habito h = habitoFacade.find(id);
            h.setSituacao(Situacao.PUBLICADO);
            habitoFacade.edit(h);
        }else ServiceUtil.redirect("/Atlas/");
    }
}
