/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import Entity.HabitoEspecie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kamilla
 */
@Stateless
public class HabitoEspecieFacade extends AbstractFacade<HabitoEspecie> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public HabitoEspecieFacade() {
        super(HabitoEspecie.class);
    }

}
