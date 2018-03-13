/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.DepartmentDao;
import com.egtechnologies.sgtapp.domain.TDepartment;
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
@Repository(value="DepartmentDao")
public class DepartmentDaoHibernate extends HibernateDaoSupport implements DepartmentDao {

    /**
     * Crea una nueva instancia de DepartmentDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public DepartmentDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public TDepartment getDepartmentById(Integer idDepartment) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TDepartment.class);
        criteria.add(Restrictions.eq("idDepartment", idDepartment));
        return (TDepartment) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TDepartment getDepartmentByName(TDepartment tdepartment) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TDepartment.class);
        criteria.add(Restrictions.eq("idBranchOffice", tdepartment.getIdBranchOffice()));
        criteria.add(Restrictions.eq("name", tdepartment.getName()));
        return (TDepartment) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TDepartment> getAllDepartments() {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT b.id_company, t.id_branch_office, t.id_department, t.name, t.description, ");
        sql.append("           t.active, t.created_by, t.created_date, t.modified_by, t.modified_date ");
        sql.append("      FROM t_department t ");
        sql.append("INNER JOIN t_branch_office b ON t.id_branch_office = b.id_branch_office ");
        sql.append("  ORDER BY b.id_company, t.id_branch_office, t.id_department, t.name ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TDepartment> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TDepartment td = new TDepartment();
                td.setIdCompany((Integer)objeto[0]);
                td.setIdBranchOffice((Integer)objeto[1]);
                td.setIdDepartment((Integer)objeto[2]);
                td.setName((String)objeto[3]);
                td.setDescription((String)objeto[4]);
                td.setActive((Boolean)objeto[5]);
                td.setCreatedBy((Integer)objeto[6]);
                td.setCreatedDate((Date)objeto[7]);
                td.setModifiedBy((Integer)objeto[8]);
                td.setModifiedDate((Date)objeto[9]);
                result.add(td);
            }
        }
        return result;
    }

    @Override
    public List<TDepartment> getAllActiveDepartments() {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT b.id_company, t.id_branch_office, t.id_department, t.name, t.description, ");
        sql.append("           t.active, t.created_by, t.created_date, t.modified_by, t.modified_date ");
        sql.append("      FROM t_department t ");
        sql.append("INNER JOIN t_branch_office b ON t.id_branch_office = b.id_branch_office ");
        sql.append("     WHERE t.active = 1 ");
        sql.append("  ORDER BY b.id_company, t.id_branch_office, t.id_department, t.name ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TDepartment> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TDepartment td = new TDepartment();
                td.setIdCompany((Integer)objeto[0]);
                td.setIdBranchOffice((Integer)objeto[1]);
                td.setIdDepartment((Integer)objeto[2]);
                td.setName((String)objeto[3]);
                td.setDescription((String)objeto[4]);
                td.setActive((Boolean)objeto[5]);
                td.setCreatedBy((Integer)objeto[6]);
                td.setCreatedDate((Date)objeto[7]);
                td.setModifiedBy((Integer)objeto[8]);
                td.setModifiedDate((Date)objeto[9]);
                result.add(td);
            }
        }
        return result;
    }
    
    @Override
    public List<TDepartment> getAllDepartmentsByBranchOffice(Integer idBranchOffice) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TDepartment.class);
        criteria.add(Restrictions.eq("idBranchOffice", idBranchOffice));
        criteria.addOrder(Order.asc("name"));
        return (List<TDepartment>) criteria.list();
    }

    @Override
    public List<TDepartment> getAllActiveDepartmentsByBranchOffice(Integer idBranchOffice) {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TDepartment.class);
        criteria.add(Restrictions.eq("idBranchOffice", idBranchOffice));
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("name"));
        return (List<TDepartment>) criteria.list();
    }

    @Override
    public List<TDepartment> search(TDepartment tdepartment) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT b.id_company, t.id_branch_office, t.id_department, t.name, t.description, ");
        sql.append("           t.active, t.created_by, t.created_date, t.modified_by, t.modified_date ");
        sql.append("      FROM t_department t ");
        sql.append("INNER JOIN t_branch_office b ON t.id_branch_office = b.id_branch_office ");
        sql.append("     WHERE 1=1 ");
        if(tdepartment.getIdCompany() != null && !tdepartment.getIdCompany().equals(Items.NULL_VALUE)) {
            sql.append("       AND b.id_company = ").append(tdepartment.getIdCompany());
        }
        if(tdepartment.getIdBranchOffice() != null && !tdepartment.getIdBranchOffice().equals(Items.NULL_VALUE)) {
            sql.append("       AND t.id_branch_office LIKE '%").append(tdepartment.getIdBranchOffice()).append("%' ");
        }
        if(StringUtils.isNotBlank(tdepartment.getName())) {
            sql.append("       AND t.name LIKE '%").append(tdepartment.getName()).append("%' ");
        }
        sql.append("  ORDER BY b.id_company, t.id_branch_office, t.id_department, t.name ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TDepartment> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TDepartment td = new TDepartment();
                td.setIdCompany((Integer)objeto[0]);
                td.setIdBranchOffice((Integer)objeto[1]);
                td.setIdDepartment((Integer)objeto[2]);
                td.setName((String)objeto[3]);
                td.setDescription((String)objeto[4]);
                td.setActive((Boolean)objeto[5]);
                td.setCreatedBy((Integer)objeto[6]);
                td.setCreatedDate((Date)objeto[7]);
                td.setModifiedBy((Integer)objeto[8]);
                td.setModifiedDate((Date)objeto[9]);
                result.add(td);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TDepartment tdepartment) {
        getHibernateTemplate().saveOrUpdate(tdepartment);
    }
    
}
