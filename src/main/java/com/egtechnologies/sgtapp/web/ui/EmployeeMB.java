/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.EmployeeService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.util.Validate;
import com.egtechnologies.sgtapp.web.bean.Employee;
import com.egtechnologies.sgtapp.web.bean.User;
import java.io.Serializable;
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

/**
 *
 * @author Jonathan
 */
@ManagedBean
@ViewScoped
public class EmployeeMB implements Serializable {

    private Integer searchCompany;
    private Integer searchBranchOffice;
    private Integer searchDepartment;
    private String searchCode;
    private String searchName;
    private String searchLastName;
    private Integer idCompany;
    private Integer idBranchOffice;
    private Integer idDepartment;
    private Integer idPosition;
    private String code;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phone;
    private String cellphone;
    private String homeemail;
    private List<Employee> listEmployees;
    private Employee selectedItem;

    public EmployeeMB() {
    }

    public Integer getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Integer searchCompany) {
        this.searchCompany = searchCompany;
    }

    public Integer getSearchBranchOffice() {
        return searchBranchOffice;
    }

    public void setSearchBranchOffice(Integer searchBranchOffice) {
        this.searchBranchOffice = searchBranchOffice;
    }

    public Integer getSearchDepartment() {
        return searchDepartment;
    }

    public void setSearchDepartment(Integer searchDepartment) {
        this.searchDepartment = searchDepartment;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchLastName() {
        return searchLastName;
    }

    public void setSearchLastName(String searchLastName) {
        this.searchLastName = searchLastName;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getIdBranchOffice() {
        return idBranchOffice;
    }

    public void setIdBranchOffice(Integer idBranchOffice) {
        this.idBranchOffice = idBranchOffice;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHomeemail() {
        return homeemail;
    }

    public void setHomeemail(String homeemail) {
        this.homeemail = homeemail;
    }

    public List<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public Employee getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Employee selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PostConstruct
    public void init() {
        try {
            EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
            this.setListEmployees(employeeService.getAllEmployees());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            Employee employee = new Employee();
            employee.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            employee.setIdBranchOffice(this.getSearchBranchOffice() != null ? this.getSearchBranchOffice() : null);
            employee.setIdDepartment(this.getSearchDepartment() != null ? this.getSearchDepartment() : null);
            employee.setCode(this.getSearchCode() != null ? this.getSearchCode().trim() : null);
            employee.setName(this.getSearchName() != null ? this.getSearchName().trim() : null);
            employee.setLastname(this.getSearchLastName() != null ? this.getSearchLastName().trim() : null);
            EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
            this.setListEmployees(employeeService.search(employee));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void toSave(ActionEvent actionEvent) {
        try {
            this.setIdCompany(null);
            this.setIdBranchOffice(null);
            this.setIdDepartment(null);
            this.setIdPosition(null);
            this.setCode(StringUtils.EMPTY);
            this.setName(StringUtils.EMPTY);
            this.setLastname(StringUtils.EMPTY);
            this.setAddress(StringUtils.EMPTY);
            this.setCity(StringUtils.EMPTY);
            this.setState(StringUtils.EMPTY);
            this.setCountry(StringUtils.EMPTY);
            this.setZipcode(StringUtils.EMPTY);
            this.setPhone(StringUtils.EMPTY);
            this.setCellphone(StringUtils.EMPTY);
            this.setHomeemail(StringUtils.EMPTY);
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
            Employee newEmployee = new Employee();
            newEmployee.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newEmployee.setIdBranchOffice(this.getIdBranchOffice() != null ? this.getIdBranchOffice() : null);
            newEmployee.setIdDepartment(this.getIdDepartment() != null ? this.getIdDepartment() : null);
            newEmployee.setIdPosition(this.getIdPosition() != null ? this.getIdPosition() : null);
            newEmployee.setCode(this.getCode() != null ? this.getCode().toUpperCase().trim() : null);
            newEmployee.setName(this.getName() != null ? this.getName().trim() : null);
            newEmployee.setLastname(this.getLastname() != null ? this.getLastname().trim() : null);
            newEmployee.setAddress(this.getAddress() != null ? this.getAddress().trim() : null);
            newEmployee.setCity(this.getCity() != null ? this.getCity().trim() : null);
            newEmployee.setState(this.getState() != null ? this.getState().trim() : null);
            newEmployee.setCountry(this.getCountry() != null ? this.getCountry().trim() : null);
            newEmployee.setZipcode(this.getZipcode() != null ? this.getZipcode().trim() : null);
            newEmployee.setPhone(this.getPhone() != null ? this.getPhone().trim() : null);
            newEmployee.setCellphone(this.getCellphone() != null ? this.getCellphone().trim() : null);
            newEmployee.setHomeemail(this.getHomeemail() != null ? this.getHomeemail().trim() : null);
            if (!errorValidation(newEmployee)) {
                EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
                Employee employee = employeeService.getEmployeeByCode(newEmployee);
                if (employee != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "This code is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    newEmployee.setActive(Boolean.TRUE);
                    newEmployee.setCreatedDate(new Date());
                    newEmployee.setCreatedBy(user.getIdUser());
                    employeeService.saveOrUpdate(newEmployee);
                    this.setListEmployees(employeeService.getAllEmployees());
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
            this.getSelectedItem().setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            this.getSelectedItem().setIdBranchOffice(this.getIdBranchOffice() != null ? this.getIdBranchOffice() : null);
            this.getSelectedItem().setIdDepartment(this.getIdDepartment() != null ? this.getIdDepartment() : null);
            this.getSelectedItem().setIdPosition(this.getIdPosition() != null ? this.getIdPosition() : null);
            this.getSelectedItem().setCode(this.getSelectedItem().getCode() != null ? this.getSelectedItem().getCode().toUpperCase().trim() : null);
            this.getSelectedItem().setName(this.getSelectedItem().getName() != null ? this.getSelectedItem().getName().trim() : null);
            this.getSelectedItem().setLastname(this.getSelectedItem().getLastname() != null ? this.getSelectedItem().getLastname().trim() : null);
            this.getSelectedItem().setAddress(this.getSelectedItem().getAddress() != null ? this.getSelectedItem().getAddress().trim() : null);
            this.getSelectedItem().setCity(this.getSelectedItem().getCity() != null ? this.getSelectedItem().getCity().trim() : null);
            this.getSelectedItem().setState(this.getState() != null ? this.getState().trim() : null);
            this.getSelectedItem().setCountry(this.getSelectedItem().getCountry() != null ? this.getSelectedItem().getCountry().trim() : null);
            this.getSelectedItem().setZipcode(this.getSelectedItem().getZipcode() != null ? this.getSelectedItem().getZipcode().trim() : null);
            this.getSelectedItem().setPhone(this.getSelectedItem().getPhone() != null ? this.getSelectedItem().getPhone().trim() : null);
            this.getSelectedItem().setCellphone(this.getSelectedItem().getCellphone() != null ? this.getSelectedItem().getCellphone().trim() : null);
            this.getSelectedItem().setHomeemail(this.getSelectedItem().getHomeemail() != null ? this.getSelectedItem().getHomeemail().trim() : null);
            if (!errorValidation(this.getSelectedItem())) {
                EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
                Employee employee = employeeService.getEmployeeByCode(this.getSelectedItem());
                if (employee != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "This code is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    this.getSelectedItem().setActive(Boolean.TRUE);
                    this.getSelectedItem().setModifiedDate(new Date());
                    this.getSelectedItem().setModifiedBy(user.getIdUser());
                    employeeService.saveOrUpdate(this.getSelectedItem());
                    this.setListEmployees(employeeService.getAllEmployees());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void disable(ActionEvent actionEvent) {
        try {
            EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
            getSelectedItem().setActive(Boolean.FALSE); //INACTIVO = 0
            employeeService.saveOrUpdate(getSelectedItem());
            this.setListEmployees(employeeService.getAllEmployees());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void enable(ActionEvent actionEvent) {
        try {
            EmployeeService employeeService = (EmployeeService) JSFUtils.findBean("EmployeeService");
            getSelectedItem().setActive(Boolean.TRUE); //ACTIVO = 1
            employeeService.saveOrUpdate(getSelectedItem());
            this.setListEmployees(employeeService.getAllEmployees());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean errorValidation(Employee employee) {
        FacesMessage message;
        boolean error = false;
        try {
            if (employee.getIdCompany() != null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (employee.getIdBranchOffice() != null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select branch office.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (employee.getIdDepartment() != null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select department.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (employee.getIdPosition() != null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Select position.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getCode())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's code.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getName())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's name.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getLastname())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's last name.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getAddress())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's address.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getCity())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's city.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getState())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's state.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getCountry())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's country.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getCellphone())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's mobile.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (!Validate.isPhone(employee.getCellphone())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Incorrect mobile format.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (StringUtils.isBlank(employee.getHomeemail())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Enter employee's email.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (!Validate.isEmail(employee.getHomeemail())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Incorrect email format.");
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
