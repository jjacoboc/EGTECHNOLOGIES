/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Network;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface NetworkService {
    
    Network getNetworkById(Integer idNetwork);
    Network getNetworkByName(Network network);
    List<Network> getAllNetworks();
    List<Network> getAllActiveNetworks();
    List<Network> search(Network network);
    void saveOrUpdate(Network network);
}
