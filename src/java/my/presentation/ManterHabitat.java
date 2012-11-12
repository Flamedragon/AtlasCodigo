/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Especie;
import Entity.Habitat;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.HabitatFacade;
import java.lang.reflect.Method;
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
@ManagedBean(name="manterHabitat")
@ViewScoped
public class ManterHabitat extends CRUDView<Habitat, Integer>{

    @EJB
    private HabitatFacade HabitatFacade;
    
    @EJB
    private EspecieFacade especieDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;
    
    @ManagedProperty("#{manterEspecie}")
    private ManterEspecie manterEspecie;

    public ManterHabitat() {
    }

    @Override
    protected AbstractFacade<Habitat> getFacade() {
        return HabitatFacade;
    }

    @Override
    protected Habitat criarInstancia() {
        return new Habitat();
    }
    
    public void adicionarHabitat(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        Especie esp = especieDAO.find(perfilEspecieView.getEntidade().getId());
        especieDAO.edit(esp);
        getEntidade().setSituacao(Situacao.EDICAO);
        getEntidade().getEspecies().add(esp);
        super.edita();
        esp.getHabitats().add(getEntidade());
        especieDAO.edit(esp);
        super.edita();
        limpa();
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
            Habitat nome = HabitatFacade.find(id);
            nome.setSituacao(Situacao.PUBLICADO);
            HabitatFacade.edit(nome);
        }else ServiceUtil.redirect("/Atlas/");
    }

    public void envia(int id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }

    public PerfilEspecieView getPerfilEspecieView() {
        return perfilEspecieView;
    }

    public void setPerfilEspecieView(PerfilEspecieView perfilEspecieView) {
        this.perfilEspecieView = perfilEspecieView;
    }

    public ManterEspecie getManterEspecie() {
        return manterEspecie;
    }

    public void setManterEspecie(ManterEspecie manterEspecie) {
        this.manterEspecie = manterEspecie;
    }
    
     public void preRender(int id){
        busca(id);
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
    
     public List<SelectItem> getSelectItens(){
        ArrayList habitatsSelects = new ArrayList<SelectItem>();
        
         List lista = super.getListagem();
         for(int i = 0; i < lista.size(); i++){
             Habitat h = (Habitat)lista.get(i);
             habitatsSelects.add(new SelectItem(h.getId(), h.getNome()));}
         return habitatsSelects;
     }
    
}
