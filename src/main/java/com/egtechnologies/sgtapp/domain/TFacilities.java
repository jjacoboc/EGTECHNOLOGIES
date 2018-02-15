package com.egtechnologies.sgtapp.domain;
// Generated 10-ene-2018 4:21:48 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TFacilities generated by hbm2java
 */
public class TFacilities implements java.io.Serializable {

    private Integer idFacilities;
    private String name;
    private String description;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;

    public TFacilities() {
    }

    public TFacilities(String name, String description, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public Integer getIdFacilities() {
        return this.idFacilities;
    }

    public void setIdFacilities(Integer idFacilities) {
        this.idFacilities = idFacilities;
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

}