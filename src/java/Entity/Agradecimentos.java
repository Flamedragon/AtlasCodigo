/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_agradecimentos",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Agradecimentos",valueColumnName="id",initialValue=0,allocationSize=2)
public class Agradecimentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_agradecimentos")
    private Byte id;
    
    @NotNull
    private String descricao;

    private Situacao situacao;

    public Agradecimentos() {
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof Agradecimentos)) {
            return false;
        }
        Agradecimentos other = (Agradecimentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Agradecimentos[id=" + id + "]";
    }

}
