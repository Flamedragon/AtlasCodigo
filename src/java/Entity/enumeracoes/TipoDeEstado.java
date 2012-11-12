/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.enumeracoes;

/**
 *
 * @author Kamilla
 */
public enum TipoDeEstado {
    EXTINTA("Extinta"),
    EXTINTA_REGIONALMENTE("Extinta Regionalmente"),
    EXTINTA_NA_NATUREZA("Extinta na Natureza"),
    AMEACADA_CRITICAMENTE_EM_PERIGO("Ameaçada - Criticamente em perigo"),
    AMEACADA_EM_PERIGO("Ameaçada - Em perigo"),
    AMEACADA_VUNERAVEL("Ameaçada - Vunerável"),
    QUASE_AMEACADA("Quase Ameaçada"),
    NAO_AMEACADA("Não Ameaçada"),
    DADOS_INSUFICIENTES("Dados Insuficientes"),
    NAO_AVALIADA("Não Avaliada");
    
    private String nome;

    private TipoDeEstado(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
