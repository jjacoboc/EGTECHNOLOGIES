/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TUser;
import com.egtechnologies.sgtapp.web.bean.PersonUser;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface UserDao {
    
    TUser getUserById(Integer idUser);
    TUser getActiveUserbyUsername(String username);
    List<TUser> getAllUsers();
    List<TUser> getAllActiveUsers();
    List getUsers();
    List search(PersonUser personUser);
    void saveOrUpdate(TUser user);
}
