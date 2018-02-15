/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.PasswordService;
import com.egtechnologies.sgtapp.service.PersonService;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.service.UserService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.util.SHA1BASE64;
import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.Person;
import com.egtechnologies.sgtapp.web.bean.PersonUser;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Items;
import com.egtechnologies.sgtapp.web.common.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jonathan
 */
@ManagedBean
@ViewScoped
public class UserMB implements Serializable {
    
    private String searchIdNumber;
    private String searchName;
    private String searchLastName;
    private String searchUserName;
    private String username;
    private String workemail;
    private List<PersonUser> list;
    private PersonUser selectedItem;
    private User selectedUser;
    private List<SelectItem> employees;
    private Integer employee;
    private List<SelectItem> roles;
    private Integer role;

    public UserMB() {
    }

    public String getSearchIdNumber() {
        return searchIdNumber;
    }

    public void setSearchIdNumber(String searchIdNumber) {
        this.searchIdNumber = searchIdNumber;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchLastName() {
        return searchLastName;
    }

    public void setSearchLastName(String searchLastName) {
        this.searchLastName = searchLastName;
    }

    public String getSearchUserName() {
        return searchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWorkemail() {
        return workemail;
    }

    public void setWorkemail(String workemail) {
        this.workemail = workemail;
    }

    public List<PersonUser> getList() {
        return list;
    }

    public void setList(List<PersonUser> list) {
        this.list = list;
    }

    public PersonUser getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(PersonUser selectedItem) {
        this.selectedItem = selectedItem;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<SelectItem> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SelectItem> employees) {
        this.employees = employees;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public List<SelectItem> getRoles() {
        return roles;
    }

    public void setRoles(List<SelectItem> roles) {
        this.roles = roles;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    
    @PostConstruct
    public void init() {
        try {
            UserService userService = (UserService) JSFUtils.findBean("UserService");
            this.setList(userService.getUsers());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            PersonUser personUser = new PersonUser();
            personUser.setIdnumber(this.getSearchIdNumber() != null ? this.getSearchIdNumber().toUpperCase().trim() : null);
            personUser.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            personUser.setLastname(this.getSearchLastName() != null ? this.getSearchLastName().toUpperCase().trim() : null);
            personUser.setUsername(this.getSearchUserName() != null ? this.getSearchUserName().toUpperCase().trim() : null);
            UserService userService = (UserService) JSFUtils.findBean("UserService");
            this.setList(userService.search(personUser));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setUsername(StringUtils.EMPTY);
            this.setWorkemail(StringUtils.EMPTY);
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            this.setEmployees(new Items(personService.getPersonsWithoutUser(), Items.FIRST_ITEM_SELECT, "idPerson", "fullname").getItems());
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setRoles(new Items(roleService.getAllActiveRoles(), Items.FIRST_ITEM_SELECT, "idRole", "name").getItems());
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void save(ActionEvent actionEvent){
        FacesMessage message;
        String[] to = new String[1];
        String subject;
        StringBuilder body = new StringBuilder();
        try {
            User userSession = (User)JSFUtils.getSessionAttribute("usuario");
            User newUser = new User();
            newUser.setIdPerson(this.getEmployee());
            newUser.setUsername(this.getUsername() != null ? this.getUsername().toUpperCase().trim() : null);
            newUser.setWorkemail(this.getWorkemail() != null ? this.getWorkemail().trim() : null);
            newUser.setIdRole(this.getRole());
            if(!errorValidation(newUser)){
                UserService userService = (UserService) JSFUtils.findBean("UserService");
                User user = userService.getActiveUserbyUsername(newUser.getUsername());
                if(user != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This username is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newUser.setActive(Boolean.TRUE);
                    newUser.setFirstlogin(Boolean.TRUE);
                    newUser.setSendkey(Boolean.FALSE);
                    newUser.setCreatedDate(new Date());
                    newUser.setCreatedBy(userSession.getIdUser());
                    userService.saveOrUpdate(newUser);
                    newUser = userService.getActiveUserbyUsername(newUser.getUsername());
                    
                    String newKey = Util.generarClave();
                    Password newPassword = new Password();
                    newPassword.setIdUser(newUser.getIdUser());
                    newPassword.setActive(Boolean.TRUE);
                    newPassword.setLocked(Boolean.FALSE);
                    newPassword.setCreatedBy(userSession.getIdUser());
                    newPassword.setCreatedDate(new Date());
                    newPassword.setPassword(SHA1BASE64.encriptar(newKey));
                    PasswordService passwordService = (PasswordService) JSFUtils.findBean("PasswordService");
                    passwordService.saveOrUpdate(newPassword);
                    
                    to[0] = newUser.getWorkemail();
                    subject = "SGTWEB - USER CREATED";
                    body.append("Your user and password has been created successfully.<br>");
                    body.append("Username: <strong>").append(newUser.getUsername()).append("</strong><br>");
                    body.append("Password: <strong>").append(newKey).append("</strong><br>");
                    Util.enviarCorreo(to, subject, body.toString());
                    
                    this.setList(userService.getUsers());
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }
    
    public void toEdit(ActionEvent actionEvent){
        try{
            Integer idUser = Integer.parseInt(JSFUtils.getRequestParameter("iduser"));
            UserService userService = (UserService) JSFUtils.findBean("UserService");
            this.setSelectedUser(userService.getUserById(idUser));
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            this.setEmployees(new Items(personService.getPersonsWithoutUser(), Items.FIRST_ITEM_SELECT, "idPerson", "fullname").getItems());
            Person person = personService.getPersonById(this.getSelectedUser().getIdPerson());
            this.getEmployees().add(1,new SelectItem(person.getIdPerson(), person.getFullname()));
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setRoles(new Items(roleService.getAllActiveRoles(), Items.FIRST_ITEM_SELECT, "idRole", "name").getItems());
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch(NumberFormatException e) {
            e.getMessage();
        }
    }
    
    public void edit(ActionEvent actionEvent){
        FacesMessage message;
        try {
            User userSession = (User)JSFUtils.getSessionAttribute("usuario");
            if(!errorValidation(this.getSelectedUser())){
                UserService userService = (UserService) JSFUtils.findBean("UserService");
                User user = userService.getActiveUserbyUsername(this.getSelectedUser().getUsername());
                if(user != null && user.getIdUser().intValue() != this.getSelectedUser().getIdUser().intValue()){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This username is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    this.getSelectedUser().setModifiedDate(new Date());
                    this.getSelectedUser().setModifiedBy(userSession.getIdUser());
                    userService.saveOrUpdate(this.getSelectedUser());
                    
                    this.setList(userService.getUsers());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            UserService userService = (UserService) JSFUtils.findBean("UserService");
            User user = userService.getUserById(this.getSelectedItem().getIdUser());
            user.setActive(Boolean.FALSE); //INACTIVO = 0
            userService.saveOrUpdate(user);
            this.setList(userService.getUsers());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            UserService userService = (UserService) JSFUtils.findBean("UserService");
            User user = userService.getUserById(this.getSelectedItem().getIdUser());
            user.setActive(Boolean.TRUE); //ACTIVO = 1
            userService.saveOrUpdate(user);
            this.setList(userService.getUsers());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(User user){
        FacesMessage message;
        boolean error = false;
        try{
            if(user.getIdPerson() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select an employee.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(user.getUsername())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter username.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(user.getWorkemail())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter business email.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(user.getIdRole() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select a role.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
        }
        return error;
    }
}
