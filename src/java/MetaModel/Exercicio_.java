/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaModel;


import Entity.Avaliacao;
import Entity.Exercicio;
import Entity.Habitat;
import Entity.Resposta;
import Entity.RespostaAluno;
import Entity.enumeracoes.Complexidade;
import Entity.enumeracoes.Elemento;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.TipoExercicio;
import Entity.enumeracoes.Visao;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.*;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Exercicio.class)


/**
 *
 * @author Ítalo
 */
public class Exercicio_ {
    
    public static volatile SingularAttribute<Exercicio,Long> id;
    public static volatile SingularAttribute<Exercicio,String> titulo;
    public static volatile SingularAttribute<Exercicio,String> pergunta;
    public static volatile SingularAttribute<Exercicio,Situacao> situacao;
    public static volatile SingularAttribute<Exercicio,Elemento> elemento;
    public static volatile SingularAttribute<Exercicio,Visao> visao;
    public static volatile SingularAttribute<Exercicio,TipoExercicio> tipo;
    public static volatile SingularAttribute<Exercicio,Complexidade> grau;
    public static volatile SingularAttribute<Exercicio,Byte> qtCorretas;
    public static volatile SingularAttribute<Exercicio,Byte> qtIncorretas;

    public static volatile PluralAttribute<Exercicio,List<Habitat>,Habitat> habitats;
    public static volatile PluralAttribute<Exercicio,List<Resposta>,Resposta> respostas;
    public static volatile PluralAttribute<Exercicio,List<RespostaAluno>,RespostaAluno> respostaAlunos;
    public static volatile PluralAttribute<Exercicio,List<Avaliacao>,Avaliacao> avaliacoes;
    
    
    
        
}
