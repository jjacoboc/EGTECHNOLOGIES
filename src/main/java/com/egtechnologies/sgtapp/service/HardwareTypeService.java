/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.HardwareType;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface HardwareTypeService {
    
    HardwareType getHardwareTypeById(Integer idHardwareType);
    HardwareType getHardwareTypeByName(HardwareType type);
    List<HardwareType> getAllHardwareTypes();
    List<HardwareType> getAllActiveHardwareTypes();
    List<HardwareType> search(HardwareType type);
    void saveOrUpdate(HardwareType type);
}
