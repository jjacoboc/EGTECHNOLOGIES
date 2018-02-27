/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Software;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface SoftwareService {
    
    Software getSoftwareById(Integer idSoftware);
    Software getSoftwareByName(Software software);
    Software getSoftwareByBrand(Software software);
    List<Software> getAllSoftwares();
    List<Software> getAllActiveSoftwares();
    List<Software> search(Software software);
    void saveOrUpdate(Software software);
}
