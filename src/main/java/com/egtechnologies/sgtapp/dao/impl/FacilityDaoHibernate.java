/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.FacilityDao;
import com.egtechnologies.sgtapp.domain.TFacilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value="FacilityDao")
public class FacilityDaoHibernate extends HibernateDaoSupport implements FacilityDao {
    
    /**
     * Crea una nueva instancia de FacilityDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public FacilityDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<TFacilities> getAllFacilities() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TFacilities.class);
        criteria.addOrder(Order.asc("name"));
        return (List<TFacilities>) criteria.list();
    }

    @Override
    public List<TFacilities> getFacilitiesByRole(Integer idRole) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT f.id_facilities, f.name, f.description, r.active, ");
        sql.append("       r.created_by, r.created_date, r.modified_by, r.modified_date ");
        sql.append("  FROM t_facilities f ");
        sql.append("  JOIN t_rights r ON f.id_facilities = r.id_facilities ");
        sql.append(" WHERE f.active = 1 ");
        sql.append("   AND r.id_role = ").append(idRole);
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TFacilities> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TFacilities tFacilities = new TFacilities();
                tFacilities.setIdFacilities((Integer)objeto[0]);
                tFacilities.setName((String)objeto[1]);
                tFacilities.setDescription((String)objeto[2]);
                tFacilities.setActive((Boolean)objeto[3]);
                tFacilities.setCreatedBy((Integer)objeto[4]);
                tFacilities.setCreatedDate((Date)objeto[5]);
                tFacilities.setModifiedBy((Integer)objeto[6]);
                tFacilities.setModifiedDate((Date)objeto[7]);
                result.add(tFacilities);
            }
        }
        return result;
    }
    
    @Override
    public List<TFacilities> getFacilitiesAssignedByRole(Integer idRole) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT f.id_facilities, f.name, f.description, f.active, ");
        sql.append("       f.created_by, f.created_date, f.modified_by, f.modified_date ");
        sql.append("  FROM t_facilities f ");
        sql.append(" WHERE f.id_facilities IN(SELECT id_facilities ");
        sql.append("                            FROM t_rights r ");
        sql.append("                           WHERE r.id_role = ").append(idRole);
        sql.append("                             AND r.active = 1) ");
        sql.append("   AND f.active = 1 ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TFacilities> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TFacilities tFacilities = new TFacilities();
                tFacilities.setIdFacilities((Integer)objeto[0]);
                tFacilities.setName((String)objeto[1]);
                tFacilities.setDescription((String)objeto[2]);
                tFacilities.setActive((Boolean)objeto[3]);
                tFacilities.setCreatedBy((Integer)objeto[4]);
                tFacilities.setCreatedDate((Date)objeto[5]);
                tFacilities.setModifiedBy((Integer)objeto[6]);
                tFacilities.setModifiedDate((Date)objeto[7]);
                result.add(tFacilities);
            }
        }
        return result;
    }
    
    @Override
    public List<TFacilities> getFacilitiesNotAssignedByRole(Integer idRole) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT f.id_facilities, f.name, f.description, f.active, ");
        sql.append("       f.created_by, f.created_date, f.modified_by, f.modified_date ");
        sql.append("  FROM t_facilities f ");
        sql.append(" WHERE f.id_facilities NOT IN(SELECT id_facilities ");
        sql.append("                                FROM t_rights r ");
        sql.append("                               WHERE r.id_role = ").append(idRole);
        sql.append("                                 AND r.active = 1) ");
        sql.append("   AND f.active = 1 ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TFacilities> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TFacilities tFacilities = new TFacilities();
                tFacilities.setIdFacilities((Integer)objeto[0]);
                tFacilities.setName((String)objeto[1]);
                tFacilities.setDescription((String)objeto[2]);
                tFacilities.setActive((Boolean)objeto[3]);
                tFacilities.setCreatedBy((Integer)objeto[4]);
                tFacilities.setCreatedDate((Date)objeto[5]);
                tFacilities.setModifiedBy((Integer)objeto[6]);
                tFacilities.setModifiedDate((Date)objeto[7]);
                result.add(tFacilities);
            }
        }
        return result;
    }
}
