/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Facilities;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface FacilityService {
    
    List<Facilities> getAllFacilities();
    List<Facilities> getFacilitiesByRole(Integer idRole);
    List<Facilities> getFacilitiesAssignedByRole(Integer idRole);
    List<Facilities> getFacilitiesNotAssignedByRole(Integer idRole);
}
