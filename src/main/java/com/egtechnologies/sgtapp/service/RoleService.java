/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.User;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface RoleService {
    
    Role getRoleById(Integer idRole);
    Role getRoleByUser(User user);
    Role getRoleByName(Role role);
    List<Role> getAllRoles();
    List<Role> getAllActiveRoles();
    List<Role> search(Role role);
    void saveOrUpdate(Role role);
}
