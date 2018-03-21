/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.HardwareTypeService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.HardwareType;
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
 * @author Jonathan
 */
@ManagedBean
@ViewScoped
public class HardwareTypeMB implements Serializable {
    
    private String searchName;
    private String name;
    private List<HardwareType> listHardwareTypes;
    private HardwareType selectedItem;
    
    /** Creates a new instance of HardwareTypeMB */
    public HardwareTypeMB() {
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

    public List<HardwareType> getListHardwareTypes() {
        return listHardwareTypes;
    }

    public void setListHardwareTypes(List<HardwareType> listHardwareTypes) {
        this.listHardwareTypes = listHardwareTypes;
    }

    public HardwareType getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(HardwareType selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            this.setListHardwareTypes(hardwareTypeService.getAllHardwareTypes());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            HardwareType hardwareType = new HardwareType();
            hardwareType.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            this.setListHardwareTypes(hardwareTypeService.search(hardwareType));
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
    
    public void save(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            HardwareType newHardwareType = new HardwareType();
            newHardwareType.setName(this.getName() != null ? this.getName().trim() : null);
            if(!errorValidation(newHardwareType)){
                HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
                HardwareType hardwareType = hardwareTypeService.getHardwareTypeByName(newHardwareType);
                if(hardwareType != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newHardwareType.setActive(Boolean.TRUE);
                    newHardwareType.setCreatedDate(new Date());
                    newHardwareType.setCreatedBy(user.getIdUser());
                    hardwareTypeService.saveOrUpdate(newHardwareType);
                    this.setListHardwareTypes(hardwareTypeService.getAllHardwareTypes());
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
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
    
    public void edit(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            HardwareType selectedHardwareType = this.getSelectedItem();
            selectedHardwareType.setName(selectedHardwareType.getName() != null ? selectedHardwareType.getName().trim() : null);
            if(!errorValidation(selectedHardwareType)){
                HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
                HardwareType hardwareType = hardwareTypeService.getHardwareTypeByName(selectedHardwareType);
                if(hardwareType !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedHardwareType.setModifiedDate(new Date());
                    selectedHardwareType.setModifiedBy(user.getIdUser());
                    hardwareTypeService.saveOrUpdate(selectedHardwareType);
                    this.setListHardwareTypes(hardwareTypeService.getAllHardwareTypes());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            getSelectedItem().setActive(Boolean.FALSE);
            hardwareTypeService.saveOrUpdate(getSelectedItem());
            this.setListHardwareTypes(hardwareTypeService.getAllHardwareTypes());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            getSelectedItem().setActive(Boolean.TRUE);
            hardwareTypeService.saveOrUpdate(getSelectedItem());
            this.setListHardwareTypes(hardwareTypeService.getAllHardwareTypes());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(HardwareType type){
        FacesMessage message;
        boolean error = false;
        try{
            if(StringUtils.isBlank(type.getName())){
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
