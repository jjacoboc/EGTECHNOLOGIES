package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TSoftware generated by hbm2java
 */
public class TSoftware implements java.io.Serializable {

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
    private Set<TInstallation> TInstallations = new HashSet<>(0);

    public TSoftware() {
    }

    public TSoftware(Integer idCompany, String name, String description, String brand, Integer licenseCount, String supportEmail, String supportPhone, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate, Set<TInstallation> TInstallations) {
        this.idCompany = idCompany;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.licenseCount = licenseCount;
        this.supportEmail = supportEmail;
        this.supportPhone = supportPhone;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.TInstallations = TInstallations;
    }

    public Integer getIdSoftware() {
        return this.idSoftware;
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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getLicenseCount() {
        return this.licenseCount;
    }

    public void setLicenseCount(Integer licenseCount) {
        this.licenseCount = licenseCount;
    }

    public String getSupportEmail() {
        return this.supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return this.supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<TInstallation> getTInstallations() {
        return this.TInstallations;
    }

    public void setTInstallations(Set<TInstallation> TInstallations) {
        this.TInstallations = TInstallations;
    }

}
