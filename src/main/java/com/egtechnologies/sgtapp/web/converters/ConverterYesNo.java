/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egtechnologies.sgtapp.web.converters;

import java.math.BigDecimal;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jonatan.jacobo
 */
@FacesConverter("converterYesNo")
public class ConverterYesNo implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
            return Boolean.parseBoolean(value);
        }catch (NumberFormatException exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String label = "";
        try{
            boolean active = (Boolean)value;
            if(active)
                return "Yes";
            else
                return "No";
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }


}
