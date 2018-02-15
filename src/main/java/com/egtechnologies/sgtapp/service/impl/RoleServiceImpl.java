/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.RoleDao;
import com.egtechnologies.sgtapp.domain.TRole;
import com.egtechnologies.sgtapp.service.RoleService;
import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value = "RoleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    public RoleServiceImpl() {
    }
    
    @Override
    public Role getRoleById(Integer idRole) {
        TRole trole = roleDao.getRoleById(idRole);
        return Util.parserRole(trole);
    }
    
    @Override
    public Role getRoleByUser(User user) {
        TRole trole = roleDao.getRoleByUser(Util.parserTUser(user));
        return Util.parserRole(trole);
    }
    
    @Override
    public Role getRoleByName(Role role) {
        TRole trole = roleDao.getRoleByName(Util.parserTRole(role));
        return Util.parserRole(trole);
    }

    @Override
    public List<Role> getAllRoles() {
        List<TRole> list = roleDao.getAllRoles();
        List<Role> result = new ArrayList<>();
        for(TRole item : list) {
            result.add(Util.parserRole(item));
        }
        return result;
    }

    @Override
    public List<Role> getAllActiveRoles() {
        List<TRole> list = roleDao.getAllActiveRoles();
        List<Role> result = new ArrayList<>();
        for(TRole item : list) {
            result.add(Util.parserRole(item));
        }
        return result;
    }

    @Override
    public List<Role> search(Role role) {
        List<TRole> list = roleDao.search(Util.parserTRole(role));
        List<Role> result = new ArrayList<>();
        for(TRole item : list) {
            result.add(Util.parserRole(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Role role) {
        roleDao.saveOrUpdate(Util.parserTRole(role));
    }
    
}
