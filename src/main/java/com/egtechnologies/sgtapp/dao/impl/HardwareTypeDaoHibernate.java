/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.HardwareTypeDao;
import com.egtechnologies.sgtapp.domain.THardwareType;
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
@Repository(value="HardwareTypeDao")
public class HardwareTypeDaoHibernate extends HibernateDaoSupport implements HardwareTypeDao {
    
    /**
     * Crea una nueva instancia de HardwareTypeDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public HardwareTypeDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public THardwareType getHardwareTypeById(Integer idHardwareType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardwareType.class);
        criteria.add(Restrictions.eq("idHardwareType", idHardwareType));
        return (THardwareType) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public THardwareType getHardwareTypeByName(THardwareType thardwareType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardwareType.class);
        criteria.add(Restrictions.eq("name", thardwareType.getName()));
        return (THardwareType) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<THardwareType> getAllHardwareTypes() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardwareType.class);
        criteria.addOrder(Order.asc("name"));
        return (List<THardwareType>) criteria.list();
    }

    @Override
    public List<THardwareType> getAllActiveHardwareTypes() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardwareType.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("name"));
        return (List<THardwareType>) criteria.list();
    }

    @Override
    public List<THardwareType> search(THardwareType thardwareType) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardwareType.class);
        if(StringUtils.isNotBlank(thardwareType.getName())) {
            criteria.add(Restrictions.like("name", thardwareType.getName(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        return (List<THardwareType>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(THardwareType thardwareType) {
        getHibernateTemplate().saveOrUpdate(thardwareType);
    }
}
