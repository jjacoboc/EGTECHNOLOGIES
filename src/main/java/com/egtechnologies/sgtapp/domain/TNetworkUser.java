package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TNetworkUser generated by hbm2java
 */
public class TNetworkUser implements java.io.Serializable {

    private Integer idNetworkUser;
    private Integer idEmployee;
    private Integer idNetwork;
    private String username;
    private String email;
    private String annex;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;

    public TNetworkUser() {
    }

    public TNetworkUser(Integer idEmployee, Integer idNetwork, String username, String email, String annex, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate) {
        this.idEmployee = idEmployee;
        this.idNetwork = idNetwork;
        this.username = username;
        this.email = email;
        this.annex = annex;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public Integer getIdNetworkUser() {
        return this.idNetworkUser;
    }

    public void setIdNetworkUser(Integer idNetworkUser) {
        this.idNetworkUser = idNetworkUser;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdNetwork() {
        return idNetwork;
    }

    public void setIdNetwork(Integer idNetwork) {
        this.idNetwork = idNetwork;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnnex() {
        return this.annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
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