/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Entity.Instituicao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luancorumba
 */
@Stateless
public class InstituicaoFacade extends AbstractFacade<Instituicao> {
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public InstituicaoFacade() {
        super(Instituicao.class);
    }
    
}
