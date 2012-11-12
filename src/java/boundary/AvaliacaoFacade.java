/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Entity.Avaliacao;
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
 * @author luancorumba
 */
@Stateless
public class AvaliacaoFacade extends AbstractFacade<Avaliacao> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AvaliacaoFacade() {
        super(Avaliacao.class);
    }
    
    public List<Avaliacao> getPorSituacao(Situacao situacao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Avaliacao> cq = cb.createQuery(Avaliacao.class);
        Root<Avaliacao> rt = cq.from(Avaliacao.class);
        cq.where(cb.equal(rt.get("situacao"), situacao));
        return em.createQuery(cq).getResultList();
    }
    
}
