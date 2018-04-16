/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.THardware;
import com.egtechnologies.sgtapp.domain.TInstallation;
import com.egtechnologies.sgtapp.domain.TSoftware;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface InstallDao {
    
    void deleteInstallsByHardware(Integer idHardware);
    void deleteInstallsBySoftware(Integer idSoftware);
    List<TSoftware> getSoftwaresNotInstalled(THardware thardware);
    List<TSoftware> getSoftwaresInstalled(THardware thardware);
    void saveOrUpdate(TInstallation tinstallation);
}
