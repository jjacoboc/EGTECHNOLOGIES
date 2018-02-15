/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TNetwork;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface NetworkDao {
    
    TNetwork getNetworkById(Integer idNetwork);
    TNetwork getNetworkByName(TNetwork tnetwork);
    List<TNetwork> getAllNetworks();
    List<TNetwork> getAllActiveNetworks();
    List<TNetwork> search(TNetwork tnetwork);
    void saveOrUpdate(TNetwork tnetwork);
}
