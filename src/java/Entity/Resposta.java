/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author luancorumba
 */
@Entity
@TableGenerator(name="tabela_Resposta",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Resposta",valueColumnName="id",initialValue=0,allocationSize=2)
public class Resposta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Resposta")
    private Integer id;
    
    @Lob
    @Column(length=512)
    private String descricao;
    
    private String justificativa;
    
    private boolean flCorreta;
    
    private Situacao situacao;
    
    private Integer nota;
    
    @OneToMany (mappedBy = "resposta",orphanRemoval=true)
    private List<RespostaAluno> respostasAlunos;
    
    @ManyToOne
    private Exercicio exercicio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public boolean isFlCorreta() {
        return flCorreta;
    }

    public void setFlCorreta(boolean flCorreta) {
        this.flCorreta = flCorreta;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public List<RespostaAluno> getRespostasAlunos() {
        return respostasAlunos;
    }

    public void setRespostasAlunos(List<RespostaAluno> respostasAlunos) {
        this.respostasAlunos = respostasAlunos;
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
        if (!(object instanceof Resposta)) {
            return false;
        }
        Resposta other = (Resposta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Resposta[ id=" + id + " ]";
    }
    
}
