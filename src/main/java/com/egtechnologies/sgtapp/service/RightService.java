/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Rights;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface RightService {
    
    List<Rights> getRightsByRole(Integer idRole);
    Rights getRightActiveByRoleAndFacility(Integer idRole, Integer idFacilities);
    void saveOrUpdate(Rights rights);
}
