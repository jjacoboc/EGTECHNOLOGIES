/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.domain.TPerson;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.egtechnologies.sgtapp.dao.PersonDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value="PersonDao")
public class PersonDaoHibernate extends HibernateDaoSupport implements PersonDao {
    
    /**
     * Crea una nueva instancia de PeopleDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public PersonDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TPerson getPersonById(Integer idPerson) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPerson.class);
        criteria.add(Restrictions.eq("idPerson", idPerson));
        return (TPerson) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TPerson getPersonByIdnumber(String idnumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPerson.class);
        criteria.add(Restrictions.eq("idnumber", idnumber));
        return (TPerson) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public TPerson getPersonByEmail(String email) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TPerson.class);
        criteria.add(Restrictions.eq("homeemail", email));
        return (TPerson) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<TPerson> getAllPersons() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPerson.class);
        criteria.addOrder(Order.asc("name"));
        criteria.addOrder(Order.asc("lastname"));
        return (List<TPerson>) criteria.list();
    }
    
    @Override
    public List<TPerson> getAllActivePersons() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPerson.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("name"));
        criteria.addOrder(Order.asc("lastname"));
        return (List<TPerson>) criteria.list();
    }
    
    @Override
    public List<TPerson> search(TPerson tperson) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TPerson.class);
        if(StringUtils.isNotBlank(tperson.getName())) {
            criteria.add(Restrictions.like("name", tperson.getName(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tperson.getLastname())) {
            criteria.add(Restrictions.like("lastname", tperson.getLastname(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(tperson.getIdnumber())) {
            criteria.add(Restrictions.like("idnumber", tperson.getIdnumber(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        criteria.addOrder(Order.asc("lastname"));
        return (List<TPerson>) criteria.list();
    }
    
    @Override
    public List<TPerson> getPersonsWithoutUser() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id_person, t.idnumber, t.name, t.lastname, t.homeemail, ");
        sql.append("       t.phone, t.cellphone, t.address, t.city, t.country, t.zipcode, ");
        sql.append("       t.created_by, t.created_date, t.modified_by, t.modified_date ");
        sql.append("  FROM t_person t ");
        sql.append(" WHERE t.id_person NOT IN(SELECT p.id_person ");
        sql.append("                            FROM t_person p ");
        sql.append("                      INNER JOIN t_user u ON p.id_person = u.id_person) ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TPerson> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TPerson tPerson = new TPerson();
                tPerson.setIdPerson((Integer)objeto[0]);
                tPerson.setIdnumber((String)objeto[1]);
                tPerson.setName((String)objeto[2]);
                tPerson.setLastname((String)objeto[3]);
                tPerson.setHomeemail((String)objeto[4]);
                tPerson.setPhone((String)objeto[5]);
                tPerson.setCellphone((String)objeto[6]);
                tPerson.setAddress((String)objeto[7]);
                tPerson.setCity((String)objeto[8]);
                tPerson.setCountry((String)objeto[9]);
                tPerson.setZipcode((String)objeto[10]);
                tPerson.setCreatedBy((Integer)objeto[11]);
                tPerson.setCreatedDate((Date)objeto[12]);
                tPerson.setModifiedBy((Integer)objeto[13]);
                tPerson.setModifiedDate((Date)objeto[14]);
                result.add(tPerson);
            }
        }
        return result;
    }
    
    @Override
    public List<TPerson> getPersonsWithUser() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id_person, t.idnumber, t.name, t.lastname, t.homeemail, ");
        sql.append("       t.phone, t.cellphone, t.address, t.city, t.country, t.zipcode, ");
        sql.append("       t.created_by, t.created_date, t.modified_by, t.modified_date ");
        sql.append("  FROM t_person t ");
        sql.append("  JOIN t_user u ON t.id_person = u.id_person)");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TPerson> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TPerson tPerson = new TPerson();
                tPerson.setIdPerson((Integer)objeto[0]);
                tPerson.setIdnumber((String)objeto[1]);
                tPerson.setName((String)objeto[2]);
                tPerson.setLastname((String)objeto[3]);
                tPerson.setHomeemail((String)objeto[4]);
                tPerson.setPhone((String)objeto[5]);
                tPerson.setCellphone((String)objeto[6]);
                tPerson.setAddress((String)objeto[7]);
                tPerson.setCity((String)objeto[8]);
                tPerson.setCountry((String)objeto[9]);
                tPerson.setZipcode((String)objeto[10]);
                tPerson.setCreatedBy((Integer)objeto[11]);
                tPerson.setCreatedDate((Date)objeto[12]);
                tPerson.setModifiedBy((Integer)objeto[13]);
                tPerson.setModifiedDate((Date)objeto[14]);
                result.add(tPerson);
            }
        }
        return result;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TPerson tperson) {
        getHibernateTemplate().saveOrUpdate(tperson);
    }
}
