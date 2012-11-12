/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.enumeracoes.Ensino;
import Entity.enumeracoes.Escolaridade;
import Entity.enumeracoes.Perfil;
import Entity.enumeracoes.Sexo;
import Entity.enumeracoes.Status;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@TableGenerator(name="tabela_Usuario",table="id_generator",pkColumnName="entidades",
    pkColumnValue="Usuario",valueColumnName="id",initialValue=0,allocationSize=2)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="tabela_Usuario")
    private Long id;
    
    @Column(length=30)
    private String login;
    
    @Column(length=30)
    private String senha;
    
    private Status status;
    
    private Perfil perfil;
    
    private String nome;
    
    private String email;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtNascimento;
    
    private Sexo sexo;
    
    private Escolaridade escolaridade;
    
    private Ensino ensino;
    
    @ManyToOne
    private Cidade cidade;
    
    @ManyToMany(mappedBy = "participantes")
    @OneToMany(mappedBy = "responsavel")
    private List<Turma> turmas;
    
    @ManyToMany(mappedBy = "usuarios")
    private List<Instituicao> instituicaos;
    
    @OneToMany(mappedBy = "usuario")
    private List<RespostaAluno> respostaAlunos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Instituicao> getInstituicaos() {
        return instituicaos;
    }

    public void setInstituicaos(List<Instituicao> instituicaos) {
        this.instituicaos = instituicaos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<RespostaAluno> getRespostaAlunos() {
        return respostaAlunos;
    }

    public void setRespostaAlunos(List<RespostaAluno> respostaAlunos) {
        this.respostaAlunos = respostaAlunos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Ensino getEnsino() {
        return ensino;
    }

    public void setEnsino(Ensino ensino) {
        this.ensino = ensino;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Usuario[ id=" + id + " ]";
    }
    
}
