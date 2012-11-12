/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Complexidade;
import Entity.enumeracoes.Elemento;
import Entity.enumeracoes.TipoExercicio;
import Entity.enumeracoes.Visao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author luancorumba
 */

@Entity
@DiscriminatorValue("E")
//@TableGenerator(name="tabela_Exercicio",table="id_generator",pkColumnName="entidades",
//  pkColumnValue="Exercicio",valueColumnName="id",initialValue=0,allocationSize=2)
public class Exercicio extends Imagem implements Serializable { //Se a imagem for obrigatória no exercícios, deverá haver herança
    private static final long serialVersionUID = 1L;
    
 
    
    @Lob
    @Column(length=1024)
    private String pergunta;
    
    private String titulo;
        
    private Situacao situacao;
    
    private Elemento elemento;
    
    
    
    
    
    private Visao visao;
    
    
    
    
    //Nao tem no Antigo
    private TipoExercicio tipo;
    //Nao tem no Antigo
    private Complexidade grau;
    //Nao tem no Antigo
    private byte qtCorretas;
    //Nao tem no Antigo
    private byte qtIncorretas;
    
    
    
    @OneToMany(mappedBy = "exercicio", orphanRemoval=true)
    private List<Resposta> respostas;
    
    @OneToMany(mappedBy = "exercicio", orphanRemoval=true)
    private List<RespostaAluno> respostaAlunos;
    
    @ManyToMany (cascade= { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="exercicios")
    @JoinTable(name = "avaliacao_exercicio")
    private List<Avaliacao> avaliacoes;

    @ManyToMany (cascade= { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="exercicios")
    @JoinTable(name = "habitat_exercicio")
    private List<Habitat> habitats;




    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Complexidade getGrau() {
        return grau;
    }

    public void setGrau(Complexidade grau) {
        this.grau = grau;
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }


    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public byte getQtCorretas() {
        return qtCorretas;
    }

    public void setQtCorretas(byte qtCorretas) {
        this.qtCorretas = qtCorretas;
    }

    public byte getQtIncorretas() {
        return qtIncorretas;
    }

    public void setQtIncorretas(byte qtIncorretas) {
        this.qtIncorretas = qtIncorretas;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

    public List<RespostaAluno> getRespostaAlunos() {
        return respostaAlunos;
    }

    public void setRespostaAlunos(List<RespostaAluno> respostaAlunos) {
        this.respostaAlunos = respostaAlunos;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }

    public Visao getVisao() {
        return visao;
    }

    public void setVisao(Visao visao) {
        this.visao = visao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    

  

 
}
