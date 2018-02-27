/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TDepartment;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface DepartmentDao {
    
    TDepartment getDepartmentById(Integer idDepartment);
    TDepartment getDepartmentByName(TDepartment tdepartment);
    List<TDepartment> getAllDepartments();
    List<TDepartment> getAllActiveDepartments();
    List<TDepartment> getAllDepartmentsByBranchOffice(Integer idBranchOffice);
    List<TDepartment> getAllActiveDepartmentsByBranchOffice(Integer idBranchOffice);
    List<TDepartment> search(TDepartment tdepartment);
    void saveOrUpdate(TDepartment tdepartment);
}
