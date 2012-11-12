/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum TipoExercicio {
    SIMPLES("Simples"),
    AVALIACAO("Avaliação");
    
    private String nome;

    private TipoExercicio(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
