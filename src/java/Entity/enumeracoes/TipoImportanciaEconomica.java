/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.enumeracoes;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Kamilla
 */
@ManagedBean
public enum TipoImportanciaEconomica {
    MEDICINAL("Medicinal"),
    ALIMENTACAO("Alimentação"),
    ORNAMENTAL("Ornamental"),
    FORRAGEIRA("Forrageira"),
    MADEREIRA("Madereira"),
    MELIFERA("Melífera"),
    PIONEIRA_REVEGETACAO("Pioneira - Revegeração"),
    USO_MAGICO_RELIGIOSO("Uso Mágico/Religioso");

    private String nome;

    private TipoImportanciaEconomica(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
