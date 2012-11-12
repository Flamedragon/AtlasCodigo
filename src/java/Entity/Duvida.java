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
import javax.persistence.TableGenerator;

/**
 *
 * @author Kamilla
 */
@Entity
@TableGenerator(name="tabela_Duvida",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Duvida",valueColumnName="id",initialValue=0,allocationSize=2)
public class Duvida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Duvida")
    private Long id;

    private String enunciado;

    private String resposta;

    private Situacao situacao;

    public Duvida() {
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
        if (!(object instanceof Duvida)) {
            return false;
        }
        Duvida other = (Duvida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Duvida[id=" + id + "]";
    }

}
