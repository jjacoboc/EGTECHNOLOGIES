/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.User;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class RoleMB implements Serializable{

    private String searchName;
    private String name;
    private List<Role> listRoles;
    private Role selectedItem;
    
    /** Creates a new instance of RoleMB */
    public RoleMB() {
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = listRoles;
    }

    public Role getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Role selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.getAllRoles());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Role role = new Role();
            role.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.search(role));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setName(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void toEdit(ActionEvent actionEvent){
        try{
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void save(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            Role newRole = new Role();
            newRole.setName(this.getName() != null ? this.getName().trim() : null);
            if(!errorValidation(newRole)){
                RoleService roleService = (RoleService) JSFUtils.findBean("RoleService"); //validando si existe un cargo con la misma descripción
                Role role = roleService.getRoleByName(newRole);
                if(role != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newRole.setActive(Boolean.TRUE);
                    newRole.setCreatedDate(new Date());
                    newRole.setCreatedBy(user.getIdUser());
                    roleService.saveOrUpdate(newRole);
                    this.setListRoles(roleService.getAllRoles());
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void edit(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            Role selectedRole = this.getSelectedItem();
            selectedRole.setName(selectedRole.getName() != null ? selectedRole.getName().trim() : null);
            if(!errorValidation(selectedRole)){
                RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
                Role role = roleService.getRoleByName(selectedRole); //validando si existe un cargo con la misma descripción
                if(role !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedRole.setModifiedDate(new Date());
                    selectedRole.setModifiedBy(user.getIdUser());
                    roleService.saveOrUpdate(selectedRole);
                    this.setListRoles(roleService.getAllRoles());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            getSelectedItem().setActive(Boolean.FALSE); //INACTIVO = 0
            roleService.saveOrUpdate(getSelectedItem());
            this.setListRoles(roleService.getAllRoles());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            getSelectedItem().setActive(Boolean.TRUE); //ACTIVO = 1
            roleService.saveOrUpdate(getSelectedItem());
            this.setListRoles(roleService.getAllRoles());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Role role){
        FacesMessage message;
        boolean error = false;
        try{
            if(StringUtils.isBlank(role.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter role's name.");
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
