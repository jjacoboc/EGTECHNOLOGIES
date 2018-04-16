/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.HardwareService;
import com.egtechnologies.sgtapp.service.InstallService;
import com.egtechnologies.sgtapp.service.SoftwareService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.bean.Installation;
import com.egtechnologies.sgtapp.web.bean.Software;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Items;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
import org.primefaces.model.DualListModel;
import org.springframework.util.CollectionUtils;

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
    private String licenseNumber;
    private List<Hardware> listHardwares;
    private Hardware selectedItem;
    private DualListModel<Software> dualListSoftwares;
    private List<Software> listTargetSoftwares;
    private Software selectedTargetSoftware;
    private List<Software> listSourceSoftwares;
    private Software selectedSourceSoftware;

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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public DualListModel<Software> getDualListSoftwares() {
        return dualListSoftwares;
    }

    public void setDualListSoftwares(DualListModel<Software> dualListSoftwares) {
        this.dualListSoftwares = dualListSoftwares;
    }

    public List<Software> getListTargetSoftwares() {
        return listTargetSoftwares;
    }

    public void setListTargetSoftwares(List<Software> listTargetSoftwares) {
        this.listTargetSoftwares = listTargetSoftwares;
    }

    public Software getSelectedTargetSoftware() {
        return selectedTargetSoftware;
    }

    public void setSelectedTargetSoftware(Software selectedTargetSoftware) {
        this.selectedTargetSoftware = selectedTargetSoftware;
    }

    public List<Software> getListSourceSoftwares() {
        return listSourceSoftwares;
    }

    public void setListSourceSoftwares(List<Software> listSourceSoftwares) {
        this.listSourceSoftwares = listSourceSoftwares;
    }

    public Software getSelectedSourceSoftware() {
        return selectedSourceSoftware;
    }

    public void setSelectedSourceSoftware(Software selectedSourceSoftware) {
        this.selectedSourceSoftware = selectedSourceSoftware;
    }

    @PostConstruct
    public void init() {
        try {
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            this.setListHardwares(hardwareService.getAllHardwares());
            this.setDualListSoftwares(new DualListModel<>(new ArrayList(), new ArrayList()));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            Hardware hardware = new Hardware();
            hardware.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            hardware.setIdHardwareType(this.getSearchHardwareType() != null ? this.getSearchHardwareType() : null);
            hardware.setModel(this.getSearchModel() != null ? this.getSearchModel().trim() : null);
            hardware.setBrand(this.getSearchBrand() != null ? this.getSearchBrand().trim() : null);
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            this.setListHardwares(hardwareService.search(hardware));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void toSave(ActionEvent actionEvent) {
        try {
            this.setIdCompany(Items.NULL_VALUE);
            this.setIdHardwareType(Items.NULL_VALUE);
            this.setModel(StringUtils.EMPTY);
            this.setBrand(StringUtils.EMPTY);
            this.setSerialNumber(StringUtils.EMPTY);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void save(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            Hardware newHardware = new Hardware();
            newHardware.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newHardware.setIdHardwareType(this.getIdHardwareType() != null ? this.getIdHardwareType() : null);
            newHardware.setModel(this.getModel() != null ? this.getModel().trim() : null);
            newHardware.setBrand(this.getBrand() != null ? this.getBrand().trim() : null);
            newHardware.setSerialNumber(this.getSerialNumber() != null ? this.getSerialNumber().trim() : null);
            if (!errorValidation(newHardware)) {
                HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
                Hardware hardware = hardwareService.getHardwareBySerialNumber(newHardware);
                if (hardware != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "This serial number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
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

    public void toEdit(ActionEvent actionEvent) {
        try {
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void edit(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User) JSFUtils.getSessionAttribute("usuario");
            Hardware selectedHardware = this.getSelectedItem();
            selectedHardware.setIdCompany(selectedHardware.getIdCompany() != null ? selectedHardware.getIdCompany() : null);
            selectedHardware.setIdHardwareType(selectedHardware.getIdHardwareType() != null ? selectedHardware.getIdHardwareType() : null);
            selectedHardware.setModel(selectedHardware.getModel() != null ? selectedHardware.getModel().trim() : null);
            selectedHardware.setBrand(selectedHardware.getBrand() != null ? selectedHardware.getBrand().trim() : null);
            selectedHardware.setSerialNumber(selectedHardware.getSerialNumber() != null ? selectedHardware.getSerialNumber() : null);
            if (!errorValidation(selectedHardware)) {
                HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
                Hardware hardware = hardwareService.getHardwareBySerialNumber(selectedHardware);
                if (hardware != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "This serial number is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
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

    public void handleSelectedHardware(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            Hardware hardware = this.getListHardwares().get(Integer.parseInt(index));
            InstallService installService = (InstallService) JSFUtils.findBean("InstallService");
            List<Software> notAssigned = installService.getSoftwaresNotInstalled(hardware);
            List<Software> assigned = installService.getSoftwaresInstalled(hardware);
            this.setSelectedItem(hardware);
            this.setListSourceSoftwares(notAssigned);
            this.setListTargetSoftwares(assigned);
            this.setDualListSoftwares(new DualListModel<>(notAssigned, assigned));
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    public void toAddSoftware(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            this.setSelectedSourceSoftware(this.getListSourceSoftwares().get(Integer.parseInt(index)));
            this.setLicenseNumber(StringUtils.EMPTY);
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    public void addSoftware(ActionEvent event) {
        try {
            this.getListTargetSoftwares().add(this.getSelectedSourceSoftware());
            this.getListSourceSoftwares().remove(this.getSelectedSourceSoftware());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void removeSoftware(ActionEvent event) {
        try {
            String index = JSFUtils.getRequestParameter("index");
            this.setSelectedTargetSoftware(this.getListTargetSoftwares().get(Integer.parseInt(index)));
            this.getListSourceSoftwares().add(this.getSelectedTargetSoftware());
            this.getListTargetSoftwares().remove(this.getSelectedTargetSoftware());
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    public void installSoftware(ActionEvent event) {
        try {
            InstallService installService = (InstallService) JSFUtils.findBean("InstallService");
            List<Software> assigned = installService.getSoftwaresInstalled(this.getSelectedItem());
            Collections.sort(assigned, Software.Comparators.IDSOFTWARE);

            SoftwareService softwareService = (SoftwareService) JSFUtils.findBean("SoftwareService");
            List<Software> target = this.getDualListSoftwares().getTarget();
            if (!CollectionUtils.isEmpty(target)) {
                User user = (User) JSFUtils.getSessionAttribute("usuario");
                for (int i = 0; i < target.size(); i++) {
                    Software software = target.get(i);
                    int index = Collections.binarySearch(assigned, software, Software.Comparators.IDSOFTWARE);
                    if (index == -1) {
                        Installation installation = new Installation();
                        installation.setCreatedBy(user.getIdUser());
                        installation.setCreatedDate(new Date());
                        installation.setIdHardware(this.getSelectedItem().getIdHardware());
                        installation.setIdSoftware(software.getIdSoftware());
                        installation.setLicenseNumber(serialNumber);
                        installService.saveOrUpdate(installation);
                        
                        software.setLicenseCount(software.getLicenseCount() - 1);
                        softwareService.saveOrUpdate(software);
                    }

                }
            }
            List<Software> source = this.getDualListSoftwares().getSource();
            if (!CollectionUtils.isEmpty(source)) {
                for (int i = 0; i < source.size(); i++) {
                    Software software = source.get(i);
                    int index = Collections.binarySearch(assigned, software, Software.Comparators.IDSOFTWARE);
                    if (index != -1) {
                        installService.deleteInstallsBySoftware(software.getIdSoftware());
                        
                        software.setLicenseCount(software.getLicenseCount() + 1);
                        softwareService.saveOrUpdate(software);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void disable(ActionEvent actionEvent) {
        try {
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            getSelectedItem().setActive(Boolean.FALSE);
            hardwareService.saveOrUpdate(getSelectedItem());
            this.setListHardwares(hardwareService.getAllHardwares());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void enable(ActionEvent actionEvent) {
        try {
            HardwareService hardwareService = (HardwareService) JSFUtils.findBean("HardwareService");
            getSelectedItem().setActive(Boolean.TRUE);
            hardwareService.saveOrUpdate(getSelectedItem());
            this.setListHardwares(hardwareService.getAllHardwares());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean errorValidation(Hardware hardware) {
        FacesMessage message;
        boolean error = false;
        try {
            if (hardware.getIdCompany().equals(Items.NULL_VALUE)) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (hardware.getIdHardwareType().equals(Items.NULL_VALUE)) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select hardware type.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(hardware.getModel())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter hardware's model.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(hardware.getBrand())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter hardware's brand.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(hardware.getSerialNumber())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter hardware's serial number.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return error;
    }
}
