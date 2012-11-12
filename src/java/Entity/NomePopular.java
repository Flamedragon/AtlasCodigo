/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_NomePopular",table="id_generator",pkColumnName="entidades",
    pkColumnValue="NomePopular",valueColumnName="id",initialValue=0,allocationSize=2)
public class NomePopular implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_NomePopular")
    private Integer id;

    @NotNull
    private String descricao;

    @ManyToOne
    private Especie especie;

    private Situacao situacao;

    public NomePopular() {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NomePopular)) {
            return false;
        }
        NomePopular other = (NomePopular) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NomePopular[id=" + id + "]";
    }

}
