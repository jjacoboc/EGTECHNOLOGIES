/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TEmployee;
import com.egtechnologies.sgtapp.domain.THardware;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface HardwareDao {
    
    THardware getHardwareById(Integer idHardware);
    THardware getHardwareBySerialNumber(THardware thardware);
    THardware getHardwareByCompany(THardware thardware);
    List<THardware> getAllHardwares();
    List<THardware> getAllActiveHardwares();
    List<THardware> search(THardware thardware);
    List<THardware> getHardwaresNotAssigned(TEmployee temployee);
    List<THardware> getHardwaresAssigned(TEmployee temployee);
    void saveOrUpdate(THardware thardware);
}
