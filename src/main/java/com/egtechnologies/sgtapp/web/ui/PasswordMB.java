/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.PasswordService;
import com.egtechnologies.sgtapp.service.UserService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.util.SHA1BASE64;
import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.User;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class PasswordMB implements Serializable {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    /**
     * Creates a new instance of ClaveMB
     */
    public PasswordMB() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String changePassword() {
        String encryptedPassword;
        String destino = null;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            if (this.getCurrentPassword() != null && !"".equals(this.getCurrentPassword())) {
                if (this.getCurrentPassword() != null && !"".equals(this.getCurrentPassword())) {
                    encryptedPassword = SHA1BASE64.encriptar(this.getCurrentPassword().trim());
                    PasswordService passwordService = (PasswordService) com.egtechnologies.sgtapp.util.JSFUtils.findBean("PasswordService");
                    Password password = passwordService.getActivePasswordbyUser(user);
                    if (password != null && encryptedPassword.equals(password.getPassword())) {
                        if (!this.getNewPassword().equals(this.getCurrentPassword())) {
                            if (this.getNewPassword().length() >= 8) {
                                if (this.getNewPassword().equals(this.getConfirmNewPassword())) {
                                    password.setPassword(SHA1BASE64.encriptar(this.getNewPassword()));
                                    password.setModifiedDate(new Date());
                                    password.setModifiedBy(user.getIdUser());
                                    passwordService.saveOrUpdate(password);

                                    user.setFirstlogin(Boolean.FALSE);
                                    user.setModifiedDate(new Date());
                                    user.setModifiedBy(user.getIdUser());
                                    UserService userService = (UserService) com.egtechnologies.sgtapp.util.JSFUtils.findBean("UserService");
                                    userService.saveOrUpdate(user);

                                    this.setCurrentPassword(StringUtils.EMPTY);
                                    this.setNewPassword(StringUtils.EMPTY);
                                    this.setConfirmNewPassword(StringUtils.EMPTY);

                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Su nueva clave se actualizó con éxito."));
                                    destino = "/pages/welcome";
                                } else {
                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Confirme su nueva clave correctamente."));
                                    destino = "changePassword";
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "La nueva clave debe tener al menos 8 caracteres."));
                                destino = "changePassword";
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "La nueva clave debe ser diferente a la actual."));
                            destino = "changePassword";
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Clave actual incorrecta."));
                        destino = "changePassword";
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese su nueva clave."));
                    destino = "changePassword";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese su clave actual."));
                destino = "changePassword";
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return destino;
    }

    public void changePassword(ActionEvent event) {
        String encryptedPassword;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            if (this.getCurrentPassword() != null && !"".equals(this.getCurrentPassword())) {
                if (this.getCurrentPassword() != null && !"".equals(this.getCurrentPassword())) {
                    encryptedPassword = SHA1BASE64.encriptar(this.getCurrentPassword().trim());
                    PasswordService passwordService = (PasswordService) com.egtechnologies.sgtapp.util.JSFUtils.findBean("PasswordService");
                    Password password = passwordService.getActivePasswordbyUser(user);
                    if (password != null && encryptedPassword.equals(password.getPassword())) {
                        if (!this.getNewPassword().equals(this.getCurrentPassword())) {
                            if (this.getNewPassword().length() >= 8) {
                                if (this.getNewPassword().equals(this.getConfirmNewPassword())) {
                                    password.setPassword(SHA1BASE64.encriptar(this.getNewPassword()));
                                    password.setModifiedDate(new Date());
                                    password.setModifiedBy(user.getIdUser());
                                    passwordService.saveOrUpdate(password);

                                    user.setFirstlogin(Boolean.FALSE);
                                    user.setModifiedDate(new Date());
                                    user.setModifiedBy(user.getIdUser());
                                    UserService userService = (UserService) com.egtechnologies.sgtapp.util.JSFUtils.findBean("UserService");
                                    userService.saveOrUpdate(user);

                                    this.setCurrentPassword(StringUtils.EMPTY);
                                    this.setNewPassword(StringUtils.EMPTY);
                                    this.setConfirmNewPassword(StringUtils.EMPTY);

                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Su nueva clave se actualizó con éxito."));
                                } else {
                                    FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Confirme su nueva clave correctamente."));
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "La nueva clave debe tener al menos 8 caracteres."));
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "La nueva clave debe ser diferente a la actual."));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Clave actual incorrecta."));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese su nueva clave."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese su clave actual."));
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }
}
