/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.BranchOfficeDao;
import com.egtechnologies.sgtapp.domain.TBranchOffice;
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
@Repository(value="BranchOfficeDao")
public class BranchOfficeDaoHibernate extends HibernateDaoSupport implements BranchOfficeDao {

    /**
     * Crea una nueva instancia de BranchOfficeDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public BranchOfficeDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TBranchOffice getBranchOfficeById(Integer idBranchOffice) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TBranchOffice.class);
        criteria.add(Restrictions.eq("idBranchOffice", idBranchOffice));
        return (TBranchOffice) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TBranchOffice getBranchOfficeByName(TBranchOffice tbranchOffice) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TBranchOffice.class);
        criteria.add(Restrictions.eq("idCompany", tbranchOffice.getIdCompany()));
        criteria.add(Restrictions.eq("name", tbranchOffice.getName()));
        return (TBranchOffice) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TBranchOffice> getAllBranchOffices() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TBranchOffice.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TBranchOffice>) criteria.list();
    }

    @Override
    public List<TBranchOffice> getAllActiveBranchOffices() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TBranchOffice.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TBranchOffice>) criteria.list();
    }
    
    @Override
    public List<TBranchOffice> getAllBranchOfficesByCompany(Integer idCompany) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TBranchOffice.class);
        criteria.add(Restrictions.eq("idCompany", idCompany));
        criteria.addOrder(Order.asc("name"));
        return (List<TBranchOffice>) criteria.list();
    }
    
    @Override
    public List<TBranchOffice> getAllActiveBranchOfficesByCompany(Integer idCompany) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TBranchOffice.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.add(Restrictions.eq("idCompany", idCompany));
        criteria.addOrder(Order.asc("name"));
        return (List<TBranchOffice>) criteria.list();
    }

    @Override
    public List<TBranchOffice> search(TBranchOffice tbranchOffice) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TBranchOffice.class);
        if(tbranchOffice.getIdCompany() != null) {
            criteria.add(Restrictions.eq("idCompany", tbranchOffice.getIdCompany()));
        }
        if(StringUtils.isNotBlank(tbranchOffice.getName())) {
            criteria.add(Restrictions.like("name", tbranchOffice.getName(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tbranchOffice.getCity())) {
            criteria.add(Restrictions.like("city", tbranchOffice.getCity(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tbranchOffice.getState())) {
            criteria.add(Restrictions.like("state", tbranchOffice.getState(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tbranchOffice.getCountry())) {
            criteria.add(Restrictions.like("country", tbranchOffice.getCountry(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("name"));
        return (List<TBranchOffice>) criteria.list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TBranchOffice tbranchOffice) {
        getHibernateTemplate().saveOrUpdate(tbranchOffice);
    }
    
}
