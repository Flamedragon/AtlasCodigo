/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Exercicio;
import Entity.Resposta;
import Entity.enumeracoes.Situacao;
import Util.AppRedirectTo;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.ExercicioFacade;
import boundary.FamiliaFacade;
import boundary.RespostaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Luan
 */
@ManagedBean(name="manterResposta")
@ViewScoped
public class ManterResposta extends CRUDView<Resposta, Integer>{

    @EJB
    private RespostaFacade respostaDAO;
    
    @EJB
    private ExercicioFacade exercicioDAO;
    
    @ManagedProperty("#{manterExercicio}")
    private ManterExercicio manterExercicio;
    

    

    @Override
    protected AbstractFacade<Resposta> getFacade() {
        return respostaDAO;
    }

    @Override
    protected Resposta criarInstancia() {
        return new Resposta();
    }
   
    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        Exercicio e = exercicioDAO.find(manterExercicio.getEntidade().getId());
        getEntidade().setExercicio(e);
        if(getEntidade().getId() == null) super.salva();
        else super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void buscaEd(Integer i){
    
        if (i != null){super.busca(i);
     
        
        
        
        }
        
    }
    
      public void salvaEd(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null){
            super.salva();
        }else{
            super.edita();
        }
        limpa();
        atualizaListagem();
        ServiceUtil.redirect("/Atlas/Exercicio/perfil.html?codigo="+manterExercicio.getEntidade().getId());
        
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

    public ManterExercicio getManterExercicio() {
        return manterExercicio;
    }

    public void setManterExercicio(ManterExercicio manterExercicio) {
        this.manterExercicio = manterExercicio;
    }
    
    
    
    public void preRender(int id){
        busca(id);
               
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
}
