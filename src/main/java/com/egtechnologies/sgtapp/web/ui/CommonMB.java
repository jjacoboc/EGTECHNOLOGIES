/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.BranchOfficeService;
import com.egtechnologies.sgtapp.service.CompanyService;
import com.egtechnologies.sgtapp.service.DepartmentService;
import com.egtechnologies.sgtapp.service.FacilityService;
import com.egtechnologies.sgtapp.service.HardwareTypeService;
import com.egtechnologies.sgtapp.service.PositionService;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.common.Items;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author Jonathan
 */
@ManagedBean
@ApplicationScoped
public class CommonMB implements Serializable {

    private List<SelectItem> listAllRoles;
    private List<SelectItem> listAllFacilities;
    private List<SelectItem> listAllCompanies;
    private List<SelectItem> listAllActiveCompanies;
    private List<SelectItem> listAllBranchOffice;
    private List<SelectItem> listAllActiveBranchOffice;
    private List<SelectItem> listAllBranchOfficeByCompany;
    private List<SelectItem> listAllActiveBranchOfficeByCompany;
    private List<SelectItem> listAllDepartment;
    private List<SelectItem> listAllActiveDepartment;
    private List<SelectItem> listAllDepartmentByBranchOffice;
    private List<SelectItem> listAllActiveDepartmentByBranchOffice;
    private List<SelectItem> listAllPositions;
    private List<SelectItem> listAllActivePositions;
    private List<SelectItem> listAllPositionsByCompany;
    private List<SelectItem> listAllActivePositionsByCompany;
    private List<SelectItem> listAllHardwareTypes;
    private List<SelectItem> listAllActiveHardwareTypes;
    
    /**
     * Creates a new instance of CommonMB
     */
    public CommonMB() {
    }

    public List<SelectItem> getListAllRoles() {
        if(this.listAllRoles == null){
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.listAllRoles = new Items(roleService.getAllRoles(), Items.FIRST_ITEM_SELECT, "idRole", "name").getItems();
        }
        return listAllRoles;
    }

    public void setListAllRoles(List<SelectItem> listAllRoles) {
        this.listAllRoles = listAllRoles;
    }

    public List<SelectItem> getListAllFacilities() {
        if(this.listAllFacilities == null){
            FacilityService facilityService = (FacilityService) JSFUtils.findBean("FacilityService");
            this.listAllFacilities = new Items(facilityService.getAllFacilities(), Items.FIRST_ITEM_SELECT, "idFacilities", "name").getItems();
        }
        return listAllFacilities;
    }

    public void setListAllFacilities(List<SelectItem> listAllFacilities) {
        this.listAllFacilities = listAllFacilities;
    }

    public List<SelectItem> getListAllCompanies() {
        if(this.listAllCompanies == null){
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            this.listAllCompanies = new Items(companyService.getAllCompanies(), Items.FIRST_ITEM_SELECT, "idCompany", "name").getItems();
        }
        return listAllCompanies;
    }

    public void setListAllCompanies(List<SelectItem> listAllCompanies) {
        this.listAllCompanies = listAllCompanies;
    }

    public List<SelectItem> getListAllActiveCompanies() {
        if(this.listAllActiveCompanies == null){
            CompanyService companyService = (CompanyService) JSFUtils.findBean("CompanyService");
            this.listAllActiveCompanies = new Items(companyService.getAllActiveCompanies(), Items.FIRST_ITEM_SELECT, "idCompany", "name").getItems();
        }
        return listAllActiveCompanies;
    }

    public void setListAllActiveCompanies(List<SelectItem> listAllActiveCompanies) {
        this.listAllActiveCompanies = listAllActiveCompanies;
    }

    public List<SelectItem> getListAllBranchOffice() {
        if(this.listAllBranchOffice == null){
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            this.listAllBranchOffice = new Items(branchOfficeService.getAllBranchOffices(), Items.FIRST_ITEM_SELECT, "idBranchOffice", "name").getItems();
        }
        return listAllBranchOffice;
    }

    public void setListAllBranchOffice(List<SelectItem> listAllBranchOffice) {
        this.listAllBranchOffice = listAllBranchOffice;
    }

    public List<SelectItem> getListAllActiveBranchOffice() {
        if(this.listAllActiveBranchOffice == null){
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            this.listAllActiveBranchOffice = new Items(branchOfficeService.getAllActiveBranchOffices(), Items.FIRST_ITEM_SELECT, "idBranchOffice", "name").getItems();
        }
        return listAllActiveBranchOffice;
    }

    public void setListAllActiveBranchOffice(List<SelectItem> listAllActiveBranchOffice) {
        this.listAllActiveBranchOffice = listAllActiveBranchOffice;
    }

    public List<SelectItem> getListAllBranchOfficeByCompany() {
        return listAllBranchOfficeByCompany;
    }

    public void setListAllBranchOfficeByCompany(List<SelectItem> listAllBranchOfficeByCompany) {
        this.listAllBranchOfficeByCompany = listAllBranchOfficeByCompany;
    }
    
    public void getListBranchOfficeByCompany(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idCompany = (Integer)((SelectOneMenu) e.getSource()).getValue();
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            listAllBranchOfficeByCompany =  new Items(branchOfficeService.getAllBranchOfficesByCompany(idCompany), Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems();
        }else{
            listAllBranchOfficeByCompany =  new Items(null, Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems();
        }
    }

    public List<SelectItem> getListAllActiveBranchOfficeByCompany() {
        return listAllActiveBranchOfficeByCompany;
    }

    public void setListAllActiveBranchOfficeByCompany(List<SelectItem> listAllActiveBranchOfficeByCompany) {
        this.listAllActiveBranchOfficeByCompany = listAllActiveBranchOfficeByCompany;
    }
    
    public void getListActiveBranchOfficeByCompany(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idCompany = (Integer)((SelectOneMenu) e.getSource()).getValue();
            BranchOfficeService branchOfficeService = (BranchOfficeService) JSFUtils.findBean("BranchOfficeService");
            listAllActiveBranchOfficeByCompany =  new Items(branchOfficeService.getAllActiveBranchOfficesByCompany(idCompany), Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems();
        }else{
            listAllActiveBranchOfficeByCompany =  new Items(null, Items.FIRST_ITEM_SELECT, "idBranchOffice","name").getItems();
        }
    }

    public List<SelectItem> getListAllDepartment() {
        if(this.listAllDepartment == null){
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            this.listAllDepartment = new Items(departmentService.getAllDepartments(), Items.FIRST_ITEM_SELECT, "idDepartment", "name").getItems();
        }
        return listAllDepartment;
    }

    public void setListAllDepartment(List<SelectItem> listAllDepartment) {
        this.listAllDepartment = listAllDepartment;
    }

    public List<SelectItem> getListAllActiveDepartment() {
        if(this.listAllDepartment == null){
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            this.listAllDepartment = new Items(departmentService.getAllActiveDepartments(), Items.FIRST_ITEM_SELECT, "idDepartment", "name").getItems();
        }
        return listAllActiveDepartment;
    }

    public void setListAllActiveDepartment(List<SelectItem> listAllActiveDepartment) {
        this.listAllActiveDepartment = listAllActiveDepartment;
    }

    public List<SelectItem> getListAllDepartmentByBranchOffice() {
        return listAllDepartmentByBranchOffice;
    }

    public void setListAllDepartmentByBranchOffice(List<SelectItem> listAllDepartmentByBranchOffice) {
        this.listAllDepartmentByBranchOffice = listAllDepartmentByBranchOffice;
    }
    
    public void getListDepartmentByBranchOffice(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idBranchOffice = (Integer)((SelectOneMenu) e.getSource()).getValue();
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            listAllDepartmentByBranchOffice =  new Items(departmentService.getAllDepartmentsByBranchOffice(idBranchOffice), Items.FIRST_ITEM_SELECT, "idDepartment","name").getItems();
        }else{
            listAllDepartmentByBranchOffice =  new Items(null, Items.FIRST_ITEM_SELECT, "idDepartment","name").getItems();
        }
    }

    public List<SelectItem> getListAllActiveDepartmentByBranchOffice() {
        return listAllActiveDepartmentByBranchOffice;
    }

    public void setListAllActiveDepartmentByBranchOffice(List<SelectItem> listAllActiveDepartmentByBranchOffice) {
        this.listAllActiveDepartmentByBranchOffice = listAllActiveDepartmentByBranchOffice;
    }
    
    public void getListActiveDepartmentByBranchOffice(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idBranchOffice = (Integer)((SelectOneMenu) e.getSource()).getValue();
            DepartmentService departmentService = (DepartmentService) JSFUtils.findBean("DepartmentService");
            listAllActiveDepartmentByBranchOffice =  new Items(departmentService.getAllActiveDepartmentsByBranchOffice(idBranchOffice), Items.FIRST_ITEM_SELECT, "idDepartment","name").getItems();
        }else{
            listAllActiveDepartmentByBranchOffice =  new Items(null, Items.FIRST_ITEM_SELECT, "idDepartment","name").getItems();
        }
    }

    public List<SelectItem> getListAllPositions() {
        if(this.listAllPositions == null){
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            this.listAllPositions = new Items(positionService.getAllPositions(), Items.FIRST_ITEM_SELECT, "idPosition", "name").getItems();
        }
        return listAllPositions;
    }

    public void setListAllPositions(List<SelectItem> listAllPositions) {
        this.listAllPositions = listAllPositions;
    }

    public List<SelectItem> getListAllActivePositions() {
        if(this.listAllPositions == null){
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            this.listAllPositions = new Items(positionService.getAllActivePositions(), Items.FIRST_ITEM_SELECT, "idPosition", "name").getItems();
        }
        return listAllActivePositions;
    }

    public void setListAllActivePositions(List<SelectItem> listAllActivePositions) {
        this.listAllActivePositions = listAllActivePositions;
    }

    public List<SelectItem> getListAllPositionsByCompany() {
        return listAllPositionsByCompany;
    }

    public void setListAllPositionsByCompany(List<SelectItem> listAllPositionsByCompany) {
        this.listAllPositionsByCompany = listAllPositionsByCompany;
    }
    
    public void getListPositionsByCompany(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idCompany = (Integer)((SelectOneMenu) e.getSource()).getValue();
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            listAllPositionsByCompany =  new Items(positionService.getAllPositionsByCompany(idCompany), Items.FIRST_ITEM_SELECT, "idPosition","name").getItems();
        }else{
            listAllPositionsByCompany =  new Items(null, Items.FIRST_ITEM_SELECT, "idPosition","name").getItems();
        }
    }

    public List<SelectItem> getListAllActivePositionsByCompany() {
        return listAllActivePositionsByCompany;
    }

    public void setListAllActivePositionsByCompany(List<SelectItem> listAllActivePositionsByCompany) {
        this.listAllActivePositionsByCompany = listAllActivePositionsByCompany;
    }
    
    public void getListActivePositionsByCompany(AjaxBehaviorEvent e) {
        if(e!=null){
            Integer idCompany = (Integer)((SelectOneMenu) e.getSource()).getValue();
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            listAllActivePositionsByCompany =  new Items(positionService.getAllActivePositionsByCompany(idCompany), Items.FIRST_ITEM_SELECT, "idPosition","name").getItems();
        }else{
            listAllActivePositionsByCompany =  new Items(null, Items.FIRST_ITEM_SELECT, "idPosition","name").getItems();
        }
    }

    public List<SelectItem> getListAllHardwareTypes() {
        if(this.listAllHardwareTypes == null){
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            this.listAllHardwareTypes = new Items(hardwareTypeService.getAllHardwareTypes(), Items.FIRST_ITEM_SELECT, "idHardwareType", "name").getItems();
        }
        return listAllHardwareTypes;
    }

    public void setListAllHardwareTypes(List<SelectItem> listAllHardwareTypes) {
        this.listAllHardwareTypes = listAllHardwareTypes;
    }

    public List<SelectItem> getListAllActiveHardwareTypes() {
        if(this.listAllActiveHardwareTypes == null){
            HardwareTypeService hardwareTypeService = (HardwareTypeService) JSFUtils.findBean("HardwareTypeService");
            this.listAllActiveHardwareTypes = new Items(hardwareTypeService.getAllActiveHardwareTypes(), Items.FIRST_ITEM_SELECT, "idHardwareType", "name").getItems();
        }
        return listAllActiveHardwareTypes;
    }

    public void setListAllActiveHardwareTypes(List<SelectItem> listAllActiveHardwareTypes) {
        this.listAllActiveHardwareTypes = listAllActiveHardwareTypes;
    }
}
