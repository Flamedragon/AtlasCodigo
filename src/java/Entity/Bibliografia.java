/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_Bibliografia",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Bibliografia",valueColumnName="id",initialValue=0,allocationSize=2)
public class Bibliografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Bibliografia")
    private Byte id;

    @NotNull
    private String descricao;

    private Situacao situacao;
    
    @OneToMany(mappedBy = "bibliografia")
    private List<EstadoDeConservacao> estadoDeConservacaos;

    public Bibliografia() {
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

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public List<EstadoDeConservacao> getEstadoDeConservacaos() {
        return estadoDeConservacaos;
    }

    public void setEstadoDeConservacaos(List<EstadoDeConservacao> estadoDeConservacaos) {
        this.estadoDeConservacaos = estadoDeConservacaos;
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
        if (!(object instanceof Bibliografia)) {
            return false;
        }
        Bibliografia other = (Bibliografia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Bibliografia[id=" + id + "]";
    }

}
