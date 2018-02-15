/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.CompanyService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Company;
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
public class CompanyMB implements Serializable {
    
    private String searchName;
    private String searchCity;
    private String searchState;
    private String searchCountry;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phone;
    private String email;
    private String website;
    private List<Company> listCompanies;
    private Company selectedItem;
    
    /** Creates a new instance of CompanyMB */
    public CompanyMB() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Company> getListCompanies() {
        return listCompanies;
    }

    public void setListCompanies(List<Company> listCompanies) {
        this.listCompanies = listCompanies;
    }

    public Company getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Company selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            this.setListCompanies(companyService.getAllCompanies());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Company company = new Company();
            company.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            company.setCity(this.getSearchCity() != null ? this.getSearchCity().toUpperCase().trim() : null);
            company.setState(this.getSearchState() != null ? this.getSearchState().toUpperCase().trim() : null);
            company.setCountry(this.getSearchCountry() != null ? this.getSearchCountry().toUpperCase().trim() : null);
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            this.setListCompanies(companyService.search(company));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setName(StringUtils.EMPTY);
            this.setAddress(StringUtils.EMPTY);
            this.setCity(StringUtils.EMPTY);
            this.setCountry(StringUtils.EMPTY);
            this.setEmail(StringUtils.EMPTY);
            this.setPhone(StringUtils.EMPTY);
            this.setState(StringUtils.EMPTY);
            this.setWebsite(StringUtils.EMPTY);
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
            Company newCompany = new Company();
            newCompany.setAddress(this.getAddress() != null ? this.getAddress().toUpperCase().trim() : null);
            newCompany.setCity(this.getCity()!= null ? this.getCity().toUpperCase().trim() : null);
            newCompany.setCountry(this.getCountry()!= null ? this.getCountry().toUpperCase().trim() : null);
            newCompany.setEmail(this.getEmail()!= null ? this.getEmail().toUpperCase().trim() : null);
            newCompany.setName(this.getName() != null ? this.getName().toUpperCase().trim() : null);
            newCompany.setPhone(this.getPhone()!= null ? this.getPhone().toUpperCase().trim() : null);
            newCompany.setState(this.getState()!= null ? this.getState().toUpperCase().trim() : null);
            newCompany.setWebsite(this.getWebsite()!= null ? this.getWebsite().toUpperCase().trim() : null);
            newCompany.setZipcode(this.getZipcode()!= null ? this.getZipcode().toUpperCase().trim() : null);
            if(!errorValidation(newCompany)){
                CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
                Company company = companyService.getCompanyByName(newCompany);
                if(company != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newCompany.setActive(Boolean.TRUE);
                    newCompany.setCreatedDate(new Date());
                    newCompany.setCreatedBy(user.getIdUser());
                    companyService.saveOrUpdate(newCompany);
                    this.setListCompanies(companyService.getAllCompanies());
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
            Company selectedCompany = this.getSelectedItem();
            selectedCompany.setAddress(selectedCompany.getAddress() != null ? selectedCompany.getAddress().toUpperCase().trim() : null);
            selectedCompany.setCity(selectedCompany.getCity()!= null ? selectedCompany.getCity().toUpperCase().trim() : null);
            selectedCompany.setCountry(selectedCompany.getCountry()!= null ? selectedCompany.getCountry().toUpperCase().trim() : null);
            selectedCompany.setEmail(selectedCompany.getEmail()!= null ? selectedCompany.getEmail().toUpperCase().trim() : null);
            selectedCompany.setName(selectedCompany.getName() != null ? selectedCompany.getName().toUpperCase().trim() : null);
            selectedCompany.setPhone(selectedCompany.getPhone()!= null ? selectedCompany.getPhone().toUpperCase().trim() : null);
            selectedCompany.setState(selectedCompany.getState()!= null ? selectedCompany.getState().toUpperCase().trim() : null);
            selectedCompany.setWebsite(selectedCompany.getWebsite()!= null ? selectedCompany.getWebsite().toUpperCase().trim() : null);
            selectedCompany.setZipcode(selectedCompany.getZipcode()!= null ? selectedCompany.getZipcode().toUpperCase().trim() : null);
            if(!errorValidation(selectedCompany)){
                CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
                Company company = companyService.getCompanyByName(selectedCompany);
                if(company !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedCompany.setModifiedDate(new Date());
                    selectedCompany.setModifiedBy(user.getIdUser());
                    companyService.saveOrUpdate(selectedCompany);
                    this.setListCompanies(companyService.getAllCompanies());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            getSelectedItem().setActive(Boolean.FALSE);
            companyService.saveOrUpdate(getSelectedItem());
            this.setListCompanies(companyService.getAllCompanies());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            getSelectedItem().setActive(Boolean.TRUE);
            companyService.saveOrUpdate(getSelectedItem());
            this.setListCompanies(companyService.getAllCompanies());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Company company){
        FacesMessage message;
        boolean error = false;
        try{
            if(StringUtils.isBlank(company.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getAddress())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's address.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getCity())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's city.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getState())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's state.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getCountry())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's country.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getZipcode())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's zipcode.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getPhone())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's phone number.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(company.getEmail())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter company's contact email.");
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
