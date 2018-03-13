/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.BranchOfficeService;
import com.egtechnologies.sgtapp.service.DepartmentService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Department;
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
public class DepartmentMB implements Serializable {
    
    private Integer searchCompany;
    private Integer searchBranchOffice;
    private String searchName;
    private Integer idCompany;
    private Integer idBranchOffice;
    private String name;
    private String description;
    private List<Department> listDepartments;
    private Department selectedItem;

    public DepartmentMB() {
        selectedItem = new Department();
    }

    public Integer getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Integer searchCompany) {
        this.searchCompany = searchCompany;
    }

    public Integer getSearchBranchOffice() {
        return searchBranchOffice;
    }

    public void setSearchBranchOffice(Integer searchBranchOffice) {
        this.searchBranchOffice = searchBranchOffice;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getIdBranchOffice() {
        return idBranchOffice;
    }

    public void setIdBranchOffice(Integer idBranchOffice) {
        this.idBranchOffice = idBranchOffice;
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

    public List<Department> getListDepartments() {
        return listDepartments;
    }

    public void setListDepartments(List<Department> listDepartments) {
        this.listDepartments = listDepartments;
    }

    public Department getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Department selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            CommonMB commonMB = (CommonMB)JSFUtils.getSessionAttribute("commonMB");
            commonMB = commonMB != null ? commonMB : new CommonMB();
            commonMB.setListAllBranchOfficeByCompany(new Items(null, Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems());
            JSFUtils.setSessionAttribute("commonMB", commonMB);
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            this.setListDepartments(departmentService.getAllDepartments());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Department department = new Department();
            department.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            department.setIdBranchOffice(this.getSearchBranchOffice()!= null ? this.getSearchBranchOffice() : null);
            department.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            this.setListDepartments(departmentService.search(department));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(Items.NULL_VALUE);
            this.setIdBranchOffice(Items.NULL_VALUE);
            this.setName(StringUtils.EMPTY);
            this.setDescription(StringUtils.EMPTY);
            CommonMB commonMB = (CommonMB)JSFUtils.getSessionAttribute("commonMB");
            commonMB = commonMB != null ? commonMB : new CommonMB();
            commonMB.setListAllActiveBranchOfficeByCompany(new Items(null, Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems());
            JSFUtils.setSessionAttribute("commonMB", commonMB);
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
            Department newDepartment = new Department();
            newDepartment.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newDepartment.setIdBranchOffice(this.getIdBranchOffice()!= null ? this.getIdBranchOffice(): null);
            newDepartment.setName(this.getName() != null ? this.getName().toUpperCase().trim() : null);
            newDepartment.setDescription(this.getDescription()!= null ? this.getDescription().toUpperCase().trim() : null);
            if(!errorValidation(newDepartment)){
                DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
                Department department = departmentService.getDepartmentByName(newDepartment);
                if(department != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newDepartment.setActive(Boolean.TRUE);
                    newDepartment.setCreatedDate(new Date());
                    newDepartment.setCreatedBy(user.getIdUser());
                    departmentService.saveOrUpdate(newDepartment);
                    this.setListDepartments(departmentService.getAllDepartments());
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toEdit(ActionEvent actionEvent){
        try{
            String index = JSFUtils.getRequestParameter("index");
            this.setSelectedItem(this.getListDepartments().get(Integer.parseInt(index)));
            Integer idCompany = this.getSelectedItem().getIdCompany();
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            CommonMB commonMB = (CommonMB)JSFUtils.getSessionAttribute("commonMB");
            commonMB = commonMB != null ? commonMB : new CommonMB();
            commonMB.setListAllActiveBranchOfficeByCompany(new Items(branchOfficeService.getAllActiveBranchOfficesByCompany(idCompany), Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems());
            JSFUtils.setSessionAttribute("commonMB", commonMB);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void edit(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            Department selectedDepartment = this.getSelectedItem();
            selectedDepartment.setIdCompany(selectedDepartment.getIdCompany() != null ? selectedDepartment.getIdCompany() : null);
            selectedDepartment.setIdBranchOffice(selectedDepartment.getIdBranchOffice()!= null ? selectedDepartment.getIdBranchOffice(): null);
            selectedDepartment.setName(selectedDepartment.getName() != null ? selectedDepartment.getName().toUpperCase().trim() : null);
            selectedDepartment.setDescription(selectedDepartment.getDescription()!= null ? selectedDepartment.getDescription().toUpperCase().trim() : null);
            if(!errorValidation(selectedDepartment)){
                DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
                Department department = departmentService.getDepartmentByName(selectedDepartment);
                if(department !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedDepartment.setModifiedDate(new Date());
                    selectedDepartment.setModifiedBy(user.getIdUser());
                    departmentService.saveOrUpdate(selectedDepartment);
                    this.setListDepartments(departmentService.getAllDepartments());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            getSelectedItem().setActive(Boolean.FALSE);
            departmentService.saveOrUpdate(getSelectedItem());
            this.setListDepartments(departmentService.getAllDepartments());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            getSelectedItem().setActive(Boolean.TRUE);
            departmentService.saveOrUpdate(getSelectedItem());
            this.setListDepartments(departmentService.getAllDepartments());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Department department){
        FacesMessage message;
        boolean error = false;
        try{
            if(department.getIdCompany().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(department.getIdBranchOffice().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select branch office.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(department.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter department's name.");
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
