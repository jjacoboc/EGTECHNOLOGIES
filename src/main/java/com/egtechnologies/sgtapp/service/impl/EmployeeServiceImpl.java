/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.EmployeeDao;
import com.egtechnologies.sgtapp.domain.TEmployee;
import com.egtechnologies.sgtapp.service.EmployeeService;
import com.egtechnologies.sgtapp.web.bean.Employee;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
    }
    
    @Override
    public Employee getEmployeeById(Integer idEmployee) {
        TEmployee temployee = employeeDao.getEmployeeById(idEmployee);
        return Util.parserEmployee(temployee);
    }

    @Override
    public Employee getEmployeeByCode(Employee employee) {
        TEmployee temployee = employeeDao.getEmployeeByCode(Util.parserTEmployee(employee));
        return Util.parserEmployee(temployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<TEmployee> list = employeeDao.getAllEmployees();
        List<Employee> result = new ArrayList<>();
        for(TEmployee item : list) {
            result.add(Util.parserEmployee(item));
        }
        return result;
    }

    @Override
    public List<Employee> getAllActiveEmployees() {
        List<TEmployee> list = employeeDao.getAllActiveEmployees();
        List<Employee> result = new ArrayList<>();
        for(TEmployee item : list) {
            result.add(Util.parserEmployee(item));
        }
        return result;
    }

    @Override
    public List<Employee> search(Employee employee) {
        List<TEmployee> list = employeeDao.search(Util.parserTEmployee(employee));
        List<Employee> result = new ArrayList<>();
        for(TEmployee item : list) {
            result.add(Util.parserEmployee(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        employeeDao.saveOrUpdate(Util.parserTEmployee(employee));
    }
    
}
