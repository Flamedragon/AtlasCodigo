/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Detalhe;
import Entity.Especie;
import Entity.Familia;
import Entity.Habitat;
import Entity.HabitoEspecie;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.FamiliaFacade;
import boundary.HabitatFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="especieView")
@ViewScoped
public class ManterEspecie extends CRUDView<Especie, Long>{

    @EJB
    private EspecieFacade especieDAO;

    @EJB
    private FamiliaFacade familiaDAO;
    
    @EJB
    private HabitatFacade habitatDAO;
    
    private Integer habitatEscolhido;
    private static List<SelectItem> habitats;
    
    //@ManagedProperty("#{perfilEspecieView}")
    //private PerfilEspecieView perfilEspecieView;
    
    @ManagedProperty("#{manterFamilia}")
    private ManterFamilia manterFamilia;
    
    @ManagedProperty("#{manterHabitat}")
    private ManterHabitat manterHabitat;
    
    

    public ManterEspecie() {
        System.out.println("teste");
    }

    @Override
    protected AbstractFacade<Especie> getFacade() {
        return especieDAO;
    }

    @Override
    protected Especie criarInstancia() {
        return new Especie();
    }
    
    public void adicionarHabitat(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        super.edita();
        Habitat hab = habitatDAO.find(habitatEscolhido);
        getEntidade().setSituacao(Situacao.EDICAO);
        hab.getEspecies().add(getEntidade());
        getEntidade().getHabitats().add(hab);
        habitatDAO.edit(hab);
        super.edita();
        limpa();
    }
    
       public void removeHabitat(int id){
        if((getSessao().isAdmin() || getSessao().isMonitor()) ){
           
        
            getEntidade().getHabitats().remove(id);
            super.edita();
        
       
        } else ServiceUtil.redirect("/Atlas/");
        limpa();
    }
    
    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        Familia f = familiaDAO.find(manterFamilia.getEntidade().getId());
        getEntidade().setFamilia(f);
        if(getEntidade().getId() == null){
            super.salva();
            f.getEspecies().add(getEntidade());
            familiaDAO.edit(f);
        }else{
            super.edita();
        }
        ServiceUtil.redirect("/Atlas/Especie/perfil.html?codigo="+getEntidade().getId());
        limpa();
    }
    
    public void editaFamilia (Familia fNova){
    
    getEntidade().setSituacao(Situacao.EDICAO);
    getEntidade().setFamilia(fNova);
    super.edita();
    limpa();
    atualizaListagem();

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
        //ServiceUtil.redirect("/Atlas/Especie/perfil.html?codigo="+getEntidade().getId());
        
    }
    
    
    
    
    public void editar(long id){
        if((getSessao().isAdmin() || getSessao().isMonitor()) ){
           busca(id);
       
        } else ServiceUtil.redirect("/Atlas/");
        limpa();
    }

    public void envia(long id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void publicar(long id){
        if(getSessao().isAdmin()) {
            Especie especie = especieDAO.find(id);
            especie.setSituacao(Situacao.PUBLICADO);
            especieDAO.edit(especie);
        }else ServiceUtil.redirect("/Atlas/");
    }
    
    public void remove(){   
        
        if(getEntidade().getId()!=null){
            if(!getEntidade().getDetalhes().isEmpty())                      //deleta imagens de detalhes da pasta
                for(Detalhe detalhe:getEntidade().getDetalhes())
                    detalhe.remover();
            if(!getEntidade().getHabitoEspecies().isEmpty())                //detela imagens de hábitos da pasta
                for(HabitoEspecie he :getEntidade().getHabitoEspecies())
                    he.remover();
            super.remove(getEntidade().getId());
            ServiceUtil.redirect("/Atlas/Especie/listagem");
        }
        else ServiceUtil.addErroMessage("Erro ao excluir espécie", "Tente novamente");
           
        
           }
/*
    public PerfilEspecieView getPerfilEspecieView() {
        return perfilEspecieView;
    }

    public void setPerfilEspecieView(PerfilEspecieView perfilEspecieView) {
        this.perfilEspecieView = perfilEspecieView;
    }
*/
    public ManterFamilia getManterFamilia() {
        return manterFamilia;
    }

    public void setManterFamilia(ManterFamilia manterFamilia) {
        this.manterFamilia = manterFamilia;
    }
    
     public ManterHabitat getHabitat() {
        return manterHabitat;
    }

    public void setManterHabitat(ManterHabitat manterHabitat) {
        this.manterHabitat = manterHabitat;
    }

    public Integer getHabitatEscolhido() {
        return habitatEscolhido;
    }

    public void setHabitatEscolhido(Integer habitatEscolhido) {
        this.habitatEscolhido = habitatEscolhido;
    }


    
    public List<Habitat> getHabitatsTotais(){
    return manterHabitat.getListagem();}
    
	
    
    
    
    public void preRender(long id){
        busca(id);
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
}
