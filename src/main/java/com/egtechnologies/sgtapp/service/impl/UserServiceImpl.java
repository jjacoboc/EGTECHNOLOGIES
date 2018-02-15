/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.UserDao;
import com.egtechnologies.sgtapp.domain.TUser;
import com.egtechnologies.sgtapp.service.UserService;
import com.egtechnologies.sgtapp.web.bean.PersonUser;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value = "UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }
    
    
    @Override
    public User getUserById(Integer idUser) {
        TUser tuser = userDao.getUserById(idUser);
        return Util.parserUser(tuser);
    }
    
    @Override
    public User getActiveUserbyUsername(String username) {
        TUser tuser = userDao.getActiveUserbyUsername(username);
        return Util.parserUser(tuser);
    }
    
    @Override
    public List<User> getAllUsers() {
        List<TUser> list = userDao.getAllUsers();
        List<User> result = new ArrayList<>();
        for(TUser item : list) {
            result.add(Util.parserUser(item));
        }
        return result;
    }

    @Override
    public List<User> getAllActiveUsers() {
        List<TUser> list = userDao.getAllActiveUsers();
        List<User> result = new ArrayList<>();
        for(TUser item : list) {
            result.add(Util.parserUser(item));
        }
        return result;
    }

    @Override
    public List<PersonUser> getUsers() {
        List list = userDao.getUsers();
        List<PersonUser> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for(Object obj : list){
                Object[] objeto = (Object[]) obj;
                PersonUser personUser = new PersonUser();
                personUser.setIdPerson((Integer)objeto[0]);
                personUser.setIdnumber((String)objeto[1]);
                personUser.setName((String)objeto[2]);
                personUser.setLastname((String)objeto[3]);
                personUser.setHomeemail((String)objeto[4]);
                personUser.setPhone((String)objeto[5]);
                personUser.setCellphone((String)objeto[6]);
                personUser.setAddress((String)objeto[7]);
                personUser.setCity((String)objeto[8]);
                personUser.setCountry((String)objeto[9]);
                personUser.setZipcode((String)objeto[10]);
                personUser.setCreatedBy((Integer)objeto[11]);
                personUser.setCreatedDate((Date)objeto[12]);
                personUser.setModifiedBy((Integer)objeto[13]);
                personUser.setModifiedDate((Date)objeto[14]);
                personUser.setIdUser((Integer)objeto[15]);
                personUser.setIdRole((Integer)objeto[16]);
                personUser.setUsername((String)objeto[17]);
                personUser.setActive((Boolean)objeto[18]);
                personUser.setWorkemail((String)objeto[19]);
                result.add(personUser);
            }
        }
        return result;
    }
    
    @Override
    public List<PersonUser> search(PersonUser personUser) {
        List list = userDao.search(personUser);
        List<PersonUser> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for(Object obj : list){
                Object[] objeto = (Object[]) obj;
                PersonUser pu = new PersonUser();
                pu.setIdPerson((Integer)objeto[0]);
                pu.setIdnumber((String)objeto[1]);
                pu.setName((String)objeto[2]);
                pu.setLastname((String)objeto[3]);
                pu.setHomeemail((String)objeto[4]);
                pu.setPhone((String)objeto[5]);
                pu.setCellphone((String)objeto[6]);
                pu.setAddress((String)objeto[7]);
                pu.setCity((String)objeto[8]);
                pu.setCountry((String)objeto[9]);
                pu.setZipcode((String)objeto[10]);
                pu.setCreatedBy((Integer)objeto[11]);
                pu.setCreatedDate((Date)objeto[12]);
                pu.setModifiedBy((Integer)objeto[13]);
                pu.setModifiedDate((Date)objeto[14]);
                pu.setIdUser((Integer)objeto[15]);
                pu.setIdRole((Integer)objeto[16]);
                pu.setUsername((String)objeto[17]);
                pu.setActive((Boolean)objeto[18]);
                pu.setWorkemail((String)objeto[19]);
                result.add(pu);
            }
        }
        return result;
    }

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(Util.parserTUser(user));
    }

    
    
}
