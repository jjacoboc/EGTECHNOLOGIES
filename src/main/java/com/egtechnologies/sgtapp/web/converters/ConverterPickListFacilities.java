/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.converters;

import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Facilities;
import com.egtechnologies.sgtapp.web.ui.AssignPermissionMB;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author JJacobo
 */
@FacesConverter("converterPickListFacilities")
public class ConverterPickListFacilities implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Object ret = null;
        HttpSession session = JSFUtils.getSession();
        AssignPermissionMB assignPermissionMB = session.getAttribute("assignPermissionMB") != null ? (AssignPermissionMB) session.getAttribute("assignPermissionMB") : new AssignPermissionMB();
        List source = assignPermissionMB.getListSourceFacilities();
        List target = assignPermissionMB.getListTargetFacilities();
        if (!CollectionUtils.isEmpty(source)) {
            for (Object o : source) {
                String id = ((Facilities) o).getIdFacilities().toString();
                if (string.equals(id)) {
                    ret = o;
                    break;
                }
            }
        }
        if (ret == null) {
            if (!CollectionUtils.isEmpty(target)) {
                for (Object o : target) {
                    String id = ((Facilities) o).getIdFacilities().toString();
                    if (string.equals(id)) {
                        ret = o;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String str = "";
        if (o instanceof Facilities) {
            str = ((Facilities) o).getIdFacilities().toString();
        }
        return str;
    }

}
