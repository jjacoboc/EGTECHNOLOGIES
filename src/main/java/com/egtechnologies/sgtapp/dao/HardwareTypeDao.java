/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.THardwareType;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface HardwareTypeDao {
    
    THardwareType getHardwareTypeById(Integer idHardwareType);
    THardwareType getHardwareTypeByName(THardwareType thardwareType);
    List<THardwareType> getAllHardwareTypes();
    List<THardwareType> getAllActiveHardwareTypes();
    List<THardwareType> search(THardwareType thardwareType);
    void saveOrUpdate(THardwareType thardwareType);
}
