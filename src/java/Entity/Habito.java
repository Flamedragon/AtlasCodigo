/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_Habito",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Habito",valueColumnName="id",initialValue=0,allocationSize=2)
public class Habito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Habito")
    private Integer id;
    
    //private String nome;

    private String descricao;

    private Situacao situacao;
    
    @OneToMany(mappedBy="habito",
            orphanRemoval=true,
            fetch= FetchType.LAZY)
    private List<HabitoEspecie> especies;

    public Habito() {
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

    public List<HabitoEspecie> getEspecies() {
        return especies;
    }

    public void setEspecies(List<HabitoEspecie> especies) {
        this.especies = especies;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habito)) {
            return false;
        }
        Habito other = (Habito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Habito[id=" + id + "]";
    }

}
