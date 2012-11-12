/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import Entity.Agradecimentos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kamilla
 */
@Stateless
public class AgradecimentosFacade extends AbstractFacade<Agradecimentos> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AgradecimentosFacade() {
        super(Agradecimentos.class);
    }

}
