/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum TipoInstituicao {
    PUBLICA("Pública"),
    PRIVADA("Privada");
    
    private String nome;

    private TipoInstituicao(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
