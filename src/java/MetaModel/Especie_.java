/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaModel;

import Entity.Detalhe;
import Entity.Especie;
import Entity.EspecieHibrida;
import Entity.EstadoDeConservacao;
import Entity.Familia;
import Entity.Habitat;
import Entity.HabitoEspecie;
import Entity.ImportanciaEconomica;
import Entity.NomePopular;
import Entity.enumeracoes.Situacao;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.*;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Especie.class)


/**
 *
 * @author Ítalo
 */
public class Especie_ {
    
    public static volatile SingularAttribute<Especie,Long> id;
    public static volatile SingularAttribute<Especie,String> genero;
    public static volatile SingularAttribute<Especie,String> epiteto;
    public static volatile SingularAttribute<Especie,String> descricao;
    public static volatile SingularAttribute<Especie,Familia> familia;
    public static volatile SingularAttribute<Especie,String> caracteristicaEcologica;
    public static volatile SingularAttribute<Especie,String> subEspecie;
    public static volatile SingularAttribute<Especie,String> variedade;
    public static volatile SingularAttribute<Especie,String> subVariedade;
    public static volatile SingularAttribute<Especie,String> forma;
    public static volatile SingularAttribute<Especie,String> subForma;
    public static volatile SingularAttribute<Especie,String> autor;
    public static volatile SingularAttribute<Especie,String> tipoHerbarioASE;
    public static volatile SingularAttribute<Especie,Situacao> situacao;
    public static volatile SingularAttribute<Especie,EspecieHibrida> hibrida;
    public static volatile PluralAttribute<Especie,List<Detalhe>,Detalhe> detalhes;
    public static volatile PluralAttribute<Especie,List<NomePopular>,NomePopular> apelidos;
    public static volatile PluralAttribute<Especie,List<EstadoDeConservacao>,EstadoDeConservacao> estadoDeConservacaos;
    public static volatile PluralAttribute<Especie,List<ImportanciaEconomica>,ImportanciaEconomica> importanciaEconomicas;
    public static volatile PluralAttribute<Especie,List<HabitoEspecie>,HabitoEspecie> habitoEspecies;
    public static volatile PluralAttribute<Especie,List<Habitat>,Habitat> habitats;
    
    
    
    
    
    
}
