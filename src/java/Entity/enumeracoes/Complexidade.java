/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum Complexidade {
    SIMPLES("Simples"),
    INTERMEDIARIO("Intermediario"),
    COMPLEXO("Complexo");
    
    private String nome;

    private Complexidade(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
