/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.NetworkDao;
import com.egtechnologies.sgtapp.domain.TNetwork;
import com.egtechnologies.sgtapp.service.NetworkService;
import com.egtechnologies.sgtapp.web.bean.Network;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="NetworkService")
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkDao networkDao;

    public NetworkServiceImpl() {
    }
    
    @Override
    public Network getNetworkById(Integer idNetwork) {
        TNetwork tnetwork = networkDao.getNetworkById(idNetwork);
        return Util.parserNetwork(tnetwork);
    }

    @Override
    public Network getNetworkByName(Network network) {
        TNetwork tnetwork = networkDao.getNetworkByName(Util.parserTNetwork(network));
        return Util.parserNetwork(tnetwork);
    }

    @Override
    public List<Network> getAllNetworks() {
        List<TNetwork> list = networkDao.getAllNetworks();
        List<Network> result = new ArrayList<>();
        for(TNetwork item : list) {
            result.add(Util.parserNetwork(item));
        }
        return result;
    }

    @Override
    public List<Network> getAllActiveNetworks() {
        List<TNetwork> list = networkDao.getAllActiveNetworks();
        List<Network> result = new ArrayList<>();
        for(TNetwork item : list) {
            result.add(Util.parserNetwork(item));
        }
        return result;
    }

    @Override
    public List<Network> search(Network network) {
        List<TNetwork> list = networkDao.search(Util.parserTNetwork(network));
        List<Network> result = new ArrayList<>();
        for(TNetwork item : list) {
            result.add(Util.parserNetwork(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Network network) {
        networkDao.saveOrUpdate(Util.parserTNetwork(network));
    }
    
}
