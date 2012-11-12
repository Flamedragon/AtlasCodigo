/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.enumeracoes;

/**
 *
 * @author Kamilla
 */
public enum Visao {
    ANATOMICA("Anatômica"),
    MORFOLOGICA("Morfológica"),
    HABITO("Hábito");
    
    private String nome;

    private Visao(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
