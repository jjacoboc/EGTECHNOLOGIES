/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.FacilityService;
import com.egtechnologies.sgtapp.service.RightService;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Facilities;
import com.egtechnologies.sgtapp.web.bean.Rights;
import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Jonathan
 */
@ManagedBean
@ViewScoped
public class AssignPermissionMB implements Serializable {

    private String searchName;
    private List<Role> listRoles;
    private Role selectedRole;
    private DualListModel<Facilities> dualListFacilities;

    /**
     * Creates a new instance of AssignPermissionMB
     */
    public AssignPermissionMB() {
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = listRoles;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public DualListModel<Facilities> getDualListFacilities() {
        return dualListFacilities;
    }

    public void setDualListFacilities(DualListModel<Facilities> dualListFacilities) {
        this.dualListFacilities = dualListFacilities;
    }

    @PostConstruct
    public void init() {
        try {
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.getAllActiveRoles());
            this.setDualListFacilities(new DualListModel<>(new ArrayList(), new ArrayList()));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            Role role = new Role();
            role.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.search(role));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleSelectedRole(ActionEvent event) {
        try {
            String rowkey = JSFUtils.getRequestParameter("rowkey");
            Role role = this.getListRoles().get(Integer.parseInt(rowkey));
            FacilityService facilityService = (FacilityService) JSFUtils.findBean("FacilityService");
            List<Facilities> notAssigned = facilityService.getFacilitiesNotAssignedByRole(role.getIdRole());
            List<Facilities> assigned = facilityService.getFacilitiesAssignedByRole(role.getIdRole());
            this.setSelectedRole(role);
            this.setDualListFacilities(new DualListModel<>(notAssigned, assigned));
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    public void save(ActionEvent event) {
        FacesMessage message;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            RightService rightService = (RightService) JSFUtils.findBean("RightService");
            List<Facilities> source = this.getDualListFacilities().getSource();
            List<Facilities> target = this.getDualListFacilities().getTarget();

            if (source != null && !source.isEmpty()) {
                for (int i = 0; i < source.size(); i++) {
                    Facilities facilities = source.get(i);

                    Rights rights = rightService.getRightActiveByRoleAndFacility(this.getSelectedRole().getIdRole(), facilities.getIdFacilities());
                    if (rights != null) {
                        rights.setActive(Boolean.FALSE);
                        rights.setModifiedDate(new Date());
                        rights.setModifiedBy(user.getIdUser());
                        rightService.saveOrUpdate(rights);
                    }
                }
            }
            if (target != null && !target.isEmpty()) {
                for (int i = 0; i < target.size(); i++) {
                    Facilities facilities = target.get(i);
                    Rights rights = rightService.getRightActiveByRoleAndFacility(this.getSelectedRole().getIdRole(), facilities.getIdFacilities());
                    if (rights != null) {
                        if (!rights.getActive()) {
                            rights.setActive(Boolean.TRUE);
                            rights.setModifiedDate(new Date());
                            rights.setModifiedBy(user.getIdUser());
                            rightService.saveOrUpdate(rights);
                        }
                    } else {
                        rights = new Rights();
                        rights.setIdFacilities(facilities.getIdFacilities());
                        rights.setIdRole(this.getSelectedRole().getIdRole());
                        rights.setActive(Boolean.TRUE);
                        rights.setCreatedDate(new Date());
                        rights.setCreatedBy(user.getIdUser());
                        rightService.saveOrUpdate(rights);
                    }
                }
            }
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Permissions assigned successfully.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "An unexpected error occurred. Contact the service administrator.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.getMessage();
        }
    }
}
