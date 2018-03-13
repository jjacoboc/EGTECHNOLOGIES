/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao;

import com.egtechnologies.sgtapp.domain.TPosition;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface PositionDao {
    
    TPosition getPositionById(Integer idPosition);
    TPosition getPositionByName(TPosition tposition);
    List<TPosition> getAllPositions();
    List<TPosition> getAllActivePositions();
    List<TPosition> getAllPositionsByCompany(Integer idCompany);
    List<TPosition> getAllActivePositionsByCompany(Integer idCompany);
    List<TPosition> search(TPosition tposition);
    void saveOrUpdate(TPosition tposition);
}
