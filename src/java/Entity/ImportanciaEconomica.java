/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoImportanciaEconomica;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_ImportanciaEconomica",table="id_generator",pkColumnName="entidades",
    pkColumnValue="ImpEconomica",valueColumnName="id",initialValue=0,allocationSize=2)
public class ImportanciaEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_ImportanciaEconomica")
    private Integer id;
    
    private String descricao;

    @ManyToOne
    private Especie especie;

    private TipoImportanciaEconomica tipo;

    private Situacao situacao;

    public ImportanciaEconomica() {
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoImportanciaEconomica getTipo() {
        return tipo;
    }

    public void setTipo(TipoImportanciaEconomica tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof ImportanciaEconomica)) {
            return false;
        }
        ImportanciaEconomica other = (ImportanciaEconomica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ImportanciaEconomica[id=" + id + "]";
    }

}
