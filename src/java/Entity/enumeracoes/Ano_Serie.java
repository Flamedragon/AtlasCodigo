/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author Ítalo
 */
public enum Ano_Serie {
    PRIMEIRA_SERIE("Primeira Série"),
    SEGUNDA_SERIE("Segunda Série"),
    TERCEIRA_SERIE("Terceira Série"),
    QUARTA_SERIE("Quarta Série"),
    QUINTA_SERIE("Quinta Série"),
    SEXTA_SERIE("Sexta Série"),
    SETIMA_SERIE("Sétima Série"),
    OITAVA_SERIE("Oitava Série"),
    NONA_SERIE("Nona Série");
    
    
    
    
    private String nome;
    
    private Ano_Serie(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
