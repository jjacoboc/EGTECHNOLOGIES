/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.InstallDao;
import com.egtechnologies.sgtapp.domain.THardware;
import com.egtechnologies.sgtapp.domain.TInstallation;
import com.egtechnologies.sgtapp.domain.TSoftware;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value = "InstallDao")
public class InstallDaoHibernate extends HibernateDaoSupport implements InstallDao {
    
    /**
     * Crea una nueva instancia de InstallDaoHibernate
     *
     * @param sessionFactory
     */
    @Autowired
    public InstallDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInstallsByHardware(Integer idHardware) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TInstallation.class);
        criteria.add(Restrictions.eq("idHardware", idHardware));
        getHibernateTemplate().delete(criteria.uniqueResult());
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteInstallsBySoftware(Integer idSoftware) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TInstallation.class);
        criteria.add(Restrictions.eq("idSoftware", idSoftware));
        getHibernateTemplate().delete(criteria.uniqueResult());
    }
    
    @Override
    public List<TSoftware> getSoftwaresNotInstalled(THardware thardware) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT s.id_company, s.id_software, s.name, s.description, s.brand, s.license_count, ");
        sql.append("           s.support_email, s.support_phone, s.active, s.created_by, s.created_date, ");
        sql.append("           s.modified_by, s.modified_date ");
        sql.append("      FROM t_software s ");
        sql.append("     WHERE s.active = 1 ");
        sql.append("       AND s.license_count > 0 ");
        sql.append("       AND s.id_company = ").append(thardware.getIdCompany());
        sql.append("       AND s.id_software NOT IN (SELECT a.id_software FROM t_installation a WHERE a.id_hardware = ").append(thardware.getIdHardware()).append(") ");
        sql.append("  ORDER BY s.id_company, s.id_software, s.name, s.brand ");

        List resultSet = (List) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql.toString()).list();
            }
        });

        List<TSoftware> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for (Object obj : resultSet) {
                Object[] objeto = (Object[]) obj;
                TSoftware tsoftware = new TSoftware();
                tsoftware.setIdCompany((Integer) objeto[0]);
                tsoftware.setIdSoftware((Integer) objeto[1]);
                tsoftware.setName((String) objeto[2]);
                tsoftware.setDescription((String) objeto[3]);
                tsoftware.setBrand((String) objeto[4]);
                tsoftware.setLicenseCount((Integer) objeto[5]);
                tsoftware.setSupportEmail((String) objeto[6]);
                tsoftware.setSupportPhone((String) objeto[7]);
                tsoftware.setActive((Boolean) objeto[8]);
                tsoftware.setCreatedBy((Integer) objeto[9]);
                tsoftware.setCreatedDate((Date) objeto[10]);
                tsoftware.setModifiedBy((Integer) objeto[11]);
                tsoftware.setModifiedDate((Date) objeto[12]);
                result.add(tsoftware);
            }
        }
        return result;
    }

    @Override
    public List<TSoftware> getSoftwaresInstalled(THardware thardware) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT s.id_company, s.id_software, s.name, s.description, s.brand, s.license_count, ");
        sql.append("           s.support_email, s.support_phone, s.active, s.created_by, s.created_date, ");
        sql.append("           s.modified_by, s.modified_date ");
        sql.append("      FROM t_software s ");
        sql.append("     WHERE s.active = 1 ");
        sql.append("       AND s.license_count > 0 ");
        sql.append("       AND s.id_company = ").append(thardware.getIdCompany());
        sql.append("       AND s.id_software IN (SELECT a.id_software FROM t_installation a WHERE a.id_hardware = ").append(thardware.getIdHardware()).append(") ");
        sql.append("  ORDER BY s.id_company, s.id_software, s.name, s.brand ");

        List resultSet = (List) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql.toString()).list();
            }
        });

        List<TSoftware> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for (Object obj : resultSet) {
                Object[] objeto = (Object[]) obj;
                TSoftware tsoftware = new TSoftware();
                tsoftware.setIdCompany((Integer) objeto[0]);
                tsoftware.setIdSoftware((Integer) objeto[1]);
                tsoftware.setName((String) objeto[2]);
                tsoftware.setDescription((String) objeto[3]);
                tsoftware.setBrand((String) objeto[4]);
                tsoftware.setLicenseCount((Integer) objeto[5]);
                tsoftware.setSupportEmail((String) objeto[6]);
                tsoftware.setSupportPhone((String) objeto[7]);
                tsoftware.setActive((Boolean) objeto[8]);
                tsoftware.setCreatedBy((Integer) objeto[9]);
                tsoftware.setCreatedDate((Date) objeto[10]);
                tsoftware.setModifiedBy((Integer) objeto[11]);
                tsoftware.setModifiedDate((Date) objeto[12]);
                result.add(tsoftware);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TInstallation tinstallation) {
        getHibernateTemplate().saveOrUpdate(tinstallation);
    }
    
}
