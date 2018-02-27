/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.SoftwareDao;
import com.egtechnologies.sgtapp.domain.TSoftware;
import com.egtechnologies.sgtapp.service.SoftwareService;
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
@Repository(value="SoftwareService")
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareDao softwareDao;

    public SoftwareServiceImpl() {
    }
    
    @Override
    public Software getSoftwareById(Integer idSoftware) {
        TSoftware tsoftware = softwareDao.getSoftwareById(idSoftware);
        return Util.parserSoftware(tsoftware);
    }

    @Override
    public Software getSoftwareByName(Software software) {
        TSoftware tsoftware = softwareDao.getSoftwareByName(Util.parserTSoftware(software));
        return Util.parserSoftware(tsoftware);
    }

    @Override
    public Software getSoftwareByBrand(Software software) {
        TSoftware tsoftware = softwareDao.getSoftwareByBrand(Util.parserTSoftware(software));
        return Util.parserSoftware(tsoftware);
    }

    @Override
    public List<Software> getAllSoftwares() {
        List<TSoftware> list = softwareDao.getAllSoftwares();
        List<Software> result = new ArrayList<>();
        for(TSoftware item : list) {
            result.add(Util.parserSoftware(item));
        }
        return result;
    }

    @Override
    public List<Software> getAllActiveSoftwares() {
        List<TSoftware> list = softwareDao.getAllActiveSoftwares();
        List<Software> result = new ArrayList<>();
        for(TSoftware item : list) {
            result.add(Util.parserSoftware(item));
        }
        return result;
    }

    @Override
    public List<Software> search(Software software) {
        List<TSoftware> list = softwareDao.search(Util.parserTSoftware(software));
        List<Software> result = new ArrayList<>();
        for(TSoftware item : list) {
            result.add(Util.parserSoftware(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Software software) {
        softwareDao.saveOrUpdate(Util.parserTSoftware(software));
    }
    
}
