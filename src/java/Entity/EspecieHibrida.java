/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamilla
 */
@Embeddable
public class EspecieHibrida implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String generoHibrido;

    @NotNull
    private String epitetoHibrido;

    public EspecieHibrida() {
    }

    public String getEpitetoHibrido() {
        return epitetoHibrido;
    }

    public void setEpitetoHibrido(String epitetoHibrido) {
        this.epitetoHibrido = epitetoHibrido;
    }

    public String getGeneroHibrido() {
        return generoHibrido;
    }

    public void setGeneroHibrido(String generoHibrido) {
        this.generoHibrido = generoHibrido;
    }

}
