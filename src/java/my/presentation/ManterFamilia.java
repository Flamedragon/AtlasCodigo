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
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.faces.model.SelectItem;

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
    
    public List<SelectItem> getSelectItens(){
        ArrayList habitatsSelects = new ArrayList<SelectItem>();
        
         List lista = super.getListagem();
         for(int i = 0; i < lista.size(); i++){
             Familia h = (Familia)lista.get(i);            
             habitatsSelects.add(new SelectItem(h.getId(),h.getNome()));}
         return habitatsSelects;
     }
    
    public void preRender(int id){
        busca(id);
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
    
    public List<Familia> completeFamilia(String query) {  
        List<Familia> suggestions = new ArrayList<Familia>();  
        List<Familia> lista = this.getListagem();
        
        for(Familia p : lista) {  
            if(p.getNome().startsWith(query))  
                suggestions.add(p);  
        }  
          
        return suggestions;  
    }  
}
