/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoDeEstado;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_EstadoDeConservacao",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Estado",valueColumnName="id",initialValue=0,allocationSize=2)
public class EstadoDeConservacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_EstadoDeConservacao")
    private Integer id;

    private TipoDeEstado tipo;

    @ManyToOne(optional=false)
    private Especie especie;
   
    @ManyToOne
    private Bibliografia bibliografia;

    @Temporal(TemporalType.DATE)
    private Date dtPublicacao;

    private Situacao situacao;

    public EstadoDeConservacao() {
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public TipoDeEstado getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeEstado tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bibliografia getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(Bibliografia bibliografia) {
        this.bibliografia = bibliografia;
    }

    public Date getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Date dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
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
        if (!(object instanceof EstadoDeConservacao)) {
            return false;
        }
        EstadoDeConservacao other = (EstadoDeConservacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EstadoDeConservacao[id=" + id + "]";
    }

}
