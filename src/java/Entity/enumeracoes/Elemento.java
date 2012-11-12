    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum Elemento {
    SEMENTE("Semente"),
    RAIZ("Raiz"),
    FOLHA("Folha"),
    CAULE("Caule"),
    FLOR("Flor"),
    FRUTO("Fruto");
    
    private String nome;

    private Elemento(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
