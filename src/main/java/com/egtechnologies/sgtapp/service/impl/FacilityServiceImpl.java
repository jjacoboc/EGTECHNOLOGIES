/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.FacilityDao;
import com.egtechnologies.sgtapp.domain.TFacilities;
import com.egtechnologies.sgtapp.service.FacilityService;
import com.egtechnologies.sgtapp.web.bean.Facilities;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value="FacilityService")
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDao facilityDao;

    public FacilityServiceImpl() {
    }
    
    @Override
    public List<Facilities> getAllFacilities() {
        List<TFacilities> list = facilityDao.getAllFacilities();
        List<Facilities> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            for(TFacilities t : list) {
                result.add(Util.parserFacilities(t));
            }
        }
        return result;
    }
    
    @Override
    public List<Facilities> getFacilitiesByRole(Integer idRole) {
        List<TFacilities> list = facilityDao.getFacilitiesByRole(idRole);
        List<Facilities> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            for(TFacilities t : list) {
                result.add(Util.parserFacilities(t));
            }
        }
        return result;
    }
    
    @Override
    public List<Facilities> getFacilitiesAssignedByRole(Integer idRole) {
        List<TFacilities> list = facilityDao.getFacilitiesAssignedByRole(idRole);
        List<Facilities> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            for(TFacilities t : list) {
                result.add(Util.parserFacilities(t));
            }
        }
        return result;
    }
    
    @Override
    public List<Facilities> getFacilitiesNotAssignedByRole(Integer idRole) {
        List<TFacilities> list = facilityDao.getFacilitiesNotAssignedByRole(idRole);
        List<Facilities> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            for(TFacilities t : list) {
                result.add(Util.parserFacilities(t));
            }
        }
        return result;
    }
}
