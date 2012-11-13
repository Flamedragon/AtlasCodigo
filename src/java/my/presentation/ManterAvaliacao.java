/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Avaliacao;
import Entity.Exercicio;
import Entity.Turma;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.AvaliacaoFacade;
import boundary.ExercicioFacade;
import boundary.TurmaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ítalo
 */
@ManagedBean(name="manterAvaliacao")
@ViewScoped
public class ManterAvaliacao extends CRUDView<Avaliacao, Integer>{

    @EJB
    private AvaliacaoFacade avaliacaoDAO;
    
    @EJB
    private ExercicioFacade exercicioDAO;
    
    @EJB
    private TurmaFacade turmaDAO;
    
     @ManagedProperty("#{manterExercicio}")
    private ManterExercicio manterExercicio;
     
    private Integer turmaEscolhida; 

    public ManterAvaliacao() {
    }

    @Override
    protected AbstractFacade<Avaliacao> getFacade() {
        return avaliacaoDAO;
    }

    @Override
    protected Avaliacao criarInstancia() {
        return new Avaliacao();
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
    
    public void Publicar(int id){
        if(getSessao().isAdmin()) {
            Avaliacao detalhe = avaliacaoDAO.find(id);
            detalhe.setSituacao(Situacao.PUBLICADO);
            avaliacaoDAO.edit(detalhe);
        }else ServiceUtil.redirect("/Atlas/");
    }

    public void envia(int id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }

    public ManterExercicio getManterExercicio() {
        return manterExercicio;
    }

    public void setManterExercicio(ManterExercicio manterExercicio) {
        this.manterExercicio = manterExercicio;
    }

    public Integer getTurmaEscolhida() {
        return turmaEscolhida;
    }

    public void setTurmaEscolhida(Integer turmaEscolhida) {
        this.turmaEscolhida = turmaEscolhida;
        Turma t = turmaDAO.find(turmaEscolhida);
        getEntidade().setTurma(t);
    }
    
    

    public List<SelectItem> getSelectItens(){
        ArrayList avaliacoesSelects = new ArrayList<SelectItem>();
        
         List lista = super.getListagem();
         for(int i = 0; i < lista.size(); i++){
             Avaliacao h = (Avaliacao)lista.get(i);
             avaliacoesSelects.add(new SelectItem(h.getId(), h.toString()));}
         return avaliacoesSelects;
     }
    
     public void preRender(int id){
        busca(id);
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
    
    
}
