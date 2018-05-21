/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.AssignDao;
import com.egtechnologies.sgtapp.domain.TAssignation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jonathan
 */
@Repository(value="AssignDao")
public class AssignDaoHibernate extends HibernateDaoSupport  implements AssignDao {

    /**
     * Crea una nueva instancia de AssignDaoHibernate
     *
     * @param sessionFactory
     */
    @Autowired
    public AssignDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteHardwaresByEmployee(Integer idEmployee) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TAssignation.class);
        criteria.add(Restrictions.eq("idEmployee", idEmployee));
        getHibernateTemplate().delete(criteria.uniqueResult());
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TAssignation tassignation) {
        getHibernateTemplate().saveOrUpdate(tassignation);
    }
}
