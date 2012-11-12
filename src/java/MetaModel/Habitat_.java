/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaModel;


import Entity.Especie;
import Entity.Exercicio;
import Entity.Habitat;
import Entity.enumeracoes.Situacao;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.*;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Habitat.class)


/**
 *
 * @author Ítalo
 */
public class Habitat_ {
    
    public static volatile SingularAttribute<Habitat,Integer> id;
    public static volatile SingularAttribute<Habitat,String> nome;
    public static volatile SingularAttribute<Habitat,String> descricao;
    public static volatile SingularAttribute<Habitat,Situacao> situacao;
    public static volatile PluralAttribute<Habitat,List<Especie>,Especie> especies;
    public static volatile PluralAttribute<Habitat,List<Exercicio>,Exercicio> exercicios;
}
