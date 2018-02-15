/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.CompanyService;
import com.egtechnologies.sgtapp.service.FacilityService;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.common.Items;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

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
    
}
