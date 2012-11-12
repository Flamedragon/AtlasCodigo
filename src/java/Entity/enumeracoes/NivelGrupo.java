/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum NivelGrupo {
    FUNDAMENTAL("Fundamental"),
    MEDIO("Médio");
    
    private String nome;

    private NivelGrupo(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
