/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.NetworkDao;
import com.egtechnologies.sgtapp.domain.TNetwork;
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
@Repository(value="NetworkDao")
public class NetworkDaoHibernate extends HibernateDaoSupport implements NetworkDao {

    /**
     * Crea una nueva instancia de NetworkDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public NetworkDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TNetwork getNetworkById(Integer idNetwork) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TNetwork.class);
        criteria.add(Restrictions.eq("idNetwork", idNetwork));
        return (TNetwork) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TNetwork getNetworkByName(TNetwork tnetwork) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TNetwork.class);
        criteria.add(Restrictions.eq("idCompany", tnetwork.getIdCompany()));
        criteria.add(Restrictions.eq("name", tnetwork.getName()));
        return (TNetwork) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TNetwork> getAllNetworks() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TNetwork.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TNetwork>) criteria.list();
    }

    @Override
    public List<TNetwork> getAllActiveNetworks() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TNetwork.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TNetwork>) criteria.list();
    }

    @Override
    public List<TNetwork> search(TNetwork tnetwork) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TNetwork.class);
        if(tnetwork.getIdCompany() != null) {
            criteria.add(Restrictions.eq("idCompany", tnetwork.getIdCompany()));
        }
        if(StringUtils.isNotBlank(tnetwork.getName())) {
            criteria.add(Restrictions.like("name", tnetwork.getName(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TNetwork>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TNetwork tnetwork) {
        getHibernateTemplate().saveOrUpdate(tnetwork);
    }
    
}
