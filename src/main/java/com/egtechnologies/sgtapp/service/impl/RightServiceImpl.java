/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.RightDao;
import com.egtechnologies.sgtapp.domain.TRights;
import com.egtechnologies.sgtapp.web.bean.Rights;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.egtechnologies.sgtapp.service.RightService;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jonathan
 */
@Repository(value="RightService")
public class RightServiceImpl implements RightService{

    @Autowired
    private RightDao rightDao;

    public RightServiceImpl() {
    }
    
    @Override
    public List<Rights> getRightsByRole(Integer idRole) {
        List<TRights> list = rightDao.getRightsByRole(idRole);
        List<Rights> results = new ArrayList<>();
        for(TRights r : list) {
            results.add(Util.parserRights(r));
        }
        return results;
    }

    @Override
    public Rights getRightActiveByRoleAndFacility(Integer idRole, Integer idFacilities) {
        TRights tRights = rightDao.getRightActiveByRoleAndFacility(idRole, idFacilities);
        return Util.parserRights(tRights);
    }

    @Override
    public void saveOrUpdate(Rights rights) {
        rightDao.saveOrUpdate(Util.parserTRights(rights));
    }
    
}
