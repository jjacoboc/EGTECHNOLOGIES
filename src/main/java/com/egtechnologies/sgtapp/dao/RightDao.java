/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TRights;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface RightDao {
    
    List<TRights> getRightsByRole(Integer idRole);
    TRights getRightActiveByRoleAndFacility(Integer idRole, Integer idFacilities);
    void saveOrUpdate(TRights trights);
}
