/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Util.ServiceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.TableGenerator;

/**
 *
 * @author luancorumba
 */
@Entity
@TableGenerator(name = "Roteiro_gen",table="id_generator", pkColumnName= "entidades",
    valueColumnName="id",pkColumnValue="Roteiro_Val", allocationSize = 2, initialValue = 1)
public class Roteiro implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="Roteiro_gen")
    private Integer id;
    
    private String titulo;
    
    private String formato;
    
    private String caminho;
        
    


    public Roteiro() {
    }
    
    public Roteiro(String nome) {
        for(int i = nome.length()-1; i >= 0;i--){
        if (nome.charAt(i) == '.') {
        this.formato = nome.substring(i);
        }
        this.caminho = nome;
        }
    }

    public void salvar(byte[] img,String titulo) throws IOException{
        FileOutputStream output;
        this.titulo =  titulo;
        setCaminho(this.id + this.titulo + "." + this.formato);
        output = new FileOutputStream(ServiceUtil.getRealPath("/arquivos/roteiros/")+ "/" + this.caminho);
        
        
        output.write(img, 0, img.length);
        output.flush();
        output.close();
        
        
    }

    public void remover(String caminho){
        File file = new File(ServiceUtil.getRealPath("/arquivos/roteiros/")+ "/" + caminho);
        file.delete();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    

    public String getFormato() {
        return formato;
    }

    public void setFormato(String nome) {
        this.formato = nome.substring(nome.length()-3, nome.length());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Roteiro)) {
            return false;
        }
        Roteiro other = (Roteiro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Roteiro[ id=" + id + " ]";
    }
    
}
