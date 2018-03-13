/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.CompanyDao;
import com.egtechnologies.sgtapp.domain.TCompany;
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
@Repository(value = "CompanyDao")
public class CompanyDaoHibernate extends HibernateDaoSupport implements CompanyDao {

    /**
     * Crea una nueva instancia de CompanyDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CompanyDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TCompany getCompanyById(Integer idCompany) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TCompany.class);
        criteria.add(Restrictions.eq("idCompany", idCompany));
        return (TCompany) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TCompany getCompanyByName(TCompany tcompany) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TCompany.class);
        criteria.add(Restrictions.eq("name", tcompany.getName()));
        return (TCompany) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TCompany> getAllCompanies() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TCompany.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.asc("name"));
        return (List<TCompany>) criteria.list();
    }

    @Override
    public List<TCompany> getAllActiveCompanies() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TCompany.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("name"));
        return (List<TCompany>) criteria.list();
    }

    @Override
    public List<TCompany> search(TCompany tcompany) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TCompany.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if(StringUtils.isNotBlank(tcompany.getName())) {
            criteria.add(Restrictions.like("name", tcompany.getName(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tcompany.getCity())) {
            criteria.add(Restrictions.like("city", tcompany.getCity(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tcompany.getState())) {
            criteria.add(Restrictions.like("state", tcompany.getState(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tcompany.getCountry())) {
            criteria.add(Restrictions.like("country", tcompany.getCountry(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        return (List<TCompany>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TCompany tcompany) {
        getHibernateTemplate().saveOrUpdate(tcompany);
    }
    
}
