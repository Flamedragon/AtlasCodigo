/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaModel;


import Entity.Especie;
import Entity.Familia;
import Entity.enumeracoes.Situacao;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.*;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Familia.class)


/**
 *
 * @author Ítalo
 */
public class Familia_ {
    
    public static volatile SingularAttribute<Familia,Integer> id;
    public static volatile SingularAttribute<Familia,String> nome;
    public static volatile SingularAttribute<Familia,String> descricao;
    public static volatile SingularAttribute<Familia,String> importanciaEconomica;
    public static volatile PluralAttribute<Familia,List<Especie>,Especie> especies;
    public static volatile SingularAttribute<Familia,Situacao> situacao;        
  
    
        
}
