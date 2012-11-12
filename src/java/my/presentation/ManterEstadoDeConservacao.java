/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Bibliografia;
import Entity.Especie;
import Entity.EstadoDeConservacao;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoDeEstado;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.BibliografiaFacade;
import boundary.EspecieFacade;
import boundary.EstadoDeConservacaoFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Luan
 */
@ManagedBean(name="manterEstadoDeConservacao")
@ViewScoped
public class ManterEstadoDeConservacao extends CRUDView<EstadoDeConservacao, Integer>{

    @EJB
    private EstadoDeConservacaoFacade estadoDeConservacaoFacade;
    @EJB
    private EspecieFacade especieDAO;
    @EJB
    private BibliografiaFacade bibliDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;
    
    private TipoDeEstado atual = TipoDeEstado.NAO_AMEACADA;
    private static final List<SelectItem> itens;

    private int biblio;
    private List<Bibliografia> biblios;
    
    public ManterEstadoDeConservacao() {
    }

    @Override
    protected AbstractFacade<EstadoDeConservacao> getFacade() {
        return estadoDeConservacaoFacade;
    }

    @Override
    protected EstadoDeConservacao criarInstancia() {
        return new EstadoDeConservacao();
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        getEntidade().setTipo(atual);
        getEntidade().setDtPublicacao(new Date());
        Especie e = especieDAO.find(perfilEspecieView.getEntidade().getId());
        getEntidade().setEspecie(e);
        if(getEntidade().getId() == null) super.salva();
        else super.edita();
        e.getEstadoDeConservacaos().add(getEntidade());
        especieDAO.edit(e);
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
            EstadoDeConservacao estado = estadoDeConservacaoFacade.find(id);
            estado.setSituacao(Situacao.PUBLICADO);
            estadoDeConservacaoFacade.edit(estado);
        }else ServiceUtil.redirect("/Atlas/");
    }

    public TipoDeEstado getAtual() {
        return atual;
    }

    public void setAtual(TipoDeEstado atual) {
        this.atual = atual;
    }

    public List<SelectItem> getItens() {
        return itens;
    }

    public int getBiblio() {
        return biblio;
    }

    public void setBiblio(int biblio) {
        this.biblio = biblio;
    }

    public List<Bibliografia> getBiblios() {
        return bibliDAO.findAll();
    }

    public void setBiblios(List<Bibliografia> biblios) {
        this.biblios = biblios;
    }

    static{
        itens = new ArrayList<SelectItem>();
        itens.add(new SelectItem(TipoDeEstado.EXTINTA, TipoDeEstado.EXTINTA.toString()));
        itens.add(new SelectItem(TipoDeEstado.AMEACADA_CRITICAMENTE_EM_PERIGO, TipoDeEstado.AMEACADA_CRITICAMENTE_EM_PERIGO.toString()));
        itens.add(new SelectItem(TipoDeEstado.AMEACADA_EM_PERIGO, TipoDeEstado.AMEACADA_EM_PERIGO.toString()));
        itens.add(new SelectItem(TipoDeEstado.AMEACADA_VUNERAVEL, TipoDeEstado.AMEACADA_VUNERAVEL.toString()));
        itens.add(new SelectItem(TipoDeEstado.DADOS_INSUFICIENTES, TipoDeEstado.DADOS_INSUFICIENTES.toString()));
        itens.add(new SelectItem(TipoDeEstado.EXTINTA_NA_NATUREZA, TipoDeEstado.EXTINTA_NA_NATUREZA.toString()));
        itens.add(new SelectItem(TipoDeEstado.EXTINTA_REGIONALMENTE, TipoDeEstado.EXTINTA_REGIONALMENTE.toString()));
        itens.add(new SelectItem(TipoDeEstado.NAO_AMEACADA, TipoDeEstado.NAO_AMEACADA.toString()));
        itens.add(new SelectItem(TipoDeEstado.NAO_AVALIADA, TipoDeEstado.NAO_AVALIADA.toString()));
        itens.add(new SelectItem(TipoDeEstado.QUASE_AMEACADA, TipoDeEstado.QUASE_AMEACADA.toString()));
    }

    public PerfilEspecieView getPerfilEspecieView() {
        return perfilEspecieView;
    }

    public void setPerfilEspecieView(PerfilEspecieView perfilEspecieView) {
        this.perfilEspecieView = perfilEspecieView;
    }
}