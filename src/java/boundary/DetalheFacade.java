/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;


import java.lang.*;
import Entity.Detalhe;
import Entity.enumeracoes.Elemento;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import MetaModel.Detalhe_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kamilla
 */
@Stateless
public class DetalheFacade extends AbstractFacade<Detalhe> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalheFacade() {
        super(Detalhe.class);
    }
    
    

    

    public List<Detalhe> getPorVisaoEElemento(Visao visao, Elemento elemento) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Detalhe> cq = cb.createQuery(Detalhe.class);
        Root<Detalhe> rt = cq.from(Detalhe.class);
        cq.where(cb.and(cb.equal(rt.get("visao"), visao),cb.equal(rt.get("elemento"), elemento)));
        cq.orderBy(cb.asc(rt.get(Detalhe_.titulo)));
        return em.createQuery(cq).getResultList();
    }
    
     public List<Detalhe> getPorSituacaoELetra(Visao visao,Elemento elemento,String c) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Detalhe> cq = cb.createQuery(Detalhe.class);
        Root<Detalhe> rt = cq.from(Detalhe.class);
        cq.where(cb.and(cb.equal(rt.get("visao"), visao),cb.equal(rt.get("elemento"), elemento),cb.or(cb.like(rt.get(Detalhe_.titulo),c.toUpperCase() + "%"),
                                                                     cb.like(rt.get(Detalhe_.titulo),c.toLowerCase() + "%"))));
        cq.orderBy(cb.asc(rt.get(Detalhe_.titulo)));
        return em.createQuery(cq).getResultList();
    }
    
}
