package Util;

import Entity.Habitat;
import boundary.HabitatFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@FacesConverter(value = "HabitatConverter")
public class HabitatConverter implements Converter {

    private HabitatFacade habitatDAO = new HabitatFacade();

    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert the unique String representation of Foo to the actual Foo object.
        //return habitatDAO.find(value);
        return "Teste";
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
        //return ((Habitat) value).getNome();
        return "Teste";
    }
    
    
    /*
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uicomp, String value) {
        List<SelectItem> items = new ArrayList<SelectItem>();
        List<UIComponent> uicompList = uicomp.getChildren();
        for(UIComponent comp: uicompList){
            if(comp instanceof UISelectItems){
                items.addAll((List<SelectItem>) ((UISelectItems)comp).getValue());
            }
        }
        return "-1".equals(value) ? null :  items.get(Integer.valueOf(value)).getValue();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uicomp, Object entity) {
        return entity == null ? "-1" : String.valueOf(index++);
    }
*/
}
