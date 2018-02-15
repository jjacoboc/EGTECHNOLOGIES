/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.PasswordDao;
import com.egtechnologies.sgtapp.domain.TPassword;
import com.egtechnologies.sgtapp.service.PasswordService;
import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value = "PasswordService")
public class PasswordServiceImpl implements PasswordService{

    @Autowired
    private PasswordDao passwordDao;

    public PasswordServiceImpl() {
    }
    
    @Override
    public Password getActivePasswordbyUser(User usuario) {
        TPassword tpassword = passwordDao.getActivePasswordbyUser(Util.parserTUser(usuario));
        return Util.parserPassword(tpassword);
    }

    @Override
    public void saveOrUpdate(Password password) {
        passwordDao.saveOrUpdate(Util.parserTPassword(password));
    }
    
    
}
