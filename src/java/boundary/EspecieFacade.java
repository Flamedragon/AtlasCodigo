/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;
import java.lang.*;
import MetaModel.Especie_;
import Entity.Especie;
//import Entity.enumeracoes.Habitat;
import Entity.enumeracoes.Situacao;
import Util.ServiceUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kamilla
 */
@Stateless
public class EspecieFacade extends AbstractFacade<Especie> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecieFacade() {
        super(Especie.class);
    }
    
    @Override
    public void remove(Especie entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        
    }
/*
    public List<Especie> getPorSituacao(Situacao situacao, int c) {
      
        String letra = ServiceUtil.getLetra(c);
        if (!letra.equals("Todos")){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        Path<String> path = rt.get("genero");
        cq.where(cb.like(path,letra+"%"));
        return em.createQuery(cq).getResultList();}
        else{
                 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        return em.createQuery(cq).getResultList();}
      
    }
  
     * 
     */
    
    
    public List<Especie> getPorSituacao(Situacao situacao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        cq.orderBy(cb.asc(rt.get(Especie_.genero)));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Especie> getPorLetra(String c) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.or(cb.like(rt.get(Especie_.genero),c.toUpperCase() + "%"),
                       cb.like(rt.get(Especie_.genero),c.toLowerCase() + "%")));
        cq.orderBy(cb.asc(rt.get(Especie_.genero)));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Especie> getPorSituacaoELetra(Situacao situacao,String c) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.and(cb.equal(rt.get("situacao"), situacao),cb.or(cb.like(rt.get(Especie_.genero),c.toUpperCase() + "%"),
                                                                     cb.like(rt.get(Especie_.genero),c.toLowerCase() + "%"))));
        cq.orderBy(cb.asc(rt.get(Especie_.genero)));
        return em.createQuery(cq).getResultList();
    }
    
    /*
    
    public List<Especie> getPorSituaçãoEHabitat(Situacao situacao, Habitat habitat) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Especie> cq = cb.createQuery(Especie.class);
        Root<Especie> rt = cq.from(Especie.class);
        cq.where(cb.and(cb.equal(rt.get("habitat"), habitat)),cb.equal(rt.get("situacao"), habitat));
        
        return em.createQuery(cq).getResultList();
    }
    */
}
