/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.enumeracoes;

/**
 *
 * @author luancorumba
 */
public enum Perfil {
    PROFESSOR_GERENCIADOR("Professor gerenciador"),
    PROFESSOR_USUARIO("Professor usuário"),
    MONITOR("Monitor"),
    ALUNO("Aluno");
    
    private String nome;

    private Perfil(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
