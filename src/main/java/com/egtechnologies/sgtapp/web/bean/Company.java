/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jonathan
 */
public class Company implements Serializable{
    
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
    private Set<BranchOffice> BranchOffices = new HashSet<>(0);
    private Set<Hardware> Hardwares = new HashSet<>(0);
    private Set<Network> Networks = new HashSet<>(0);
    private Set<Position> Positions = new HashSet<>(0);
    private Set<Software> Softwares = new HashSet<>(0);
    
    public Company() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Set<BranchOffice> getBranchOffices() {
        return BranchOffices;
    }

    public void setBranchOffices(Set<BranchOffice> BranchOffices) {
        this.BranchOffices = BranchOffices;
    }

    public Set<Hardware> getHardwares() {
        return Hardwares;
    }

    public void setHardwares(Set<Hardware> Hardwares) {
        this.Hardwares = Hardwares;
    }

    public Set<Network> getNetworks() {
        return Networks;
    }

    public void setNetworks(Set<Network> Networks) {
        this.Networks = Networks;
    }

    public Set<Position> getPositions() {
        return Positions;
    }

    public void setPositions(Set<Position> Positions) {
        this.Positions = Positions;
    }

    public Set<Software> getSoftwares() {
        return Softwares;
    }

    public void setSoftwares(Set<Software> Softwares) {
        this.Softwares = Softwares;
    }
    
}
