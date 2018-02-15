/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.User;

/**
 *
 * @author Jonathan
 */

public interface PasswordService {
    
    Password getActivePasswordbyUser(User usuario);
    void saveOrUpdate(Password password);
}
