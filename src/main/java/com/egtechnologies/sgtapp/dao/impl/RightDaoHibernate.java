/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.RightDao;
import com.egtechnologies.sgtapp.domain.TRights;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
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
@Repository(value="RightDao")
public class RightDaoHibernate extends HibernateDaoSupport implements RightDao {
    
    /**
     * Crea una nueva instancia de RightDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public RightDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<TRights> getRightsByRole(Integer idRole) {
        Session session = this.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TRights.class);
        criteria.add(Restrictions.eq("idRole", idRole));
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        return (List<TRights>) criteria.list();
    }
    
    @Override
    public TRights getRightActiveByRoleAndFacility(Integer idRole, Integer idFacilities) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TRights.class);
        criteria.add(Restrictions.eq("idRole", idRole));
        criteria.add(Restrictions.eq("idFacilities", idFacilities));
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        return (TRights) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TRights trights) {
        getHibernateTemplate().saveOrUpdate(trights);
    }
}
