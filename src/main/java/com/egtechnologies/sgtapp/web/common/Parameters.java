/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egtechnologies.sgtapp.web.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class Parameters {

    public final static String ACTIVO = "1";
    public final static String INACTIVO = "0";
    
    public final static String TXT_MUY_ALTA = "MUY ALTA";
    public final static String TXT_ALTA = "ALTA";
    public final static String TXT_MEDIA = "MEDIA";
    public final static String TXT_BAJA = "BAJA";
    public final static String TXT_MUY_BAJA = "MUY BAJA";

    /**
     * Devuelve la ubicación del archivo messages.properties
     * @return
     */
    public static String getMessages(){
        return "com.egtechnologies.sgtapp.resources.messages";
    }

    /**
     * Devuelve la ubicación del archivo properties
     * @return
     */
    public static String getJDBC(){
        return "com.egtechnologies.sgtapp.resources.jdbc";
    }

    /**
     * Devuelve la ubicación del archivo mail.properties
     * @return
     */
    public static String getMail(){
        return "com.egtechnologies.sgtapp.resources.mail";
    }

    /**
     * Devuelve la ubicación del archivo parameters.properties
     * @return
     */
    public static String getParameters(){
        return "com.egtechnologies.sgtapp.resources.parameters";
    }

    /**
     * Devuelve la lista SI/NO.
     * @return
     */
    public static List<GeneralBean> getListaSINO(){
        List lista = new ArrayList();
        GeneralBean bean = new GeneralBean();
        bean.setCodigo("1");
        bean.setDescripcion("SI");
        lista.add(bean);
        bean = new GeneralBean();
        bean.setCodigo("0");
        bean.setDescripcion("NO");
        lista.add(bean);
        return lista;
    }
}
