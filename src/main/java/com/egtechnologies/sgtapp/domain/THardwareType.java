package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * THardwareType generated by hbm2java
 */
public class THardwareType implements java.io.Serializable {

    private Integer idHardwareType;
    private String name;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
    private Set<THardware> THardwares = new HashSet<>(0);

    public THardwareType() {
    }

    public THardwareType(String name, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate, Set<THardware> THardwares) {
        this.name = name;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.THardwares = THardwares;
    }

    public Integer getIdHardwareType() {
        return this.idHardwareType;
    }

    public void setIdHardwareType(Integer idHardwareType) {
        this.idHardwareType = idHardwareType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<THardware> getTHardwares() {
        return this.THardwares;
    }

    public void setTHardwares(Set<THardware> THardwares) {
        this.THardwares = THardwares;
    }

}
