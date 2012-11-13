/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Entity.Detalhe;
import Entity.enumeracoes.Elemento;
import Entity.Especie;
import Entity.enumeracoes.Situacao;
import Entity.enumeracoes.Visao;
import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.DetalheFacade;
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
@ManagedBean(name="manterDetalhe")
@ViewScoped
public class ManterDetalhe extends CRUDView<Detalhe, Long>{

    @EJB
    private DetalheFacade detalheDAO;
    @EJB
    private EspecieFacade especieDAO;
    
    @ManagedProperty("#{perfilEspecieView}")
    private PerfilEspecieView perfilEspecieView;
    
    @ManagedProperty("#{manterEspecie}")
    private ManterEspecie manterEspecie;
    
    private Visao visaoEscolhida;
    private Elemento elementoEscolhido;
    private static final List<SelectItem> visoes;
    private static final List<SelectItem> elementos;
    
    private UploadedFile img;

    
    
    public ManterDetalhe() {
    }
      
    
    
    @Override
    protected AbstractFacade<Detalhe> getFacade() {
        return detalheDAO;
    }

    @Override
    protected Detalhe criarInstancia() {
        return new Detalhe();
    }

    public Visao getVisaoEscolhida() {
        return visaoEscolhida;
    }

    public void setVisaoEscolhida(Visao visaoEscolhida) {
        this.visaoEscolhida = visaoEscolhida;
        getEntidade().setVisao(visaoEscolhida);
    }

    public Elemento getElementoEscolhido() {
        return elementoEscolhido;
    }

    public void setElementoEscolhido(Elemento elementoEscolhido) {
        this.elementoEscolhido = elementoEscolhido;
        getEntidade().setElemento(elementoEscolhido);
    }

    public List<SelectItem> getVisoes() {
        return visoes;
    }
    
    public List<SelectItem> getElementos(){
        return elementos;
    }

    @Override
    public void salva(){
        if(!(getSessao().isAdmin() || getSessao().isMonitor())){ 
            ServiceUtil.redirect("/Atlas/");
            return;
        }
        getEntidade().setSituacao(Situacao.EDICAO);
        if(getEntidade().getId() == null){
            Especie esp = especieDAO.find(perfilEspecieView.getEntidade().getId());
            getEntidade().setEspecie(esp);
            super.salva();
            esp.getDetalhes().add(getEntidade());
            especieDAO.edit(esp);
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

    public void envia(long id){
        busca(id);
        getEntidade().setSituacao(Situacao.PENDENTE);
        super.edita();
        limpa();
        atualizaListagem();
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
    public void remove(Long codigo){
        
        
        if(getEntidade().getVisao()==Visao.ANATOMICA) {
            perfilEspecieView.removeAnatomico(getEntidade());}
        if(getEntidade().getVisao()==Visao.MORFOLOGICA) {
            perfilEspecieView.removeMorfologico(getEntidade());}
        else { perfilEspecieView.removeMorfologico(getEntidade());}
        Detalhe d = detalheDAO.find(codigo);
        getEntidade().remover(d.getCaminho());
        super.remove(codigo);
        
    }
    
    public void Publicar(long id){
        if(getSessao().isAdmin()) {
            Detalhe detalhe = detalheDAO.find(id);
            detalhe.setSituacao(Situacao.PUBLICADO);
            detalheDAO.edit(detalhe);
        }else ServiceUtil.redirect("/Atlas/");
    }

    public PerfilEspecieView getPerfilEspecieView() {
        return perfilEspecieView;
    }

    public void setPerfilEspecieView(PerfilEspecieView perfilEspecieView) {
        this.perfilEspecieView = perfilEspecieView;
    }

    public ManterEspecie getManterEspecie() {
        return manterEspecie;
    }

    public void setManterEspecie(ManterEspecie manterEspecie) {
        this.manterEspecie = manterEspecie;
    }
    
    
    
    static{
        visoes = new ArrayList<SelectItem>();
        visoes.add(new SelectItem(Visao.ANATOMICA, Visao.ANATOMICA.toString()));
        visoes.add(new SelectItem(Visao.MORFOLOGICA, Visao.MORFOLOGICA.toString()));
       
        elementos = new ArrayList<SelectItem>();
        elementos.add(new SelectItem(Elemento.CAULE, Elemento.CAULE.toString()));
        elementos.add(new SelectItem(Elemento.FLOR, Elemento.FLOR.toString()));
        elementos.add(new SelectItem(Elemento.FOLHA, Elemento.FOLHA.toString()));
        elementos.add(new SelectItem(Elemento.FRUTO, Elemento.FRUTO.toString()));
        elementos.add(new SelectItem(Elemento.RAIZ, Elemento.RAIZ.toString()));
        elementos.add(new SelectItem(Elemento.SEMENTE, Elemento.SEMENTE.toString()));
    }
}
