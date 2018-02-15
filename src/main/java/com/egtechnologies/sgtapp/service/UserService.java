/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.PersonUser;
import com.egtechnologies.sgtapp.web.bean.User;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface UserService {
    
    User getUserById(Integer idUser);
    User getActiveUserbyUsername(String username);
    List<User> getAllUsers();
    List<User> getAllActiveUsers();
    List<PersonUser> getUsers();
    List<PersonUser> search(PersonUser personUser);
    void saveOrUpdate(User user);
}
