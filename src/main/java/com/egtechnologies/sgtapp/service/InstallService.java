/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.bean.Installation;
import com.egtechnologies.sgtapp.web.bean.Software;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface InstallService {
    
    void deleteInstallsByHardware(Integer idHardware);
    void deleteInstallsBySoftware(Integer idSoftware);
    List<Software> getSoftwaresNotInstalled(Hardware hardware);
    List<Software> getSoftwaresInstalled(Hardware hardware);
    void saveOrUpdate(Installation installation);
}
