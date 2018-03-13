/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.PositionDao;
import com.egtechnologies.sgtapp.domain.TPosition;
import com.egtechnologies.sgtapp.web.common.Items;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jonathan
 */
@Repository(value = "PositionDao")
public class PositionDaoHibernate extends HibernateDaoSupport implements PositionDao {

    /**
     * Crea una nueva instancia de PositionDaoHibernate
     *
     * @param sessionFactory
     */
    @Autowired
    public PositionDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public TPosition getPositionById(Integer idPosition) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPosition.class);
        criteria.add(Restrictions.eq("idPosition", idPosition));
        return (TPosition) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TPosition getPositionByName(TPosition tposition) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPosition.class);
        criteria.add(Restrictions.eq("idCompany", tposition.getIdCompany()));
        criteria.add(Restrictions.eq("name", tposition.getName()));
        return (TPosition) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TPosition> getAllPositions() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPosition.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TPosition>) criteria.list();
    }

    @Override
    public List<TPosition> getAllActivePositions() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPosition.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TPosition>) criteria.list();
    }
    
    @Override
    public List<TPosition> getAllPositionsByCompany(Integer idCompany) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPosition.class);
        criteria.add(Restrictions.eq("idCompany", idCompany));
        criteria.addOrder(Order.asc("name"));
        return (List<TPosition>) criteria.list();
    }
    
    @Override
    public List<TPosition> getAllActivePositionsByCompany(Integer idCompany) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPosition.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.add(Restrictions.eq("idCompany", idCompany));
        criteria.addOrder(Order.asc("name"));
        return (List<TPosition>) criteria.list();
    }

    @Override
    public List<TPosition> search(TPosition tposition) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPosition.class);
        if (tposition.getIdCompany() != null && !tposition.getIdCompany().equals(Items.NULL_VALUE)) {
            criteria.add(Restrictions.eq("idCompany", tposition.getIdCompany()));
        }
        if (StringUtils.isNotBlank(tposition.getName())) {
            criteria.add(Restrictions.like("name", tposition.getName(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TPosition>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TPosition tposition) {
        getHibernateTemplate().saveOrUpdate(tposition);
    }

}
