/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author Ítalo
 */
public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");
   
    private String nome;
    
    private Sexo(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
