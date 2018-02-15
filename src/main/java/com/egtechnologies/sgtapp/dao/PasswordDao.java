/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TPassword;
import com.egtechnologies.sgtapp.domain.TUser;

/**
 *
 * @author Jonathan
 */
public interface PasswordDao {
    
    TPassword getActivePasswordbyUser(TUser usuario);
    void saveOrUpdate(TPassword password);
}
