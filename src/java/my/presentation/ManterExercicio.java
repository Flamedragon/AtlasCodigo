package my.presentation;



import Entity.Avaliacao;
import Entity.Exercicio;
import Entity.Imagem;
import Entity.Resposta;
import Entity.RespostaAluno;
import my.presentation.CRUDView;
import Entity.enumeracoes.Elemento;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Complexidade;
import Entity.enumeracoes.Visao;
import Entity.enumeracoes.TipoExercicio;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.AvaliacaoFacade;
import boundary.ExercicioFacade;
import boundary.ImagemFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ítalo
 */
@ManagedBean(name="manterExercicio")
@ViewScoped
public class ManterExercicio extends CRUDView<Exercicio, Long>{
    

    @EJB
    private ExercicioFacade exercicioDAO;

    @EJB
    private AvaliacaoFacade avaliacaoDAO;
    
    @EJB
    private ImagemFacade imagemDAO;

  
 private UploadedFile img;
 private String tempFormato; 
 
    private Visao visaoEscolhida;
    private Complexidade grauEscolhido;
    private Elemento elementoEscolhido;
    private TipoExercicio tipoEscolhido;
    private Integer respostaEscolhida;
    private Integer avaliacaoEscolhida;
   
    
   
    private static final List<SelectItem> elementos;
   
 
   
    


    public ManterExercicio() {
    }

    @Override
    protected AbstractFacade<Exercicio> getFacade() {
        return exercicioDAO;
    }

    @Override
    protected Exercicio criarInstancia() {
        return new Exercicio();
    }

    public Integer getAvaliacaoEscolhida() {
        return avaliacaoEscolhida;
    }

    public void setAvaliacaoEscolhida(Integer avaliacaoEscolhida) {
        this.avaliacaoEscolhida = avaliacaoEscolhida;
    }

   
    
    
    public Visao getVisaoEscolhida() {
        return visaoEscolhida;
    }

    public void setVisaoEscolhida(Visao visaoEscolhida) {
        this.visaoEscolhida = visaoEscolhida;
        getEntidade().setVisao(visaoEscolhida);
    }

    public Complexidade getGrauEscolhido() {
        return grauEscolhido;
    }

    public void setGrauEscolhido(Complexidade grauEscolhido) {
        this.grauEscolhido = grauEscolhido;
        getEntidade().setGrau(grauEscolhido);
    }
    
     public Elemento getElementoEscolhido() {
        return elementoEscolhido;
    }

    public void setElementoEscolhido(Elemento elementoEscolhido) {
        this.elementoEscolhido = elementoEscolhido;
        getEntidade().setElemento(elementoEscolhido);
    }

    public TipoExercicio getTipoEscolhido() {
        return tipoEscolhido;
    }

    public void setTipoEscolhido(TipoExercicio tipoEscolhido) {
        this.tipoEscolhido = tipoEscolhido;
        getEntidade().setTipo(tipoEscolhido);
    }

    public Integer getRespostaEscolhida() {
        return respostaEscolhida;
    }

    public void setRespostaEscolhida(Integer respostaEscolhida) {
        this.respostaEscolhida = respostaEscolhida;
    }

    

    
   public List<SelectItem> getTipos()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (TipoExercicio tipo: TipoExercicio.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
   
   public List<SelectItem> getGraus()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Complexidade tipo: Complexidade.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
   
   public List<SelectItem> getElementos()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Elemento tipo: Elemento.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
   
   public List<SelectItem> getVisoes()
  {
    List<SelectItem> items = new ArrayList<SelectItem>();
    for (Visao tipo: Visao.values())
   {
     items.add(new SelectItem(tipo, tipo.toString()));
   }
return items;
  }
   
   public List<SelectItem> getRespostas()
  {
    ArrayList items = new ArrayList<SelectItem>();
    List lista = getEntidade().getRespostas();
    
    for(int i = 0; i < lista.size(); i++){
             Resposta h = (Resposta)lista.get(i);
             items.add(new SelectItem(h.getId(), h.getDescricao()));}
         return items;

  }
   
   
 
    public void upload(FileUploadEvent event) {  
        img = event.getFile();
    
        getEntidade().setFormato(event.getFile().getFileName());
       // getEntidade().setCaminho(getEntidade().getId() + "." + getEntidade().getFormato());
            
        
        // Do what you want with the file        
    /*    try {
            copyFile(img.getFileName(), img.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }  

    public String getTempFormato() {
        return tempFormato;
    }

    public void setTempFormato(String tempFormato) {
        this.tempFormato = tempFormato;
    }
    
    
    
   
/*
    public List<SelectItem> getVisoes() {
        return visoes;
    }
  */

   
         public void adicionarAvaliacao(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        Avaliacao a = avaliacaoDAO.find(avaliacaoEscolhida);
        getEntidade().setSituacao(Situacao.EDICAO);
        a.getExercicios().add(getEntidade());
        getEntidade().getAvaliacoes().add(a);
        avaliacaoDAO.edit(a);
        super.edita();
        limpa();
    }
    
    
    
    
    

     @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null){
          
            super.salva();
            
            getEntidade().setCaminho(getEntidade().getId() + "." + getEntidade().getFormato());
            super.edita();
            try{
                getEntidade().salvar(img.getContents());
                ServiceUtil.addInfoMessage("Imagem salva com sucesso","");

            }catch(IOException e){
                e.printStackTrace();
                remove(getEntidade().getId());
                ServiceUtil.addErroMessage("Erro ao salvar imagem", "Contate o erro aos responsáveis pelo Atlas");
            }
        } else super.edita();
        
        limpa();
        atualizaListagem();
    }
     
     
        public void remove(){   
        
        if(getEntidade().getId()!=null){
            
            getEntidade().remover(getEntidade().getCaminho());
            super.remove(getEntidade().getId());
            ServiceUtil.redirect("/Atlas/Exercicio/listagem.html");
        }
        else ServiceUtil.addErroMessage("Erro ao excluir exercício", "Tente novamente");
           
        
           } 
        

         
        

    public void envia(long id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
    }
 
    
    public void Publicar(long id){
        if(getSessao().isAdmin()) {
            Exercicio exercicio = exercicioDAO.find(id);
            exercicio.setSituacao(Situacao.PUBLICADO);
            exercicioDAO.edit(exercicio);
        }else ServiceUtil.redirect("/Atlas/");
    }
    
    


    
    

    public void preRender(long id){
        busca(id);
        
        if(getEntidade()==null) ServiceUtil.redirect("/Atlas/");
    }
    
    
    static{
    
      
        
        elementos = new ArrayList<SelectItem>();
        elementos.add(new SelectItem(Elemento.CAULE, Elemento.CAULE.toString()));
        elementos.add(new SelectItem(Elemento.FLOR, Elemento.FLOR.toString()));
        elementos.add(new SelectItem(Elemento.FOLHA, Elemento.FOLHA.toString()));
        elementos.add(new SelectItem(Elemento.FRUTO, Elemento.FRUTO.toString()));
        elementos.add(new SelectItem(Elemento.RAIZ, Elemento.RAIZ.toString()));
        elementos.add(new SelectItem(Elemento.SEMENTE, Elemento.SEMENTE.toString()));
        
       
        
        
    }
}
