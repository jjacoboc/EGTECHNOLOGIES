/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.HardwareTypeDao;
import com.egtechnologies.sgtapp.domain.THardwareType;
import com.egtechnologies.sgtapp.service.HardwareTypeService;
import com.egtechnologies.sgtapp.web.bean.HardwareType;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value = "HardwareTypeService")
public class HardwareTypeServiceImpl implements HardwareTypeService {

    @Autowired
    private HardwareTypeDao hardwareTypeDao;

    public HardwareTypeServiceImpl() {
    }
    
    @Override
    public HardwareType getHardwareTypeById(Integer idHardwareType) {
        THardwareType thardwareType = hardwareTypeDao.getHardwareTypeById(idHardwareType);
        return Util.parserHardwareType(thardwareType);
    }

    @Override
    public HardwareType getHardwareTypeByName(HardwareType type) {
        THardwareType thardwareType = hardwareTypeDao.getHardwareTypeByName(Util.parserTHardwareType(type));
        return Util.parserHardwareType(thardwareType);
    }

    @Override
    public List<HardwareType> getAllHardwareTypes() {
        List<THardwareType> list = hardwareTypeDao.getAllHardwareTypes();
        List<HardwareType> result = new ArrayList<>();
        for(THardwareType item : list) {
            result.add(Util.parserHardwareType(item));
        }
        return result;
    }

    @Override
    public List<HardwareType> getAllActiveHardwareTypes() {
        List<THardwareType> list = hardwareTypeDao.getAllActiveHardwareTypes();
        List<HardwareType> result = new ArrayList<>();
        for(THardwareType item : list) {
            result.add(Util.parserHardwareType(item));
        }
        return result;
    }

    @Override
    public List<HardwareType> search(HardwareType type) {
        List<THardwareType> list = hardwareTypeDao.search(Util.parserTHardwareType(type));
        List<HardwareType> result = new ArrayList<>();
        for(THardwareType item : list) {
            result.add(Util.parserHardwareType(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(HardwareType type) {
        hardwareTypeDao.saveOrUpdate(Util.parserTHardwareType(type));
    }
    
}
