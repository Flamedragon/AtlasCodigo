/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.enumeracoes;

/**
 *
 * @author Kamilla
 */
public enum Situacao {
    EDICAO("Edição"),
    PENDENTE("Pendente"),
    PUBLICADO("Publicado");
    
    private String nome;

    private Situacao(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public boolean isEdicao(){
        return this == EDICAO;
    }
    
    public boolean isPendente(){
        return this == PENDENTE;
    }
    
    public boolean isPublicado(){
        return this == PUBLICADO;
    }
}
