/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Entity.Imagem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luancorumba
 */
@Stateless
public class ImagemFacade extends AbstractFacade<Imagem> {
    
    @PersistenceContext(unitName = "AtlasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagemFacade() {
        super(Imagem.class);
    }
    
}
