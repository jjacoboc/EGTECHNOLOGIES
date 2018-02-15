/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.FacilityService;
import com.egtechnologies.sgtapp.service.PasswordService;
import com.egtechnologies.sgtapp.service.PersonService;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.service.UserService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.util.SHA1BASE64;
import com.egtechnologies.sgtapp.web.bean.Facilities;
import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.Person;
import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author JJ
 */
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MINUTES = 1;
    private String username;
    private String key;
    private String secondKey;
    private String destinationPage;
    private boolean flagKey;
    private String generatedKey;
    private User userSession;
    private Person personSession;
    private Role roleSession;
    private HashMap<String, Boolean> mapObjetos;
    private Timer timer;

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
        this.flagKey = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecondKey() {
        return secondKey;
    }

    public void setSecondKey(String secondKey) {
        this.secondKey = secondKey;
    }

    public String getDestinationPage() {
        return destinationPage;
    }

    public void setDestinationPage(String destinationPage) {
        this.destinationPage = destinationPage;
    }

    public boolean isFlagKey() {
        return flagKey;
    }

    public void setFlagKey(boolean flagKey) {
        this.flagKey = flagKey;
    }

    public String getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }

    public User getUserSession() {
        return userSession;
    }

    public void setUserSession(User userSession) {
        this.userSession = userSession;
    }

    public Person getPersonSession() {
        return personSession;
    }

    public void setPersonSession(Person personSession) {
        this.personSession = personSession;
    }

    public Role getRoleSession() {
        return roleSession;
    }

    public void setRoleSession(Role roleSession) {
        this.roleSession = roleSession;
    }

    public HashMap<String, Boolean> getMapObjetos() {
        return mapObjetos;
    }

    public void setMapObjetos(HashMap<String, Boolean> mapObjetos) {
        this.mapObjetos = mapObjetos;
    }

    public void clear() {
        this.setUsername(null);
        this.setKey(null);
        this.setGeneratedKey(null);
        this.setSecondKey(null);
        this.setFlagKey(false);
    }

    /**
     * Login del sistema.
     *
     * @return destino Página a la que redirecciona el método.
     */
    public String login() {
        String sessionAttempt;
        String[] to = new String[1];
        String subject;
        StringBuilder body = new StringBuilder("");
        try {
            HttpSession session = JSFUtils.getSession();
            UserService userService = (UserService) JSFUtils.findBean("UserService");

            sessionAttempt = session.getAttribute("sessionAttempt") != null ? (String) session.getAttribute("sessionAttempt") : "1";

            User user = userService.getActiveUserbyUsername(this.getUsername().trim());
            if (user != null) {
                PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
                Person person = personService.getPersonById(user.getIdPerson());
                PasswordService passwordService = (PasswordService) JSFUtils.findBean("PasswordService");
                Password password = passwordService.getActivePasswordbyUser(user);
                if (password != null && !password.getLocked()) {
                    if (Integer.parseInt(sessionAttempt) < 3) {
                        String claveEncriptada = SHA1BASE64.encriptar(this.getKey().trim());
                        if (claveEncriptada.equals(password.getPassword())) {
                            if (!user.getSendkey()) {
                                this.setUserSession(user);
                                this.setPersonSession(person);
                                JSFUtils.getSession().setAttribute("usuario", user);
                                JSFUtils.getSession().setAttribute("person", person);
                                RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
                                Role role = roleService.getRoleById(user.getIdRole());
                                if (role != null) {
                                    FacilityService facilityService = (FacilityService) JSFUtils.findBean("FacilityService");
                                    List<Facilities> permisos = facilityService.getFacilitiesByRole(role.getIdRole());
                                    HashMap<String, Boolean> mapa = new HashMap<>();

                                    if (permisos != null && !permisos.isEmpty()) {
                                        for (Facilities obj : permisos) {
                                            if (obj.getActive()) {
                                                mapa.put(obj.getName(), true);
                                            } else {
                                                mapa.put(obj.getName(), false);
                                            }
                                        }

                                        this.setRoleSession(role);
                                        this.setMapObjetos(mapa);

                                        JSFUtils.getSession().setAttribute("mapaObjetos", mapa);
                                        JSFUtils.getSession().setAttribute("perfil", role);
                                        if (user.getFirstlogin()) {
                                            this.setDestinationPage("/pages/changePassword");
                                        } else {
                                            this.setDestinationPage("/pages/welcome");
                                        }
                                    } else {
                                        this.setDestinationPage("login");
                                        FacesContext.getCurrentInstance().addMessage(null,
                                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su perfil no tiene permisos asignados. Comuníquese con el administrador del servicio."));
                                    }
                                } else {
                                    this.setDestinationPage("login");
                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su usuario no tiene un perfil asignado. Comuníquese con el administrador del servicio."));
                                }
                            } else {
                                if (!this.flagKey) {
                                    this.setGeneratedKey(Util.generarClave());
                                    to[0] = user.getWorkemail();
                                    subject = "SISTEMA DE GESTIÓN DE SEGURIDAD - CLAVE DE CONFIRMACIÓN";
                                    body.append("Estimado(a) ").append(person.getName()).append(" ").append(person.getLastname()).append(",<br/>");
                                    body.append("Su clave de confirmación es: ").append(this.getGeneratedKey());
                                    Util.enviarCorreo(to, subject, body.toString());

                                    this.setFlagKey(true);
                                    this.setDestinationPage("login");

                                    this.delay(MINUTES);

                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se ha enviado un email con la clave de confirmación a " + user.getWorkemail() + ", por favor verifique su correo."));
                                } else {
                                    if (this.secondKey.equals(this.generatedKey)) {
                                        this.setUserSession(user);
                                        JSFUtils.getSession().setAttribute("usuario", user);
                                        RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
                                        Role role = roleService.getRoleByUser(user);
                                        if (role != null) {
                                            FacilityService facilityService = (FacilityService) JSFUtils.findBean("FacilityService");
                                            List<Facilities> permisos = facilityService.getFacilitiesByRole(role.getIdRole());
                                            HashMap<String, Boolean> mapa = new HashMap<>();

                                            if (permisos != null && !permisos.isEmpty()) {
                                                for (Facilities obj : permisos) {
                                                    if (obj.getActive()) {
                                                        mapa.put(obj.getName(), true);
                                                    } else {
                                                        mapa.put(obj.getName(), false);
                                                    }
                                                }

                                                this.setRoleSession(role);
                                                this.setMapObjetos(mapa);

                                                JSFUtils.getSession().setAttribute("mapaObjetos", mapa);
                                                JSFUtils.getSession().setAttribute("perfil", role);
                                                if (user.getFirstlogin()) {
                                                    this.setDestinationPage("/pages/changePassword");
                                                } else {
                                                    this.setDestinationPage("/pages/welcome");
                                                }
                                            } else {
                                                this.setDestinationPage("login");
                                                FacesContext.getCurrentInstance().addMessage(null,
                                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su perfil no tiene permisos asignados. Comuníquese con el administrador del servicio."));
                                            }
                                        } else {
                                            this.setDestinationPage("login");
                                            FacesContext.getCurrentInstance().addMessage(null,
                                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su usuario no tiene un perfil asignado. Comuníquese con el administrador del servicio."));
                                        }
                                    } else {
                                        FacesContext.getCurrentInstance().addMessage(null,
                                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta.", "Intente nuevamente."));
                                        session.setAttribute("sessionAttempt", String.valueOf(Integer.parseInt(sessionAttempt) + 1));
                                        this.setDestinationPage("login");
                                    }
                                }
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta.", "Intente nuevamente."));
                            session.setAttribute("sessionAttempt", String.valueOf(Integer.parseInt(sessionAttempt) + 1));
                            this.setDestinationPage("login");
                        }
                    } else {
                        password.setLocked(true);
                        password.setLockedDate(new Date());
                        password.setModifiedBy(user.getIdUser());
                        password.setModifiedDate(password.getLockedDate());
                        passwordService.saveOrUpdate(password);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        to[0] = user.getWorkemail();
                        subject = "SGTWEB - PASSWORD BLOQUED";
                        body.append("Your password has been blocked. <br>It was loged in with the username '");
                        body.append(user.getUsername()).append("' and wrong password in more than three attempts.<br><br>");
                        body.append("Username: ").append(user.getUsername()).append("<br>");
                        body.append("Date: ").append(sdf.format(password.getModifiedDate())).append("<br>");
                        body.append("Contact the service administrator.");
                        Util.enviarCorreo(to, subject, body.toString());
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password blocked.", "Contact the service administrator."));
                        this.setDestinationPage("login");
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password blocked.", "Contact the service administrator."));
                    this.setDestinationPage("login");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario incorrecto.", "Intente nuevamente."));
                this.setDestinationPage("login");
            }
        } catch (IllegalStateException | NumberFormatException e) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }

        return this.getDestinationPage();
    }

    public void resetPassword(ActionEvent event) {
        String[] to = new String[1];
        String subject;
        StringBuilder body = new StringBuilder("");
        try {
            if (this.username != null && !this.username.isEmpty()) {
                UserService userService = (UserService) JSFUtils.findBean("UserService");
                User user = userService.getActiveUserbyUsername(username);
                if (user != null) {
                    String newKey = Util.generarClave();
                    PasswordService passwordService = (PasswordService) JSFUtils.findBean("PasswordService");
                    Password password = passwordService.getActivePasswordbyUser(user);
                    password.setPassword(SHA1BASE64.encriptar(newKey));
                    password.setActive(Boolean.TRUE);
                    password.setLocked(Boolean.FALSE);
                    password.setLockedDate(null);
                    password.setModifiedDate(new Date());
                    password.setModifiedBy(user.getIdUser());
                    passwordService.saveOrUpdate(password);

                    user.setFirstlogin(Boolean.TRUE);
                    userService.saveOrUpdate(user);

                    to[0] = user.getWorkemail();
                    subject = "SGTWEB - RESET PASSWORD";
                    body.append("Your password has been reset successfully.<br>");
                    body.append("Username: <strong>").append(user.getUsername()).append("</strong><br>");
                    body.append("New Password: <strong>").append(newKey).append("</strong><br>");
                    Util.enviarCorreo(to, subject, body.toString());

                    this.setUsername(null);

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "An email with your new password has been sent. Please, check your email and try again."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "You must enter a username."));
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }

    public String destino() {
        return this.getDestinationPage();
    }

    /**
     * Cierra la sesión del username.
     *
     * @return destino Página a la que redirecciona el método.
     */
    public String closeSession() {
        String destino = null;
        try {
            JSFUtils.getSession().invalidate();
            destino = "/commons/login?faces-redirect=true";
        } catch (Exception e) {
            e.getMessage();
        }
        return destino;

    }

    class Task extends TimerTask {

        @Override
        public void run() {
            setSecondKey(StringUtils.EMPTY);
            System.out.println("Clave Secundaria expirada... ");
            timer.cancel();
        }
    }

    public void delay(int minutes) {
        this.timer = new Timer();
        this.timer.schedule(new Task(), minutes * 60 * 1000); //delay in milliseconds
    }
}
