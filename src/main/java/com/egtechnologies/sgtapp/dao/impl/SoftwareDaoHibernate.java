/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.SoftwareDao;
import com.egtechnologies.sgtapp.domain.TSoftware;
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
@Repository(value="SoftwareDao")
public class SoftwareDaoHibernate extends HibernateDaoSupport implements SoftwareDao {

    /**
     * Crea una nueva instancia de SoftwareDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public SoftwareDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TSoftware getSoftwareById(Integer idSoftware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TSoftware.class);
        criteria.add(Restrictions.eq("idSoftware", idSoftware));
        return (TSoftware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TSoftware getSoftwareByName(TSoftware tsoftware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TSoftware.class);
        criteria.add(Restrictions.eq("idCompany", tsoftware.getIdCompany()));
        criteria.add(Restrictions.eq("name", tsoftware.getName()));
        return (TSoftware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TSoftware getSoftwareByBrand(TSoftware tsoftware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TSoftware.class);
        criteria.add(Restrictions.eq("idCompany", tsoftware.getIdCompany()));
        criteria.add(Restrictions.eq("brand", tsoftware.getBrand()));
        return (TSoftware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TSoftware> getAllSoftwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TSoftware.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TSoftware>) criteria.list();
    }

    @Override
    public List<TSoftware> getAllActiveSoftwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TSoftware.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TSoftware>) criteria.list();
    }

    @Override
    public List<TSoftware> search(TSoftware tsoftware) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TSoftware.class);
        if(tsoftware.getIdCompany() != null) {
            criteria.add(Restrictions.eq("idCompany", tsoftware.getIdCompany()));
        }
        if(StringUtils.isNotBlank(tsoftware.getName())) {
            criteria.add(Restrictions.like("name", tsoftware.getName(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tsoftware.getBrand())) {
            criteria.add(Restrictions.like("brand", tsoftware.getBrand(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TSoftware>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TSoftware tsoftware) {
        getHibernateTemplate().saveOrUpdate(tsoftware);
    }
    
}
