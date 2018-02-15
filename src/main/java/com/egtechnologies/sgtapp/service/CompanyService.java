/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Company;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface CompanyService {
    
    Company getCompanyById(Integer idCompany);
    Company getCompanyByName(Company company);
    List<Company> getAllCompanies();
    List<Company> getAllActiveCompanies();
    List<Company> search(Company company);
    void saveOrUpdate(Company company);
}
