/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.SoftwareService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Software;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Items;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class SoftwareMB implements Serializable {
    
    private Integer searchCompany;
    private String searchName;
    private String searchBrand;
    private Integer idCompany;
    private String name;
    private String description;
    private String brand;
    private Integer licenseCount;
    private String supportEmail;
    private String supportPhone;
    private List<Software> listSoftwares;
    private Software selectedItem;

    public SoftwareMB() {
        selectedItem = new Software();
    }

    public Integer getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Integer searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchBrand() {
        return searchBrand;
    }

    public void setSearchBrand(String searchBrand) {
        this.searchBrand = searchBrand;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(Integer licenseCount) {
        this.licenseCount = licenseCount;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public List<Software> getListSoftwares() {
        return listSoftwares;
    }

    public void setListSoftwares(List<Software> listSoftwares) {
        this.listSoftwares = listSoftwares;
    }

    public Software getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Software selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
            this.setListSoftwares(softwareService.getAllSoftwares());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Software software = new Software();
            software.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            software.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            software.setBrand(this.getSearchBrand()!= null ? this.getSearchBrand().trim() : null);
            SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
            this.setListSoftwares(softwareService.search(software));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(null);
            this.setName(StringUtils.EMPTY);
            this.setDescription(StringUtils.EMPTY);
            this.setBrand(StringUtils.EMPTY);
            this.setLicenseCount(BigDecimal.ZERO.intValue());
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
            Software newSoftware = new Software();
            newSoftware.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newSoftware.setName(this.getName() != null ? this.getName().trim() : null);
            newSoftware.setDescription(this.getDescription()!= null ? this.getDescription().trim() : null);
            newSoftware.setBrand(this.getBrand()!= null ? this.getBrand().trim() : null);
            newSoftware.setLicenseCount(this.getLicenseCount()!= null ? this.getLicenseCount() : null);
            if(!errorValidation(newSoftware)){
                SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
                Software software = softwareService.getSoftwareByName(newSoftware);
                if(software != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newSoftware.setActive(Boolean.TRUE);
                    newSoftware.setCreatedDate(new Date());
                    newSoftware.setCreatedBy(user.getIdUser());
                    softwareService.saveOrUpdate(newSoftware);
                    this.setListSoftwares(softwareService.getAllSoftwares());
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
            Software selectedSoftware = this.getSelectedItem();
            selectedSoftware.setIdCompany(selectedSoftware.getIdCompany() != null ? selectedSoftware.getIdCompany() : null);
            selectedSoftware.setName(selectedSoftware.getName() != null ? selectedSoftware.getName().trim() : null);
            selectedSoftware.setDescription(selectedSoftware.getDescription()!= null ? selectedSoftware.getDescription().trim() : null);
            selectedSoftware.setBrand(selectedSoftware.getBrand()!= null ? selectedSoftware.getBrand().trim() : null);
            selectedSoftware.setLicenseCount(selectedSoftware.getLicenseCount()!= null ? selectedSoftware.getLicenseCount() : null);
            if(!errorValidation(selectedSoftware)){
                SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
                Software software = softwareService.getSoftwareByName(selectedSoftware);
                if(software !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedSoftware.setModifiedDate(new Date());
                    selectedSoftware.setModifiedBy(user.getIdUser());
                    softwareService.saveOrUpdate(selectedSoftware);
                    this.setListSoftwares(softwareService.getAllSoftwares());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
            getSelectedItem().setActive(Boolean.FALSE);
            softwareService.saveOrUpdate(getSelectedItem());
            this.setListSoftwares(softwareService.getAllSoftwares());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
            getSelectedItem().setActive(Boolean.TRUE);
            softwareService.saveOrUpdate(getSelectedItem());
            this.setListSoftwares(softwareService.getAllSoftwares());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Software software){
        FacesMessage message;
        boolean error = false;
        try{
            if(software.getIdCompany().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(software.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter software's name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(software.getBrand())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter software's brand.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(software.getLicenseCount() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter number of lincenses.");
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
