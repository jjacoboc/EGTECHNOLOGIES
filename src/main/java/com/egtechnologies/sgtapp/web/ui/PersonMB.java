/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.PersonService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.util.Validate;
import com.egtechnologies.sgtapp.web.bean.Person;
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
public class PersonMB implements Serializable {
    
    private String searchIdNumber;
    private String searchName;
    private String searchLastName;
    private String searchUserName;
    private String idnumber;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private String cellphone;
    private String homeemail;
    private List<Person> listPersons;
    private Person selectedItem;

    public PersonMB() {
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

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHomeemail() {
        return homeemail;
    }

    public void setHomeemail(String homeemail) {
        this.homeemail = homeemail;
    }

    public List<Person> getListPersons() {
        return listPersons;
    }

    public void setListPersons(List<Person> listPersons) {
        this.listPersons = listPersons;
    }

    public Person getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Person selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            this.setListPersons(personService.getAllPersons());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Person person = new Person();
            person.setIdnumber(this.getSearchIdNumber() != null ? this.getSearchIdNumber().trim() : null);
            person.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            person.setLastname(this.getSearchLastName() != null ? this.getSearchLastName().trim() : null);
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            this.setListPersons(personService.search(person));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdnumber(StringUtils.EMPTY);
            this.setName(StringUtils.EMPTY);
            this.setLastname(StringUtils.EMPTY);
            this.setAddress(StringUtils.EMPTY);
            this.setCity(StringUtils.EMPTY);
            this.setCountry(StringUtils.EMPTY);
            this.setZipcode(StringUtils.EMPTY);
            this.setPhone(StringUtils.EMPTY);
            this.setCellphone(StringUtils.EMPTY);
            this.setHomeemail(StringUtils.EMPTY);
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
            Person newPerson = new Person();
            newPerson.setIdnumber(this.getIdnumber() != null ? this.getIdnumber().trim() : null);
            newPerson.setName(this.getName() != null ? this.getName().trim() : null);
            newPerson.setLastname(this.getLastname() != null ? this.getLastname().trim() : null);
            newPerson.setAddress(this.getAddress() != null ? this.getAddress().trim() : null);
            newPerson.setCity(this.getCity() != null ? this.getCity().trim() : null);
            newPerson.setCountry(this.getCountry() != null ? this.getCountry().trim() : null);
            newPerson.setZipcode(this.getZipcode() != null ? this.getZipcode().trim() : null);
            newPerson.setPhone(this.getPhone() != null ? this.getPhone().trim() : null);
            newPerson.setCellphone(this.getCellphone() != null ? this.getCellphone().trim() : null);
            newPerson.setHomeemail(this.getHomeemail() != null ? this.getHomeemail().trim() : null);
            if(!errorValidation(newPerson)){
                PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
                Person person = personService.getPersonByIdnumber(newPerson.getIdnumber());
                if(person != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This id number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    person = personService.getPersonByEmail(newPerson.getHomeemail());
                    if(person != null){
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This email is already in use.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    } else {
                        newPerson.setActive(Boolean.TRUE);
                        newPerson.setCreatedDate(new Date());
                        newPerson.setCreatedBy(user.getIdUser());
                        personService.saveOrUpdate(newPerson);
                        this.setListPersons(personService.getAllPersons());
                        RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                    }
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
            this.getSelectedItem().setIdnumber(this.getSelectedItem().getIdnumber() != null ? this.getSelectedItem().getIdnumber().trim() : null);
            this.getSelectedItem().setName(this.getSelectedItem().getName() != null ? this.getSelectedItem().getName().trim() : null);
            this.getSelectedItem().setLastname(this.getSelectedItem().getLastname() != null ? this.getSelectedItem().getLastname().trim() : null);
            this.getSelectedItem().setAddress(this.getSelectedItem().getAddress() != null ? this.getSelectedItem().getAddress().trim() : null);
            this.getSelectedItem().setCity(this.getSelectedItem().getCity() != null ? this.getSelectedItem().getCity().trim() : null);
            this.getSelectedItem().setCountry(this.getSelectedItem().getCountry() != null ? this.getSelectedItem().getCountry().trim() : null);
            this.getSelectedItem().setZipcode(this.getSelectedItem().getZipcode() != null ? this.getSelectedItem().getZipcode().trim() : null);
            this.getSelectedItem().setPhone(this.getSelectedItem().getPhone() != null ? this.getSelectedItem().getPhone().trim() : null);
            this.getSelectedItem().setCellphone(this.getSelectedItem().getCellphone() != null ? this.getSelectedItem().getCellphone().trim() : null);
            this.getSelectedItem().setHomeemail(this.getSelectedItem().getHomeemail() != null ? this.getSelectedItem().getHomeemail().trim() : null);
            if(!errorValidation(this.getSelectedItem())){
                PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
                Person person = personService.getPersonByIdnumber(this.getSelectedItem().getIdnumber());
                if(person != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This id number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    person = personService.getPersonByEmail(this.getSelectedItem().getHomeemail());
                    if(person != null){
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This email is already in use.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    } else {
                        this.getSelectedItem().setActive(Boolean.TRUE);
                        this.getSelectedItem().setModifiedDate(new Date());
                        this.getSelectedItem().setModifiedBy(user.getIdUser());
                        personService.saveOrUpdate(this.getSelectedItem());
                        this.setListPersons(personService.getAllPersons());
                        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            getSelectedItem().setActive(Boolean.FALSE); //INACTIVO = 0
            personService.saveOrUpdate(getSelectedItem());
            this.setListPersons(personService.getAllPersons());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            PersonService personService = (PersonService) JSFUtils.findBean("PersonService");
            getSelectedItem().setActive(Boolean.TRUE); //ACTIVO = 1
            personService.saveOrUpdate(getSelectedItem());
            this.setListPersons(personService.getAllPersons());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Person person){
        FacesMessage message;
        boolean error = false;
        try{
            if(StringUtils.isBlank(person.getIdnumber())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's id.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getLastname())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's last name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getAddress())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's address.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getCity())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's city.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getCountry())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's country.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getCellphone())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's mobile.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            } else if(!Validate.isPhone(person.getCellphone())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Incorrect mobile format.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(person.getHomeemail())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter employee's email.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            } else if(!Validate.isEmail(person.getHomeemail())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Incorrect email format.");
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
