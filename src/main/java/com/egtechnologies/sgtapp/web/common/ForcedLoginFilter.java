/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */
public class ForcedLoginFilter implements Filter {

    private static final String LOGIN = "login.jsf";
  
    public ForcedLoginFilter() {    
    }
    
    private static boolean checkLoginState(ServletRequest request, ServletResponse response) throws IOException, ServletException {  
  
        boolean isLoggedIn = false;
//        SegCabUsuario segCabUsuario = null;
//        HttpSession session = ((HttpServletRequest) request).getSession(false);
//        if(session != null){
//            segCabUsuario = (SegCabUsuario) session.getAttribute("usuario");
//            if (segCabUsuario != null) {
//                isLoggedIn = true;
//            }
//        }
        return isLoggedIn;
    }
    
    private boolean isRedirect(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return (!requestURI.contains(LOGIN));
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isLoggedIn = checkLoginState(request, response);
        if (isRedirect((HttpServletRequest) request) && !isLoggedIn) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/commons/forcedLogin.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
    
}
