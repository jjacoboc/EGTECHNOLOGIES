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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Jonathan
 */
@ManagedBean(name="assignPermissionMB")
@SessionScoped
public class AssignPermissionMB implements Serializable {

    private String searchName;
    private List<Role> listRoles;
    private Role selectedRole;
    
    private List<Facilities> listTargetFacilities;
    private Facilities selectedTargetFacility;
    private List<Facilities> listSourceFacilities;
    private Facilities selectedSourceFacility;
    
    private DualListModel<Facilities> pickList;

    /**
     * Creates a new instance of AssignPermissionMB
     */
    public AssignPermissionMB() {
        selectedRole = new Role();
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

    public List<Facilities> getListTargetFacilities() {
        return listTargetFacilities;
    }

    public void setListTargetFacilities(List<Facilities> listTargetFacilities) {
        this.listTargetFacilities = listTargetFacilities;
    }

    public Facilities getSelectedTargetFacility() {
        return selectedTargetFacility;
    }

    public void setSelectedTargetFacility(Facilities selectedTargetFacility) {
        this.selectedTargetFacility = selectedTargetFacility;
    }

    public List<Facilities> getListSourceFacilities() {
        return listSourceFacilities;
    }

    public void setListSourceFacilities(List<Facilities> listSourceFacilities) {
        this.listSourceFacilities = listSourceFacilities;
    }

    public Facilities getSelectedSourceFacility() {
        return selectedSourceFacility;
    }

    public void setSelectedSourceFacility(Facilities selectedSourceFacility) {
        this.selectedSourceFacility = selectedSourceFacility;
    }

    public DualListModel<Facilities> getPickList() {
        return pickList;
    }

    public void setPickList(DualListModel<Facilities> pickList) {
        this.pickList = pickList;
    }

    @PostConstruct
    public void init() {
        try {
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.getAllActiveRoles());
            this.setListSourceFacilities(new ArrayList<Facilities>());
            this.setListTargetFacilities(new ArrayList<Facilities>());
            this.setPickList(new DualListModel<>(this.getListSourceFacilities(), this.getListTargetFacilities()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            Role role = new Role();
            role.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            RoleService roleService = (RoleService) JSFUtils.findBean("RoleService");
            this.setListRoles(roleService.search(role));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleSelectedRole(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            Role role = this.getListRoles().get(Integer.parseInt(index));
            FacilityService facilityService = (FacilityService) JSFUtils.findBean("FacilityService");
            List<Facilities> notAssigned = facilityService.getFacilitiesNotAssignedByRole(role.getIdRole());
            List<Facilities> assigned = facilityService.getFacilitiesAssignedByRole(role.getIdRole());
            this.setSelectedRole(role);
            this.setListSourceFacilities(notAssigned);
            this.setListTargetFacilities(assigned);
            this.setPickList(new DualListModel<>(this.getListSourceFacilities(), this.getListTargetFacilities()));
        } catch (NumberFormatException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onTransfer(TransferEvent event) {
        int index;
        try {
            if (event != null) {
                if (event.isAdd()) {
                    Collections.sort(this.getListSourceFacilities(), Facilities.Comparators.ID);
                    for (Facilities ele : (List<Facilities>) event.getItems()) {
                        index = Collections.binarySearch(this.getListSourceFacilities(), ele, Facilities.Comparators.ID);
                        if (this.getListTargetFacilities()== null) {
                            this.setListTargetFacilities(new ArrayList<Facilities>());
                        }
                        this.getListTargetFacilities().add(this.getListSourceFacilities().get(index));
                        this.getListSourceFacilities().remove(index);
                    }
                }
                if (event.isRemove()) {
                    Collections.sort(this.getListTargetFacilities(), Facilities.Comparators.ID);
                    for (Facilities ele : (List<Facilities>) event.getItems()) {
                        index = Collections.binarySearch(this.getListTargetFacilities(), ele, Facilities.Comparators.ID);
                        if (this.getListSourceFacilities() == null) {
                            this.setListSourceFacilities(new ArrayList<Facilities>());
                        }
                        this.getListSourceFacilities().add(this.getListTargetFacilities().get(index));
                        this.getListTargetFacilities().remove(index);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void addFacilityToTarget(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            this.setSelectedSourceFacility(this.getListSourceFacilities().get(Integer.parseInt(index)));
            this.getListTargetFacilities().add(this.getSelectedSourceFacility());
            this.getListSourceFacilities().remove(this.getSelectedSourceFacility());
        } catch (NumberFormatException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void addFacilityToSource(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            this.setSelectedTargetFacility(this.getListTargetFacilities().get(Integer.parseInt(index)));
            this.getListSourceFacilities().add(this.getSelectedTargetFacility());
            this.getListTargetFacilities().remove(this.getSelectedTargetFacility());
        } catch (NumberFormatException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        FacesMessage message;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            RightService rightService = (RightService) JSFUtils.findBean("RightService");
            List<Facilities> source = this.getListSourceFacilities();
            List<Facilities> target = this.getListTargetFacilities();

            if (source != null && !source.isEmpty()) {
                for (int i = 0; i < source.size(); i++) {
                    Facilities facilities = source.get(i);
                    //Integer id = Integer.parseInt(source.get(i).toString());

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
                    //Integer id = Integer.parseInt(target.get(i).toString());
                    
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
        } catch (NumberFormatException e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "An unexpected error occurred. Contact the service administrator.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.getMessage();
            e.printStackTrace();
        }
    }
}
