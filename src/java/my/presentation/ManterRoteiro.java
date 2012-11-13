/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Roteiro;
import Entity.enumeracoes.Elemento;
import Entity.Especie;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.RoteiroFacade;
import boundary.EspecieFacade;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="manterRoteiro")
@ViewScoped
public class ManterRoteiro extends CRUDView<Roteiro, Integer>{

    @EJB
    private RoteiroFacade roteiroDAO;
   
    
   


    private UploadedFile img;

    
    
    public ManterRoteiro() {
    }
      
    
    
    @Override
    protected AbstractFacade<Roteiro> getFacade() {
        return roteiroDAO;
    }

    @Override
    protected Roteiro criarInstancia() {
        return new Roteiro();
    }

    

   

    

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
      
        if(getEntidade().getId() == null){
           
            super.salva();
            getEntidade().setCaminho(getEntidade().getId()+ getEntidade().getTitulo() + "." + getEntidade().getFormato());
            super.edita();
            try{
                getEntidade().salvar(img.getContents(),getEntidade().getTitulo());
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

    public List<String> getRoteiros(){
        List<Roteiro> lista = getListagem();
        List<String> retorno = new ArrayList<String>();
        
        
        for (Roteiro roteiro : lista) {
            retorno.add(roteiro.getCaminho());
        }
            return retorno;
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
/*
    public void copyFile(String fileName, InputStream in) {
           try {
             
             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File("/Atlas/arquivos/fotos/" + fileName));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
  */  
    @Override
    public void remove(Integer codigo){
        
        
        getEntidade().remover();
        super.remove(codigo);
        
    }
    
    public void Publicar(long id){
        if(getSessao().isAdmin()) {
            Roteiro roteiro = roteiroDAO.find(id);
            roteiroDAO.edit(roteiro);
        }else ServiceUtil.redirect("/Atlas/");
    }


    
    
}
