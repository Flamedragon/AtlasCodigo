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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Ítalo
 */
@Entity
@TableGenerator(name="tabela_Habitat",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Habitat",valueColumnName="id",initialValue=0,allocationSize=2)
public class Habitat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Habitat")
    private Integer id;

    
    
    private String nome;

    private Situacao situacao;
    
    private String descricao;
    
    
    
    @ManyToMany (cascade= { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="habitats")
    @JoinTable(name = "especie_habitat")
    private List<Especie> especies;
    
    @ManyToMany (cascade= { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "habitat_exercicio")
    private List<Exercicio> exercicios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    
    public List<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(List<Especie> especies) {
        this.especies = especies;
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
        if (!(object instanceof Habitat)) {
            return false;
        }
        Habitat other = (Habitat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Habitat[ id=" + id + " ]";
    }
    
}
