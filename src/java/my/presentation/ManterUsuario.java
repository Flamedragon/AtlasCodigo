/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Entity.Cidade;
import Entity.Habitat;
import Entity.Usuario;
import Entity.enumeracoes.Ensino;
import Entity.enumeracoes.Escolaridade;
import Entity.enumeracoes.Perfil;
import Entity.enumeracoes.Sexo;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author luancorumba
 */
@ManagedBean
@RequestScoped
public class ManterUsuario extends CRUDView<Usuario, Long> {
    
    
    private Sexo sexoEscolhido;
    private Ensino ensinoEscolhido;
    private Escolaridade escolariodadeEscolhida;
    private Cidade cidadeEscolhida;
    private Perfil perfilEscolhido;
    
    @EJB
    private UsuarioFacade DAO;


    public ManterUsuario() {
    }

    @Override
    protected AbstractFacade<Usuario> getFacade() {
        return DAO;
    }

    @Override
    protected Usuario criarInstancia() {
        return new Usuario();
    }
    
    @Override
    public void salva(){
        
        if(perfilEscolhido.equals(Perfil.ALUNO)){
            getEntidade().setPerfil(Perfil.ALUNO);
        }else if(perfilEscolhido.equals(Perfil.MONITOR) && getSessao().isAdmin()){ //apenas o admin pode cadastrar alguém que não seja aluno nem professor
            getEntidade().setPerfil(Perfil.MONITOR);
        }else if(perfilEscolhido.equals(Perfil.PROFESSOR_USUARIO)){
            getEntidade().setPerfil(Perfil.PROFESSOR_USUARIO);
        }else if(perfilEscolhido.equals(Perfil.PROFESSOR_GERENCIADOR) && getSessao().isAdmin()){
            getEntidade().setPerfil(Perfil.PROFESSOR_GERENCIADOR);
        }else{
            ServiceUtil.addInfoMessage("Não foi possível criar o usuário",null);
            return;
        }
        if(getEntidade().getId() == null){
            super.salva();
        }
        else super.edita();
        limpa();
    }

    public Perfil getPerfilEscolhido() {
        return perfilEscolhido;
    }

    public void setPerfilEscolhido(Perfil perfilEscolhido) {
        this.perfilEscolhido = perfilEscolhido;
        getEntidade().setPerfil(perfilEscolhido);
    }

    

    public Sexo getSexoEscolhido() {
        return sexoEscolhido;
    }

    public void setSexoEscolhido(Sexo sexoEscolhido) {
        this.sexoEscolhido = sexoEscolhido;
        getEntidade().setSexo(sexoEscolhido);
    }

    public Ensino getEnsinoEscolhido() {
        return ensinoEscolhido;
    }

    public void setEnsinoEscolhido(Ensino ensinoEscolhido) {
        this.ensinoEscolhido = ensinoEscolhido;
        getEntidade().setEnsino(ensinoEscolhido);
    }

    public Escolaridade getEscolariodadeEscolhida() {
        return escolariodadeEscolhida;
    }

    public void setEscolariodadeEscolhida(Escolaridade escolariodadeEscolhida) {
        this.escolariodadeEscolhida = escolariodadeEscolhida;
        getEntidade().setEscolaridade(escolariodadeEscolhida);
    }

    public Cidade getCidadeEscolhida() {
        return cidadeEscolhida;
    }

    public void setCidadeEscolhida(Cidade cidadeEscolhida) {
        this.cidadeEscolhida = cidadeEscolhida;
        getEntidade().setCidade(cidadeEscolhida);
        
    }
    
    
    
    public List<SelectItem> getSelectItens(){
        ArrayList habitatsSelects = new ArrayList<SelectItem>();
        
         List lista = super.getListagem();
         for(int i = 0; i < lista.size(); i++){
             Usuario h = (Usuario)lista.get(i);
             habitatsSelects.add(new SelectItem(h.getId(), h.getLogin()));}
         return habitatsSelects;
     }
    
    public List<SelectItem> getSexos()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Sexo tipo: Sexo.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
    
  
    public List<SelectItem> getEnsinos()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Ensino tipo: Ensino.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }  
    
    
    public List<SelectItem> getEscolaridades()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Escolaridade tipo: Escolaridade.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
    
    
    
    
    
   public List<SelectItem> getPerfis()
  { 
    
    List<SelectItem> items = new ArrayList<SelectItem>();
    
    
    if (getSessao().isAdmin())
        { // O administrador pode cadastrar qualquer tipo
        for (Perfil tipo: Perfil.values())
            {
                items.add(new SelectItem(tipo, tipo.toString()));
            }
        }
    else if (getSessao().isAluno()){ //O Aluno só poderá adicionar alunos
        items.add(new SelectItem(Perfil.ALUNO, Perfil.ALUNO.toString()));
        } 
    else{ // O resto só inclui agora o Professor e o Monitor
        items.add(new SelectItem(Perfil.ALUNO, Perfil.ALUNO.toString()));
        items.add(new SelectItem(Perfil.PROFESSOR_USUARIO, Perfil.PROFESSOR_USUARIO.toString()));
        }  
return items;
      
  }  
    
    
    
    
}
