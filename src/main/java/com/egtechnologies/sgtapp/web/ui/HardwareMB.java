/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.HardwareService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Items;
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
public class HardwareMB implements Serializable {
    
    private Integer searchCompany;
    private Integer searchHardwareType;
    private String searchBrand;
    private String searchModel;
    private Integer idCompany;
    private Integer idHardwareType;
    private String brand;
    private String model;
    private String serialNumber;
    private List<Hardware> listHardwares;
    private Hardware selectedItem;

    public HardwareMB() {
        selectedItem = new Hardware();
    }

    public Integer getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Integer searchCompany) {
        this.searchCompany = searchCompany;
    }

    public Integer getSearchHardwareType() {
        return searchHardwareType;
    }

    public void setSearchHardwareType(Integer searchHardwareType) {
        this.searchHardwareType = searchHardwareType;
    }

    public String getSearchBrand() {
        return searchBrand;
    }

    public void setSearchBrand(String searchBrand) {
        this.searchBrand = searchBrand;
    }

    public String getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(String searchModel) {
        this.searchModel = searchModel;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getIdHardwareType() {
        return idHardwareType;
    }

    public void setIdHardwareType(Integer idHardwareType) {
        this.idHardwareType = idHardwareType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Hardware> getListHardwares() {
        return listHardwares;
    }

    public void setListHardwares(List<Hardware> listHardwares) {
        this.listHardwares = listHardwares;
    }

    public Hardware getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Hardware selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            this.setListHardwares(hardwareService.getAllHardwares());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Hardware hardware = new Hardware();
            hardware.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            hardware.setIdHardwareType(this.getSearchHardwareType()!= null ? this.getSearchHardwareType() : null);
            hardware.setModel(this.getSearchModel() != null ? this.getSearchModel().trim() : null);
            hardware.setBrand(this.getSearchBrand()!= null ? this.getSearchBrand().trim() : null);
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            this.setListHardwares(hardwareService.search(hardware));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(Items.NULL_VALUE);
            this.setIdHardwareType(Items.NULL_VALUE);
            this.setModel(StringUtils.EMPTY);
            this.setBrand(StringUtils.EMPTY);
            this.setSerialNumber(StringUtils.EMPTY);
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
            Hardware newHardware = new Hardware();
            newHardware.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newHardware.setIdHardwareType(this.getIdHardwareType()!= null ? this.getIdHardwareType() : null);
            newHardware.setModel(this.getModel()!= null ? this.getModel().trim() : null);
            newHardware.setBrand(this.getBrand()!= null ? this.getBrand().trim() : null);
            newHardware.setSerialNumber(this.getSerialNumber()!= null ? this.getSerialNumber().trim() : null);
            if(!errorValidation(newHardware)){
                HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
                Hardware hardware = hardwareService.getHardwareBySerialNumber(newHardware);
                if(hardware != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This serial number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newHardware.setActive(Boolean.TRUE);
                    newHardware.setCreatedDate(new Date());
                    newHardware.setCreatedBy(user.getIdUser());
                    hardwareService.saveOrUpdate(newHardware);
                    this.setListHardwares(hardwareService.getAllHardwares());
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
            Hardware selectedHardware = this.getSelectedItem();
            selectedHardware.setIdCompany(selectedHardware.getIdCompany() != null ? selectedHardware.getIdCompany() : null);
            selectedHardware.setIdHardwareType(selectedHardware.getIdHardwareType() != null ? selectedHardware.getIdHardwareType() : null);
            selectedHardware.setModel(selectedHardware.getModel()!= null ? selectedHardware.getModel().trim() : null);
            selectedHardware.setBrand(selectedHardware.getBrand()!= null ? selectedHardware.getBrand().trim() : null);
            selectedHardware.setSerialNumber(selectedHardware.getSerialNumber()!= null ? selectedHardware.getSerialNumber(): null);
            if(!errorValidation(selectedHardware)){
                HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
                Hardware hardware = hardwareService.getHardwareBySerialNumber(selectedHardware);
                if(hardware !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This serial number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedHardware.setModifiedDate(new Date());
                    selectedHardware.setModifiedBy(user.getIdUser());
                    hardwareService.saveOrUpdate(selectedHardware);
                    this.setListHardwares(hardwareService.getAllHardwares());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            getSelectedItem().setActive(Boolean.FALSE);
            hardwareService.saveOrUpdate(getSelectedItem());
            this.setListHardwares(hardwareService.getAllHardwares());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            getSelectedItem().setActive(Boolean.TRUE);
            hardwareService.saveOrUpdate(getSelectedItem());
            this.setListHardwares(hardwareService.getAllHardwares());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Hardware hardware){
        FacesMessage message;
        boolean error = false;
        try{
            if(hardware.getIdCompany().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(hardware.getIdHardwareType().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select hardware type.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(hardware.getModel())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter hardware's model.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(hardware.getBrand())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter hardware's brand.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(hardware.getSerialNumber())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter hardware's serial number.");
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
