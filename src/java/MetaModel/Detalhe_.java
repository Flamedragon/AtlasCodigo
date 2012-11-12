/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaModel;



import Entity.Detalhe;
import Entity.Especie;
import Entity.enumeracoes.Elemento;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.*;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Detalhe.class)


/**
 *
 * @author Ítalo
 */
public class Detalhe_ extends Imagem_{
    
 
    public static volatile SingularAttribute<Detalhe,String> titulo;
    public static volatile SingularAttribute<Detalhe,Situacao> situacao;
    public static volatile SingularAttribute<Detalhe,Visao> visao;
    public static volatile SingularAttribute<Detalhe,Elemento> elemento;
    public static volatile SingularAttribute<Detalhe,Especie> especie;
    
    
    
    
 
    
    
}
