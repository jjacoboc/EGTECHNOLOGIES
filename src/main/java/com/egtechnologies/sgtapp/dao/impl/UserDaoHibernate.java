/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.UserDao;
import com.egtechnologies.sgtapp.domain.TUser;
import com.egtechnologies.sgtapp.web.bean.PersonUser;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jonathan
 */
@Repository(value="UserDao")
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {
    
    /**
     * Crea una nueva instancia de UserDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TUser getUserById(Integer idUser) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TUser.class);
        criteria.add(Restrictions.eq("idUser", idUser));
        return (TUser) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TUser getActiveUserbyUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TUser.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        return (TUser) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<TUser> getAllUsers() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TUser.class);
        criteria.addOrder(Order.asc("username"));
        return (List<TUser>) criteria.list();
    }
    
    @Override
    public List<TUser> getAllActiveUsers() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TUser.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("username"));
        return (List<TUser>) criteria.list();
    }
    
    @Override
    public List getUsers() {
        final StringBuffer sql = new StringBuffer("");
        sql.append("  SELECT p.id_person, p.idnumber, p.name, p.lastname, p.homeemail, ");
        sql.append("         p.phone, p.cellphone, p.address, p.city, p.country, p.zipcode, ");
        sql.append("         p.created_by, p.created_date, p.modified_by, p.modified_date, ");
        sql.append("         u.id_user, u.id_role, u.username, u.active, u.workemail ");
        sql.append("    FROM t_person p ");
        sql.append("    JOIN t_user u ON p.id_person = u.id_person ");
        sql.append("ORDER BY p.name, p.lastname, u.created_date ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        return resultSet;
    }
    
    @Override
    public List search(PersonUser personUser) {
        final StringBuffer sql = new StringBuffer();
        sql.append("  SELECT p.id_person, p.idnumber, p.name, p.lastname, p.homeemail, ");
        sql.append("         p.phone, p.cellphone, p.address, p.city, p.country, p.zipcode, ");
        sql.append("         p.created_by, p.created_date, p.modified_by, p.modified_date, ");
        sql.append("         u.id_user, u.id_role, u.username, u.active, u.workemail ");
        sql.append("    FROM t_person p ");
        sql.append("    JOIN t_user u ON p.id_person = u.id_person ");
        sql.append("   WHERE 1 = 1 ");
        if(StringUtils.isNotBlank(personUser.getIdnumber())) {
            sql.append("     AND p.idnumber LIKE '%").append(personUser.getIdnumber()).append("%' ");
        }
        if(StringUtils.isNotBlank(personUser.getName())) {
            sql.append("     AND p.name LIKE '%").append(personUser.getName()).append("%' ");
        }
        if(StringUtils.isNotBlank(personUser.getLastname())) {
            sql.append("     AND p.lastname LIKE '%").append(personUser.getLastname()).append("%' ");
        }
        if(StringUtils.isNotBlank(personUser.getUsername())) {
            sql.append("     AND u.username LIKE '%").append(personUser.getUsername()).append("%' ");
        }
        sql.append("ORDER BY p.name, p.lastname, u.created_date ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        return resultSet;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TUser user) {
        getHibernateTemplate().saveOrUpdate(user);
    }
}
