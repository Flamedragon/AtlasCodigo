/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Entity.Exercicio;
import Entity.enumeracoes.Situacao;
import MetaModel.Exercicio_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luancorumba
 */
@Stateless
public class ExercicioFacade extends AbstractFacade<Exercicio> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public ExercicioFacade() {
        super(Exercicio.class);
    }
    
    public List<Exercicio> getPorSituacao(Situacao situacao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exercicio> cq = cb.createQuery(Exercicio.class);
        Root<Exercicio> rt = cq.from(Exercicio.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        cq.orderBy(cb.asc(rt.get(Exercicio_.titulo)));
        
        return em.createQuery(cq).getResultList();
    }
    
    public List<Exercicio> getPorSituacaoELetra(Situacao situacao,String c) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exercicio> cq = cb.createQuery(Exercicio.class);
        Root<Exercicio> rt = cq.from(Exercicio.class);
        cq.where(cb.and(cb.equal(rt.get("situacao"), situacao),cb.or(cb.like(rt.get(Exercicio_.titulo),c.toUpperCase() + "%"),
                                                                     cb.like(rt.get(Exercicio_.titulo),c.toLowerCase() + "%"))));
        cq.orderBy(cb.asc(rt.get(Exercicio_.titulo)));
        return em.createQuery(cq).getResultList();
    }
    
}
