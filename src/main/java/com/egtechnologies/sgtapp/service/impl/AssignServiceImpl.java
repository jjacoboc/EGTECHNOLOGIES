/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.AssignDao;
import com.egtechnologies.sgtapp.service.AssignService;
import com.egtechnologies.sgtapp.web.bean.Assignation;
import com.egtechnologies.sgtapp.web.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="AssignService")
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignDao assignDao;

    public AssignServiceImpl() {
    }
    
    @Override
    public void deleteHardwaresByEmployee(Integer idEmployee) {
        assignDao.deleteHardwaresByEmployee(idEmployee);
    }
    
    public void saveOrUpdate(Assignation assignation) {
        assignDao.saveOrUpdate(Util.parserTAssignation(assignation));
    }
}
