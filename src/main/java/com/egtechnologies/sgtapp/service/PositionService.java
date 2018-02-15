/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service;

import com.egtechnologies.sgtapp.web.bean.Position;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface PositionService {
    
    Position getPositionById(Integer idPosition);
    Position getPositionByName(Position position);
    List<Position> getAllPositions();
    List<Position> getAllActivePositions();
    List<Position> search(Position position);
    void saveOrUpdate(Position position);
}
