/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.HardwareDao;
import com.egtechnologies.sgtapp.domain.TEmployee;
import com.egtechnologies.sgtapp.domain.THardware;
import com.egtechnologies.sgtapp.web.common.Items;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Jonathan
 */
@Repository(value = "HardwareDao")
public class HardwareDaoHibernate extends HibernateDaoSupport implements HardwareDao {

    /**
     * Crea una nueva instancia de HardwareDaoHibernate
     *
     * @param sessionFactory
     */
    @Autowired
    public HardwareDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public THardware getHardwareById(Integer idHardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idHardware", idHardware));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public THardware getHardwareBySerialNumber(THardware thardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        criteria.add(Restrictions.eq("serialNumber", thardware.getSerialNumber()));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public THardware getHardwareByCompany(THardware thardware) {
        DetachedCriteria criteria = DetachedCriteria.forClass(THardware.class);
        criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        return (THardware) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<THardware> getAllHardwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    public List<THardware> getAllActiveHardwares() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    public List<THardware> search(THardware thardware) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(THardware.class);
        if (thardware.getIdCompany() != null && !thardware.getIdCompany().equals(Items.NULL_VALUE)) {
            criteria.add(Restrictions.eq("idCompany", thardware.getIdCompany()));
        }
        if (thardware.getIdHardwareType() != null && !thardware.getIdHardwareType().equals(Items.NULL_VALUE)) {
            criteria.add(Restrictions.eq("idHardwareType", thardware.getIdHardwareType()));
        }
        if (StringUtils.isNotBlank(thardware.getModel())) {
            criteria.add(Restrictions.like("model", thardware.getModel(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(thardware.getBrand())) {
            criteria.add(Restrictions.like("brand", thardware.getBrand(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("idCompany"));
        criteria.addOrder(Order.asc("idHardwareType"));
        criteria.addOrder(Order.asc("brand"));
        criteria.addOrder(Order.asc("model"));
        return (List<THardware>) criteria.list();
    }

    @Override
    public List<THardware> getHardwaresNotAssigned(TEmployee temployee) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT h.id_company, h.id_hardware, h.id_hardware_type, h.brand, h.model, h.serial_number, ");
        sql.append("           h.active, h.created_by, h.created_date, h.modified_by, h.modified_date, t.name ");
        sql.append("      FROM t_hardware h ");
        sql.append("INNER JOIN t_hardware_type t ON h.id_hardware_type = t.id_hardware_type ");
        sql.append("     WHERE h.active = 1 ");
        sql.append("       AND h.id_company = ").append(temployee.getIdCompany());
        sql.append("       AND h.id_hardware NOT IN (SELECT a.id_hardware FROM t_assignation a WHERE a.id_employee = ").append(temployee.getIdEmployee()).append(") ");
        sql.append("  ORDER BY h.id_company, h.id_hardware, h.id_hardware_type, h.brand, h.model, h.serial_number ");

        List resultSet = (List) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql.toString()).list();
            }
        });

        List<THardware> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for (Object obj : resultSet) {
                Object[] objeto = (Object[]) obj;
                THardware thardware = new THardware();
                thardware.setIdCompany((Integer) objeto[0]);
                thardware.setIdHardware((Integer) objeto[1]);
                thardware.setIdHardwareType((Integer) objeto[2]);
                thardware.setBrand((String) objeto[3]);
                thardware.setModel((String) objeto[4]);
                thardware.setSerialNumber((String) objeto[5]);
                thardware.setActive((Boolean) objeto[6]);
                thardware.setCreatedBy((Integer) objeto[7]);
                thardware.setCreatedDate((Date) objeto[8]);
                thardware.setModifiedBy((Integer) objeto[9]);
                thardware.setModifiedDate((Date) objeto[10]);
                thardware.setHardwareType((String) objeto[11]);
                result.add(thardware);
            }
        }
        return result;
    }

    @Override
    public List<THardware> getHardwaresAssigned(TEmployee temployee) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT h.id_company, h.id_hardware, h.id_hardware_type, h.brand, h.model, h.serial_number, ");
        sql.append("           h.active, h.created_by, h.created_date, h.modified_by, h.modified_date, t.name ");
        sql.append("      FROM t_hardware h ");
        sql.append("INNER JOIN t_hardware_type t ON h.id_hardware_type = t.id_hardware_type ");
        sql.append("     WHERE h.active = 1 ");
        sql.append("       AND h.id_company = ").append(temployee.getIdCompany());
        sql.append("       AND h.id_hardware IN (SELECT a.id_hardware FROM t_assignation a WHERE a.id_employee = ").append(temployee.getIdEmployee()).append(") ");
        sql.append("  ORDER BY h.id_company, h.id_hardware, h.id_hardware_type, h.brand, h.model, h.serial_number ");

        List resultSet = (List) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql.toString()).list();
            }
        });

        List<THardware> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for (Object obj : resultSet) {
                Object[] objeto = (Object[]) obj;
                THardware thardware = new THardware();
                thardware.setIdCompany((Integer) objeto[0]);
                thardware.setIdHardware((Integer) objeto[1]);
                thardware.setIdHardwareType((Integer) objeto[2]);
                thardware.setBrand((String) objeto[3]);
                thardware.setModel((String) objeto[4]);
                thardware.setSerialNumber((String) objeto[5]);
                thardware.setActive((Boolean) objeto[6]);
                thardware.setCreatedBy((Integer) objeto[7]);
                thardware.setCreatedDate((Date) objeto[8]);
                thardware.setModifiedBy((Integer) objeto[9]);
                thardware.setModifiedDate((Date) objeto[10]);
                thardware.setHardwareType((String) objeto[11]);
                result.add(thardware);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(THardware thardware) {
        getHibernateTemplate().saveOrUpdate(thardware);
    }

}
