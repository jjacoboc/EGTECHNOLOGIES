/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.BranchOfficeService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.BranchOffice;
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
public class BranchOfficeMB implements Serializable {
    
    private Integer searchCompany;
    private String searchName;
    private String searchCity;
    private String searchState;
    private String searchCountry;
    private Integer idCompany;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phone;
    private List<BranchOffice> listBranchOffices;
    private BranchOffice selectedItem;

    public BranchOfficeMB() {
        selectedItem = new BranchOffice();
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

    public String getSearchCity() {
        return searchCity;
    }

    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }

    public String getSearchState() {
        return searchState;
    }

    public void setSearchState(String searchState) {
        this.searchState = searchState;
    }

    public String getSearchCountry() {
        return searchCountry;
    }

    public void setSearchCountry(String searchCountry) {
        this.searchCountry = searchCountry;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BranchOffice> getListBranchOffices() {
        return listBranchOffices;
    }

    public void setListBranchOffices(List<BranchOffice> listBranchOffices) {
        this.listBranchOffices = listBranchOffices;
    }

    public BranchOffice getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(BranchOffice selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            this.setListBranchOffices(branchOfficeService.getAllBranchOffices());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            BranchOffice branchOffice = new BranchOffice();
            branchOffice.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            branchOffice.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            branchOffice.setCity(this.getSearchCity() != null ? this.getSearchCity().trim() : null);
            branchOffice.setState(this.getSearchState() != null ? this.getSearchState().trim() : null);
            branchOffice.setCountry(this.getSearchCountry() != null ? this.getSearchCountry().trim() : null);
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            this.setListBranchOffices(branchOfficeService.search(branchOffice));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(null);
            this.setName(StringUtils.EMPTY);
            this.setAddress(StringUtils.EMPTY);
            this.setCity(StringUtils.EMPTY);
            this.setCountry(StringUtils.EMPTY);
            this.setPhone(StringUtils.EMPTY);
            this.setState(StringUtils.EMPTY);
            this.setZipcode(StringUtils.EMPTY);
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
            BranchOffice newBranchOffice = new BranchOffice();
            newBranchOffice.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newBranchOffice.setAddress(this.getAddress() != null ? this.getAddress().trim() : null);
            newBranchOffice.setCity(this.getCity()!= null ? this.getCity().trim() : null);
            newBranchOffice.setCountry(this.getCountry()!= null ? this.getCountry().trim() : null);
            newBranchOffice.setName(this.getName() != null ? this.getName().trim() : null);
            newBranchOffice.setPhone(this.getPhone()!= null ? this.getPhone().trim() : null);
            newBranchOffice.setState(this.getState()!= null ? this.getState().trim() : null);
            newBranchOffice.setZipcode(this.getZipcode()!= null ? this.getZipcode().trim() : null);
            if(!errorValidation(newBranchOffice)){
                BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
                BranchOffice branchOffice = branchOfficeService.getBranchOfficeByName(newBranchOffice);
                if(branchOffice != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newBranchOffice.setActive(Boolean.TRUE);
                    newBranchOffice.setCreatedDate(new Date());
                    newBranchOffice.setCreatedBy(user.getIdUser());
                    branchOfficeService.saveOrUpdate(newBranchOffice);
                    this.setListBranchOffices(branchOfficeService.getAllBranchOffices());
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
            BranchOffice selectedBranchOffice = this.getSelectedItem();
            selectedBranchOffice.setIdCompany(selectedBranchOffice.getIdCompany() != null ? selectedBranchOffice.getIdCompany() : null);
            selectedBranchOffice.setAddress(selectedBranchOffice.getAddress() != null ? selectedBranchOffice.getAddress().trim() : null);
            selectedBranchOffice.setCity(selectedBranchOffice.getCity()!= null ? selectedBranchOffice.getCity().trim() : null);
            selectedBranchOffice.setCountry(selectedBranchOffice.getCountry()!= null ? selectedBranchOffice.getCountry().trim() : null);
            selectedBranchOffice.setName(selectedBranchOffice.getName() != null ? selectedBranchOffice.getName().trim() : null);
            selectedBranchOffice.setPhone(selectedBranchOffice.getPhone()!= null ? selectedBranchOffice.getPhone().trim() : null);
            selectedBranchOffice.setState(selectedBranchOffice.getState()!= null ? selectedBranchOffice.getState().trim() : null);
            selectedBranchOffice.setZipcode(selectedBranchOffice.getZipcode()!= null ? selectedBranchOffice.getZipcode().trim() : null);
            if(!errorValidation(selectedBranchOffice)){
                BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
                BranchOffice branchOffice = branchOfficeService.getBranchOfficeByName(selectedBranchOffice);
                if(branchOffice !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedBranchOffice.setModifiedDate(new Date());
                    selectedBranchOffice.setModifiedBy(user.getIdUser());
                    branchOfficeService.saveOrUpdate(selectedBranchOffice);
                    this.setListBranchOffices(branchOfficeService.getAllBranchOffices());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            getSelectedItem().setActive(Boolean.FALSE);
            branchOfficeService.saveOrUpdate(getSelectedItem());
            this.setListBranchOffices(branchOfficeService.getAllBranchOffices());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            getSelectedItem().setActive(Boolean.TRUE);
            branchOfficeService.saveOrUpdate(getSelectedItem());
            this.setListBranchOffices(branchOfficeService.getAllBranchOffices());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(BranchOffice branchOffice){
        FacesMessage message;
        boolean error = false;
        try{
            if(branchOffice.getIdCompany() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getAddress())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's address.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getCity())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's city.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getState())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's state.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getCountry())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's country.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getZipcode())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's zipcode.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(branchOffice.getPhone())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter branch office's phone number.");
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
