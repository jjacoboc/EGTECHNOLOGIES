/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.HardwareDao;
import com.egtechnologies.sgtapp.domain.THardware;
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
@Repository(value="HardwareDao")
public class HardwareDaoHibernate extends HibernateDaoSupport implements HardwareDao {

    /**
     * Crea una nueva instancia de HardwareDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public HardwareDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public THardware getHardwareById(Integer idHardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idHardware", idHardware));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public THardware getHardwareBySerialNumber(THardware thardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        criteria.add(Restrictions.eq("serialNumber", thardware.getSerialNumber()));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public THardware getHardwareByCompany(THardware thardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<THardware> getAllHardwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    public List<THardware> getAllActiveHardwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    public List<THardware> search(THardware thardware) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        if(thardware.getIdCompany() != null && !thardware.getIdCompany().equals(Items.NULL_VALUE)) {
            criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        }
        if(thardware.getIdHardwareType() != null && !thardware.getIdHardwareType().equals(Items.NULL_VALUE)) {
            criteria.add(Restrictions.eq("idHardwareType", thardware.getIdHardwareType()));
        }
        if(StringUtils.isNotBlank(thardware.getModel())) {
            criteria.add(Restrictions.like("model", thardware.getModel(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(thardware.getBrand())) {
            criteria.add(Restrictions.like("brand", thardware.getBrand(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(THardware thardware) {
        getHibernateTemplate().saveOrUpdate(thardware);
    }
    
}
