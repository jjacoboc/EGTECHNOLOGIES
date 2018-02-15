/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TFacilities;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface FacilityDao {
    
    List<TFacilities> getAllFacilities();
    List<TFacilities> getFacilitiesByRole(Integer idRole);
    List<TFacilities> getFacilitiesAssignedByRole(Integer idRole);
    List<TFacilities> getFacilitiesNotAssignedByRole(Integer idRole);
}
