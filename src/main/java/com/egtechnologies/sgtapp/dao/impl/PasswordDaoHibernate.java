/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.PasswordDao;
import com.egtechnologies.sgtapp.domain.TPassword;
import com.egtechnologies.sgtapp.domain.TUser;
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
 * @author JJ
 */
@Repository(value="PasswordDao")
public class PasswordDaoHibernate extends HibernateDaoSupport implements PasswordDao{
    
    /**
     * Crea una nueva instancia de PasswordDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public PasswordDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public TPassword getActivePasswordbyUser(TUser usuario) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPassword.class);
        criteria.add(Restrictions.eq("idUser", usuario.getIdUser()));
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        return (TPassword) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TPassword password) {
        getHibernateTemplate().saveOrUpdate(password);
    }
    
}
