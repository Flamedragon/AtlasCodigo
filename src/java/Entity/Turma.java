/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.enumeracoes.Ano_Serie;
import Entity.enumeracoes.NivelGrupo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author luancorumba
 */
@Entity
@TableGenerator(name="tabela_Turma",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Turma",valueColumnName="id",initialValue=0,allocationSize=2)
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Turma")
    private Integer id;
    
    private Ano_Serie ano_serie;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInicial;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtFinal;
    
    private NivelGrupo nivel;
    
    @OneToMany(mappedBy = "turma")
    private List<Avaliacao> avaliacoes;
    
    @ManyToMany
    private List<Usuario> participantes;
    
    @ManyToOne
    private Usuario responsavel;
    
    @ManyToOne
    private Instituicao instituicao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Ano_Serie getAno_serie() {
        return ano_serie;
    }

    public void setAno_serie(Ano_Serie ano_serie) {
        this.ano_serie = ano_serie;
    }

   

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    public Date getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public NivelGrupo getNivel() {
        return nivel;
    }

    public void setNivel(NivelGrupo nivel) {
        this.nivel = nivel;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getAno_serie().toString() + " do Ensino " + (getNivel() == null?"":getNivel().toString()) +
                                                                              " do(a) professor(a) " + (getResponsavel().getNome() == null?" nao identificado ":getResponsavel().getNome());
    }
    
}
