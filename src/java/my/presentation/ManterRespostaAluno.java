/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Exercicio;
import Entity.Resposta;
import Entity.RespostaAluno;
import Entity.Usuario;
import Entity.enumeracoes.Situacao;
import Util.AppRedirectTo;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.ExercicioFacade;
import boundary.FamiliaFacade;
import boundary.RespostaFacade;
import boundary.RespostaAlunoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Luan
 */
@ManagedBean(name="manterRespostaAluno")
@ViewScoped
public class ManterRespostaAluno extends CRUDView<RespostaAluno, Integer>{

    @EJB
    private RespostaAlunoFacade RespostaAlunoDAO;
    
    @EJB
    private RespostaFacade respostaDAO;
    
    @EJB
    private ExercicioFacade exercicioDAO;
    
    @ManagedProperty("#{manterResposta}")
    private ManterResposta manterResposta;
    
    @ManagedProperty("#{manterExercicio}")
    private ManterExercicio manterExercicio;


    

    @Override
    protected AbstractFacade<RespostaAluno> getFacade() {
        return RespostaAlunoDAO;
    }

    @Override
    protected RespostaAluno criarInstancia() {
        return new RespostaAluno();
    }
   
    
    public void salva(Usuario user){
        
        
        
        Resposta r = respostaDAO.find(manterExercicio.getRespostaEscolhida());
        getEntidade().setResposta(r);
        Exercicio e = exercicioDAO.find(manterExercicio.getEntidade().getId());
        getEntidade().setExercicio(e);
        
        getEntidade().setUsuario(user);
        if(r.isFlCorreta()){
            getEntidade().getExercicio().setQtCorretas((byte)(getEntidade().getExercicio().getQtCorretas() + 1));
            ServiceUtil.addInfoMessage("Parabéns!!", getEntidade().getResposta().getJustificativa());
        } else {getEntidade().getExercicio().setQtIncorretas((byte)(getEntidade().getExercicio().getQtIncorretas() + 1));
            ServiceUtil.addInfoMessage("A resposta dada está incorreta", getEntidade().getResposta().getJustificativa());
        }
        if(getEntidade().getId() == null) super.salva();
        else super.edita();
        limpa();
        atualizaListagem();
    }

    public ManterResposta getManterResposta() {
        return manterResposta;
    }

    public void setManterResposta(ManterResposta manterResposta) {
        this.manterResposta = manterResposta;
    }

    public ManterExercicio getManterExercicio() {
        return manterExercicio;
    }

    public void setManterExercicio(ManterExercicio manterExercicio) {
        this.manterExercicio = manterExercicio;
    }
    
    

    

 
    public void preRender(int id){
        busca(id);
               
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
}
