/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import Entity.enumeracoes.Elemento;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kamilla
 */
@Entity
@DiscriminatorValue("D")
public class Detalhe extends Imagem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    private String titulo;

    private Situacao situacao;

    private Visao visao;
    
    private Elemento elemento;

    @ManyToOne
    private Especie especie;

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Visao getVisao() {
        return visao;
    }

    public void setVisao(Visao visao) {
        this.visao = visao;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

}
