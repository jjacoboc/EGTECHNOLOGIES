/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Jonathan
 */
@FacesConverter("converterBranchOffice")
public class ConverterBranchOffice implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        List lista;
        String label = "";
        try {
            HttpSession session = JSFUtils.getSession();
            CommonMB commonMB = session.getAttribute("commonMB") != null ? (CommonMB) session.getAttribute("commonMB") : new CommonMB();
            lista = commonMB.getListAllBranchOffice();
            for (int i = 0; i < lista.size(); i++) {
                SelectItem item = (SelectItem) lista.get(i);
                if (o.toString().equals(item.getValue().toString())) {
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
