/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TBranchOffice;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface BranchOfficeDao {
    
    TBranchOffice getBranchOfficeById(Integer idBranchOffice);
    TBranchOffice getBranchOfficeByName(TBranchOffice tbranchOffice);
    List<TBranchOffice> getAllBranchOffices();
    List<TBranchOffice> getAllActiveBranchOffices();
    List<TBranchOffice> getAllBranchOfficesByCompany(Integer idCompany);
    List<TBranchOffice> getAllActiveBranchOfficesByCompany(Integer idCompany);
    List<TBranchOffice> search(TBranchOffice tbranchOffice);
    void saveOrUpdate(TBranchOffice tbranchOffice);
}
