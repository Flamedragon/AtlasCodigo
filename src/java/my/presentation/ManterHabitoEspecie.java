/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Especie;
import Entity.Habito;
import Entity.HabitoEspecie;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.HabitoEspecieFacade;
import boundary.HabitoFacade;
import java.io.*;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author luancorumba
 */
@ManagedBean(name="manterHabitoEspecie")
@ViewScoped
public class ManterHabitoEspecie extends CRUDView<HabitoEspecie, Long>{

    @EJB
    private HabitoFacade habitoFacade;

    @EJB
    private HabitoEspecieFacade habitoEspecieFacade;
    
    @EJB
    private EspecieFacade especieDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;

    @NotNull
    private int habito;
    
    private List<Habito> itens;
    
    private UploadedFile img;

    public ManterHabitoEspecie() {
    }

    @Override
    protected AbstractFacade<HabitoEspecie> getFacade() {
        return habitoEspecieFacade;
    }

    @Override
    protected HabitoEspecie criarInstancia() {
        return new HabitoEspecie();
    }

    public int getHabito() {
        return habito;
    }

    public void setHabito(int habito) {
        this.habito = habito;
    }

    public List<Habito> getItens() {
        if(itens == null){
            itens = habitoFacade.findAll();
        }
        return itens;
    }
    
    public void Publicar(long id){
        if(getSessao().isAdmin()) {
            HabitoEspecie he = habitoEspecieFacade.find(id);
            he.setSituacao(Situacao.PUBLICADO);
            habitoEspecieFacade.edit(he);
        }else ServiceUtil.redirect("/Atlas/");
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null && habito != 0){
            Especie esp = especieDAO.find(perfilEspecieView.getEntidade().getId());
            getEntidade().setEspecie(esp);
            Habito h = habitoFacade.find(habito);
            getEntidade().setHabito(h);
            super.salva();
            esp.getHabitoEspecies().add(getEntidade());
            especieDAO.edit(esp);
            h.getEspecies().add(getEntidade());
            habitoFacade.edit(h);
            getEntidade().setCaminho(getEntidade().getId() + "." + getEntidade().getFormato());
            super.edita();
            try{
                getEntidade().salvar(img.getContents());
                ServiceUtil.addInfoMessage("Imagem salva com sucesso","");
            }catch(IOException e){
                e.printStackTrace();
                remove(getEntidade().getId());
                ServiceUtil.addErroMessage("Erro ao salvar imagem", "Contate o erro aos responsáveis pelo Atlas");
            }
        } else super.edita();
        limpa();
    }

    public void envia(long id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void upload(FileUploadEvent event){
        img = event.getFile();
        getEntidade().setFormato(event.getFile().getFileName());
    }
    
    @Override
    public void edita(){
       
        
        Habito h1 = getEntidade().getHabito();
       if (img == null) {
           
           Habito h = habitoFacade.find(habito);
           getEntidade().setHabito(h);           
           super.edita();
       }
        else try{
                
                getEntidade().remover();
                getEntidade().salvar(img.getContents());
                ServiceUtil.addInfoMessage("Imagem salva com sucesso","");
                Habito h = habitoFacade.find(habito);
           getEntidade().setHabito(h);           
           super.edita();
            }catch(IOException e){
                e.printStackTrace();
                remove(getEntidade().getId());
                ServiceUtil.addErroMessage("Erro ao salvar imagem", "Contate o erro aos responsáveis pelo Atlas");
            }
       super.salva(); 
       super.edita();
    }
    
    
    @Override
    public void remove(Long codigo){
        getEntidade().remover();
        super.remove(codigo);
    }

    public PerfilEspecieView getPerfilEspecieView() {
        return perfilEspecieView;
    }

    public void setPerfilEspecieView(PerfilEspecieView perfilEspecieView) {
        this.perfilEspecieView = perfilEspecieView;
    }
    
}
