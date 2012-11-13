/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;


import Entity.Roteiro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Italo
 */
@Stateless
public class RoteiroFacade extends AbstractFacade<Roteiro> {
    
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoteiroFacade() {
        super(Roteiro.class);
    }
    
}
