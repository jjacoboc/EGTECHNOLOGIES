/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jonatan Jacobo
 */
public class JSFUtils {

    /**
     * Devuelve la sesión actual asociado a la solicitud, o si la solicitud es
     * nula, crea una.
     *
     * @return session.
     */
    public static HttpSession getSession() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        return session;
    }

    /**
     * Devuelve el actual valor del objeto de solicitud.
     *
     * @return request.
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request;
    }

    /**
     * Devuelve el actual valor del objeto de respuesta.
     *
     * @return response.
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getResponse();
        return response;
    }

    /**
     * Devuelve una instancia de la clase ServletContext.
     *
     * @return context.
     */
    public static ServletContext getServletContext() {
        ServletContext context = (ServletContext) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getContext();
        return context;
    }

    /**
     * Devuelve el valor de un parámetro de la sesión como cadena, o nulo si no
     * existe.
     *
     * @param id Nombre del parámetro a devolver.
     * @return parameter.
     */
    public static String getRequestParameter(String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String parameter = myRequest.getParameter(id);

        return parameter;
    }

    /**
     * Devuelve el valor de un atributo de la sesión, o nulo si no existe.
     *
     * @param id Nombre del atributo a devolver.
     * @return object.
     */
    public static Object getSessionAttribute(String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = ((HttpServletRequest) context.getExternalContext().getRequest()).getSession(false);
        Object object = session.getAttribute(id);

        return object;
    }

    public static void setSessionAttribute(String key, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = ((HttpServletRequest) context.getExternalContext().getRequest()).getSession(false);
        session.setAttribute(key, object);
    }
    
    /**
     * Devuelve el objeto bean del contexto correspondiente al nombre solicitado.
     * @param beanName Nombre del bean.
     * @return o Objeto bean encontrado.
     */
    public static Object findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        Object o = appContext.getBean(beanName);
        return o;
    }
}
