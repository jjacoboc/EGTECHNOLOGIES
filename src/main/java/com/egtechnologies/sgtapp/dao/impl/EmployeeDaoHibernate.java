/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.dao.impl;

import com.egtechnologies.sgtapp.dao.EmployeeDao;
import com.egtechnologies.sgtapp.domain.TEmployee;
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
@Repository(value="EmployeeDao")
public class EmployeeDaoHibernate extends HibernateDaoSupport implements EmployeeDao {

    /**
     * Crea una nueva instancia de DepartmentDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public EmployeeDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public TEmployee getEmployeeById(Integer idEmployee) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TEmployee.class);
        criteria.add(Restrictions.eq("idEmployee", idEmployee));
        return (TEmployee) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TEmployee getEmployeeByCode(TEmployee temployee) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TEmployee.class);
        criteria.add(Restrictions.eq("idDepartment", temployee.getIdDepartment()));
        criteria.add(Restrictions.eq("code", temployee.getCode()));
        return (TEmployee) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TEmployee> getAllEmployees() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TEmployee.class);
        criteria.addOrder(Order.asc("idDepartment"));
        criteria.addOrder(Order.asc("name"));
        return (List<TEmployee>) criteria.list();
    }

    @Override
    public List<TEmployee> getAllActiveEmployees() {
        Criteria criteria = this.getSessionFactory().openSession().createCriteria(TEmployee.class);
        criteria.add(Restrictions.eq("active", Boolean.TRUE));
        criteria.addOrder(Order.asc("idDepartment"));
        criteria.addOrder(Order.asc("name"));
        return (List<TEmployee>) criteria.list();
    }

    @Override
    public List<TEmployee> search(TEmployee temployee) {
        final StringBuilder sql = new StringBuilder();
        sql.append("    SELECT b.id_company, d.id_branch_office, e.id_department, e.id_employee, ");
        sql.append("           e.id_position, e.codigo, e.name, e.lastname, e.homeemail, e.phone, ");
        sql.append("           e.cellphone, e.address, e.city, e.state, e.country, e.zipcode, e.active, ");
        sql.append("           e.created_by, e.created_date, e.modified_by, e.modified_date ");
        sql.append("      FROM t_employee e ");
        sql.append("INNER JOIN t_department d ON e.id_department = d.id_department ");
        sql.append("INNER JOIN t_branch_office b ON d.id_branch_office = b.id_branch_office ");
        sql.append("     WHERE 1=1 ");
        if(temployee.getIdCompany() != null) {
            sql.append("       AND b.id_company = ").append(temployee.getIdCompany());
        }
        if(temployee.getIdBranchOffice() != null) {
            sql.append("       AND b.id_branch_office = ").append(temployee.getIdBranchOffice());
        }
        if(temployee.getIdDepartment() != null) {
            sql.append("       AND b.id_department = ").append(temployee.getIdDepartment());
        }
        if(StringUtils.isNotBlank(temployee.getName())) {
            sql.append("       AND e.name LIKE '%").append(temployee.getName()).append("%' ");
        }
        if(StringUtils.isNotBlank(temployee.getLastname())) {
            sql.append("       AND e.lastname LIKE '%").append(temployee.getLastname()).append("%' ");
        }
        if(StringUtils.isNotBlank(temployee.getCode())) {
            sql.append("       AND e.codigo = '").append(temployee.getCode()).append("' ");
        }
        sql.append("  ORDER BY b.id_company, d.id_branch_office, e.id_department, e.id_employee, e.name, e.lastname ");
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<TEmployee> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resultSet)) {
            for(Object obj : resultSet){
                Object[] objeto = (Object[]) obj;
                TEmployee te = new TEmployee();
                te.setIdCompany((Integer)objeto[0]);
                te.setIdBranchOffice((Integer)objeto[1]);
                te.setIdDepartment((Integer)objeto[2]);
                te.setIdEmployee((Integer)objeto[3]);
                te.setIdPosition((Integer)objeto[4]);
                te.setCode((String)objeto[5]);
                te.setName((String)objeto[6]);
                te.setLastname((String)objeto[7]);
                te.setHomeemail((String)objeto[8]);
                te.setPhone((String)objeto[9]);
                te.setCellphone((String)objeto[10]);
                te.setAddress((String)objeto[11]);
                te.setCity((String)objeto[12]);
                te.setState((String)objeto[13]);
                te.setCountry((String)objeto[14]);
                te.setZipcode((String)objeto[15]);
                te.setActive((Boolean)objeto[16]);
                te.setCreatedBy((Integer)objeto[17]);
                te.setCreatedDate((Date)objeto[18]);
                te.setModifiedBy((Integer)objeto[19]);
                te.setModifiedDate((Date)objeto[20]);
                result.add(te);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TEmployee temployee) {
        getHibernateTemplate().saveOrUpdate(temployee);
    }
    
}
