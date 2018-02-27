/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TSoftware;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface SoftwareDao {
    
    TSoftware getSoftwareById(Integer idSoftware);
    TSoftware getSoftwareByName(TSoftware tsoftware);
    TSoftware getSoftwareByBrand(TSoftware tsoftware);
    List<TSoftware> getAllSoftwares();
    List<TSoftware> getAllActiveSoftwares();
    List<TSoftware> search(TSoftware tsoftware);
    void saveOrUpdate(TSoftware tsoftware);
}
