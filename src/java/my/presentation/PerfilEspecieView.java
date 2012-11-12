/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Entity.Detalhe;
import Entity.Especie;
import Entity.HabitoEspecie;

import Entity.enumeracoes.Visao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.EspecieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luancorumba
 */

@ManagedBean
@ViewScoped
public class PerfilEspecieView extends CRUDView<Especie, Long> {

  
    
    @EJB
    private EspecieFacade especieDAO;
    private Long especieId;
    private List<Detalhe> anatomico;
    
    private List<Detalhe> morfologico;
    
    

    public PerfilEspecieView() {
    }

    @Override
    protected AbstractFacade<Especie> getFacade() {
        return especieDAO;
    }

    @Override
    protected Especie criarInstancia() {
        return new Especie();
    }
    
    public List<String> getImagens(){
        List<String> lista = new ArrayList<String>();
        for (HabitoEspecie he : getEntidade().getHabitoEspecies()) {
            lista.add(he.getCaminho());
        }
        for (Detalhe detalhe : getEntidade().getDetalhes()) {
            lista.add(detalhe.getCaminho());
        }
            return lista;
    }

    public Long getEspecie() {
        return especieId;
    }

    public void setEspecie(Long especie) {
        this.especieId = especie;
    }

    public List<Detalhe> getAnatomico() {
        return anatomico;
    }

    public void setAnatomico(List<Detalhe> anatomico) {
        this.anatomico = anatomico;
    }

    public List<Detalhe> getMorfologico() {
        return morfologico;
    }

    public void setMorfologico(List<Detalhe> morfologico) {
        this.morfologico = morfologico;
    }
    
    public void removeAnatomico(Detalhe anat){anatomico.remove(anat);
    }
    
    public void removeMorfologico(Detalhe morf){morfologico.remove(morf);
    }
    
    public void preRendered(){
        busca(especieId);
        anatomico = new ArrayList<Detalhe>();
        morfologico = new ArrayList<Detalhe>();
        for (Detalhe detalhe : getEntidade().getDetalhes()) {
            if(detalhe.getVisao()==Visao.ANATOMICA) anatomico.add(detalhe);
            if(detalhe.getVisao()==Visao.MORFOLOGICA) morfologico.add(detalhe);
        }
    }
}
