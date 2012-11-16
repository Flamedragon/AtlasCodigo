/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.enumeracoes.Situacao;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
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
@TableGenerator(name="tabela_Avaliacao",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Avaliacao",valueColumnName="id",initialValue=0,allocationSize=2)
public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Avaliacao")
    private Integer id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInicial;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtFinal;
    
    private Situacao situacao;
    
    
    
    @ManyToOne
    private Turma turma;
    
    @OneToMany(mappedBy = "avaliacao")
    private List<RespostaAluno> respostaAlunos;
    
    @ManyToMany(cascade= { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "avaliacao_exercicio")
    private List<Exercicio> exercicios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public List<RespostaAluno> getRespostaAlunos() {
        return respostaAlunos;
    }

    public void setRespostaAlunos(List<RespostaAluno> respostaAlunos) {
        this.respostaAlunos = respostaAlunos;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return ("Avaliaçao de " + ((turma == null)?"Turma nao alocada":turma.toString()) + " de: " + dateFormat.format(dtInicial) + " a " + dateFormat.format(dtFinal));
    }
    
}
