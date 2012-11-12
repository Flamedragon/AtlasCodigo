/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.enumeracoes.Situacao;
import java.io.*;
import javax.persistence.*;

/**
 *
 * @author Kamilla
 */
@Entity
@DiscriminatorValue("H")
@TableGenerator(name="tabela_HabitoEspecie",table="id_generator",pkColumnName="entidades",
    pkColumnValue="HabitoEspecie",valueColumnName="id",initialValue=0,allocationSize=2)
public class HabitoEspecie extends Imagem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @ManyToOne
    private Habito habito;

    @ManyToOne(optional=false)
    private Especie especie;

    private Situacao situacao;

    public HabitoEspecie() {
    }

    public HabitoEspecie(String nome, Situacao situacao) {
        super(nome);
        this.situacao = situacao;
    }

    public HabitoEspecie(Situacao situacao) {
        this.situacao = situacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }
    
}
