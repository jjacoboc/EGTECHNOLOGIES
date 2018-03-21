/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.HardwareDao;
import com.egtechnologies.sgtapp.domain.THardware;
import com.egtechnologies.sgtapp.service.HardwareService;
import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="HardwareService")
public class HardwareServiceImpl implements HardwareService {

    @Autowired
    private HardwareDao hardwareDao;

    public HardwareServiceImpl() {
    }
    
    @Override
    public Hardware getHardwareById(Integer idHardware) {
        THardware thardware = hardwareDao.getHardwareById(idHardware);
        return Util.parserHardware(thardware);
    }

    @Override
    public Hardware getHardwareBySerialNumber(Hardware hardware) {
        THardware thardware = hardwareDao.getHardwareBySerialNumber(Util.parserTHardware(hardware));
        return Util.parserHardware(thardware);
    }

    @Override
    public Hardware getHardwareByCompany(Hardware hardware) {
        THardware thardware = hardwareDao.getHardwareByCompany(Util.parserTHardware(hardware));
        return Util.parserHardware(thardware);
    }

    @Override
    public List<Hardware> getAllHardwares() {
        List<THardware> list = hardwareDao.getAllHardwares();
        List<Hardware> result = new ArrayList<>();
        for(THardware item : list) {
            result.add(Util.parserHardware(item));
        }
        return result;
    }

    @Override
    public List<Hardware> getAllActiveHardwares() {
        List<THardware> list = hardwareDao.getAllActiveHardwares();
        List<Hardware> result = new ArrayList<>();
        for(THardware item : list) {
            result.add(Util.parserHardware(item));
        }
        return result;
    }

    @Override
    public List<Hardware> search(Hardware hardware) {
        List<THardware> list = hardwareDao.search(Util.parserTHardware(hardware));
        List<Hardware> result = new ArrayList<>();
        for(THardware item : list) {
            result.add(Util.parserHardware(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Hardware hardware) {
        hardwareDao.saveOrUpdate(Util.parserTHardware(hardware));
    }
    
}
