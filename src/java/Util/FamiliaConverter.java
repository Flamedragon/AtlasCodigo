/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Italo
 */

package Util;

import Entity.Familia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import my.presentation.ManterEspecie;
import my.presentation.ManterFamilia;


@ManagedBean(name="familiaConverter")
@RequestScoped
public class FamiliaConverter implements Converter {


@ManagedProperty(value="#{especieView}")
private ManterEspecie manterEspecie;

@ManagedProperty(value="#{manterFamilia}")
private ManterFamilia manterFamilia;


    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                List<Familia> playerDB = manterFamilia.getListagem();
                for (Familia p : playerDB) {
                    if (p.getId() == number) {
                        return p;
                    }
                }

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Familia) value).getId());
        }
    }

    public ManterEspecie getManterEspecie() {
        return manterEspecie;
    }

    public void setManterEspecie(ManterEspecie manterEspecie) {
        this.manterEspecie = manterEspecie;
    }

    public ManterFamilia getManterFamilia() {
        return manterFamilia;
    }

    public void setManterFamilia(ManterFamilia manterFamilia) {
        this.manterFamilia = manterFamilia;
    }
    
    
    
}
                    