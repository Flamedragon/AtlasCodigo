/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Turma;
import Entity.enumeracoes.Ano_Serie;
import Entity.enumeracoes.NivelGrupo;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoExercicio;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.TurmaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="manterTurma")
@ViewScoped
public class ManterTurma extends CRUDView<Turma, Integer>{

    @EJB
    private TurmaFacade turmaDAO;
    
    private NivelGrupo nivelEscolhido;
    private Ano_Serie anoEscolhido;
    
    
    private static final List<SelectItem> seriesFundamental;
    private static final List<SelectItem> seriesMedio;
    
    
    public ManterTurma() {
    }

    @Override
    protected AbstractFacade<Turma> getFacade() {
        return turmaDAO;
    }

    @Override
    protected Turma criarInstancia() {
        return new Turma();
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        
        if(getEntidade().getId() == null) super.salva();
        else super.edita();
        limpa();
        atualizaListagem();
    }

    
     public List<SelectItem> getNiveis()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (NivelGrupo item: NivelGrupo.values())
   {
     items.add(new SelectItem(item, item.toString()));
   }
return items;
  }

    public static List<SelectItem> getSeriesFundamental() {
        return seriesFundamental;
    }

    public static List<SelectItem> getSeriesMedio() {
        return seriesMedio;
    }

    public NivelGrupo getNivelEscolhido() {
        return nivelEscolhido;
    }

    public void setNivelEscolhido(NivelGrupo nivelEscolhido) {
        this.nivelEscolhido = nivelEscolhido;
        getEntidade().setNivel(nivelEscolhido);
    }

    public Ano_Serie getAnoEscolhido() {
        return anoEscolhido;
    }

    public void setAnoEscolhido(Ano_Serie anoEscolhido) {
        this.anoEscolhido = anoEscolhido;
        getEntidade().setAno_serie(anoEscolhido);
    }
    
    @Override
   public String toString(){
     return getEntidade().getAno_serie().toString() + " do Ensino " + (getEntidade().getNivel() == null?"":getEntidade().getNivel().toString()) +
                                                                              " do(a) professor(a) " + (getEntidade().getResponsavel().getNome() == null?" nao identificado ":getEntidade().getResponsavel().getNome());
    }
   
    public List<SelectItem> getSelectItens(){
        ArrayList habitatsSelects = new ArrayList<SelectItem>();
        
         List lista = super.getListagem();
         for(int i = 0; i < lista.size(); i++){
             Turma h = (Turma)lista.get(i);
             
             // Adicionar ao add instituiçao quando completado;
             habitatsSelects.add(new SelectItem(h.getId(), h.getAno_serie().toString() + " do Ensino " + (h.getNivel() == null?"":h.getNivel().toString()) +
                                                                              " do(a) professor(a) " + (h.getResponsavel().getNome() == null?" nao identificado ":h.getResponsavel().getNome())
                                                                              ));}
         return habitatsSelects;
     }
    
      
     static{
        seriesFundamental = new ArrayList<SelectItem>();
        seriesFundamental.add(new SelectItem(Ano_Serie.PRIMEIRA_SERIE, Ano_Serie.PRIMEIRA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.SEGUNDA_SERIE, Ano_Serie.SEGUNDA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.TERCEIRA_SERIE, Ano_Serie.TERCEIRA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.QUARTA_SERIE, Ano_Serie.QUARTA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.QUINTA_SERIE, Ano_Serie.QUINTA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.SEXTA_SERIE, Ano_Serie.SEXTA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.SETIMA_SERIE, Ano_Serie.SETIMA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.OITAVA_SERIE, Ano_Serie.OITAVA_SERIE.toString()));
        seriesFundamental.add(new SelectItem(Ano_Serie.NONA_SERIE, Ano_Serie.NONA_SERIE.toString()));
       
        seriesMedio = new ArrayList<SelectItem>();
        seriesMedio.add(new SelectItem(Ano_Serie.PRIMEIRA_SERIE, Ano_Serie.PRIMEIRA_SERIE.toString()));
        seriesMedio.add(new SelectItem(Ano_Serie.SEGUNDA_SERIE, Ano_Serie.SEGUNDA_SERIE.toString()));
        seriesMedio.add(new SelectItem(Ano_Serie.TERCEIRA_SERIE, Ano_Serie.TERCEIRA_SERIE.toString()));
    }

    }
