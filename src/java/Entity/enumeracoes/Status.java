/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum Status {
    ATIVO("Ativo"),
    INATIVO("Inativo");
    
    private String nome;

    private Status(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
