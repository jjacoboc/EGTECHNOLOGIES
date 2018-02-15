/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TCompany;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface CompanyDao {
    
    TCompany getCompanyById(Integer idCompany);
    TCompany getCompanyByName(TCompany tcompany);
    List<TCompany> getAllCompanies();
    List<TCompany> getAllActiveCompanies();
    List<TCompany> search(TCompany tcompany);
    void saveOrUpdate(TCompany tcompany);
}
