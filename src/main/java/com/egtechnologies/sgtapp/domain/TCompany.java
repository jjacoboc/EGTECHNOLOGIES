package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TCompany generated by hbm2java
 */
public class TCompany implements java.io.Serializable {

    private Integer idCompany;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phone;
    private String email;
    private String website;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
    private Set<TBranchOffice> TBranchOffices = new HashSet<>(0);
    private Set<THardware> THardwares = new HashSet<>(0);
    private Set<TNetwork> TNetworks = new HashSet<>(0);
    private Set<TPosition> TPositions = new HashSet<>(0);
    private Set<TSoftware> TSoftwares = new HashSet<>(0);

    public TCompany() {
    }

    public TCompany(String name, String address, String city, String state, String country, String zipcode, String phone, String email, String website, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate, Set<TBranchOffice> TBranchOffices, Set<THardware> THardwares, Set<TNetwork> TNetworks, Set<TPosition> TPositions, Set<TSoftware> TSoftwares) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.TBranchOffices = TBranchOffices;
        this.THardwares = THardwares;
        this.TNetworks = TNetworks;
        this.TPositions = TPositions;
        this.TSoftwares = TSoftwares;
    }

    public Integer getIdCompany() {
        return this.idCompany;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Set<TBranchOffice> getTBranchOffices() {
        return this.TBranchOffices;
    }

    public void setTBranchOffices(Set<TBranchOffice> TBranchOffices) {
        this.TBranchOffices = TBranchOffices;
    }

    public Set<THardware> getTHardwares() {
        return this.THardwares;
    }

    public void setTHardwares(Set<THardware> THardwares) {
        this.THardwares = THardwares;
    }

    public Set<TNetwork> getTNetworks() {
        return this.TNetworks;
    }

    public void setTNetworks(Set<TNetwork> TNetworks) {
        this.TNetworks = TNetworks;
    }

    public Set<TPosition> getTPositions() {
        return this.TPositions;
    }

    public void setTPositions(Set<TPosition> TPositions) {
        this.TPositions = TPositions;
    }

    public Set<TSoftware> getTSoftwares() {
        return this.TSoftwares;
    }

    public void setTSoftwares(Set<TSoftware> TSoftwares) {
        this.TSoftwares = TSoftwares;
    }

}
