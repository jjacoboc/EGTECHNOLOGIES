package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TInstallation generated by hbm2java
 */
public class TInstallation implements java.io.Serializable {

    private Integer idInstallation;
    private Integer idHardware;
    private Integer idSoftware;
    private String licenseNumber;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;

    public TInstallation() {
    }

    public TInstallation(Integer idHardware, Integer idSoftware, String licenseNumber, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate) {
        this.idHardware = idHardware;
        this.idSoftware = idSoftware;
        this.licenseNumber = licenseNumber;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public Integer getIdInstallation() {
        return this.idInstallation;
    }

    public void setIdInstallation(Integer idInstallation) {
        this.idInstallation = idInstallation;
    }

    public Integer getIdHardware() {
        return idHardware;
    }

    public void setIdHardware(Integer idHardware) {
        this.idHardware = idHardware;
    }

    public Integer getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(Integer idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

}
