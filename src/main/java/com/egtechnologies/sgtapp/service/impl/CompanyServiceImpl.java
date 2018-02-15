/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.CompanyDao;
import com.egtechnologies.sgtapp.domain.TCompany;
import com.egtechnologies.sgtapp.service.CompanyService;
import com.egtechnologies.sgtapp.web.bean.Company;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value = "CompanyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public CompanyServiceImpl() {
    }
    
    @Override
    public Company getCompanyById(Integer idCompany) {
        TCompany tcompany = companyDao.getCompanyById(idCompany);
        return Util.parserCompany(tcompany);
    }

    @Override
    public Company getCompanyByName(Company company) {
        TCompany tcompany = companyDao.getCompanyByName(Util.parserTCompany(company));
        return Util.parserCompany(tcompany);
    }

    @Override
    public List<Company> getAllCompanies() {
        List<TCompany> list = companyDao.getAllCompanies();
        List<Company> result = new ArrayList<>();
        for(TCompany item : list) {
            result.add(Util.parserCompany(item));
        }
        return result;
    }

    @Override
    public List<Company> getAllActiveCompanies() {
        List<TCompany> list = companyDao.getAllActiveCompanies();
        List<Company> result = new ArrayList<>();
        for(TCompany item : list) {
            result.add(Util.parserCompany(item));
        }
        return result;
    }

    @Override
    public List<Company> search(Company company) {
        List<TCompany> list = companyDao.search(Util.parserTCompany(company));
        List<Company> result = new ArrayList<>();
        for(TCompany item : list) {
            result.add(Util.parserCompany(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Company company) {
        companyDao.saveOrUpdate(Util.parserTCompany(company));
    }
    
}
