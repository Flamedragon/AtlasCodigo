/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import Entity.Habitat;
import Entity.enumeracoes.Situacao;
import MetaModel.Habitat_;
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
public class HabitatFacade extends AbstractFacade<Habitat> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabitatFacade() {
        super(Habitat.class);
    }

    public List<Habitat> getPorSituacao(Situacao situacao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Habitat> cq = cb.createQuery(Habitat.class);
        Root<Habitat> rt = cq.from(Habitat.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        cq.orderBy(cb.asc(rt.get(Habitat_.nome)));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Habitat> getPorSituacaoELetra(Situacao situacao,String c) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Habitat> cq = cb.createQuery(Habitat.class);
        Root<Habitat> rt = cq.from(Habitat.class);
        cq.where(cb.and(cb.equal(rt.get("situacao"), situacao),cb.or(cb.like(rt.get(Habitat_.nome),c.toUpperCase() + "%"),
                                                                     cb.like(rt.get(Habitat_.nome),c.toLowerCase() + "%"))));
        cq.orderBy(cb.asc(rt.get(Habitat_.nome)));
        return em.createQuery(cq).getResultList();
    }
    
}
