/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Agradecimentos;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.AgradecimentosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="manterAgradecimento")
@ViewScoped
public class ManterAgradecimento extends CRUDView<Agradecimentos, Byte>{

    @EJB
    private AgradecimentosFacade agradecimentoDAO;
    
    public ManterAgradecimento() {
    }

    @Override
    protected AbstractFacade<Agradecimentos> getFacade() {
        return agradecimentoDAO;
    }

    @Override
    protected Agradecimentos criarInstancia() {
        return new Agradecimentos();
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
    
    public void Publicar(byte id){
        if(getSessao().isAdmin()) {
            Agradecimentos especie = agradecimentoDAO.find(id);
            especie.setSituacao(Situacao.PUBLICADO);
        }else ServiceUtil.redirect("/Atlas/");
    }

    public void envia(byte id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
}
