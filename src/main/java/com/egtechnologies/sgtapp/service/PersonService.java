/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Person;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface PersonService {
    
    Person getPersonById(Integer idPerson);
    Person getPersonByIdnumber(String idnumber);
    Person getPersonByEmail(String email);
    List<Person> getAllPersons();
    List<Person> getAllActivePersons();
    List<Person> search(Person person);
    List<Person> getPersonsWithoutUser();
    List<Person> getPersonsWithUser();
    void saveOrUpdate(Person person);
}
