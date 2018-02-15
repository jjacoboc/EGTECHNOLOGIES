package com.egtechnologies.sgtapp.domain;
// Generated 09-feb-2018 0:17:04 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TEmployee generated by hbm2java
 */
public class TEmployee implements java.io.Serializable {

    private Integer idEmployee;
    private Integer idDepartment;
    private Integer idPosition;
    private String idnumber;
    private String name;
    private String lastname;
    private String homeemail;
    private String phone;
    private String cellphone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private Boolean active;
    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
    private Set<TNetworkUser> TNetworkUsers = new HashSet<>(0);
    private Set<TAssignation> TAssignations = new HashSet<>(0);

    public TEmployee() {
    }

    public TEmployee(Integer idDepartment, Integer idPosition, String idnumber, String name, String lastname, String homeemail, String phone, String cellphone, String address, String city, String state, String country, String zipcode, Boolean active, Integer createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate, Set<TNetworkUser> TNetworkUsers, Set<TAssignation> TAssignations) {
        this.idDepartment = idDepartment;
        this.idPosition = idPosition;
        this.idnumber = idnumber;
        this.name = name;
        this.lastname = lastname;
        this.homeemail = homeemail;
        this.phone = phone;
        this.cellphone = cellphone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.TNetworkUsers = TNetworkUsers;
        this.TAssignations = TAssignations;
    }

    public Integer getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public String getIdnumber() {
        return this.idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHomeemail() {
        return this.homeemail;
    }

    public void setHomeemail(String homeemail) {
        this.homeemail = homeemail;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public Set<TNetworkUser> getTNetworkUsers() {
        return this.TNetworkUsers;
    }

    public void setTNetworkUsers(Set<TNetworkUser> TNetworkUsers) {
        this.TNetworkUsers = TNetworkUsers;
    }

    public Set<TAssignation> getTAssignations() {
        return this.TAssignations;
    }

    public void setTAssignations(Set<TAssignation> TAssignations) {
        this.TAssignations = TAssignations;
    }

}