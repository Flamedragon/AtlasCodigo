/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_especie",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Especie",valueColumnName="id",initialValue=0,allocationSize=2)
public class Especie implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_especie")
    private Long id;

    @Size(min=2,max=30,message="O tamanho não confere, deve ser entre 2 e 30")
    private String genero;
    
    private String epiteto;
    
    @Lob
    @Column(length=1024)
    private String descricao;

    @ManyToOne
    private Familia familia;
    
    private String caracteristicaEcologica;

    private String subEspecie;

    private String variedade;

    private String subVariedade;

    

    private String forma;

    private String subForma;

    private String autor;
    
    private String tipoHerbarioASE;

    private Situacao situacao;
    
    

    @Embedded
    private EspecieHibrida hibrida;

    
    
    @ManyToMany (cascade= { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "especie_habitat")
    private List<Habitat> habitats;
    
    @OneToMany(mappedBy = "especie",orphanRemoval=true,fetch= FetchType.LAZY)
    private List<Detalhe> detalhes;

    @OneToMany(mappedBy = "especie",orphanRemoval=true,fetch= FetchType.LAZY)
    private List<NomePopular> apelidos;
    
    @OneToMany(mappedBy = "especie",orphanRemoval=true,fetch= FetchType.LAZY)
    private List<EstadoDeConservacao> estadoDeConservacaos;
    
    @OneToMany(mappedBy = "especie",orphanRemoval=true,fetch= FetchType.LAZY)
    private List<ImportanciaEconomica> importanciaEconomicas;
    
    @OneToMany(mappedBy = "especie",orphanRemoval=true,fetch= FetchType.LAZY)
    private List<HabitoEspecie> habitoEspecies;

    public Especie() {
        familia = new Familia();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEpiteto() {
        return epiteto;
    }

    public void setEpiteto(String epiteto) {
        this.epiteto = epiteto;
    }

    public String getCaracteristicaEcologica() {
        return caracteristicaEcologica;
    }

    public void setCaracteristicaEcologica(String impEcologica) {
        this.caracteristicaEcologica = impEcologica;
    }

    public String getSubEspecie() {
        return subEspecie;
    }

    public void setSubEspecie(String subEspecie) {
        this.subEspecie = subEspecie;
    }

    public String getVariedade() {
        return variedade;
    }

    public void setVariedade(String variedade) {
        this.variedade = variedade;
    }

    public String getSubVariedade() {
        return subVariedade;
    }

    public void setSubVariedade(String subVariedade) {
        this.subVariedade = subVariedade;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getSubForma() {
        return subForma;
    }

    public void setSubForma(String subForma) {
        this.subForma = subForma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoHerbarioASE() {
        return tipoHerbarioASE;
    }

    public void setTipoHerbarioASE(String tipoHerbarioASE) {
        this.tipoHerbarioASE = tipoHerbarioASE;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public EspecieHibrida getHibrida() {
        return hibrida;
    }

    public void setHibrida(EspecieHibrida hibrida) {
        this.hibrida = hibrida;
    }

    public List<NomePopular> getApelidos() {
        return apelidos;
    }

    public void setApelidos(List<NomePopular> apelidos) {
        this.apelidos = apelidos;
    }

    public List<Detalhe> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<Detalhe> detalhes) {
        this.detalhes = detalhes;
    }

    public List<EstadoDeConservacao> getEstadoDeConservacaos() {
        return estadoDeConservacaos;
    }

    public void setEstadoDeConservacaos(List<EstadoDeConservacao> estadoDeConservacaos) {
        this.estadoDeConservacaos = estadoDeConservacaos;
    }

    public List<ImportanciaEconomica> getImportanciaEconomicas() {
        return importanciaEconomicas;
    }

    public void setImportanciaEconomicas(List<ImportanciaEconomica> importanciaEconomicas) {
        this.importanciaEconomicas = importanciaEconomicas;
    }

    public List<HabitoEspecie> getHabitoEspecies() {
        return habitoEspecies;
    }

    public void setHabitoEspecies(List<HabitoEspecie> habitoEspecies) {
        this.habitoEspecies = habitoEspecies;
    }
    
    public boolean isPublicada(){
        return situacao!=null && situacao.equals(Situacao.PUBLICADO);
    }
    
    public boolean isPendente(){
        return situacao!=null && situacao.equals(Situacao.PENDENTE);
    }
    
    public boolean isEdicao(){
        return situacao!=null && situacao.equals(Situacao.EDICAO);
    }
    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Especie[id=" + id + "]";
    }
}
