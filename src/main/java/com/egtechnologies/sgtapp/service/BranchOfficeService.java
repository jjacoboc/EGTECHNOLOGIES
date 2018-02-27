/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.BranchOffice;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface BranchOfficeService {
    
    BranchOffice getBranchOfficeById(Integer idBranchOffice);
    BranchOffice getBranchOfficeByName(BranchOffice branchOffice);
    List<BranchOffice> getAllBranchOffices();
    List<BranchOffice> getAllActiveBranchOffices();
    List<BranchOffice> getAllBranchOfficesByCompany(Integer idCompany);
    List<BranchOffice> getAllActiveBranchOfficesByCompany(Integer idCompany);
    List<BranchOffice> search(BranchOffice branchOffice);
    void saveOrUpdate(BranchOffice branchOffice);
}
