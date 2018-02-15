/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TRole;
import com.egtechnologies.sgtapp.domain.TUser;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface RoleDao {
    
    TRole getRoleById(Integer idRole);
    TRole getRoleByUser(TUser user);
    TRole getRoleByName(TRole trole);
    List<TRole> getAllRoles();
    List<TRole> getAllActiveRoles();
    List<TRole> search(TRole trole);
    void saveOrUpdate(TRole trole);
}
