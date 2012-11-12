/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author Ítalo
 */
public enum Ensino {
    INFANTIL("Ensino Infantil"),
    FUNDAMENTAL("Ensino Fundamental"),
    MEDIO("Ensino Médio"),
    SUPERIOR("Ensino Superior"),
    POS_GRADUACAO("Pós-graduação"),
    CURSINHO("Cursinho");
    
    
    
    
    private String nome;
    
    private Ensino(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
