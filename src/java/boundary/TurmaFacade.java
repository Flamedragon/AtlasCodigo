/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Entity.Turma;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luancorumba
 */
@Stateless
public class TurmaFacade extends AbstractFacade<Turma> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TurmaFacade() {
        super(Turma.class);
    }
    
}
