/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import Entity.ImportanciaEconomica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kamilla
 */
@Stateless
public class ImportanciaEconomicaFacade extends AbstractFacade<ImportanciaEconomica> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ImportanciaEconomicaFacade() {
        super(ImportanciaEconomica.class);
    }

}
