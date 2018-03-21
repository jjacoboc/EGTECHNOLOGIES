/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Hardware;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface HardwareService {
    
    Hardware getHardwareById(Integer idHardware);
    Hardware getHardwareBySerialNumber(Hardware hardware);
    Hardware getHardwareByCompany(Hardware hardware);
    List<Hardware> getAllHardwares();
    List<Hardware> getAllActiveHardwares();
    List<Hardware> search(Hardware hardware);
    void saveOrUpdate(Hardware hardware);
}
