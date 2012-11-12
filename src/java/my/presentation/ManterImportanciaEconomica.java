/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Especie;
import Entity.ImportanciaEconomica;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoImportanciaEconomica;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import boundary.ImportanciaEconomicaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author luancorumba
 */
@ManagedBean(name="manterImportanciaEconomica")
@ViewScoped
public class ManterImportanciaEconomica  extends CRUDView<ImportanciaEconomica, Integer>{

    @EJB
    private ImportanciaEconomicaFacade importanciaDAO;
    @EJB
    private EspecieFacade especieDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;
    
    @ManagedProperty("#{manterEspecie}")
    private ManterEspecie manterEspecie;
    
    private TipoImportanciaEconomica atual = TipoImportanciaEconomica.FORRAGEIRA;
    private static final List<SelectItem> itens;

    public ManterImportanciaEconomica() {
    }

    @Override
    protected AbstractFacade<ImportanciaEconomica> getFacade() {
        return importanciaDAO;
    }

    @Override
    protected ImportanciaEconomica criarInstancia() {
        return new ImportanciaEconomica();
    }

    public TipoImportanciaEconomica getAtual() {
        return atual;
    }

    public void setAtual(TipoImportanciaEconomica atual) {
        this.atual = atual;
        getEntidade().setTipo(atual);
    }

    public List<SelectItem> getItens() {
        return itens;
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        Especie esp = especieDAO.find(perfilEspecieView.getEntidade().getId());
        getEntidade().setEspecie(esp);
        if(getEntidade().getId() == null){
            super.salva();
            esp.getImportanciaEconomicas().add(getEntidade());
            especieDAO.edit(esp);
        }
        else super.edita();
        limpa();
        atualizaListagem();
    }

    public void envia(int id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
    
    public void Publicar(int id){
        if(getSessao().isAdmin()) {
            ImportanciaEconomica imp = importanciaDAO.find(id);
            imp.setSituacao(Situacao.PUBLICADO);
            importanciaDAO.edit(imp);
        }else ServiceUtil.redirect("/Atlas/");
    }

    static{
        itens = new ArrayList<SelectItem>();
        itens.add(new SelectItem(TipoImportanciaEconomica.ALIMENTACAO, TipoImportanciaEconomica.ALIMENTACAO.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.FORRAGEIRA, TipoImportanciaEconomica.FORRAGEIRA.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.MADEREIRA, TipoImportanciaEconomica.MADEREIRA.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.MEDICINAL, TipoImportanciaEconomica.MEDICINAL.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.MELIFERA, TipoImportanciaEconomica.MELIFERA.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.ORNAMENTAL, TipoImportanciaEconomica.ORNAMENTAL.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.PIONEIRA_REVEGETACAO, TipoImportanciaEconomica.PIONEIRA_REVEGETACAO.toString()));
        itens.add(new SelectItem(TipoImportanciaEconomica.USO_MAGICO_RELIGIOSO, TipoImportanciaEconomica.USO_MAGICO_RELIGIOSO.toString()));
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
