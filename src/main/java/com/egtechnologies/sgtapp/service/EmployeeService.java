/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Employee;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface EmployeeService {
    
    Employee getEmployeeById(Integer idEmployee);
    Employee getEmployeeByCode(Employee employee);
    List<Employee> getAllEmployees();
    List<Employee> getAllActiveEmployees();
    List<Employee> search(Employee employee);
    void saveOrUpdate(Employee employee);
}
