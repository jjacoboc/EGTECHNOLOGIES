/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.RoleDao;
import com.egtechnologies.sgtapp.domain.TRole;
import com.egtechnologies.sgtapp.domain.TUser;
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
@Repository(value="RoleDao")
public class RoleDaoHibernate extends HibernateDaoSupport implements RoleDao {
    
    /**
     * Crea una nueva instancia de RoleDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public RoleDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TRole getRoleById(Integer idRole) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TRole.class);
        criteria.add(Restrictions.eq("idRole", idRole));
        return (TRole) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TRole getRoleByUser(TUser user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TRole.class);
        criteria.add(Restrictions.eq("idUser", user.getIdUser()));
        return (TRole) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TRole getRoleByName(TRole trole) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TRole.class);
        criteria.add(Restrictions.eq("name", trole.getName()));
        return (TRole) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<TRole> getAllRoles() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TRole.class);
        criteria.addOrder(Order.asc("name"));
        return (List<TRole>) criteria.list();
    }
    
    @Override
    public List<TRole> getAllActiveRoles() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TRole.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("name"));
        return (List<TRole>) criteria.list();
    }
    
    @Override
    public List<TRole> search(TRole trole) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TRole.class);
        if(StringUtils.isNotBlank(trole.getName())) {
            criteria.add(Restrictions.like("name", trole.getName(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        return (List<TRole>) criteria.list();
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TRole trole) {
        getHibernateTemplate().saveOrUpdate(trole);
    }
}
