/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.converters;

import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.ui.CommonMB;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JJ
 */
@FacesConverter("converterRole")
public class ConverterRole implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List lista;
        String label = "";
        try {
            HttpSession session = JSFUtils.getSession();
            CommonMB commonMB = session.getAttribute("commonMB") != null ? (CommonMB) session.getAttribute("commonMB") : new CommonMB();
            lista = commonMB.getListAllRoles();
            for (int i = 0; i < lista.size(); i++) {
                SelectItem item = (SelectItem) lista.get(i);
                if (value.toString().equals(item.getValue().toString())) {
                    label = item.getLabel();
                    break;
                }
            }
            return label;
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }

}
