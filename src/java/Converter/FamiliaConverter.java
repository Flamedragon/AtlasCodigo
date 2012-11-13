package Converter;

import Entity.Familia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import my.presentation.ManterFamilia;



public class FamiliaConverter implements Converter {

    @ManagedProperty("#{manterFamilia}")
    private ManterFamilia manterFamilia;

   

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Familia p : manterFamilia.getListagem()) {
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
            return String.valueOf(((Familia) value).getNome());
        }
    }

    public ManterFamilia getManterFamilia() {
        return manterFamilia;
    }

    public void setManterFamilia(ManterFamilia manterFamilia) {
        this.manterFamilia = manterFamilia;
    }
    
    
}
                    