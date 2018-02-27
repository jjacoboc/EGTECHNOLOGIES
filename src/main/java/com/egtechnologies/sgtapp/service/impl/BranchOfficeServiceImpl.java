/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.BranchOfficeDao;
import com.egtechnologies.sgtapp.domain.TBranchOffice;
import com.egtechnologies.sgtapp.service.BranchOfficeService;
import com.egtechnologies.sgtapp.web.bean.BranchOffice;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="BranchOfficeService")
public class BranchOfficeServiceImpl implements BranchOfficeService {

    @Autowired
    private BranchOfficeDao branchOfficeDao;

    public BranchOfficeServiceImpl() {
    }
    
    @Override
    public BranchOffice getBranchOfficeById(Integer idBranchOffice) {
        TBranchOffice tbranchOffice = branchOfficeDao.getBranchOfficeById(idBranchOffice);
        return Util.parserBranchOffice(tbranchOffice);
    }

    @Override
    public BranchOffice getBranchOfficeByName(BranchOffice branchOffice) {
        TBranchOffice tbranchOffice = branchOfficeDao.getBranchOfficeByName(Util.parserTBranchOffice(branchOffice));
        return Util.parserBranchOffice(tbranchOffice);
    }

    @Override
    public List<BranchOffice> getAllBranchOffices() {
        List<TBranchOffice> list = branchOfficeDao.getAllBranchOffices();
        List<BranchOffice> result = new ArrayList<>();
        for(TBranchOffice item : list) {
            result.add(Util.parserBranchOffice(item));
        }
        return result;
    }

    @Override
    public List<BranchOffice> getAllActiveBranchOffices() {
        List<TBranchOffice> list = branchOfficeDao.getAllActiveBranchOffices();
        List<BranchOffice> result = new ArrayList<>();
        for(TBranchOffice item : list) {
            result.add(Util.parserBranchOffice(item));
        }
        return result;
    }
    
    @Override
    public List<BranchOffice> getAllBranchOfficesByCompany(Integer idCompany) {
        List<TBranchOffice> list = branchOfficeDao.getAllBranchOfficesByCompany(idCompany);
        List<BranchOffice> result = new ArrayList<>();
        for(TBranchOffice item : list) {
            result.add(Util.parserBranchOffice(item));
        }
        return result;
    }
    
    @Override
    public List<BranchOffice> getAllActiveBranchOfficesByCompany(Integer idCompany) {
        List<TBranchOffice> list = branchOfficeDao.getAllActiveBranchOfficesByCompany(idCompany);
        List<BranchOffice> result = new ArrayList<>();
        for(TBranchOffice item : list) {
            result.add(Util.parserBranchOffice(item));
        }
        return result;
    }

    @Override
    public List<BranchOffice> search(BranchOffice branchOffice) {
        List<TBranchOffice> list = branchOfficeDao.search(Util.parserTBranchOffice(branchOffice));
        List<BranchOffice> result = new ArrayList<>();
        for(TBranchOffice item : list) {
            result.add(Util.parserBranchOffice(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(BranchOffice branchOffice) {
        branchOfficeDao.saveOrUpdate(Util.parserTBranchOffice(branchOffice));
    }
    
}
