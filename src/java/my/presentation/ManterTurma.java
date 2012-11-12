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
public class ManterTurma extends CRUDView<Turma, Byte>{

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
    }

    public Ano_Serie getAnoEscolhido() {
        return anoEscolhido;
    }

    public void setAnoEscolhido(Ano_Serie anoEscolhido) {
        this.anoEscolhido = anoEscolhido;
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
