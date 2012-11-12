/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Entity.Avaliacao;
import Entity.Detalhe;
import Entity.enumeracoes.Elemento;
import Entity.Especie;
import Entity.Exercicio;
import Entity.Familia;
import Entity.Habitat;
import Entity.NomePopular;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import boundary.AvaliacaoFacade;
import boundary.DetalheFacade;
import boundary.EspecieFacade;
import boundary.ExercicioFacade;
import boundary.FamiliaFacade;
import boundary.HabitatFacade;
import boundary.NomePopularFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author luancorumba
 */
@ManagedBean
@RequestScoped
public class ListagemView {
    
    @EJB
    private EspecieFacade especieDAO;
    
    @EJB
    private FamiliaFacade familiaDAO;
    
    @EJB
    private NomePopularFacade nomeDAO;
    
    @EJB
    private DetalheFacade detalheDAO;
    
    @EJB
    private HabitatFacade habitatDAO;
    
    @EJB
    private ExercicioFacade exercicioDAO;
    
    @EJB 
    private AvaliacaoFacade avaliacaoDAO;
    
    private String letra = "";
    
    
    private boolean mostraEdicao;
    private boolean mostraPendente;
    private boolean mostraPublicados;
    private boolean mostraCaule;
    private boolean mostraFolha;
    private boolean mostraFlor;
    private boolean mostraRaiz;
    private boolean mostraFruto;
    private boolean mostraSemente;
    private boolean mostraAnatomica;
    private boolean mostraMorfologica;
    private boolean mostraHabito;
    private boolean mostraSimples;
    private boolean mostraAvaliacao;
    private boolean mostraIniciante;
    private boolean mostraIntermediario;
    private boolean mostraAvancado;
    
    public ListagemView() {
        
    }
    //planejar solução com groupBy
    public List<Especie> getEspeciesEmEdicao(){
        return especieDAO.getPorSituacao(Situacao.EDICAO);
    }
    
    public List<Especie> getEspeciesEmEdicaoLetra(){
        if (letra.equals("")) {return especieDAO.getPorSituacao(Situacao.EDICAO); }
        else return especieDAO.getPorSituacaoELetra(Situacao.EDICAO,letra);    
    }
    
    public List<Especie> getEspeciesPendentes(){
        return especieDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
    public List<Especie> getEspeciesPendentesLetra(){
        if (letra.equals("")) {return especieDAO.getPorSituacao(Situacao.PENDENTE);}
        else return especieDAO.getPorSituacaoELetra(Situacao.PENDENTE,letra);
    }
    
    public List<Especie> getEspeciesPublicadas(){
        return especieDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
    public List<Especie> getEspeciesPublicadasLetra(){
        if (letra.equals("")) {return especieDAO.getPorSituacao(Situacao.PUBLICADO);}
        else return especieDAO.getPorSituacaoELetra(Situacao.PUBLICADO,letra);
    }
    
    
    
    
    public List<Familia> getFamiliasEmEdicao(){
        return familiaDAO.getPorSituacao(Situacao.EDICAO);
    }
    
    public List<Familia> getFamiliasEmEdicaoLetra(){
        if (letra.equals("")) {return familiaDAO.getPorSituacao(Situacao.EDICAO);}
        else return familiaDAO.getPorSituacaoELetra(Situacao.EDICAO,letra);
    }
    
    public List<Familia> getFamiliasPendentes(){
        return familiaDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
    
    public List<Familia> getFamiliasPendentesLetra(){
        if (letra.equals("")) {return familiaDAO.getPorSituacao(Situacao.PENDENTE);}
            else return familiaDAO.getPorSituacaoELetra(Situacao.PENDENTE,letra);
    }
    
    
    public List<Familia> getFamiliasPublicadas(){
        return familiaDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
    
    public List<Familia> getFamiliasPublicadasLetra(){
        if (letra.equals("")) {return familiaDAO.getPorSituacao(Situacao.PUBLICADO);}
        else return familiaDAO.getPorSituacaoELetra(Situacao.PUBLICADO,letra);
    }
    

    public List<NomePopular> getNomesPopularesEmEdicao(){
        return nomeDAO.getPorSituacao(Situacao.EDICAO);}
    
    
    public List<NomePopular> getNomesPopularesPendentes(){
        return nomeDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
    public List<NomePopular> getNomesPopularesPublicados(){
        return nomeDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
    public List<Detalhe> getMorfologiasRaiz(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.RAIZ);
    }
    
    public List<Detalhe> getMorfologiasRaizLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.RAIZ,letra);
    }
    
    public List<Detalhe> getMorfologiasCaule(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.CAULE);
    }
    public List<Detalhe> getMorfologiasCauleLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.CAULE,letra);
    }
    
    public List<Detalhe> getMorfologiasFolha(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.FOLHA);
    }
    
    public List<Detalhe> getMorfologiasFolhaLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.FOLHA,letra);
    }
    
    public List<Detalhe> getMorfologiasFlor(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.FLOR);
    }
    
    public List<Detalhe> getMorfologiasFlorLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.FLOR,letra);
    }
    
    public List<Detalhe> getMorfologiasFruto(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.FRUTO);
    }
    
    public List<Detalhe> getMorfologiasFrutoLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.FRUTO,letra);
    }
    
    public List<Detalhe> getMorfologiasSemente(){
        return detalheDAO.getPorVisaoEElemento(Visao.MORFOLOGICA, Elemento.SEMENTE);
    }
    
    public List<Detalhe> getMorfologiasSementeLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.MORFOLOGICA, Elemento.SEMENTE,letra);
    }
    
    public List<Detalhe> getAnatomiasRaiz(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.RAIZ);
    }
    
    public List<Detalhe> getAnatomiasRaizLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.RAIZ,letra);
    }
    
    public List<Detalhe> getAnatomiasCaule(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.CAULE);
    }
    
    public List<Detalhe> getAnatomiasCauleLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.CAULE,letra);
    }
    
    public List<Detalhe> getAnatomiasFolha(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.FOLHA);
    }
    
    
    public List<Detalhe> getAnatomiasFolhaLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.FOLHA,letra);
    }
    
    public List<Detalhe> getAnatomiasFlor(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.FLOR);
    }
    
    
    public List<Detalhe> getAnatomiasFlorLetra(){
        List<Detalhe> s = detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.FLOR,letra);    
        return s;
    }
    
    public List<Detalhe> getAnatomiasFruto(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.FRUTO);
    }
    
    
    public List<Detalhe> getAnatomiasFrutoLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.FRUTO,letra);
    }
    
    public List<Detalhe> getAnatomiasSemente(){
        return detalheDAO.getPorVisaoEElemento(Visao.ANATOMICA, Elemento.SEMENTE);
    }
    
    public List<Detalhe> getAnatomiasSementeLetra(){
        return detalheDAO.getPorSituacaoELetra(Visao.ANATOMICA, Elemento.SEMENTE,letra);
    }
    
    public List<Habitat> getHabitatsEmEdicao(){
        return habitatDAO.getPorSituacao(Situacao.EDICAO);
    }
    
    public List<Habitat> getHabitatsEmEdicaoLetra(){
        if (letra.equals("")) {return habitatDAO.getPorSituacao(Situacao.EDICAO);}
        else return habitatDAO.getPorSituacaoELetra(Situacao.EDICAO,letra);
    }
    
    public List<Habitat> getHabitatsPendentes(){
        return habitatDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
     public List<Habitat> getHabitatsPendentesLetra(){
        if (letra.equals("")) {return habitatDAO.getPorSituacao(Situacao.PENDENTE);}
        else return habitatDAO.getPorSituacaoELetra(Situacao.PENDENTE,letra);
    }
    
    public List<Habitat> getHabitatsPublicados(){
        return habitatDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
     public List<Habitat> getHabitatsPublicadosLetra(){
        if (letra.equals("")) {return habitatDAO.getPorSituacao(Situacao.PUBLICADO);}
        else return habitatDAO.getPorSituacaoELetra(Situacao.PUBLICADO,letra);
    }
    
    public List<Exercicio> getExerciciosEmEdicao(){
        return exercicioDAO.getPorSituacao(Situacao.EDICAO);
    }
    
    public List<Exercicio> getExerciciosEmEdicaoLetra(){
        if (letra.equals("")) {return exercicioDAO.getPorSituacao(Situacao.EDICAO);}
        else return exercicioDAO.getPorSituacaoELetra(Situacao.EDICAO,letra);
    }
    
    public List<Exercicio> getExerciciosPendentes(){
        return exercicioDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
    public List<Exercicio> getExerciciosPendentesLetra(){
        if (letra.equals("")) {return exercicioDAO.getPorSituacao(Situacao.PENDENTE);}
        else return exercicioDAO.getPorSituacaoELetra(Situacao.PENDENTE,letra);
    }
    
    public List<Exercicio> getExercicioPublicados(){
        return exercicioDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
    public List<Exercicio> getExerciciosPublicadosLetra(){
        if (letra.equals("")) {return exercicioDAO.getPorSituacao(Situacao.PUBLICADO);}
        else return exercicioDAO.getPorSituacaoELetra(Situacao.PUBLICADO,letra);
    }
    
    public List<Avaliacao> getAvaliacoesEmEdicao(){
        return avaliacaoDAO.getPorSituacao(Situacao.EDICAO);
    }
    
    public List<Avaliacao> getAvaliacoesPendentes(){
        return avaliacaoDAO.getPorSituacao(Situacao.PENDENTE);
    }
    
    public List<Avaliacao> getAvaliacoesPublicados(){
        return avaliacaoDAO.getPorSituacao(Situacao.PUBLICADO);
    }
    
    
  

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean isMostraEdicao() {
        return mostraEdicao;
    }

    public void setMostraEdicao(boolean mostraEdicao) {
        this.mostraEdicao = mostraEdicao;
    }

    public boolean isMostraPendente() {
        return mostraPendente;
    }

    public void setMostraPendente(boolean mostraPendente) {
        this.mostraPendente = mostraPendente;
    }

    public boolean isMostraPublicados() {
        return mostraPublicados;
    }

    public void setMostraPublicados(boolean mostraPublicados) {
        this.mostraPublicados = mostraPublicados;
    }

    public boolean isMostraAnatomica() {
        return mostraAnatomica;
    }

    public void setMostraAnatomica(boolean mostraAnatomica) {
        this.mostraAnatomica = mostraAnatomica;
    }

    public boolean isMostraCaule() {
        return mostraCaule;
    }

    public void setMostraCaule(boolean mostraCaule) {
        this.mostraCaule = mostraCaule;
    }

    public boolean isMostraFlor() {
        return mostraFlor;
    }

    public void setMostraFlor(boolean mostraFlor) {
        this.mostraFlor = mostraFlor;
    }

    public boolean isMostraFolha() {
        return mostraFolha;
    }

    public void setMostraFolha(boolean mostraFolha) {
        this.mostraFolha = mostraFolha;
    }

    public boolean isMostraFruto() {
        return mostraFruto;
    }

    public void setMostraFruto(boolean mostraFruto) {
        this.mostraFruto = mostraFruto;
    }

    public boolean isMostraHabito() {
        return mostraHabito;
    }

    public void setMostraHabito(boolean mostraHabito) {
        this.mostraHabito = mostraHabito;
    }

    public boolean isMostraMorfologica() {
        return mostraMorfologica;
    }

    public void setMostraMorfologica(boolean mostraMorfologica) {
        this.mostraMorfologica = mostraMorfologica;
    }

    public boolean isMostraRaiz() {
        return mostraRaiz;
    }

    public void setMostraRaiz(boolean mostraRaiz) {
        this.mostraRaiz = mostraRaiz;
    }

    public boolean isMostraSemente() {
        return mostraSemente;
    }

    public void setMostraSemente(boolean mostraSemente) {
        this.mostraSemente = mostraSemente;
    }

    public boolean isMostraAvaliacao() {
        return mostraAvaliacao;
    }

    public void setMostraAvaliacao(boolean mostraAvaliacao) {
        this.mostraAvaliacao = mostraAvaliacao;
    }

    public boolean isMostraAvancado() {
        return mostraAvancado;
    }

    public void setMostraAvancado(boolean mostraAvancado) {
        this.mostraAvancado = mostraAvancado;
    }

    public boolean isMostraIniciante() {
        return mostraIniciante;
    }

    public void setMostraIniciante(boolean mostraIniciante) {
        this.mostraIniciante = mostraIniciante;
    }

    public boolean isMostraIntermediario() {
        return mostraIntermediario;
    }

    public void setMostraIntermediario(boolean mostraIntermediario) {
        this.mostraIntermediario = mostraIntermediario;
    }

    public boolean isMostraSimples() {
        return mostraSimples;
    }

    public void setMostraSimples(boolean mostraSimples) {
        this.mostraSimples = mostraSimples;
    }
    
    
    
    
}
