/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Familia;
import Entity.enumeracoes.Situacao;
import Util.AppRedirectTo;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.FamiliaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Luan
 */
@ManagedBean(name="manterFamilia")
@ViewScoped
public class ManterFamilia extends CRUDView<Familia, Integer>{

    @EJB
    private FamiliaFacade familiaDAO;

    @Override
    protected AbstractFacade<Familia> getFacade() {
        return familiaDAO;
    }

    @Override
    protected Familia criarInstancia() {
        return new Familia();
    }
    
    
    public void remove(){   
        
        if(getEntidade().getId()!=null){
            super.remove(getEntidade().getId());
            ServiceUtil.redirect("/Atlas/Familia/listagem.html");
        }
        else ServiceUtil.addErroMessage("Erro ao excluir espécie", "Tente novamente");
           
        
           }
    
    
    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null) 
            super.salva();
        else 
            super.edita();
        limpa();
        atualizaListagem();
    }

    public void envia(){
        if(getEntidade().getId()==null){
            ServiceUtil.addErroMessage("Ocorreu algum erro na requisição", "Tente de novo ou contate o responsável pelo sistema");
            return;
        }
        if(getSessao().isAdmin()||getSessao().isMonitor()){
            getEntidade().setSituacao(Situacao.PENDENTE);
            super.edita();
        } else ServiceUtil.redirect("/Atlas/");
    }
    
    public void publicar(){
        if(getEntidade().getId()==null){
            ServiceUtil.addErroMessage("Ocorreu algum erro na requisição", "Tente de novo ou contate o responsável pelo sistema");
            return;
        }
        if(getSessao().isAdmin()) {
            getEntidade().setSituacao(Situacao.PUBLICADO);
            super.edita();
        }else ServiceUtil.redirect("/Atlas/");
    }
    
    public String novaEspecie(long id){
        getSessao().setParametro(id);
        return AppRedirectTo.ESPECIE_FORM;
    }
    
    public void preRender(int id){
        busca(id);
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
}
