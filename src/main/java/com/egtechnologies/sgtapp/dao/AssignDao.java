/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TAssignation;

/**
 *
 * @author Jonathan
 */
public interface AssignDao {
    
    void deleteHardwaresByEmployee(Integer idEmployee);
    void saveOrUpdate(TAssignation tassignation);
}
