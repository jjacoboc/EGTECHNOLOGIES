/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.InstallDao;
import com.egtechnologies.sgtapp.domain.TSoftware;
import com.egtechnologies.sgtapp.service.InstallService;
import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.bean.Installation;
import com.egtechnologies.sgtapp.web.bean.Software;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="InstallService")
public class InstallServiceImpl implements InstallService {
    
    @Autowired
    private InstallDao installDao;

    public InstallServiceImpl() {
    }
    
    @Override
    public void deleteInstallsByHardware(Integer idHardware) {
        installDao.deleteInstallsByHardware(idHardware);
    }
    
    @Override
    public void deleteInstallsBySoftware(Integer idSoftware) {
        installDao.deleteInstallsBySoftware(idSoftware);
    }
    
    @Override
    public List<Software> getSoftwaresNotInstalled(Hardware hardware) {
        List<TSoftware> list = installDao.getSoftwaresNotInstalled(Util.parserTHardware(hardware));
        List<Software> result = new ArrayList<>();
        for(TSoftware item : list) {
            result.add(Util.parserSoftware(item));
        }
        return result;
    }
    
    @Override
    public List<Software> getSoftwaresInstalled(Hardware hardware) {
        List<TSoftware> list = installDao.getSoftwaresInstalled(Util.parserTHardware(hardware));
        List<Software> result = new ArrayList<>();
        for(TSoftware item : list) {
            result.add(Util.parserSoftware(item));
        }
        return result;
    }
    
    @Override
    public void saveOrUpdate(Installation installation) {
        installDao.saveOrUpdate(Util.parserTInstallation(installation));
    }
}
