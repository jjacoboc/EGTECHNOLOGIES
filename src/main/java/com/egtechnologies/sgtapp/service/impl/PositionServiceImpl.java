/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.service.impl;

import com.egtechnologies.sgtapp.dao.PositionDao;
import com.egtechnologies.sgtapp.domain.TPosition;
import com.egtechnologies.sgtapp.service.PositionService;
import com.egtechnologies.sgtapp.web.bean.Position;
import com.egtechnologies.sgtapp.web.common.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan
 */
@Repository(value="PositionService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    public PositionServiceImpl() {
    }
    
    @Override
    public Position getPositionById(Integer idPosition) {
        TPosition tposition = positionDao.getPositionById(idPosition);
        return Util.parserPosition(tposition);
    }

    @Override
    public Position getPositionByName(Position position) {
        TPosition tposition = positionDao.getPositionByName(Util.parserTPosition(position));
        return Util.parserPosition(tposition);
    }

    @Override
    public List<Position> getAllPositions() {
        List<TPosition> list = positionDao.getAllPositions();
        List<Position> result = new ArrayList<>();
        for(TPosition item : list) {
            result.add(Util.parserPosition(item));
        }
        return result;
    }

    @Override
    public List<Position> getAllActivePositions() {
        List<TPosition> list = positionDao.getAllActivePositions();
        List<Position> result = new ArrayList<>();
        for(TPosition item : list) {
            result.add(Util.parserPosition(item));
        }
        return result;
    }

    @Override
    public List<Position> search(Position position) {
        List<TPosition> list = positionDao.search(Util.parserTPosition(position));
        List<Position> result = new ArrayList<>();
        for(TPosition item : list) {
            result.add(Util.parserPosition(item));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Position position) {
        positionDao.saveOrUpdate(Util.parserTPosition(position));
    }
    
}
