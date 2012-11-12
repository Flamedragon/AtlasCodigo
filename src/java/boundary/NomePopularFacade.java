/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import Entity.NomePopular;
import Entity.enumeracoes.Situacao;
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
public class NomePopularFacade extends AbstractFacade<NomePopular> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public NomePopularFacade() {
        super(NomePopular.class);
    }

    public List<NomePopular> getPorSituacao(Situacao situacao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NomePopular> cq = cb.createQuery(NomePopular.class);
        Root<NomePopular> rt = cq.from(NomePopular.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        cq.orderBy(cb.asc(rt.get("descricao")));
        return em.createQuery(cq).getResultList();
    }
    
}
