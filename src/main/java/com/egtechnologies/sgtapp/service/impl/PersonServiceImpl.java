/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.PersonDao;
import com.egtechnologies.sgtapp.domain.TPerson;
import com.egtechnologies.sgtapp.service.PersonService;
import com.egtechnologies.sgtapp.web.bean.Person;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="PersonService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;
    
    public PersonServiceImpl() {
    }

    @Override
    public Person getPersonById(Integer idPerson) {
        TPerson tperson = personDao.getPersonById(idPerson);
        return Util.parserPerson(tperson);
    }
    
    @Override
    public Person getPersonByIdnumber(String idnumber) {
        TPerson tperson = personDao.getPersonByIdnumber(idnumber);
        return Util.parserPerson(tperson);
    }
    
    @Override
    public Person getPersonByEmail(String email) {
        TPerson tperson = personDao.getPersonByEmail(email);
        return Util.parserPerson(tperson);
    }
    
    @Override
    public List<Person> getAllPersons() {
        List<TPerson> list = personDao.getAllPersons();
        List<Person> result = new ArrayList<>();
        for(TPerson tperson : list) {
            result.add(Util.parserPerson(tperson));
        }
        return result;
    }
    
    @Override
    public List<Person> getAllActivePersons() {
        List<TPerson> list = personDao.getAllActivePersons();
        List<Person> result = new ArrayList<>();
        for(TPerson tperson : list) {
            result.add(Util.parserPerson(tperson));
        }
        return result;
    }
    
    @Override
    public List<Person> search(Person person) {
        List<TPerson> list = personDao.search(Util.parserTPerson(person));
        List<Person> result = new ArrayList<>();
        for(TPerson tperson : list) {
            result.add(Util.parserPerson(tperson));
        }
        return result;
    }

    @Override
    public List<Person> getPersonsWithoutUser() {
        List<TPerson> list = personDao.getPersonsWithoutUser();
        List<Person> result = new ArrayList<>();
        for(TPerson tperson : list) {
            result.add(Util.parserPerson(tperson));
        }
        return result;
    }
    
    @Override
    public List<Person> getPersonsWithUser() {
        List<TPerson> list = personDao.getPersonsWithUser();
        List<Person> result = new ArrayList<>();
        for(TPerson tperson : list) {
            result.add(Util.parserPerson(tperson));
        }
        return result;
    }
    
    @Override
    public void saveOrUpdate(Person person) {
        personDao.saveOrUpdate(Util.parserTPerson(person));
    }
}
