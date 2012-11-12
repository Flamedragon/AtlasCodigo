/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Especie;
import Entity.NomePopular;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.NomePopularFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luancorumba
 */
@ManagedBean(name="manterNomePopular")
@ViewScoped
public class ManterNomePopular extends CRUDView<NomePopular, Integer>{

    @EJB
    private NomePopularFacade nomePopularFacade;
    
    @EJB
    private EspecieFacade especieDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;
    
    @ManagedProperty("#{manterEspecie}")
    private ManterEspecie manterEspecie;
    

    public ManterNomePopular() {
    }

    @Override
    protected AbstractFacade<NomePopular> getFacade() {
        return nomePopularFacade;
    }

    @Override
    protected NomePopular criarInstancia() {
        return new NomePopular();
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        Especie esp = especieDAO.find(perfilEspecieView.getEntidade().getId());
        getEntidade().setSituacao(Situacao.EDICAO);
        getEntidade().setEspecie(esp);
        if(getEntidade().getId() == null){
            super.salva();
            esp.getApelidos().add(getEntidade());
            especieDAO.edit(esp);
        }
        else super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void Publicar(int id){
        if(getSessao().isAdmin()) {
            NomePopular nome = nomePopularFacade.find(id);
            nome.setSituacao(Situacao.PUBLICADO);
            nomePopularFacade.edit(nome);
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
    
    
}
