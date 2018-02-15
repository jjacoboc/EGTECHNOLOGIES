/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TPerson;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface PersonDao {
    
    TPerson getPersonById(Integer idPerson);
    TPerson getPersonByIdnumber(String idnumber);
    TPerson getPersonByEmail(String email);
    List<TPerson> getAllPersons();
    List<TPerson> getAllActivePersons();
    List<TPerson> search(TPerson tperson);
    List<TPerson> getPersonsWithoutUser();
    List<TPerson> getPersonsWithUser();
    void saveOrUpdate(TPerson tperson);
}
