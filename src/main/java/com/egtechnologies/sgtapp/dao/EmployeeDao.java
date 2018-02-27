/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TEmployee;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface EmployeeDao {
    
    TEmployee getEmployeeById(Integer idEmployee);
    TEmployee getEmployeeByCode(TEmployee temployee);
    List<TEmployee> getAllEmployees();
    List<TEmployee> getAllActiveEmployees();
    List<TEmployee> search(TEmployee temployee);
    void saveOrUpdate(TEmployee temployee);
}
