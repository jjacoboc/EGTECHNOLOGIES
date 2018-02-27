/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.DepartmentDao;
import com.egtechnologies.sgtapp.domain.TDepartment;
import com.egtechnologies.sgtapp.service.DepartmentService;
import com.egtechnologies.sgtapp.web.bean.Department;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl() {
    }
    
    @Override
    public Department getDepartmentById(Integer idBranchOffice) {
        TDepartment tdepartment = departmentDao.getDepartmentById(idBranchOffice);
        return Util.parserDepartment(tdepartment);
    }

    @Override
    public Department getDepartmentByName(Department department) {
        TDepartment tdepartment = departmentDao.getDepartmentByName(Util.parserTDepartment(department));
        return Util.parserDepartment(tdepartment);
    }

    @Override
    public List<Department> getAllDepartments() {
        List<TDepartment> list = departmentDao.getAllDepartments();
        List<Department> result = new ArrayList<>();
        for(TDepartment item : list) {
            result.add(Util.parserDepartment(item));
        }
        return result;
    }

    @Override
    public List<Department> getAllActiveDepartments() {
        List<TDepartment> list = departmentDao.getAllActiveDepartments();
        List<Department> result = new ArrayList<>();
        for(TDepartment item : list) {
            result.add(Util.parserDepartment(item));
        }
        return result;
    }
    
    @Override
    public List<Department> getAllDepartmentsByBranchOffice(Integer idBranchOffice) {
        List<TDepartment> list = departmentDao.getAllDepartmentsByBranchOffice(idBranchOffice);
        List<Department> result = new ArrayList<>();
        for(TDepartment item : list) {
            result.add(Util.parserDepartment(item));
        }
        return result;
    }
    
    @Override
    public List<Department> getAllActiveDepartmentsByBranchOffice(Integer idBranchOffice) {
        List<TDepartment> list = departmentDao.getAllActiveDepartmentsByBranchOffice(idBranchOffice);
        List<Department> result = new ArrayList<>();
        for(TDepartment item : list) {
            result.add(Util.parserDepartment(item));
        }
        return result;
    }

    @Override
    public List<Department> search(Department department) {
        List<TDepartment> list = departmentDao.search(Util.parserTDepartment(department));
        List<Department> result = new ArrayList<>();
        for(TDepartment item : list) {
            result.add(Util.parserDepartment(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Department department) {
        departmentDao.saveOrUpdate(Util.parserTDepartment(department));
    }
    
}
