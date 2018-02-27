/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Department;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface DepartmentService {
    
    Department getDepartmentById(Integer idBranchOffice);
    Department getDepartmentByName(Department department);
    List<Department> getAllDepartments();
    List<Department> getAllActiveDepartments();
    List<Department> getAllDepartmentsByBranchOffice(Integer idBranchOffice);
    List<Department> getAllActiveDepartmentsByBranchOffice(Integer idBranchOffice);
    List<Department> search(Department department);
    void saveOrUpdate(Department department);
}
