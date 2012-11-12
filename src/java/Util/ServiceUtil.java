/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luancorumba
 */
public class ServiceUtil {
    public static final String FIELD_BLANK = "";

    /**
     * Campo em Branco (Inteiro)
     */
    public static final int FIELD_BLANK_INTEGER = Integer.MAX_VALUE;

    /**
     * Campo em Branco (Booleano)
     */
    public static final boolean FIELD_BLANK_BOOLEAN = false;

    /**
     * Permite obter o contexto atual de Java Server Faces
     * @return Contexto de Java Server Faces
     */
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Método para obter o indicador se foi feito uma ação HTTP POST na página ou não
     * @return Indicador se foi feito postback ou não
     */
    public static boolean isPostBack(){
        return getFacesContext().isPostback();
    }

    /**
     * Método que permite obter o contexto externo à aplicação JSF
     * @return Retorno contexto externo a aplicação JSF
     */
    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    /**
     * Método para obter referência de uma requisição
     * @return Retorna a referência de uma requisição
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)getExternalContext().getRequest();
    }

    /**
     * Método para obter referência de uma reposta
     * @return Retorna a referência de uma resposta
     */
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)getExternalContext().getResponse();
    }

    /**
     * Método para obter sessão do usuário
     * @return Referência de sessão
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
    
    public static HttpSession getSession(boolean b) {
        return (HttpSession) getFacesContext().getExternalContext().getSession(b);
    }

    public static void saveAttributeInSession(String name, String valor) {
        getSession(true).setAttribute(name, valor);
    }

    public static Object getAttributeFromSession(String name) {
        return getSession(false).getAttribute(name);
    }

    public static void removeAttributeFromSession(String name) {
        getSession(false).removeAttribute(name);
    }

    public static void redirect(String url) {
        try {
            getFacesContext().getExternalContext().redirect(url);
        } catch (Exception e) {
        }
    }

    public static Object getParameterFromURL(String param){
        return getFacesContext().getExternalContext().getRequestParameterMap().get(param);
    }
    
    public static void addInfoMessage(String msg, String detalhe){
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detalhe));
    }
    
    public static void addErroMessage(String msg, String detalhe){
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detalhe));
    }
    
    public static String getRealPath(String path){
        return getSession().getServletContext().getRealPath(path);
    }
    
    public static List<SelectItem> entityToSelectItem(List _items, String _idMethod, String _descMethod)throws Exception {
  List<SelectItem> items = new ArrayList<SelectItem>();

  Method idMethod = null;
  Method descMethod = null;

  for (int i = 0; i < _items.size(); i++) {
    Object item = _items.get(i);
    // On the first run, initialize reflection methods for object
    if (idMethod == null) {
      Class obj = item.getClass();
      idMethod = obj.getMethod(_idMethod, new Class[]{});
      descMethod = obj.getMethod(_descMethod, new Class[]{});
    }
    // invoke Methods
    
    String id = (String) idMethod.invoke(item, new Object[]{}).toString();
    String name = (String) descMethod.invoke(item, new Object[]{});

    SelectItem selectItem = new SelectItem();
    selectItem.setLabel(name);
    selectItem.setValue(id.toString());
    items.add(selectItem);
  }
  return items;
}
   
    public static String getLetra(int l){
        
        String ret = "";
        
        switch (l){
            
            case 0: ret = "A";
            case 1: ret = "B";
            case 2: ret = "C";
            case 3: ret = "D";
            case 4: ret = "E";
            case 5: ret = "F";
            case 6: ret = "G";
            case 7: ret = "H";
            case 8: ret = "I";
            case 9: ret = "J";
            case 10: ret = "K";
            case 11: ret = "L";
            case 12: ret = "M";
            case 13: ret = "N";
            case 14: ret = "O";
            case 15: ret = "P";
            case 16: ret = "Q";
            case 17: ret = "R";
            case 18: ret = "S";
            case 19: ret = "T";
            case 20: ret = "U";
            case 21: ret = "W";
            case 22: ret = "X";
            case 23: ret = "Y";
            case 24: ret = "Z";
                 
        }
           
        return ret;
        
    }
}
