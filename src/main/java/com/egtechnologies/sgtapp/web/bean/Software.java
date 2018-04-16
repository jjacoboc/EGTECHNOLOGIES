/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jonathan
 */
public class Software implements Serializable, Comparable<Software> {
    
    private Integer idSoftware;
    private Integer idCompany;
    private String name;
    private String description;
    private String brand;
    private Integer licenseCount;
    private String supportEmail;
    private String supportPhone;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
    private Set<Installation> Installations = new HashSet<>(0);

    public Software() {
    }

    public Integer getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(Integer idSoftware) {
        this.idSoftware = idSoftware;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(Integer licenseCount) {
        this.licenseCount = licenseCount;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Installation> getInstallations() {
        return Installations;
    }

    public void setInstallations(Set<Installation> Installations) {
        this.Installations = Installations;
    }

    @Override
    public int compareTo(Software o) {
        return Comparators.IDSOFTWARE.compare(this, o);
    }
    
    public static class Comparators {
        
        public static Comparator<Software> IDSOFTWARE = new Comparator<Software>() {
            @Override
            public int compare(Software o1, Software o2) {
                return o1.getIdSoftware() - o2.getIdSoftware();
            }
        };
        
        public static Comparator<Software> NAME = new Comparator<Software>() {
            @Override
            public int compare(Software o1, Software o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        
        public static Comparator<Software> BRAND = new Comparator<Software>() {
            @Override
            public int compare(Software o1, Software o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        };
    }
}
