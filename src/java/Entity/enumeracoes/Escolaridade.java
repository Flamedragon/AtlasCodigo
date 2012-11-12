/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author Ítalo
 */
public enum Escolaridade {
    INFANTIL_INCOMPLETO("Infantil Incompleto"),
    INFANTIL_COMPLENTO("Infantil Completo"),
    FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto"),
    FUNDAMENTAL_COMPLETO("Fundamental Completo"),
    MEDIO_INCOMPLETO("Médio Incompleto"),
    MEDIO_COMPLETO("Médio Completo"),
    SUPERIOR_INCOMPLETO("Superior Incompleto"),
    SUPERIOR_COMPLETO("Superior Completo"),
    POS_GRADUACAO_INCOMPLETO("Pós-graduação Incompleta"),
    POS_GRADUACAO_COMPLETO("Pós-graduação Completa");
    
    
    
    private String nome;
    
    private Escolaridade(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
